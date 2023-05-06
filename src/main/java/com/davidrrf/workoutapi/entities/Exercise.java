package com.davidrrf.workoutapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long weight;
    @Column(name="weight_increase", columnDefinition = "bigint default 0")
    private Long weightIncrease;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workoutId", nullable = false)
    private Workout workout;
}
