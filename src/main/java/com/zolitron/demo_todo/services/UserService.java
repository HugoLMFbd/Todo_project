package com.zolitron.demo_todo.services;

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
public class UserService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    private final List<User> todos = new ArrayList<>();

    public User createUser(String username, String password){
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        return userRepository.save(newUser);
    }

    public Optional<User> getUserById(Long id){

        return userRepository.findById(id);
    }

    public Optional<User> getUser(Long userId){
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User updateUserName(Long id, String newUserName) throws EntityNotFoundException{
        Optional<User> optionalUser =userRepository.findById(id);
        if (optionalUser.isPresent()){
            User userNameToUpdate = optionalUser.get();
            userNameToUpdate.setUsername(newUserName);
            userNameToUpdate = userRepository.save(userNameToUpdate);
            return userNameToUpdate;
        }
        throw new EntityNotFoundException("User with id " + id + " not found!");
    }

}
