package com.project.fitness.service;

import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.models.Activity;
import com.project.fitness.models.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

//    public ActivityResponse trackActivity(ActivityRequest request, String userId) {
//        User user=userRepository.findById(request.getUserId())
//                .orElseThrow(()->new RuntimeException("Invalid user: "+request.getUserId()));
//        Activity activity=Activity.builder()
//                .user(user)
//                .type(request.getType())
//                .duration(request.getDuration())
//                .caloriesBurned(request.getCaloriesBurned())
//                .startTime(request.getStartTime())
//                .additionalMetrics(request.getAdditionalMetrics())
//                .build();
//        Activity savedActivity=activityRepository.save(activity);
//        return mapToResponse(savedActivity);
//    }
public ActivityResponse trackActivity(ActivityRequest request, String userId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Invalid user: " + userId));

    Activity activity = new Activity();
    activity.setUser(user);
    activity.setType(request.getType());
    activity.setAdditionalMetrics(request.getAdditionalMetrics());
    activity.setDuration(request.getDuration());
    activity.setCaloriesBurned(request.getCaloriesBurned());
    activity.setCreatedAt(LocalDateTime.now());
    activity.setUpdatedAt(LocalDateTime.now());

    Activity saved = activityRepository.save(activity);

    return mapToResponse(saved);
}

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse activityResponse=new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setType(activity.getType());
        activityResponse.setUserId(activity.getUser().getId());
        activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
        activityResponse.setDuration(activity.getDuration());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());
        activityResponse.setCreatedAt(activity.getCreatedAt());
        activityResponse.setUpdatedAt(activity.getUpdatedAt());

        return activityResponse;

    }

    public List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activityList=activityRepository.findByUserId(userId);
//        List<ActivityResponse> activityResponses=new ArrayList<>();
//        for(Activity activity:activityList){
//            ActivityResponse activityResponse=mapToResponse(activity);
//            activityResponses.add(activityResponse);
//        }
//        return  activityResponses;
        return activityList.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
}
