package com.zuitt.coursebooking.controllers;

import com.zuitt.coursebooking.models.CourseEnrollment;
import com.zuitt.coursebooking.repositories.ICourseEnrollmentRepository;
import com.zuitt.coursebooking.repositories.ICourseRepository;
import com.zuitt.coursebooking.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
public class CourseEnrollmentController {

    @Autowired
    ICourseRepository courseRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ICourseEnrollmentRepository courseEnrollmentRepository;

    @GetMapping("/api/enrollments")
    public ResponseEntity<Object> getAllUser() {
        return new ResponseEntity<>(courseEnrollmentRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("api/courses/enrolled")
    public ResponseEntity<Object> isEnrolled(@RequestBody HashMap<String, Object> data) {

        HashMap <String, Object> response = new HashMap<>();

        int courseId = Integer.parseInt(data.get("courseId").toString());
        int userId = Integer.parseInt((data.get("userId").toString()));

        CourseEnrollment courseEnrollment =
                courseEnrollmentRepository.findPriorEnrollment(courseId, userId);

        response.put("result", Objects.requireNonNullElse(courseEnrollment, "not_enrolled"));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
