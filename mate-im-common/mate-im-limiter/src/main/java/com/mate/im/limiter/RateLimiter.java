package com.mate.im.limiter;

/**
 * 限流服务
 *
 * @author yuanxmo
 */
public interface RateLimiter {

    /**
     * 判断一个 key 是否可以通过
     *
     * @param key 限流的 key
     * @param limit 限流的数量
     * @param windowsSize 窗口大小，单位为秒
     * @return
     */
    Boolean tryAcquire(String key, int limit, int windowsSize);
}
