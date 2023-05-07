package com.davidrrf.workoutapi.services.impl;

import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.exceptions.ResourceNotFoundException;
import com.davidrrf.workoutapi.repositories.UserRepository;
import com.davidrrf.workoutapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user){
        Optional<User> savedUser = userRepository.findByEmail(user.getEmail());
        if(savedUser.isPresent()) {
            throw new ResourceNotFoundException("User with that email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("User with that id " + id + " does not exist");
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
