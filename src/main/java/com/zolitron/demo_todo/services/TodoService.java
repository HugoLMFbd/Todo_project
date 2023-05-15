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

    public Todo getTodoById(Long id) throws EntityNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            return optionalTodo.get();
        }
        throw new EntityNotFoundException("Todo with id " + id + "not found!");
    }
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }
    public Todo updateTask(Long id , String newTask) throws EntityNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()){
                Todo toUpdateTodo = optionalTodo.get();
                toUpdateTodo.setTask(newTask);
                toUpdateTodo = todoRepository.save(toUpdateTodo);
                return toUpdateTodo;
            }
        throw new EntityNotFoundException("Todo with id " + id + " not found!");
        }
    public List<Todo> removeTask(Long id) throws EntityNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()){
            Todo to_delete_todo = optionalTodo.get();
            todoRepository.delete(to_delete_todo);
            return todoRepository.findAll();
            }
        throw new EntityNotFoundException("Todo with id " + id + " not found!");
    }
}
