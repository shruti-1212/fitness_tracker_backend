package com.fitness.service;

import com.fitness.dto.GoalDTO;
import com.fitness.entity.User;
import com.fitness.entity.Goal;
import com.fitness.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {
    
    @Autowired
    private GoalRepository goalRepository;
    
    @Autowired
    private UserService userService;
    
    public Goal createGoal(String sessionToken, GoalDTO goalDTO) {
        User user = userService.getUserFromSession(sessionToken);
        
        Goal goal = new Goal();
        goal.setUser(user);
        goal.setTitle(goalDTO.getTitle());
        goal.setDescription(goalDTO.getDescription());
        goal.setGoalType(goalDTO.getGoalType());
        goal.setTargetValue(goalDTO.getTargetValue());
        goal.setUnit(goalDTO.getUnit());
        goal.setTargetDate(goalDTO.getTargetDate());
        
        return goalRepository.save(goal);
    }
    
    public List<Goal> getUserGoals(String sessionToken) {
        User user = userService.getUserFromSession(sessionToken);
        return goalRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
    }
    
    public List<Goal> getUserActiveGoals(String sessionToken) {
        User user = userService.getUserFromSession(sessionToken);
        return goalRepository.findByUserIdAndCompletedOrderByCreatedAtDesc(user.getId(), false);
    }
    
    public Goal updateGoalProgress(String sessionToken, Long goalId, Double currentValue) {
        User user = userService.getUserFromSession(sessionToken);
        
        Goal goal = goalRepository.findById(goalId)
            .orElseThrow(() -> new RuntimeException("Goal not found"));
        
        // Verify the goal belongs to the user
        if (!goal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Goal not found");
        }
        
        goal.setCurrentValue(currentValue);
        
        // Check if goal is completed
        if (currentValue >= goal.getTargetValue()) {
            goal.setCompleted(true);
        }
        
        return goalRepository.save(goal);
    }
} 