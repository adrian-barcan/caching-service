package dev.abarcan.cachingservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({RedisAutoConfiguration.class})
@ConditionalOnProperty(value="spring.cache.type", havingValue = "redis")
public class RedisConfig {

    public RedisConfig() {
        log.info("Redis cache active...");
    }

}