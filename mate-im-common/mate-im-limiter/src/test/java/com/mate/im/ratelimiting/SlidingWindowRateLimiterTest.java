package com.mate.im.ratelimiting;

import com.mate.im.limiter.SlidingWindowRateLimiter;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RateLimiterTestConfiguration.class})
@ActiveProfiles("test")
public class SlidingWindowRateLimiterTest {

    @Test
    @Ignore
    public void tryAcquireTest() {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://host:port").setPassword("password");
        RedissonClient redissonClient = Redisson.create(config);
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(redissonClient);


        Boolean result = slidingWindowRateLimiter.tryAcquire("testLock001", 3, 10);
        Assert.assertTrue(result);
        result = slidingWindowRateLimiter.tryAcquire("testLock001", 3, 10);
        Assert.assertTrue(result);
        result = slidingWindowRateLimiter.tryAcquire("testLock001", 3, 10);
        Assert.assertTrue(result);
        result = slidingWindowRateLimiter.tryAcquire("testLock001", 3, 10);
        Assert.assertFalse(result);

        try {
            Thread.currentThread().sleep(10000);
        } catch (Exception e) {

        }
        result = slidingWindowRateLimiter.tryAcquire("testLock001", 3, 10);
        Assert.assertTrue(result);
    }

}
