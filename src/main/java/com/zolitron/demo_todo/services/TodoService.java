package com.zolitron.demo_todo.services;

import com.zolitron.demo_todo.model.Todo;
import com.zolitron.demo_todo.model.User;
import com.zolitron.demo_todo.repository.TodoRepository;
import com.zolitron.demo_todo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;


    public Todo createTodo(Long userId, String taskName) throws EntityNotFoundException {
        Todo newTodo = new Todo();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            newTodo.setTask(taskName);
            newTodo.setUser(user);
            todoRepository.save(newTodo);
            user.addTodo(newTodo);
            userRepository.save(user);
            return newTodo;
        }
        throw new EntityNotFoundException("User with id " + userId + " not found!");

    }

    public List<Todo> getAllTodosByUser(Long id) throws EntityNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getTodos();
        }
        throw new EntityNotFoundException("User with id " + id + " not found!");
    }

    public Todo updateTask(Long userId, String newTask, Long todoId) throws EntityNotFoundException {
        List<Todo> todoList = getAllTodosByUser(userId);
        if (!todoList.isEmpty()) {
            Optional<Todo> optionalTodo = todoList.stream().filter(todo -> todo.getId().equals(todoId)).findFirst();
            if (optionalTodo.isPresent()) {
                Todo toUpdateTodo = optionalTodo.get();
                toUpdateTodo.setTask(newTask);
                toUpdateTodo = todoRepository.save(toUpdateTodo);
                return toUpdateTodo;
            } else {
                throw new EntityNotFoundException("Todo with id " + todoId + " not found!");
            }
        } else {
            throw new RuntimeException("The list of todos is empty for user with ID " + userId);
        }
    }

    public List<Todo> removeTodo(Long userId, Long todoId) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found!"));

        List<Todo> todoList = user.getTodos();
        if (!todoList.isEmpty()) {
            Todo todo = user.getTodos().stream().filter(t -> t.getId().equals(todoId))
                    .findFirst()
                    .orElseThrow(()-> new EntityNotFoundException("Todo with id " + todoId + " not found"));
            user.getTodos().remove(todo);
            todo.setUser(null);
            todoRepository.delete(todo);
            return user.getTodos();
        } else {
            throw new RuntimeException("The list of todos is empty for user with ID " + userId);
        }
    }
}