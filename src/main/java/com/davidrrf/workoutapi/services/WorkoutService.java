package com.davidrrf.workoutapi.services;

import com.davidrrf.workoutapi.dtos.WorkoutUpdateRequest;
import com.davidrrf.workoutapi.entities.Workout;
import java.util.Set;

public interface WorkoutService {
    Workout addWorkout(int userId, Workout workout);
    Set<Workout> getAllWorkouts(int userId);
    Workout getWorkout(int userId, int workoutId);
    Workout updateWorkout(int userId, int workoutId, WorkoutUpdateRequest workout);
    Workout deleteWorkout(int userId, int workoutId);
}
