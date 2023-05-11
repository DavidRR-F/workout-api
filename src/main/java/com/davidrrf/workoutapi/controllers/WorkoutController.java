package com.davidrrf.workoutapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{useId}/workouts")
public class WorkoutController {
    @GetMapping()
    public void getAllWorkouts(@PathVariable int userId) {

    }

    @PostMapping()
    public void createWorkouts(@PathVariable int userId) {

    }

    @GetMapping("/{workoutId}")
    public void getWorkout(@PathVariable int userId, @PathVariable int workoutId) {

    }

    @PutMapping("/{workoutId}")
    public void updateWorkout(@PathVariable int userId, @PathVariable int workoutId) {

    }

    @DeleteMapping("/{workoutId}")
    public void deleteWorkout(@PathVariable int userId, @PathVariable int workoutId) {

    }
}
