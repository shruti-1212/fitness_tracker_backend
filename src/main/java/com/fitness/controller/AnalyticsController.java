package com.fitness.controller;

import com.fitness.dto.WeeklyAnalyticsDTO;
import com.fitness.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fitness_tracker/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {
    
    @Autowired
    private AnalyticsService analyticsService;
    
    @GetMapping("/weekly")
    public ResponseEntity<?> getWeeklyAnalytics(@RequestHeader("Authorization") String sessionToken) {
        try {
            WeeklyAnalyticsDTO analytics = analyticsService.getWeeklyAnalytics(sessionToken);
            return ResponseEntity.ok(analytics);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 