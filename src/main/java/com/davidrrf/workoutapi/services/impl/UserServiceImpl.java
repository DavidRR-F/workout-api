package com.davidrrf.workoutapi.services.impl;

import com.davidrrf.workoutapi.dtos.UserUpdateRequest;
import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.exceptions.ResourceErrorException;
import com.davidrrf.workoutapi.repositories.UserRepository;
import com.davidrrf.workoutapi.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public User addUser(User user){
        Optional<User> savedUser = userRepository.findByEmail(user.getEmail());
        if(savedUser.isPresent()) {
            throw new ResourceErrorException("User with that email already exists", 409);
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ResourceErrorException("User with that id " + id + " does not exist", 404);
        }
        return user.get();
    }

    @Override
    public User updateUser(int userId, UserUpdateRequest user) {
        User updateUser = getUser(userId);
        modelMapper.map(user, updateUser);
        return userRepository.save(updateUser);
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
