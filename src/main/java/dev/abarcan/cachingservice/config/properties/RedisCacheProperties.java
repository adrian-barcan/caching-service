package dev.abarcan.cachingservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cache.ttl")
public record RedisCacheProperties(int defaultTtl) {

}
