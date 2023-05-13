package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.entities.Exercise;
import com.davidrrf.workoutapi.exceptions.HandleException;
import com.davidrrf.workoutapi.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/workouts/{workoutId}/exercises")
public class ExerciseController extends HandleException {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping()
    public ResponseEntity<?> getAllExercises(@PathVariable int userId, @PathVariable int workoutId) {
        return tryCall(() -> exerciseService.getAllExercises(userId, workoutId));
    }

    @PostMapping()
    public ResponseEntity<?> createExercise(@PathVariable int userId, @PathVariable int workoutId, @RequestBody Exercise exercise) {
        return tryCall(() -> exerciseService.addExercise(userId, workoutId, exercise));
    }

    @GetMapping("/{exerciseId}")
    public void getExercise(@PathVariable int userId, @PathVariable int workoutId, @PathVariable int exerciseId) {

    }

    @PutMapping("/{exerciseId}")
    public void updateExercise(@PathVariable int userId, @PathVariable int workoutId, @PathVariable int exerciseId) {

    }

    @DeleteMapping("/{exerciseId}")
    public void deleteExercise(@PathVariable int userId, @PathVariable int workoutId, @PathVariable int exerciseId) {

    }
}
