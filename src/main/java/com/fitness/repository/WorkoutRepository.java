package com.fitness.repository;

import com.fitness.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    
    List<Workout> findByUserIdOrderByWorkoutDateDesc(Long userId);
    
    List<Workout> findByUserIdAndWorkoutDateBetweenOrderByWorkoutDateDesc(
        Long userId, LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT SUM(w.duration) FROM Workout w WHERE w.user.id = :userId AND w.workoutDate BETWEEN :startDate AND :endDate")
    Integer sumDurationByUserIdAndDateRange(@Param("userId") Long userId, 
                                           @Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT SUM(w.caloriesBurned) FROM Workout w WHERE w.user.id = :userId AND w.workoutDate BETWEEN :startDate AND :endDate")
    Integer sumCaloriesBurnedByUserIdAndDateRange(@Param("userId") Long userId, 
                                                 @Param("startDate") LocalDateTime startDate, 
                                                 @Param("endDate") LocalDateTime endDate);
} 