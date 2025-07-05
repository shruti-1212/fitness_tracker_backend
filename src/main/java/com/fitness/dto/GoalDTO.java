package com.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public class GoalDTO {
    
    @NotBlank(message = "Goal title is required")
    private String title;
    
    @NotBlank(message = "Goal description is required")
    private String description;
    
    @NotBlank(message = "Goal type is required")
    private String goalType;
    
    @NotNull(message = "Target value is required")
    @Positive(message = "Target value must be positive")
    private Double targetValue;
    
    private String unit;
    
    private LocalDateTime targetDate;
    
    // Constructors
    public GoalDTO() {}
    
    public GoalDTO(String title, String description, String goalType, Double targetValue, String unit) {
        this.title = title;
        this.description = description;
        this.goalType = goalType;
        this.targetValue = targetValue;
        this.unit = unit;
    }
    
    // Getters and Setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getGoalType() {
        return goalType;
    }
    
    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }
    
    public Double getTargetValue() {
        return targetValue;
    }
    
    public void setTargetValue(Double targetValue) {
        this.targetValue = targetValue;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public LocalDateTime getTargetDate() {
        return targetDate;
    }
    
    public void setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }
} 