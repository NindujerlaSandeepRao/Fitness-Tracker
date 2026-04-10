package com.project.fitness.dto;


import com.project.fitness.models.ActivityType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {

    private String id;

    private String userId;

    private ActivityType type;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String,Object> additionalMetrics;

    private  Integer duration;

    private  Integer caloriesBurned;
    private LocalDateTime startTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
