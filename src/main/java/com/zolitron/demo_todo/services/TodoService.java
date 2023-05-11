package com.zolitron.demo_todo.services;

import com.zolitron.demo_todo.DemoTodoApplication;
import com.zolitron.demo_todo.model.Todo;
import com.zolitron.demo_todo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(String task){
        Todo newTodo = new Todo();
        newTodo.setTask(task);
        newTodo = todoRepository.save(newTodo);
        return newTodo;
    }
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }
    public Todo getTodoById(int id) throws EntityNotFoundException{
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()){
            return optionalTodo.get();
        }
        throw new EntityNotFoundException("Todo with id " + id + " not found!");
    }
    public Todo updateTask(int id , String newTask) throws EntityNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()){
                Todo to_update_todo = optionalTodo.get();
                to_update_todo.setTask(newTask);
                to_update_todo = todoRepository.save(to_update_todo);
                return to_update_todo;
            }
        throw new EntityNotFoundException("Todo with id " + id + " not found!");
        }
    public List<Todo> removeTask(int id) throws EntityNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()){
            Todo to_delete_todo = optionalTodo.get();
            todoRepository.delete(to_delete_todo);
            return todoRepository.findAll();
            }
        throw new EntityNotFoundException("Todo with id " + id + " not found!");
    }
}
