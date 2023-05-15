package com.zolitron.demo_todo.controller;

import com.zolitron.demo_todo.model.Todo;
import com.zolitron.demo_todo.services.TodoService;
import com.zolitron.demo_todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;

    @GetMapping("user/{id}")
    public List<Todo> getAllUserTodos(@PathVariable Long id){
        return todoService.getAllTodosByUser(id);
    }

    @PatchMapping("/user/{userId}/todo/{todoId}")
    public Todo updateTask(@PathVariable Long userId,
                           @PathVariable Long todoId,
                           @RequestBody String newTask){
        return todoService.updateTask(userId, newTask, todoId);
    }
    @PostMapping("/user/{Id}")
    public Todo createTodoForUser(@PathVariable Long Id,
                                  @RequestBody String task){
        return todoService.createTodo(Id, task);
    }
    @DeleteMapping("/user/{userId}/todo/{todoId}")
    public List<Todo> deleteTodo(@PathVariable Long userId,
                                 @PathVariable Long todoId){
        return todoService.removeTodo(userId, todoId);
    }
}

