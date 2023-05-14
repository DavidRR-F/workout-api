package com.davidrrf.workoutapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Workout> workouts;

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

//    public void setEmail(String email) {
//        this.email = normalizeEmail(email);
//    }
//
//    private String normalizeEmail(String email) {
//        if (email == null) {
//            return null;
//        }
//        return email.trim().toLowerCase();
//    }
}
