package com.davidrrf.workoutapi.services;

import com.davidrrf.workoutapi.dtos.ExerciseUpdateRequest;
import com.davidrrf.workoutapi.entities.Exercise;

import java.util.Set;

public interface ExerciseService {
    Exercise addExercise(int userId, int workoutId, Exercise exercise);
    Set<Exercise> getAllExercises(int userId, int workoutId);
    Exercise getExercise(int userId, int workoutId, int exerciseId);
    Exercise updateExercise(int userId, int workoutId, int exerciseId, ExerciseUpdateRequest exercise);
    Exercise deleteExercise(int userId, int workoutId, int exerciseId);
}
