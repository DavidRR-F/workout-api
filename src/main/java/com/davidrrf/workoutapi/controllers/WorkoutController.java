package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.entities.Workout;
import com.davidrrf.workoutapi.exceptions.ResourceErrorException;
import com.davidrrf.workoutapi.exceptions.HandleException;
import com.davidrrf.workoutapi.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{useId}/workouts")
public class WorkoutController extends HandleException {
    @Autowired
    private WorkoutService workoutService;

    @GetMapping()
    public void getAllWorkouts(@PathVariable int userId) {

    }

    @PostMapping()
    public ResponseEntity<?> createWorkout(@PathVariable int userId, Workout workout) {
        try {
            return ResponseEntity.ok(workoutService.addWorkout(userId, workout));
        } catch (ResourceErrorException e) {
            return handleError(e);
        }
    }

    @GetMapping("/{workoutId}")
    public void getWorkout(@PathVariable int userId, @PathVariable int workoutId) {

    }

    @PutMapping("/{workoutId}")
    public void updateWorkout(@PathVariable int userId, @PathVariable int workoutId, @RequestBody Workout workout) {

    }

    @DeleteMapping("/{workoutId}")
    public void deleteWorkout(@PathVariable int userId, @PathVariable int workoutId) {

    }
}
