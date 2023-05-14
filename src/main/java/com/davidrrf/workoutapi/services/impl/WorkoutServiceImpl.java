package com.davidrrf.workoutapi.services.impl;

import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.entities.Workout;
import com.davidrrf.workoutapi.exceptions.ResourceErrorException;
import com.davidrrf.workoutapi.repositories.UserRepository;
import com.davidrrf.workoutapi.repositories.WorkoutRepository;
import com.davidrrf.workoutapi.services.UserService;
import com.davidrrf.workoutapi.services.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    private boolean workoutExists(User user, String workoutName) {
        return user.getWorkouts().stream().anyMatch(workout -> workout.getName().equals(workoutName));
    }

    private Optional<Workout> workoutExists(User user, int workoutId) {
        return user.getWorkouts().stream().filter(workout -> workout.getId() == workoutId).findFirst();
    }

    @Override
    public Workout addWorkout(int userId, Workout workout){
        User user = userService.getUser(userId);
        if(workoutExists(user, workout.getName())) {
            throw new ResourceErrorException("Workout with that name already exists", 409);
        }
        workout.setUser(user);
        return workoutRepository.save(workout);
    }

    @Override
    public Set<Workout> getAllWorkouts(int userId) {
        User user = userService.getUser(userId);
        return user.getWorkouts();
    }

    @Override
    public Workout getWorkout(int userId, int workoutId) {
        User user = userService.getUser(userId);
        Optional<Workout> foundWorkout = workoutExists(user, workoutId);
        if(foundWorkout.isEmpty()) {
            throw new ResourceErrorException("Workout with that id does not exists", 404);
        }
        return foundWorkout.get();
    }

    @Override
    public Workout updateWorkout(int userId, int workoutId, Workout workout) {
        Workout updatedWorkout = getWorkout(userId, workoutId);
        updatedWorkout.setName(workout.getName());
        return workoutRepository.save(updatedWorkout);
    }

    @Override
    public Workout deleteWorkout(int userId, int workoutId) {
        Workout workout = getWorkout(userId, workoutId);
        workoutRepository.deleteById(workout.getId());
        return workout;
    }
}
