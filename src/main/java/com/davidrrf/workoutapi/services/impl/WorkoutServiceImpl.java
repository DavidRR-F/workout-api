package com.davidrrf.workoutapi.services.impl;

import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.entities.Workout;
import com.davidrrf.workoutapi.exceptions.ResourceErrorException;
import com.davidrrf.workoutapi.repositories.UserRepository;
import com.davidrrf.workoutapi.repositories.WorkoutRepository;
import com.davidrrf.workoutapi.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Workout addWorkout(int userId, Workout workout){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new ResourceErrorException("User with that id does not exists", 404);
        }
        if(workoutExists(user.get(), workout.getName())) {
            throw new ResourceErrorException("Workout with that name already exists", 409);
        }
        workout.setUser(user.get());
        return workoutRepository.save(workout);
    }
}
