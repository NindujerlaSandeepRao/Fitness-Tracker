package com.project.fitness.controller;


import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.models.Activity;
import com.project.fitness.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

//    @PostMapping("/activities")
//    public ResponseEntity<ActivityResponse> TrackActivity(@RequestBody ActivityRequest activityRequest){
//        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
//    }

//    @GetMapping("/activities")
//    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader(value = "X-User-ID") String userId){
//        return ResponseEntity.ok(activityService.getUserActivities(userId));
//    }
    @PostMapping("/activities")
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest) {

    String userId = (String) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

    return ResponseEntity.ok(activityService.trackActivity(activityRequest, userId));
}

    @GetMapping("/activities")
    public ResponseEntity<List<ActivityResponse>> getUserActivities() {

        String userId = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }
}
