package com.halodoc.medical.modal;

import java.io.Serializable;

public class SliderModal implements Serializable {

    private int Image;

    public SliderModal(int image) {
        Image = image;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
