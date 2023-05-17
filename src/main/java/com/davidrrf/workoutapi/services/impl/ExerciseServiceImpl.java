package com.davidrrf.workoutapi.services.impl;

import com.davidrrf.workoutapi.dtos.ExerciseUpdateRequest;
import com.davidrrf.workoutapi.entities.Exercise;
import com.davidrrf.workoutapi.entities.Workout;
import com.davidrrf.workoutapi.exceptions.ResourceErrorException;
import com.davidrrf.workoutapi.repositories.ExerciseRepository;
import com.davidrrf.workoutapi.services.ExerciseService;
import com.davidrrf.workoutapi.services.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private WorkoutService workoutService;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private ModelMapper modelMapper;




    public boolean exerciseExists(Workout workout, String exerciseName) {
        return workout.getExercises().stream().anyMatch(exercise -> exercise.getName().equals(exerciseName));
    }

    public Optional<Exercise> exerciseExists(Workout workout, int exerciseId) {
        return workout.getExercises().stream().filter(exercise -> exercise.getId() == exerciseId).findFirst();
    }

    @Override
    public Exercise addExercise(int userId, int workoutId, Exercise exercise) {
        Workout workout = workoutService.getWorkout(userId, workoutId);
        if(exerciseExists(workout, exercise.getName())) {
            throw new ResourceErrorException("Exercise already exists", 409);
        }
        exercise.setWorkout(workout);
        return exerciseRepository.save(exercise);
    }

    @Override
    public Set<Exercise> getAllExercises(int userId, int workoutId) {
        Workout workout = workoutService.getWorkout(userId, workoutId);
        return workout.getExercises();
    }

    @Override
    public Exercise getExercise(int userId, int workoutId, int exerciseId) {
        Workout workout = workoutService.getWorkout(userId, workoutId);
        Optional<Exercise> exercise = exerciseExists(workout, exerciseId);
        if(exercise.isEmpty()) {
            throw new ResourceErrorException("Exercise does not exist", 404);
        }
        return exercise.get();
    }

    @Override
    public Exercise updateExercise(int userId, int workoutId, int exerciseId, ExerciseUpdateRequest exercise) {
        Exercise updateExercise = getExercise(userId, workoutId, exerciseId);
        modelMapper.map(exercise, updateExercise);
        return exerciseRepository.save(updateExercise);
    }

    @Override
    public Exercise deleteExercise(int userId, int workoutId, int exerciseId) {
        Exercise deleteExercise = getExercise(userId, workoutId, exerciseId);
        exerciseRepository.delete(deleteExercise);
        return deleteExercise;
    }
}
