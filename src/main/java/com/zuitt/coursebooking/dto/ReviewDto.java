package com.zuitt.coursebooking.dto;

import com.zuitt.coursebooking.models.Course;

import java.io.Serializable;

public class ReviewDto implements Serializable {

    private Integer id;

    private Integer courseId;

    private String name;

    private String description;

    private Double price;
    private Integer rating;

    public ReviewDto() {
    }

    public ReviewDto(Integer id, Integer courseId, String name, String description, Double price, Integer rating) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}