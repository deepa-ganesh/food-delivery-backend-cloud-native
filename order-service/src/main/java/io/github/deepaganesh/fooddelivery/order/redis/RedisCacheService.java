package io.github.deepaganesh.fooddelivery.order.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
public class RedisCacheService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.ttl.order:60")
    private long ttlSeconds;

    public void addToCache(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlSeconds));
    }

    public Object getFromCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void evictFromCache(String key) {
        if (redisTemplate.delete(key)) {
            log.info("Entry deleted from cache for the key: {}", key);
        }
    }
}
