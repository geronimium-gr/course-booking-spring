package com.zuitt.coursebooking.repositories;

import com.zuitt.coursebooking.models.Course;
import com.zuitt.coursebooking.models.Review;
import com.zuitt.coursebooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {
//    @Query(value = "SELECT * FROM reviews WHERE course_id = ?1 LIMIT 1", nativeQuery = true)
//    User findByCourse(String email);

    @Query(value = "SELECT * FROM reviews WHERE user_id = ?1 AND course_id = ?2", nativeQuery = true)
    Review findExistingReview(int userId, int courseId);
}
