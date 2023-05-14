package com.davidrrf.workoutapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseUpdateRequest {
    private String name;
    private Integer sets;
    private Integer reps;
    private Integer repRangeTop;
    private Integer repRangeBottom;
    private Double weight;
    private Double weightIncrease;
}
