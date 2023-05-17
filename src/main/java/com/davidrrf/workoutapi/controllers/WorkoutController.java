package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.dtos.WorkoutUpdateRequest;
import com.davidrrf.workoutapi.entities.Workout;
import com.davidrrf.workoutapi.services.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users/{userId}/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<Workout> getAllWorkouts(@PathVariable int userId) {
        return workoutService.getAllWorkouts(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Workout createWorkout(@PathVariable int userId,@Valid @RequestBody Workout workout) {
        return workoutService.addWorkout(userId, workout);
    }

    @GetMapping("/{workoutId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Workout getWorkout(@PathVariable int userId, @PathVariable int workoutId) {
        return workoutService.getWorkout(userId, workoutId);
    }

    @PutMapping("/{workoutId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Workout updateWorkout(@PathVariable int userId, @PathVariable int workoutId,@Valid @RequestBody WorkoutUpdateRequest workout) {
        return workoutService.updateWorkout(userId, workoutId, workout);
    }

    @DeleteMapping("/{workoutId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Workout deleteWorkout(@PathVariable int userId, @PathVariable int workoutId) {
        return workoutService.deleteWorkout(userId, workoutId);
    }
}
