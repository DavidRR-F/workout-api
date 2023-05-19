package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.dtos.ExerciseUpdateRequest;
import com.davidrrf.workoutapi.dtos.WorkoutUpdateRequest;
import com.davidrrf.workoutapi.entities.Exercise;
import com.davidrrf.workoutapi.entities.Workout;
import com.davidrrf.workoutapi.exceptions.ResourceErrorException;
import com.davidrrf.workoutapi.services.ExerciseService;
import com.davidrrf.workoutapi.services.UserService;
import com.davidrrf.workoutapi.services.WorkoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ExerciseControllerTest {
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
    public void givenWorkoutId_whenGetAllExercises_thenReturnExerciseList() throws Exception {
        Set<Exercise> exerciseList = new HashSet<>();
        int userId = 1;
        int workoutId = 1;
        exerciseList.add(Exercise.builder().name("Squat").build());
        exerciseList.add(Exercise.builder().name("Splits").build());
        given(exerciseService.getAllExercises(userId, workoutId)).willReturn(exerciseList);
        ResultActions response = mockMvc.perform(get("/users/{userId}/workouts/{workoutId}/exercises", userId, workoutId));
        response.andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()) // print response
                .andExpect(jsonPath("$.size()", is(exerciseList.size())));
        verify(exerciseService, times(1)).getAllExercises(userId, workoutId);
    }

    @Test
    public void givenWorkoutIdId_whenGetAllExercises_ReturnUserNotFound() throws Exception {
        // Arrange
        int userId = 1;
        int workoutId = 1;

        given(exerciseService.getAllExercises(userId, workoutId)).willThrow(new ResourceErrorException("User not found", 404));

        // Act
        ResultActions result = mockMvc.perform(get("/users/{userId}/workouts/{workoutId}/exercises", userId, workoutId));

        // Assert
        result.andExpect(status().isNotFound());

        verify(exerciseService, times(1)).getAllExercises(userId, workoutId);
    }

    @Test
    public void givenWorkoutId_whenGetExercise_ReturnExerciseObject() throws Exception {
        // Arrange
        int userId = 1;
        int workoutId = 1;
        int exerciseId = 1;

        Exercise exercise = Exercise.builder().name("Squat").sets(3).reps(3).repRangeTop(3).repRangeBottom(3).weight(3.0).weightIncrease(3.0).build();
        // Set the properties of the 'workout' object for testing

        given(exerciseService.getExercise(userId, workoutId, exerciseId)).willReturn(exercise);

        // Act
        ResultActions result = mockMvc.perform(get("/users/{userId}/workouts/{workoutId}/exercises/{exerciseId}", userId, workoutId, exerciseId));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.sets", is(exercise.getSets())))
                .andExpect(jsonPath("$.reps", is(exercise.getReps())))
                .andExpect(jsonPath("$.repRangeBottom", is(exercise.getRepRangeBottom())))
                .andExpect(jsonPath("$.repRangeTop", is(exercise.getRepRangeTop())))
                .andExpect(jsonPath("$.weight", is(exercise.getWeight())))
                .andExpect(jsonPath("$.weightIncrease", is(exercise.getWeightIncrease())))
        ;

        verify(exerciseService, times(1)).getExercise(userId, workoutId, exerciseId);
    }

    @Test
    public void givenExerciseObject_whenCreateExercise_thenReturnExerciseObject() throws Exception {
        int userId = 1;
        int workoutId = 1;
        Exercise exercise = Exercise.builder().name("Leg Day").build();
        // given
        given(exerciseService.addExercise(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.any(Exercise.class)))
                .willReturn(exercise);
        // when
        ResultActions response =  mockMvc.perform(post("/users/{userId}/workouts/{workoutId}/exercises", userId, workoutId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exercise))
        );
        // then
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.sets", is(exercise.getSets())))
                .andExpect(jsonPath("$.reps", is(exercise.getReps())))
                .andExpect(jsonPath("$.repRangeBottom", is(exercise.getRepRangeBottom())))
                .andExpect(jsonPath("$.repRangeTop", is(exercise.getRepRangeTop())))
                .andExpect(jsonPath("$.weight", is(exercise.getWeight())))
                .andExpect(jsonPath("$.weightIncrease", is(exercise.getWeightIncrease())))
        ;
    }

    @Test
    public void givenExerciseRequest_whenUpdateExercise_thenReturnUpdatedExercise() throws Exception {
        // Arrange
        int userId = 1;
        int workoutId = 1;
        int exerciseId = 1;
        ExerciseUpdateRequest exerciseUpdateRequest = new ExerciseUpdateRequest();
        // Set the properties of userUpdateRequest for testing

        Exercise updatedExercise = new Exercise();
        // Set the properties of updatedUser for testing

        given(exerciseService.updateExercise(userId, workoutId, exerciseId, exerciseUpdateRequest)).willReturn(updatedExercise);

        // Act
        ResultActions result = mockMvc.perform(put("/users/{userId}/workouts/{workoutId}/exercises/{exerciseId}", userId, workoutId, exerciseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exerciseUpdateRequest)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(updatedExercise.getName())))
                .andExpect(jsonPath("$.sets", is(updatedExercise.getSets())))
                .andExpect(jsonPath("$.reps", is(updatedExercise.getReps())))
                .andExpect(jsonPath("$.repRangeBottom", is(updatedExercise.getRepRangeBottom())))
                .andExpect(jsonPath("$.repRangeTop", is(updatedExercise.getRepRangeTop())))
                .andExpect(jsonPath("$.weight", is(updatedExercise.getWeight())))
                .andExpect(jsonPath("$.weightIncrease", is(updatedExercise.getWeightIncrease())));

        verify(exerciseService, times(1)).updateExercise(userId, workoutId, exerciseId, exerciseUpdateRequest);
    }

    @Test
    public void givenExerciseId_whenDeleteExercise_ReturnDeletedExercise() throws Exception {
        // Arrange
        int userId = 1;
        int workoutId = 1;
        int exerciseId = 1;

        // Act
        ResultActions result = mockMvc.perform(delete("/users/{userId}/workouts/{workoutId}/exercises/{exerciseId}", userId, workoutId, exerciseId));

        // Assert
        result.andExpect(status().isAccepted());

        verify(exerciseService, times(1)).deleteExercise(userId, workoutId, exerciseId);
    }
}
