package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.dtos.UserUpdateRequest;
import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.exceptions.HandleException;
import com.davidrrf.workoutapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends HandleException {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId) {
        return tryCall(() -> userService.getUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody UserUpdateRequest user) {
        return tryCall(() -> userService.updateUser(userId, user));
    }

   @DeleteMapping("/{userId}")
   @ResponseStatus(HttpStatus.ACCEPTED)
   public void deleteUser(@PathVariable int userId) {
       userService.deleteUser(userId);
   }

}
