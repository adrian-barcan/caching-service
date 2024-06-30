package dev.abarcan.cachingservice;

import dev.abarcan.cachingservice.config.properties.RedisCacheProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableConfigurationProperties({RedisCacheProperties.class})
@EnableCaching
public class CachingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingServiceApplication.class, args);
	}

}
