package dev.abarcan.cachingservice.service.client;

import dev.abarcan.cachingservice.model.dto.Todo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoClient {

    private final RestClient restClient;
    private final String host;

    public TodoClient(RestClient restClient,
                      @Value("${service.todos}") String host) {
        this.restClient = restClient;
        this.host = host;
    }

    @CircuitBreaker(name = "retrieveTodos")
    public List<Todo> retrieveTodos() {

        var url = UriComponentsBuilder.fromHttpUrl(host)
                .path("/todos")
                .build()
                .toUri();

        log.info("Retrieving todos from {}.", url);

        return restClient.get()
                .uri(url)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @CircuitBreaker(name = "getTodo")
    public Optional<Todo> getTodo(Integer id) {
        var url = UriComponentsBuilder.fromHttpUrl(host)
                .path("/todos/{id}")
                .build(id);
        log.info("Retrieving todo from {}.", url);

        return Optional.ofNullable(restClient.get()
                .uri(url)
                .retrieve()
                .body(Todo.class));
    }

    @CircuitBreaker(name = "deleteTodo")
    public void deleteTodo(Integer id) {
        var url = UriComponentsBuilder.fromHttpUrl(host)
                .path("/todos/{id}")
                .build(id);
        log.info("Removing todo from {}.", url);

        restClient.delete()
                .uri(url)
                .retrieve()
                .body(Void.class);
    }
}
