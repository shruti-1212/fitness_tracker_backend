package com.fitness.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "meals")
public class Meal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String description;
    
    @NotNull
    private Integer calories;
    
    private String mealType; // "breakfast", "lunch", "dinner", "snack"
    
    private LocalDateTime mealDate;
    
    @PrePersist
    protected void onCreate() {
        if (mealDate == null) {
            mealDate = LocalDateTime.now();
        }
    }
    
    // Constructors
    public Meal() {}
    
    public Meal(User user, String name, String description, Integer calories, String mealType) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.mealType = mealType;
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getCalories() {
        return calories;
    }
    
    public void setCalories(Integer calories) {
        this.calories = calories;
    }
    
    public String getMealType() {
        return mealType;
    }
    
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
    
    public LocalDateTime getMealDate() {
        return mealDate;
    }
    
    public void setMealDate(LocalDateTime mealDate) {
        this.mealDate = mealDate;
    }
} 