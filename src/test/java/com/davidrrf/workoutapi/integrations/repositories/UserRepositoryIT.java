package com.davidrrf.workoutapi.integrations.repositories;

import com.davidrrf.workoutapi.entities.User;
import com.davidrrf.workoutapi.integrations.AbstractContainerBaseTest;
import com.davidrrf.workoutapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIT extends AbstractContainerBaseTest {
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        // given
        user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johnDoe@gmail.com")
                .build();
    }
    //JUnit Test Cases
    @DisplayName("Save User")
    @Test
    public void givenUserObject_whenSave_thenReturnSavedUser() {
        // when
        User savedUser = userRepository.save(user);
        //then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @DisplayName("Get All Users")
    @Test
    public void givenUserList_whenFindAll_thenReturnUserList() {

        User user2 = User.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("janeDoe@gmail.com")
                .build();
        userRepository.save(user);
        userRepository.save(user2);
        // when
        List<User> userList = userRepository.findAll();
        //then
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);
    }

    @DisplayName("Get User By Id")
    @Test
    public void givenUserObject_whenFindById_thenReturnUserObject() {

        userRepository.save(user);
        // when
        User userDB = userRepository.findById(user.getId()).get();
        //then
        assertThat(userDB).isNotNull();
        assertThat(userDB).isEqualTo(user);
    }

    @DisplayName("Get User By Email")
    @Test
    public void givenUserObject_whenFindByEmail_thenReturnUserObject() {
        userRepository.save(user);
        // when
        User userDB = userRepository.findByEmail(user.getEmail()).get();
        //then
        assertThat(userDB).isNotNull();
        assertThat(userDB).isEqualTo(user);
    }
    // Test for custom Query
    @DisplayName("Get User by First and Last Name")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnUserObject() {
        userRepository.save(user);
        // when
        String firstName = "John";
        String lastName = "Doe";
        User savedUser = userRepository.findByJPQL(firstName, lastName);
        //then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isEqualTo(user);
    }
    // Test for custom Query named params
    @DisplayName("Get User by First and Last Name")
    @Test
    public void givenFirstNameAndLastNameParams_whenFindByJPQL_thenReturnUserObject() {
        userRepository.save(user);
        // when
        String firstName = "John";
        String lastName = "Doe";
        User savedUser = userRepository.findByJPQLNamed(firstName, lastName);
        //then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isEqualTo(user);
    }

    @DisplayName("Update User")
    @Test
    public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser() {
        userRepository.save(user);
        // when
        User savedUser = userRepository.findById(user.getId()).get();
        savedUser.setEmail("jobojoe423@gmail.com");
        savedUser.setLastName("Jobo");
        User updatedUser = userRepository.save(savedUser);
        //then
        assertThat(updatedUser.getEmail()).isEqualTo("jobojoe423@gmail.com");
        assertThat(updatedUser.getLastName()).isEqualTo("Jobo");
    }

    @DisplayName("Delete User")
    @Test
    public void givenUserObject_whenDeletedUser_thenUserNotExist() {
        userRepository.save(user);
        // when
        userRepository.deleteById(user.getId());
        Optional<User> userOptional = userRepository.findById(user.getId());
        //then
        assertThat(userOptional).isEmpty();
    }
}
