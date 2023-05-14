package com.davidrrf.workoutapi.services;

import com.davidrrf.workoutapi.dtos.UserUpdateRequest;
import com.davidrrf.workoutapi.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);
    User getUser(int id);
    User updateUser(int userId, UserUpdateRequest user);
    void deleteUser(int id);
    List<User> getAllUsers();


}
