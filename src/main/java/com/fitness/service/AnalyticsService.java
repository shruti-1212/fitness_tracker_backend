package com.fitness.service;

import com.fitness.dto.WeeklyAnalyticsDTO;
import com.fitness.entity.Workout;
import com.fitness.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {
    
    @Autowired
    private WorkoutService workoutService;
    
    @Autowired
    private MealService mealService;
    
    public WeeklyAnalyticsDTO getWeeklyAnalytics(String sessionToken) {
        // Calculate week start (Monday) and end (Sunday)
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.with(DayOfWeek.MONDAY);
        LocalDate weekEnd = today.with(DayOfWeek.SUNDAY);
        
        LocalDateTime weekStartDateTime = weekStart.atStartOfDay();
        LocalDateTime weekEndDateTime = weekEnd.atTime(23, 59, 59);
        
        // Get workouts and meals for the week
        List<Workout> workouts = workoutService.getUserWorkoutsByDateRange(sessionToken, weekStartDateTime, weekEndDateTime);
        List<Meal> meals = mealService.getUserMealsByDateRange(sessionToken, weekStartDateTime, weekEndDateTime);
        
        // Calculate totals
        Integer totalWorkoutMinutes = workoutService.getTotalWorkoutMinutes(sessionToken, weekStartDateTime, weekEndDateTime);
        Integer totalCaloriesBurned = workoutService.getTotalCaloriesBurned(sessionToken, weekStartDateTime, weekEndDateTime);
        Integer totalCaloriesConsumed = mealService.getTotalCaloriesConsumed(sessionToken, weekStartDateTime, weekEndDateTime);
        Integer netCalories = (totalCaloriesConsumed != null ? totalCaloriesConsumed : 0) - (totalCaloriesBurned != null ? totalCaloriesBurned : 0);
        
        // Create daily summaries
        List<WeeklyAnalyticsDTO.DailySummary> dailySummaries = createDailySummaries(weekStart, weekEnd, workouts, meals);
        
        // Build response
        WeeklyAnalyticsDTO analytics = new WeeklyAnalyticsDTO(weekStart, weekEnd);
        analytics.setTotalWorkoutMinutes(totalWorkoutMinutes);
        analytics.setTotalCaloriesBurned(totalCaloriesBurned);
        analytics.setTotalCaloriesConsumed(totalCaloriesConsumed);
        analytics.setNetCalories(netCalories);
        analytics.setWorkoutCount(workouts.size());
        analytics.setMealCount(meals.size());
        analytics.setDailySummaries(dailySummaries);
        
        return analytics;
    }
    
    private List<WeeklyAnalyticsDTO.DailySummary> createDailySummaries(LocalDate weekStart, LocalDate weekEnd, 
                                                                       List<Workout> workouts, List<Meal> meals) {
        List<WeeklyAnalyticsDTO.DailySummary> summaries = new ArrayList<>();
        
        // Group workouts and meals by date
        Map<LocalDate, List<Workout>> workoutsByDate = workouts.stream()
            .collect(Collectors.groupingBy(w -> w.getWorkoutDate().toLocalDate()));
        
        Map<LocalDate, List<Meal>> mealsByDate = meals.stream()
            .collect(Collectors.groupingBy(m -> m.getMealDate().toLocalDate()));
        
        // Create summary for each day of the week
        LocalDate currentDate = weekStart;
        while (!currentDate.isAfter(weekEnd)) {
            WeeklyAnalyticsDTO.DailySummary summary = new WeeklyAnalyticsDTO.DailySummary(currentDate);
            
            List<Workout> dayWorkouts = workoutsByDate.getOrDefault(currentDate, new ArrayList<>());
            List<Meal> dayMeals = mealsByDate.getOrDefault(currentDate, new ArrayList<>());
            
            summary.setWorkoutCount(dayWorkouts.size());
            summary.setMealCount(dayMeals.size());
            
            summary.setWorkoutMinutes(dayWorkouts.stream()
                .mapToInt(w -> w.getDuration() != null ? w.getDuration() : 0)
                .sum());
            
            summary.setCaloriesBurned(dayWorkouts.stream()
                .mapToInt(w -> w.getCaloriesBurned() != null ? w.getCaloriesBurned() : 0)
                .sum());
            
            summary.setCaloriesConsumed(dayMeals.stream()
                .mapToInt(m -> m.getCalories() != null ? m.getCalories() : 0)
                .sum());
            
            summaries.add(summary);
            currentDate = currentDate.plusDays(1);
        }
        
        return summaries;
    }
} 