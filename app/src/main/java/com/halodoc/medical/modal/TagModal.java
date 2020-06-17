package com.halodoc.medical.modal;

import java.io.Serializable;

public class TagModal implements Serializable {

    private String name;

    public TagModal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
