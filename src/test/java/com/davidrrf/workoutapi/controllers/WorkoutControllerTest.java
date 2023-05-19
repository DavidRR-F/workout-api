package com.davidrrf.workoutapi.controllers;
import com.davidrrf.workoutapi.entities.Workout;
import com.davidrrf.workoutapi.exceptions.ResourceErrorException;
import com.davidrrf.workoutapi.services.ExerciseService;
import com.davidrrf.workoutapi.services.UserService;
import com.davidrrf.workoutapi.services.WorkoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest
public class WorkoutControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private WorkoutService workoutService;
    @MockBean
    private ExerciseService exerciseService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUserId_whenGetAllWorkouts_thenReturnWorkoutList() throws Exception {
        Set<Workout> workoutList = new HashSet<>();
        int userId = 1;
        workoutList.add(Workout.builder().name("Leg Day").build());
        workoutList.add(Workout.builder().name("Arm Day").build());
        given(workoutService.getAllWorkouts(userId)).willReturn(workoutList);
        ResultActions response = mockMvc.perform(get("/users/{userId}/workouts", userId));
        response.andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()) // print response
                .andExpect(jsonPath("$.size()", is(workoutList.size())));
        verify(workoutService, times(1)).getAllWorkouts(userId);
    }

    @Test
    public void givenUserId_whenGetAllWorkouts_ReturnUserNotFound() throws Exception {
        // Arrange
        int userId = 1;

        given(workoutService.getAllWorkouts(userId)).willThrow(new ResourceErrorException("User not found", 404));

        // Act
        ResultActions result = mockMvc.perform(get("/users/{userId}/workouts", userId));

        // Assert
        result.andExpect(status().isNotFound());

        verify(workoutService, times(1)).getAllWorkouts(userId);
    }



    
}
