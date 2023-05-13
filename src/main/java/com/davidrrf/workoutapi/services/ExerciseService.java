package com.davidrrf.workoutapi.services;

import com.davidrrf.workoutapi.entities.Exercise;

import java.util.Set;

public interface ExerciseService {
    Exercise addExercise(int userId, int workoutId, Exercise exercise);
    Set<Exercise> getAllExercises(int userId, int workoutId);
    Exercise getExercise(int userId, int workoutId, int exerciseId);
}
