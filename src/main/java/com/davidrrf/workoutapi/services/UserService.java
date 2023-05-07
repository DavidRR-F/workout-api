package com.davidrrf.workoutapi.services;

import com.davidrrf.workoutapi.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);
    Optional<User> getUser(int id);
    User updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();


}
