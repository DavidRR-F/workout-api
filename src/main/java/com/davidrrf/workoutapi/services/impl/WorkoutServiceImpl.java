package com.davidrrf.workoutapi.services.impl;

import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.entities.Workout;
import com.davidrrf.workoutapi.exceptions.ResourceNotFoundException;
import com.davidrrf.workoutapi.repositories.UserRepository;
import com.davidrrf.workoutapi.repositories.WorkoutRepository;
import com.davidrrf.workoutapi.services.UserService;
import com.davidrrf.workoutapi.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    private boolean workoutExists(User user, String workoutName) {
        return user.getWorkouts().stream().anyMatch(workout -> workout.getName().equals(workoutName));
    }

    @Override
    public ResponseEntity<String> addWorkout(int userId, Workout workout){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if(workoutExists(user.get(), workout.getName())) {
            return ResponseEntity.badRequest().build();
        }
        workout.setUser(user.get());
        workoutRepository.save(workout);
        return ResponseEntity.accepted().build();
    }
}
