package com.zolitron.demo_todo.controller;

import com.zolitron.demo_todo.model.Todo;
import com.zolitron.demo_todo.model.User;
import com.zolitron.demo_todo.repository.UserRepository;
import com.zolitron.demo_todo.services.TodoService;
import com.zolitron.demo_todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        User nerUser =userService.createUser(user.getUserName(), user.getPassword());
        userRepository.save(nerUser);
        return nerUser;

    }

}
