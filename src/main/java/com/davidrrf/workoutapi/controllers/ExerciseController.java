package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.dtos.ExerciseUpdateRequest;
import com.davidrrf.workoutapi.entities.Exercise;
import com.davidrrf.workoutapi.services.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/users/{userId}/workouts/{workoutId}/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Set<Exercise> getAllExercises(@PathVariable int userId, @PathVariable int workoutId) {
        return exerciseService.getAllExercises(userId, workoutId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Exercise createExercise(
            @PathVariable int userId, @PathVariable int workoutId, @Valid @RequestBody Exercise exercise) {
        return exerciseService.addExercise(userId, workoutId, exercise);
    }

    @GetMapping("/{exerciseId}")
    @ResponseStatus(HttpStatus.OK)
    public Exercise getExercise(@PathVariable int userId, @PathVariable int workoutId, @PathVariable int exerciseId) {
        return exerciseService.getExercise(userId, workoutId, exerciseId);
    }

    @PutMapping("/{exerciseId}")
    @ResponseStatus(HttpStatus.OK)
    public Exercise updateExercise(
            @PathVariable int userId,
            @PathVariable int workoutId,
            @PathVariable int exerciseId,
            @Valid @RequestBody ExerciseUpdateRequest exercise
    ) {
        return exerciseService.updateExercise(userId, workoutId, exerciseId, exercise);
    }

    @DeleteMapping("/{exerciseId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Exercise deleteExercise(@PathVariable int userId, @PathVariable int workoutId, @PathVariable int exerciseId) {
        return exerciseService.deleteExercise(userId, workoutId, exerciseId);
    }
}
