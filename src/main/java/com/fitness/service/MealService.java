package com.fitness.service;

import com.fitness.entity.User;
import com.fitness.entity.Meal;
import com.fitness.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MealService {
    
    @Autowired
    private MealRepository mealRepository;
    
    @Autowired
    private UserService userService;
    
    public Meal createMeal(String sessionToken, String name, String description, Integer calories, String mealType) {
        User user = userService.getUserFromSession(sessionToken);
        
        Meal meal = new Meal();
        meal.setUser(user);
        meal.setName(name);
        meal.setDescription(description);
        meal.setCalories(calories);
        meal.setMealType(mealType);
        
        return mealRepository.save(meal);
    }
    
    public List<Meal> getUserMeals(String sessionToken) {
        User user = userService.getUserFromSession(sessionToken);
        return mealRepository.findByUserIdOrderByMealDateDesc(user.getId());
    }
    
    public List<Meal> getUserMealsByDateRange(String sessionToken, LocalDateTime startDate, LocalDateTime endDate) {
        User user = userService.getUserFromSession(sessionToken);
        return mealRepository.findByUserIdAndMealDateBetweenOrderByMealDateDesc(user.getId(), startDate, endDate);
    }
    
    public Integer getTotalCaloriesConsumed(String sessionToken, LocalDateTime startDate, LocalDateTime endDate) {
        User user = userService.getUserFromSession(sessionToken);
        Integer totalCalories = mealRepository.sumCaloriesByUserIdAndDateRange(user.getId(), startDate, endDate);
        return totalCalories != null ? totalCalories : 0;
    }
} 