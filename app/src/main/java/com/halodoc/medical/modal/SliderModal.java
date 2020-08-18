package com.halodoc.medical.modal;

import java.io.Serializable;

public class SliderModal implements Serializable {

    private String image;

    public SliderModal(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
