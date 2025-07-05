package com.fitness.dto;

import java.time.LocalDate;
import java.util.List;

public class WeeklyAnalyticsDTO {
    
    private LocalDate weekStart;
    private LocalDate weekEnd;
    private Integer totalWorkoutMinutes;
    private Integer totalCaloriesBurned;
    private Integer totalCaloriesConsumed;
    private Integer netCalories;
    private Integer workoutCount;
    private Integer mealCount;
    private List<DailySummary> dailySummaries;
    
    // Constructors
    public WeeklyAnalyticsDTO() {}
    
    public WeeklyAnalyticsDTO(LocalDate weekStart, LocalDate weekEnd) {
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
    }
    
    // Inner class for daily summary
    public static class DailySummary {
        private LocalDate date;
        private Integer workoutMinutes;
        private Integer caloriesBurned;
        private Integer caloriesConsumed;
        private Integer workoutCount;
        private Integer mealCount;
        
        public DailySummary() {}
        
        public DailySummary(LocalDate date) {
            this.date = date;
        }
        
        // Getters and Setters
        public LocalDate getDate() {
            return date;
        }
        
        public void setDate(LocalDate date) {
            this.date = date;
        }
        
        public Integer getWorkoutMinutes() {
            return workoutMinutes;
        }
        
        public void setWorkoutMinutes(Integer workoutMinutes) {
            this.workoutMinutes = workoutMinutes;
        }
        
        public Integer getCaloriesBurned() {
            return caloriesBurned;
        }
        
        public void setCaloriesBurned(Integer caloriesBurned) {
            this.caloriesBurned = caloriesBurned;
        }
        
        public Integer getCaloriesConsumed() {
            return caloriesConsumed;
        }
        
        public void setCaloriesConsumed(Integer caloriesConsumed) {
            this.caloriesConsumed = caloriesConsumed;
        }
        
        public Integer getWorkoutCount() {
            return workoutCount;
        }
        
        public void setWorkoutCount(Integer workoutCount) {
            this.workoutCount = workoutCount;
        }
        
        public Integer getMealCount() {
            return mealCount;
        }
        
        public void setMealCount(Integer mealCount) {
            this.mealCount = mealCount;
        }
    }
    
    // Getters and Setters
    public LocalDate getWeekStart() {
        return weekStart;
    }
    
    public void setWeekStart(LocalDate weekStart) {
        this.weekStart = weekStart;
    }
    
    public LocalDate getWeekEnd() {
        return weekEnd;
    }
    
    public void setWeekEnd(LocalDate weekEnd) {
        this.weekEnd = weekEnd;
    }
    
    public Integer getTotalWorkoutMinutes() {
        return totalWorkoutMinutes;
    }
    
    public void setTotalWorkoutMinutes(Integer totalWorkoutMinutes) {
        this.totalWorkoutMinutes = totalWorkoutMinutes;
    }
    
    public Integer getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }
    
    public void setTotalCaloriesBurned(Integer totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
    
    public Integer getTotalCaloriesConsumed() {
        return totalCaloriesConsumed;
    }
    
    public void setTotalCaloriesConsumed(Integer totalCaloriesConsumed) {
        this.totalCaloriesConsumed = totalCaloriesConsumed;
    }
    
    public Integer getNetCalories() {
        return netCalories;
    }
    
    public void setNetCalories(Integer netCalories) {
        this.netCalories = netCalories;
    }
    
    public Integer getWorkoutCount() {
        return workoutCount;
    }
    
    public void setWorkoutCount(Integer workoutCount) {
        this.workoutCount = workoutCount;
    }
    
    public Integer getMealCount() {
        return mealCount;
    }
    
    public void setMealCount(Integer mealCount) {
        this.mealCount = mealCount;
    }
    
    public List<DailySummary> getDailySummaries() {
        return dailySummaries;
    }
    
    public void setDailySummaries(List<DailySummary> dailySummaries) {
        this.dailySummaries = dailySummaries;
    }
} 