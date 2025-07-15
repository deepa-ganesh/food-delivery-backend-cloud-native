package io.github.deepaganesh.fooddelivery.order.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    private final RedisProperties properties;

    public RedisConfig(RedisProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnProperty(name = "redis.mode", havingValue = "standalone")
    public RedisConnectionFactory standaloneRedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(properties.getHost(), properties.getPort());
        return new LettuceConnectionFactory(config);
    }

    @Bean
    @ConditionalOnProperty(name = "redis.mode", havingValue = "standalone-pool")
    public RedisConnectionFactory standalonePoolRedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(properties.getHost(), properties.getPort());

        // Setting pool to handle multiple concurrent requests
        GenericObjectPoolConfig<Object> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(properties.getLettuce().getPool().getMaxActive());
        poolConfig.setMaxIdle(properties.getLettuce().getPool().getMaxIdle());
        poolConfig.setMinIdle(properties.getLettuce().getPool().getMinIdle());
        poolConfig.setMaxWait(Duration.ofMillis(properties.getLettuce().getPool().getMaxWait()));

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                                                                                    .commandTimeout(Duration.ofMillis(properties.getTimeout()))
                                                                                    .poolConfig(poolConfig)
                                                                                    .build();

        return new LettuceConnectionFactory(config, clientConfig);
    }

    @Bean
    @ConditionalOnProperty(name = "redis.mode", havingValue = "sentinel", matchIfMissing = true)
    public RedisConnectionFactory sentinelRedisConnectionFactory() {
        RedisSentinelConfiguration config = new RedisSentinelConfiguration();
        config.setMaster(properties.getSentinel().getMaster());
        config.setPassword(RedisPassword.of(properties.getSentinel().getPassword())); // Optional: set password if needed

        properties.getSentinel().getNodes().stream()
              .map(node -> node.split(":"))
              .forEach(parts -> config.addSentinel(new RedisNode(parts[0], Integer.parseInt(parts[1]))));

        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
