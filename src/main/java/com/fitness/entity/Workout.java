package com.fitness.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "workouts")
public class Workout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotBlank
    private String type; // e.g., "cardio", "strength", "yoga"
    
    @NotBlank
    private String description;
    
    @NotNull
    private Integer duration; // in minutes
    
    private Integer caloriesBurned;
    
    private LocalDateTime workoutDate;
    
    @PrePersist
    protected void onCreate() {
        if (workoutDate == null) {
            workoutDate = LocalDateTime.now();
        }
    }
    
    // Constructors
    public Workout() {}
    
    public Workout(User user, String type, String description, Integer duration) {
        this.user = user;
        this.type = type;
        this.description = description;
        this.duration = duration;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
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
    
    public LocalDateTime getWorkoutDate() {
        return workoutDate;
    }
    
    public void setWorkoutDate(LocalDateTime workoutDate) {
        this.workoutDate = workoutDate;
    }
} 