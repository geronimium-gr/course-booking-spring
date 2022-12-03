package com.zuitt.coursebooking.dto;

import com.zuitt.coursebooking.models.Course;

import java.io.Serializable;

public class ReviewDto implements Serializable {

    private int id;

    private int courseId;

    private String name;

    private String description;

    private double rating;

    public ReviewDto() {
    }

    public ReviewDto(int id, int courseId, String name, String description, double rating) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}