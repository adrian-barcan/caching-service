package dev.abarcan.cachingservice.service;

import dev.abarcan.cachingservice.model.dto.Todo;
import dev.abarcan.cachingservice.service.client.TodoClient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoClient client;

    public TodoService(TodoClient client) {
        this.client = client;
    }

    @Cacheable(value = "todosCache", key = "#root.method.name")
    public List<Todo> getTodos() {
        return client.retrieveTodos();
    }

    @Cacheable(value = "todosCache", key = "#id")
    public Optional<Todo> getTodo(Integer id) {
        return client.getTodo(id);
    }

    @CacheEvict(value = "todosCache", key = "#id")
    public void deleteTodo(Integer id) {
        client.deleteTodo(id);
    }

}
