package com.davidrrf.workoutapi.services;

import com.davidrrf.workoutapi.entities.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUser(int id);
    User updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();


}
