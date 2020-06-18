package com.halodoc.medical.modal;

import java.io.Serializable;

public class DokterModal implements Serializable {

    private int image;
    private String title;
    private String job;
    private String price;
    private String rating;
    private String experience;

    public DokterModal(int image, String title, String job, String price, String rating, String experience) {
        this.image = image;
        this.title = title;
        this.job = job;
        this.price = price;
        this.rating = rating;
        this.experience = experience;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
