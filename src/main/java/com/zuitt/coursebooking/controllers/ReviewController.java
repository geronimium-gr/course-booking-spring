package com.zuitt.coursebooking.controllers;

import com.zuitt.coursebooking.models.Course;
import com.zuitt.coursebooking.models.CourseEnrollment;
import com.zuitt.coursebooking.models.Review;
import com.zuitt.coursebooking.models.User;
import com.zuitt.coursebooking.repositories.ICourseEnrollmentRepository;
import com.zuitt.coursebooking.repositories.ICourseRepository;
import com.zuitt.coursebooking.repositories.IReviewRepository;
import com.zuitt.coursebooking.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
public class ReviewController{

    @Autowired
    private IReviewRepository reviewRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICourseEnrollmentRepository courseEnrollmentRepository;

    @GetMapping("/api/reviews")
    public ResponseEntity<Object> getAllReviews() {
        return new ResponseEntity<>(reviewRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/api/{courseId}/reviews")
    public ResponseEntity<Object> add(
            @RequestBody Review review,
            @PathVariable int courseId) {
        HashMap<String, String> response = new HashMap<>();

        Review newReview = new Review();
        newReview.setUser(review.getUser());
        User author = userRepository.findById(review.getUser().getId()).get();
        Course course = courseRepository.findById(courseId).get();

        CourseEnrollment priorEnrollment = courseEnrollmentRepository.findPriorEnrollment(courseId, review.getUser().getId());

        if (priorEnrollment != null) {
            newReview.setUser(author);
            newReview.setCourse(course);
            newReview.setRating(review.getRating());
            newReview.setFeedback(review.getFeedback());
            newReview.setDatetimeCreated(LocalDateTime.now());

            reviewRepository.save(newReview);
            response.put("result", "added");
        } else {
            response.put("result", "student not enrolled");
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<Object> archive(@PathVariable int id) {
        HashMap<String, String> response = new HashMap<>();

        reviewRepository.deleteById(id);
        response.put("result", "archived");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}