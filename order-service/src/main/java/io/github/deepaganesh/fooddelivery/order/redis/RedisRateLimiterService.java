package io.github.deepaganesh.fooddelivery.order.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class RedisRateLimiterService {

    private static final String RATE_LIMIT_PREFIX =    "rate-limit";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${rate.limit.requests:5}")
    private int maxRequests;

    @Value("${rate.limit.windowSeconds:60}")
    private int windowInSeconds;

    public boolean isAllowed(String clientId) {
        String key = getKeyByClientId(clientId);

        // Increments the value of a key in Redis.
        // If the key doesn’t exist, it is created and initialized to 1.
        Long currentCount = redisTemplate.opsForValue().increment(key);

        if (currentCount == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(windowInSeconds));
        }

        return currentCount <= maxRequests;
    }

    private String getKeyByClientId(String clientId) {
        long window = Instant.now().getEpochSecond() / windowInSeconds;

        return RATE_LIMIT_PREFIX + ":" + clientId + ":" + window;
    }
}
