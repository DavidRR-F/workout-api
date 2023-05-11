package com.davidrrf.workoutapi.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("workouts/{workoutId}/exercises")
public class ExerciseController {
    @GetMapping()
    public void getAllExercises(@PathVariable int workoutId) {

    }

    @PostMapping()
    public void createExercises(@PathVariable int workoutId) {

    }

    @GetMapping("/{exerciseId}")
    public void getExercise(@PathVariable int workoutId, @PathVariable int exerciseId) {

    }

    @PutMapping("/{exerciseId}")
    public void updateExercise(@PathVariable int workoutId, @PathVariable int exerciseId) {

    }

    @DeleteMapping("/{exerciseId}")
    public void deleteExercise(@PathVariable int workoutId, @PathVariable int exerciseId) {

    }
}
