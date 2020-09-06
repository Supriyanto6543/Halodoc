package com.halodoc.medical.modal;

import java.io.Serializable;

/**
 * Created by Supriyanto on 9/5/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class ModalBank implements Serializable {

    private String id_bank;
    private String image_bank;
    private String name_bank;
    private String number_bank;
    private String account_bank;

    public ModalBank(String id_bank, String image_bank, String name_bank, String number_bank, String account_bank) {
        this.id_bank = id_bank;
        this.image_bank = image_bank;
        this.name_bank = name_bank;
        this.number_bank = number_bank;
        this.account_bank = account_bank;
    }

    public String getId_bank() {
        return id_bank;
    }

    public void setId_bank(String id_bank) {
        this.id_bank = id_bank;
    }

    public String getImage_bank() {
        return image_bank;
    }

    public void setImage_bank(String image_bank) {
        this.image_bank = image_bank;
    }

    public String getName_bank() {
        return name_bank;
    }

    public void setName_bank(String name_bank) {
        this.name_bank = name_bank;
    }

    public String getNumber_bank() {
        return number_bank;
    }

    public void setNumber_bank(String number_bank) {
        this.number_bank = number_bank;
    }

    public String getAccount_bank() {
        return account_bank;
    }

    public void setAccount_bank(String account_bank) {
        this.account_bank = account_bank;
    }
}
