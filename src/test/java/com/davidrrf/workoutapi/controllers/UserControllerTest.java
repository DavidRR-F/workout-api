package com.davidrrf.workoutapi.controllers;

import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUserList_whenGetAllUsers_thenReturnUserList() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().firstName("John").lastName("Doe").email("johnDoe@gmail.com").build());
        userList.add(User.builder().firstName("Jane").lastName("Doe").email("janeDoe@gmail.com").build());
        given(userService.getAllUsers()).willReturn(userList);
        ResultActions response = mockMvc.perform(get("/users"));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()) // print response
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(userList.size())));
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
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(user.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(user.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(user.getEmail())))
        ;
    }
}