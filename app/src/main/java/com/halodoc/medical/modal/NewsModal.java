package com.halodoc.medical.modal;

import java.io.Serializable;

public class NewsModal implements Serializable {

    private int image;
    private String category;
    private String title;

    public NewsModal(int image, String category, String title) {
        this.image = image;
        this.category = category;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
