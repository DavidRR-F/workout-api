package com.davidrrf.workoutapi.services;

import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.entities.Workout;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    Workout addWorkout(int userId, Workout workout);
}
