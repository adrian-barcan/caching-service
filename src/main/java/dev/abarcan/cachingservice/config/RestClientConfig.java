package dev.abarcan.cachingservice.config;

import dev.abarcan.cachingservice.config.logging.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(LoggingInterceptor interceptor) {
        return RestClient.builder()
                .requestInterceptor(interceptor)
                .build();
    }

}
