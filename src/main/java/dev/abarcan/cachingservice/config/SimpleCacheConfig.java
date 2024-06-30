package dev.abarcan.cachingservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
@ConditionalOnProperty(value="spring.cache.type", havingValue = "simple")
public class SimpleCacheConfig {

    public SimpleCacheConfig() {
        log.info("In Memory cache active...");
    }
}