package com.fitness.repository;

import com.fitness.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    
    List<Meal> findByUserIdOrderByMealDateDesc(Long userId);
    
    List<Meal> findByUserIdAndMealDateBetweenOrderByMealDateDesc(
        Long userId, LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT SUM(m.calories) FROM Meal m WHERE m.user.id = :userId AND m.mealDate BETWEEN :startDate AND :endDate")
    Integer sumCaloriesByUserIdAndDateRange(@Param("userId") Long userId, 
                                           @Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate);
} 