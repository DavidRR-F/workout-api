package com.davidrrf.workoutapi.repositories;

import com.davidrrf.workoutapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    // custom queries using jpa syntax
    @Query("select u from User u where u.firstName = ?1 and u.lastName = ?2")
    User findByJPQL(String firstName, String lastName);

    @Query("select u from User u where u.firstName =:firstName and u.lastName =:lastName")
    User findByJPQLNamed(@Param("firstName") String firstName,@Param("lastName") String lastName);
    //custom queries using native sql syntax
    @Query(value = "select * from User u where u.firstName = ?1 and u.lastName = ?2", nativeQuery = true)
    User findByNativeJPQL(String firstName, String lastName);
}
