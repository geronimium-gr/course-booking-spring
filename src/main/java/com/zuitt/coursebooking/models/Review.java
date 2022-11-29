package com.zuitt.coursebooking.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    // Sets the relationship of this property and the user_id column in the database to the primary key in the user model
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    // Sets the relationship of this property and the user_id column in the database to the primary key in the user model
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetimeCreated;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String feedback;

    public Review() {
    }

    public Review(User user, Course course, int rating, String feedback) {
        this.user = user;
        this.course = course;
        this.rating = rating;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(LocalDateTime datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
