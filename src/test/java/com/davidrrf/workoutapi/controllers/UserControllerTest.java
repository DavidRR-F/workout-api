package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.dtos.UserUpdateRequest;
import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.services.ExerciseService;
import com.davidrrf.workoutapi.services.UserService;
import com.davidrrf.workoutapi.services.WorkoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.*;

import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest
class UserControllerTest {
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
    public void givenUserList_whenGetAllUsers_thenReturnUserList() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().firstName("John").lastName("Doe").email("johnDoe@gmail.com").build());
        userList.add(User.builder().firstName("Jane").lastName("Doe").email("janeDoe@gmail.com").build());
        given(userService.getAllUsers()).willReturn(userList);
        ResultActions response = mockMvc.perform(get("/users"));
        response.andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()) // print response
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }

    @Test
    public void givenUserId_whenGetUser_ReturnUserObject() throws Exception {
        // Arrange
        int userId = 1;
        User user = new User();
        // Set the properties of the 'user' object for testing

        given(userService.getUser(userId)).willReturn(user);

        // Act
        ResultActions result = mockMvc.perform(get("/users/{userId}", userId));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));

        verify(userService, times(1)).getUser(userId);
    }

    @Test
    public void givenUserObject_whenCreateUser_thenReturnUser() throws Exception {
        User user = User.builder().firstName("John").lastName("Doe").email("johnDoe@gmail.com").build();
        // given
        given(userService.addUser(ArgumentMatchers.any(User.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));
        // when
        ResultActions response =  mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );
        // then
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
        ;
    }

    @Test
    public void givenMissingName_whenCreateUser_thenReturnBadRequest() throws Exception {
        User user = User.builder()
                .lastName("Doe")
                .email("johnDoe@gmail.com")
                .build();

        // when
        ResultActions response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        // then
        response.andExpect(status().isBadRequest());
    }

    @Test
    public void givenUserRequest_whenUpdateUser_thenReturnUpdatedUser() throws Exception {
        // Arrange
        int userId = 1;
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        // Set the properties of userUpdateRequest for testing

        User updatedUser = new User();
        // Set the properties of updatedUser for testing

        given(userService.updateUser(userId, userUpdateRequest)).willReturn(updatedUser);

        // Act
        ResultActions result = mockMvc.perform(put("/users/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userUpdateRequest)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is(updatedUser.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedUser.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedUser.getEmail())));

        verify(userService, times(1)).updateUser(userId, userUpdateRequest);
    }

    @Test
    public void givenUserId_whenDeleteUser_ReturnDeletedUser() throws Exception {
        // Arrange
        int userId = 1;

        // Act
        ResultActions result = mockMvc.perform(delete("/users/{userId}", userId));

        // Assert
        result.andExpect(status().isAccepted());

        verify(userService, times(1)).deleteUser(userId);
    }
}