package com.zolitron.demo_todo.controller;

import com.zolitron.demo_todo.model.Todo;
import com.zolitron.demo_todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }
    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoService.getTodoById(id);
    }
    @PatchMapping("/{id}")
    public Todo updateTask(@PathVariable int id,
                           @RequestBody String newTask){
        return todoService.updateTask(id, newTask);
    }
    @PostMapping
    public Todo createATodo(@RequestBody String task){
        return todoService.createTodo(task);
    }
    @DeleteMapping("/{id}")
    public List<Todo> deleteTodo(@PathVariable int id){
        return todoService.removeTask(id);
    }
}

