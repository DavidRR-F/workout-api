package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.services.UserService;
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
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        return userService.getUser(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) {
        return userService.getUser(userId)
                .map(savedUser -> {
                    savedUser.setEmail(user.getEmail());
                    savedUser.setFirstName(user.getFirstName());
                    savedUser.setLastName(user.getLastName());

                    User updatedUser = userService.updateUser(savedUser);
                    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

   @DeleteMapping("/{userId}")
   @ResponseStatus(HttpStatus.ACCEPTED)
   public void deleteUser(@PathVariable int userId) {
       userService.deleteUser(userId);
   }

}
