package com.deepak.gitinsight.Service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

    private static final int CAPACITY = 3;
    private static final int REFILL_TIME = 60000;
    private static final int REFILL_RATE = 10;
    private final StringRedisTemplate redisTemplate;

    public RateLimiterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isRequestAllowed(String username) {

        String key = "rate_limit:token:" + username;
        long currentTime = System.currentTimeMillis();

        Object tokenObj = redisTemplate.opsForHash().get(key, "token");
        Object lastRefillObj = redisTemplate.opsForHash().get(key, "last_refill_time");

        int tokens;
        long lastRefillTime;

        if (tokenObj == null || lastRefillObj == null) {
            tokens = CAPACITY;
            lastRefillTime = currentTime;
        } else {
            tokens = Integer.parseInt(tokenObj.toString());
            lastRefillTime = Long.parseLong(lastRefillObj.toString());
        }

        int tokensToAdd = (int) ((currentTime - lastRefillTime) * REFILL_RATE / REFILL_TIME);

        if (tokensToAdd > 0) {
            tokens = Math.min(tokens + tokensToAdd, CAPACITY);
            lastRefillTime = currentTime;
        }

        if (tokens > 0) {
            tokens--;

            redisTemplate.opsForHash().put(key, "token", String.valueOf(tokens));
            redisTemplate.opsForHash().put(key, "last_refill_time", String.valueOf(lastRefillTime));

            return true;
        }

        redisTemplate.opsForHash().put(key, "token", String.valueOf(tokens));
        redisTemplate.opsForHash().put(key, "last_refill_time", String.valueOf(lastRefillTime));

        return false;
    }
}

