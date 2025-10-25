package com.mate.im.web.filter;

import cn.hutool.core.lang.UUID;
import com.mate.im.web.util.TokenUtil;
import io.lettuce.core.RedisException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.BooleanUtils;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * token 过滤器
 *
 * @author yuanxmo
 */
public class TokenFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    public static final ThreadLocal<String> TOKEN_THREAD_LOCAL = new ThreadLocal<>();

    public static final ThreadLocal<Boolean> STRESS_THREAD_LOCAL = new ThreadLocal<>();

    private static final String HEADER_VALUE_NULL = "null";

    private static final String HEADER_VALUE_UNDEFINED = "undefined";

    private RedissonClient redissonClient;

    public TokenFilter(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

            // 从请求头获取 Token
            String token = httpRequest.getHeader("Authorization");
            Boolean isStress = BooleanUtils.toBoolean(httpRequest.getHeader("isStress"));

            if (token == null || HEADER_VALUE_NULL.equals(token) || HEADER_VALUE_UNDEFINED.equals(token)) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("No Token Found ...");
                logger.error("No token found in header, please check!");
                return;
            }

            // 检验 Token 的有效性
            boolean isValid = checkTokenValidity(token, isStress);
            if (!isValid) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Invalid or expired token");
                logger.error("Invalid token failed, please check!");
                return;
            }

            // Token 有效，继续执行其他过滤器链
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            TOKEN_THREAD_LOCAL.remove();
            STRESS_THREAD_LOCAL.remove();
        }
    }

    /**
     * <p>
     *     1. 把加密后的 token 解密
     *     2. 把加密后的 token 的 value 转成 key
     *     3. Redis 按照 key 查询并且判断 value 是否一致
     *     4. 不一致抛异常；一致则删除这个 key
     * </p>
     *
     * @param token
     * @param isStress
     * @return
     */
    private boolean checkTokenValidity(String token, Boolean isStress) {
        String result;
        if (isStress) {
            result = UUID.randomUUID().toString();
            STRESS_THREAD_LOCAL.set(true);
        } else {
            String tokenKey = TokenUtil.getTokenKeyByValue(token);
            String luaScript = """
                    local value = redis.call('GET', KEYS[1])
                    if value ~= ARGV[1] then
                        return redis.error_reply('Token not valid')
                    end
                    redis.call('DEL', KEYS[1])
                    return value""";
            try {
                result = (String) redissonClient.getScript().eval(RScript.Mode.READ_WRITE,
                        luaScript,
                        RScript.ReturnType.STATUS,
                        Arrays.asList(tokenKey),
                        token
                );
            } catch (RedisException e) {
                logger.error("Redis Exception, check token failed", e);
                return false;
            }
        }
        TOKEN_THREAD_LOCAL.set(result);
        return result != null;
    }

    @Override
    public void destroy() {
    }
}
