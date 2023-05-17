package com.davidrrf.workoutapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="name", nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(name="sets", columnDefinition = "integer default 0")
    private Integer sets;
    @Column(name="reps", columnDefinition = "integer default 0")
    private Integer reps;
    @Column(name="rep_range_top", columnDefinition = "integer default 0")
    private Integer repRangeTop;
    @Column(name="rep_range_bottom", columnDefinition = "integer default 0")
    private Integer repRangeBottom;
    @Column(name="weight", columnDefinition = "bigint default 0")
    private Double weight;
    @Column(name="weight_increase", columnDefinition = "bigint default 0")
    private Double weightIncrease;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workoutId", nullable = false)
    @JsonIgnore
    private Workout workout;

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sets, reps, repRangeTop, repRangeBottom, weight, weightIncrease);
    }
}