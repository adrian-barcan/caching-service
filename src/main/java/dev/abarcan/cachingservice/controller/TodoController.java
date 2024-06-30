package dev.abarcan.cachingservice.controller;

import dev.abarcan.cachingservice.model.dto.Todo;
import dev.abarcan.cachingservice.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTodos(@PathVariable("id") Integer id) {
        return todoService.getTodo(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable("id") Integer id) {
        todoService.deleteTodo(id);
    }
}
