package com.zolitron.demo_todo.controller;

import com.zolitron.demo_todo.model.Todo;
import com.zolitron.demo_todo.model.User;
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
    public Todo getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }
    @PatchMapping("/{id}")
    public Todo updateTask(@PathVariable Long id,
                           @RequestBody String newTask){
        return todoService.updateTask(id, newTask);
    }
    @PostMapping("/user/{Id}")
    public Todo createTodoForUser(@PathVariable Long Id,
                                  @RequestBody String task){
        System.out.println(Id);
        return todoService.createTodo(Id, task);
    }
    @DeleteMapping("/{id}")
    public List<Todo> deleteTodo(@PathVariable Long id){
        return todoService.removeTask(id);
    }
}

