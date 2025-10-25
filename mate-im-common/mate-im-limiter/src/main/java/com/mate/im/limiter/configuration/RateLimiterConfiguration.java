package com.mate.im.limiter.configuration;

import com.mate.im.limiter.SlidingWindowRateLimiter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 限流配置类
 *
 * @author yuanxmo
 */
@Configuration
public class RateLimiterConfiguration {
    @Bean
    public SlidingWindowRateLimiter slidingWindowRateLimiter(RedissonClient redisson) {
        return new SlidingWindowRateLimiter(redisson);
    }
}
