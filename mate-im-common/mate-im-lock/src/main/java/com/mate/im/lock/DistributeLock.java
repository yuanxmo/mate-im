package com.mate.im.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式锁注解
 *
 * @author yuanxmo
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributeLock {

    /**
     * 锁的场景
     *
     * @return
     */
    String scene();

    /**
     * 加锁的 key，优先使用 key(), 为空则取 keyExpression()
     * @return
     */
    String key() default DistributeLockConstant.NONE_KEY;

    /**
     * SPEL 表达式
     * <pre>
     *     #id
     *     #insertResult.id
     * </pre>
     *
     * @return
     */
    String keyExpression() default DistributeLockConstant.NONE_KEY;

    /**
     * 超时时间，毫秒
     * 默认情况下不设置超时时间，会自动续期
     *
     * @return
     */
    int expireTime() default DistributeLockConstant.DEFAULT_EXPIRE_TIME;

    /**
     * 加锁等待时长，毫秒
     * 默认情况下不设置等待时长，会一直等待直到获取到锁
     * @return
     */
    int waitTime() default DistributeLockConstant.DEFAULT_WAIT_TIME;
}
