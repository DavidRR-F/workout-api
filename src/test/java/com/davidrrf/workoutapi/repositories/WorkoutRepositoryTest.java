package com.davidrrf.workoutapi.repositories;

import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.entities.Workout;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class WorkoutRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    private User user;
    private Workout workout;

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
    }

    @Test
    public void givenWorkoutObject_whenFindByName_thenReturnWorkoutObject() {
        // given
        workoutRepository.save(workout);
        // when
        Workout savedWorkout = workoutRepository.findByName(workout.getName()).get();
        // then
        assertThat(savedWorkout).isNotNull();
        assertThat(savedWorkout).isEqualTo(workout);
    }

}