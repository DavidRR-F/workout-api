package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.entities.Workout;
import com.davidrrf.workoutapi.exceptions.HandleException;
import com.davidrrf.workoutapi.exceptions.ResourceErrorException;
import com.davidrrf.workoutapi.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@RestController
@RequestMapping("/users/{userId}/workouts")
public class WorkoutController extends HandleException {
    @Autowired
    private WorkoutService workoutService;

    @GetMapping()
    public ResponseEntity<?> getAllWorkouts(@PathVariable int userId) {
        return tryCall(() -> workoutService.getAllWorkouts(userId));
    }

    @PostMapping()
    public ResponseEntity<?> createWorkout(@PathVariable int userId, @RequestBody Workout workout) {
        if(workout.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Name can not be Null");
        }
        return tryCall(() -> workoutService.addWorkout(userId, workout));
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<?> getWorkout(@PathVariable int userId, @PathVariable int workoutId) {
        return tryCall(() -> workoutService.getWorkout(userId, workoutId));
    }

    @PutMapping("/{workoutId}")
    public ResponseEntity<?> updateWorkout(@PathVariable int userId, @PathVariable int workoutId, @RequestBody Workout workout) {
        return tryCall(() -> workoutService.updateWorkout(userId, workoutId, workout));
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<?> deleteWorkout(@PathVariable int userId, @PathVariable int workoutId) {
        return tryCall(() -> workoutService.deleteWorkout(userId, workoutId));
    }
}
