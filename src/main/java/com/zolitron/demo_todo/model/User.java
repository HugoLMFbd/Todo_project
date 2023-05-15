package com.zolitron.demo_todo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zolitron.demo_todo.repository.TodoRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "custom_user")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    public User(){}
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Todo> todos = new ArrayList<>();
    public Long getId(){
        return id;
    }
    public String getUserName(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public List<Todo> getTodos() {
        return todos;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void addTodo(Todo todo) {
        todos.add(todo);
    }
}