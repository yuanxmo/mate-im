package com.mate.im.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Order(Integer.MIN_VALUE + 1)
public class DistributeLockAspect {

    private RedissonClient redissonClient;

    private static final Logger LOG = LoggerFactory.getLogger(DistributeLockAspect.class);

    public DistributeLockAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(com.mate.im.lock.Distribute)")
    public Object process(ProceedingJoinPoint joinPoint) throws Exception {
        Object response = null;
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        // 获取 key
        String key = distributeLock.key();
        // key 为空则解析 keyExpression
        if (DistributeLockConstant.NONE_KEY.equals(key)) {
            // keyExpression 为空则抛出异常
            if (DistributeLockConstant.NONE_KEY.equals(distributeLock.keyExpression())) {
                throw new DistributeLockException("Don't found lock key");
            }
            // 解析 keyExpress 的 SPEL
            SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
            Expression expression = spelExpressionParser.parseExpression(distributeLock.keyExpression());

            EvaluationContext context = new StandardEvaluationContext();
            // 获取参数
            Object[] args = joinPoint.getArgs();
            // 获取运行时参数
            StandardReflectionParameterNameDiscoverer discoverer
                    = new StandardReflectionParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);

            // 参数绑定至 context
            if (parameterNames != null) {
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i], args[i]);
                }
            }
            // 解析表达式
            key = String.valueOf(expression.getValue(context));
        }
        // 获取使用场景
        String scene = distributeLock.scene();
        // 拼接分布式锁 key
        String lockKey = scene + "#" + key;

        // 加锁
        int expireTime = distributeLock.expireTime();
        int waitTime = distributeLock.waitTime();
        RLock rLock = redissonClient.getLock(lockKey);
        try {
            boolean lockResult = false;
            if (waitTime == DistributeLockConstant.DEFAULT_WAIT_TIME) {
                if (expireTime == DistributeLockConstant.DEFAULT_EXPIRE_TIME) {
                    LOG.info("Lock for key : {}", lockKey);
                    rLock.lock();
                } else {
                    LOG.info("Lock for key : {} , expire : {}", lockKey, expireTime);
                    rLock.lock(expireTime, TimeUnit.MILLISECONDS);
                }
                lockResult = true;
            } else {
                if (expireTime == DistributeLockConstant.DEFAULT_EXPIRE_TIME) {
                    LOG.info("Try lock for key : {} , wait : {}", lockKey, waitTime);
                    lockResult = rLock.tryLock(waitTime, TimeUnit.MILLISECONDS);
                } else {
                    LOG.info("Try lock for key : {} , expire : {} , wait : {}", lockKey, expireTime, waitTime);
                    lockResult = rLock.tryLock(waitTime, expireTime, TimeUnit.MILLISECONDS);
                }
            }

            if (!lockResult) {
                LOG.warn("Lock failed for key : {} , expire : {}", lockKey, expireTime);
                throw new DistributeLockException("Acquire lock failed... key : " + lockKey);
            }


            LOG.info("Lock success for key : {} , expire : {}", lockKey, expireTime);
            response = joinPoint.proceed();
        } catch (Throwable e) {
            throw new Exception(e);
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                rLock.unlock();
                LOG.info("Unlock for key : {} , expire : {}", lockKey, expireTime);
            }
        }
        return response;
    }
}
