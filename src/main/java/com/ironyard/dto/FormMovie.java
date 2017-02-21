package com.ironyard.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by jasonskipper on 2/14/17.
 */
public class FormMovie {
    private long id;
    private String name;
    private String description;
    private String category;
    private String mpaaRating;
    private double rating;
    private String posterUrl;
    private MultipartFile movieFile;

    public MultipartFile getMovieFile() {
        return movieFile;
    }

    public void setMovieFile(MultipartFile movieFile) {
        this.movieFile = movieFile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
