package com.fitness.repository;

import com.fitness.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    
    List<Goal> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    List<Goal> findByUserIdAndCompletedOrderByCreatedAtDesc(Long userId, boolean completed);
} 