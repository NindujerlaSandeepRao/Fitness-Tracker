package com.project.fitness.service;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.models.Activity;
import com.project.fitness.models.Recommendation;
import com.project.fitness.models.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecommendationService {
    @Autowired
    private RecommendationRepository recommendationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;

    public Recommendation generateRecommendation(RecommendationRequest request) {
        User user=userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("User Not Found"+request.getUserId()));

        Activity activity =activityRepository.findById(request.getActivityId())
                .orElseThrow(()->new RuntimeException("Activity Not Found"+request.getUserId()));
        Recommendation recommendation=Recommendation.builder()
                .activity(activity)
                .user(user)
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .improvements(request.getImprovements())
                .build();
        return recommendationRepository.save(recommendation);
    }

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}
