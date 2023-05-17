package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.dtos.UserUpdateRequest;
import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.exceptions.HandleException;
import com.davidrrf.workoutapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User getUser(@PathVariable int userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable int userId, @Valid @RequestBody UserUpdateRequest user) {
        return userService.updateUser(userId, user);
    }

   @DeleteMapping("/{userId}")
   @ResponseStatus(HttpStatus.ACCEPTED)
   public void deleteUser(@PathVariable int userId) {
       userService.deleteUser(userId);
   }

}
