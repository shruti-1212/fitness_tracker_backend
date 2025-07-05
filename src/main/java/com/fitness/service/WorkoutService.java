package com.fitness.service;

import com.fitness.dto.WorkoutDTO;
import com.fitness.entity.User;
import com.fitness.entity.Workout;
import com.fitness.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutService {
    
    @Autowired
    private WorkoutRepository workoutRepository;
    
    @Autowired
    private UserService userService;
    
    public Workout createWorkout(String sessionToken, WorkoutDTO workoutDTO) {
        User user = userService.getUserFromSession(sessionToken);
        
        Workout workout = new Workout();
        workout.setUser(user);
        workout.setType(workoutDTO.getType());
        workout.setDescription(workoutDTO.getDescription());
        workout.setDuration(workoutDTO.getDuration());
        workout.setCaloriesBurned(workoutDTO.getCaloriesBurned());
        
        return workoutRepository.save(workout);
    }
    
    public List<Workout> getUserWorkouts(String sessionToken) {
        User user = userService.getUserFromSession(sessionToken);
        return workoutRepository.findByUserIdOrderByWorkoutDateDesc(user.getId());
    }
    
    public List<Workout> getUserWorkoutsByDateRange(String sessionToken, LocalDateTime startDate, LocalDateTime endDate) {
        User user = userService.getUserFromSession(sessionToken);
        return workoutRepository.findByUserIdAndWorkoutDateBetweenOrderByWorkoutDateDesc(user.getId(), startDate, endDate);
    }
    
    public Integer getTotalWorkoutMinutes(String sessionToken, LocalDateTime startDate, LocalDateTime endDate) {
        User user = userService.getUserFromSession(sessionToken);
        Integer totalMinutes = workoutRepository.sumDurationByUserIdAndDateRange(user.getId(), startDate, endDate);
        return totalMinutes != null ? totalMinutes : 0;
    }
    
    public Integer getTotalCaloriesBurned(String sessionToken, LocalDateTime startDate, LocalDateTime endDate) {
        User user = userService.getUserFromSession(sessionToken);
        Integer totalCalories = workoutRepository.sumCaloriesBurnedByUserIdAndDateRange(user.getId(), startDate, endDate);
        return totalCalories != null ? totalCalories : 0;
    }
} 