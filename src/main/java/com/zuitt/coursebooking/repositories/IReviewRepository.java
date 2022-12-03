package com.zuitt.coursebooking.repositories;

import com.zuitt.coursebooking.dto.ReviewDto;
import com.zuitt.coursebooking.models.Course;
import com.zuitt.coursebooking.models.Review;
import com.zuitt.coursebooking.models.User;
import org.hibernate.annotations.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT * FROM reviews WHERE course_id = ?1", nativeQuery = true)
    List<Review> findByCourse(int courseId);

    @Query(value = "SELECT * FROM reviews WHERE user_id = ?1 AND course_id = ?2", nativeQuery = true)
    Review findExistingReview(int userId, int courseId);

    /*
    * Sample of SQL Native Query
    * */
//    @Query(value = "SELECT r.id, c.name, c.description, AVG(r.rating) " +
//            "FROM reviews r " +
//            "INNER JOIN courses c ON r.course_id = c.id GROUP BY c.name",
//            nativeQuery = true)
//    List<Object> findRatings();

    /*
    * Sample of JPQL (Java Persistence Query Language)
    * */
//    @Query(value = "SELECT r.id, c.name, c.description, AVG(r.rating) " +
//            "FROM Review r " +
//            "INNER JOIN Course c ON r.course.id = c.id GROUP BY c.name")
//    List<Object> findRatings();

    /*
    * Using NEW to create a new Instance of Review DTO
    * */
    @Query(value = "SELECT NEW com.zuitt.coursebooking.dto.ReviewDto(r.id, c.name, c.description, AVG(r.rating))" +
            "FROM Review r " +
            "INNER JOIN Course c ON r.course.id = c.id GROUP BY c.name")
    List<ReviewDto> findRatings();
}
