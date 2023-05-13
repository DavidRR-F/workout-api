package com.davidrrf.workoutapi.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.exceptions.ResourceErrorException;
import com.davidrrf.workoutapi.repositories.UserRepository;
import com.davidrrf.workoutapi.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("johnDoe@gmail.com")
                .build();
    }

    @DisplayName("Save User")
    @Test
    public void givenUserObject_whenUserAdded_thenReturnUserObject() {
        // given
        given(userRepository.save(user)).willReturn(user);
        // when
        User savedUser = userService.addUser(user);
        // then
        assertThat(savedUser).isNotNull();
    }

    @DisplayName("User Already Exists")
    @Test
    public void givenUserEmailExists_whenUserAdded_thenThrowException() {
        // given
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
        // when
        assertThrows(ResourceErrorException.class, () -> {
            userService.addUser(user);
        });
        // then
        verify(userRepository, never()).save(any(User.class));
    }

    @DisplayName("Get User by Id")
    @Test
    public void givenUserId_whenGetUser_thenReturnUserObject() {
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        User user = userService.getUser(1);
        assertThat(user).isNotNull();
    }

    @DisplayName("User Does Not Exist")
    @Test
    public void givenUserId_whenGetUser_thenThrowException() {
        given(userRepository.findById(user.getId())).willReturn(Optional.empty());
        assertThrows(ResourceErrorException.class, () -> {
            userService.getUser(user.getId());
        });
        verify(userRepository).findById(1);
    }

    @DisplayName("Get All Users")
    @Test
    public void givenUserList_whenGetAllUsers_thenReturnUserList() {
        given(userRepository.findAll()).willReturn(List.of(user));
        List<User> userList = userService.getAllUsers();
        assertThat(userList.size()).isGreaterThan(0);
    }

    @DisplayName("User List Empty")
    @Test
    public void givenEmptyUserList_whenGetAllUsers_thenThrowException() {
        given(userRepository.findAll()).willReturn(Collections.emptyList());
        List<User> userList = userService.getAllUsers();
        assertThat(userList).isEmpty();
        assertThat(userList.size()).isEqualTo(0);
    }

    @DisplayName("Update User")
    @Test
    public void givenUserObject_whenUpdateUser_thenReturnUpdatedEmployee() {
        // given
        given(userRepository.save(user)).willReturn(user);
        user.setEmail("jobo123@gmail.com");
        user.setFirstName("Jobo");
        // when
        User updatedUser = userService.updateUser(user.getId(), user);
        // then
        assertThat(updatedUser.getEmail()).isEqualTo("jobo123@gmail.com");
        assertThat(updatedUser.getFirstName()).isEqualTo("Jobo");
    }

    @DisplayName("Delete User")
    @Test
    public void givenUserId_whenDeleteUser_thenDeleteUser() {
        willDoNothing().given(userRepository).deleteById(user.getId());
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }


}