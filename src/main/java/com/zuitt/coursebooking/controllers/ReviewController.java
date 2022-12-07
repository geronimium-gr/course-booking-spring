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

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
public class ReviewController extends AppController{

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

    /*
    * To view the ratings per course.
    * */
    @GetMapping("/api/reviews/ratings")
    public ResponseEntity<Object> getAllRatings() {
        return new ResponseEntity<>(reviewRepository.findRatings(), HttpStatus.OK);
    }

    /*
     * To view reviews per course
     * */
    @GetMapping("/api/reviews/{courseId}")
    public ResponseEntity<Object> getReviewsOfCourse(@PathVariable int courseId) {
        return new ResponseEntity<>(reviewRepository.findByCourse(courseId), HttpStatus.OK);
    }


    /*
    * To add a review
    * */
    @PostMapping("/api/reviews/{courseId}")
    public ResponseEntity<Object> add(
            @RequestBody Review review,
            @PathVariable int courseId) {
        HashMap<String, String> response = new HashMap<>();

//        For Adding
        Review newReview = new Review();
        newReview.setUser(review.getUser());

//        Is the User or Course Existing?
        User author = userRepository.findById(review.getUser().getId()).get();
        Course course = courseRepository.findById(courseId).get();

//        If User Enrollment or Review exists?
        CourseEnrollment priorEnrollment = courseEnrollmentRepository.findPriorEnrollment(courseId, review.getUser().getId());
        Review checkExistingReview = reviewRepository.findExistingReview(review.getUser().getId(), courseId);

        if (priorEnrollment != null) {
            if (checkExistingReview != null) {
//                Updates the review
                int reviewId = reviewRepository.findExistingReview(review.getUser().getId(), courseId).getId();
                Review updatedReview = reviewRepository.findById(reviewId).get();
                updatedReview.setUser(author);
                updatedReview.setCourse(course);
                updatedReview.setRating(review.getRating());
                updatedReview.setFeedback(review.getFeedback());
                updatedReview.setDatetimeCreated(LocalDateTime.now());

                reviewRepository.save(updatedReview);
                response.put("result", "updated_review");
            } else {
                newReview.setUser(author);
                newReview.setCourse(course);
                newReview.setRating(review.getRating());
                newReview.setFeedback(review.getFeedback());
                newReview.setDatetimeCreated(LocalDateTime.now());

                reviewRepository.save(newReview);
                response.put("result", "added");
            }
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
