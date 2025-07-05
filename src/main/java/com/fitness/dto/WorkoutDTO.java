package com.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WorkoutDTO {
    
    @NotBlank(message = "Workout type is required")
    private String type;
    
    @NotBlank(message = "Workout description is required")
    private String description;
    
    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be positive")
    private Integer duration;
    
    private Integer caloriesBurned;
    
    // Constructors
    public WorkoutDTO() {}
    
    public WorkoutDTO(String type, String description, Integer duration, Integer caloriesBurned) {
        this.type = type;
        this.description = description;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }
    
    // Getters and Setters
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public Integer getCaloriesBurned() {
        return caloriesBurned;
    }
    
    public void setCaloriesBurned(Integer caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
} 