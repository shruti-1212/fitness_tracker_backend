package com.fitness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.dto.WorkoutDTO;
import com.fitness.entity.Workout;
import com.fitness.service.WorkoutService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin(origins = "*")
public class WorkoutController {
    
    @Autowired
    private WorkoutService workoutService;
    
    @PostMapping
    public ResponseEntity<?> createWorkout(@RequestHeader("Authorization") String sessionToken,
                                         @Valid @RequestBody WorkoutDTO workoutDTO) {
        try {
            Workout workout = workoutService.createWorkout(sessionToken, workoutDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Workout created successfully");
            response.put("workoutId", workout.getId());
            response.put("type", workout.getType());
            response.put("duration", workout.getDuration());
            response.put("caloriesBurned", workout.getCaloriesBurned());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getUserWorkouts(@RequestHeader("Authorization") String sessionToken) {
        try {
            List<Workout> workouts = workoutService.getUserWorkouts(sessionToken);
            Map<String, Object> response = new HashMap<>();
            response.put("workouts", workouts);
            response.put("count", workouts.size());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 