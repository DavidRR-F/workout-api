package com.davidrrf.workoutapi.repositories;

import com.davidrrf.workoutapi.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    Optional<Workout> findByName(String name);
}
