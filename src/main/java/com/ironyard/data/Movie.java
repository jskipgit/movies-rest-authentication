package com.ironyard.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ironyard.dto.FormMovie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jasonskipper on 2/6/17.
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Movie {

    @Id @GeneratedValue
    private long id;
    private String name;
    private String description;
    private String category;
    private String mpaaRating;
    private double rating;
    private String posterUrl;
    private String posterFileName;

    public Movie(){

    }
    public Movie(FormMovie dataFromForm){
        this.id = dataFromForm.getId();
        this.name = dataFromForm.getName();
        this.description = dataFromForm.getDescription();
        this.category = dataFromForm.getCategory();
        this.mpaaRating = dataFromForm.getMpaaRating();
        this.rating = dataFromForm.getRating();
        this.posterUrl = dataFromForm.getPosterUrl();
    }
    public Movie(FormMovie dataFromForm, String posterFileName){
        this.id = dataFromForm.getId();
        this.name = dataFromForm.getName();
        this.description = dataFromForm.getDescription();
        this.category = dataFromForm.getCategory();
        this.mpaaRating = dataFromForm.getMpaaRating();
        this.rating = dataFromForm.getRating();
        this.posterUrl = dataFromForm.getPosterUrl();
        this.posterFileName = posterFileName;
    }

    public String getPosterFileName() {
        return posterFileName;
    }

    public void setPosterFileName(String posterFileName) {
        this.posterFileName = posterFileName;
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
