package com.davidrrf.workoutapi.repositories;

import com.davidrrf.workoutapi.entities.Exercise;
import static org.assertj.core.api.Assertions.assertThat;
import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.entities.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ExerciseRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    private User user;
    private Workout workout;
    private Exercise exercise;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johnDoe@gmail.com")
                .build();
        userRepository.save(user);
        workout = Workout.builder()
                .user(user)
                .name("Leg Day")
                .build();
        workoutRepository.save(workout);
        exercise = Exercise.builder()
                .workout(workout)
                .name("Split Squats")
                .sets(3)
                .reps(12)
                .repRangeBottom(8)
                .repRangeTop(16)
                .weight(37.5)
                .weightIncrease(2.5)
                .build();
    }

    @Test
    public void givenExerciseObject_whenFindByName_thenReturnExerciseObject() {
        //given
        exerciseRepository.save(exercise);
        // when
        Exercise savedExercise = exerciseRepository.findByName(exercise.getName()).get();
        // then
        assertThat(savedExercise).isNotNull();
        assertThat(savedExercise).isEqualTo(exercise);
    }

}