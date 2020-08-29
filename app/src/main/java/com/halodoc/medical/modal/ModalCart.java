package com.halodoc.medical.modal;

import java.io.Serializable;

/**
 * Created by Supriyanto on 8/26/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class ModalCart implements Serializable {

    private String id_cart;
    private String id_user_cart;
    private String id_product_cart;
    private String date_cart;
    private String title;
    private String amount;
    private String image;
    private String qty;

    public ModalCart(String id_cart, String id_user_cart, String id_product_cart, String date_cart, String title, String amount, String image, String qty) {
        this.id_cart = id_cart;
        this.id_user_cart = id_user_cart;
        this.id_product_cart = id_product_cart;
        this.date_cart = date_cart;
        this.title = title;
        this.amount = amount;
        this.image = image;
        this.qty = qty;
    }

    public String getId_cart() {
        return id_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
    }

    public String getId_user_cart() {
        return id_user_cart;
    }

    public void setId_user_cart(String id_user_cart) {
        this.id_user_cart = id_user_cart;
    }

    public String getId_product_cart() {
        return id_product_cart;
    }

    public void setId_product_cart(String id_product_cart) {
        this.id_product_cart = id_product_cart;
    }

    public String getDate_cart() {
        return date_cart;
    }

    public void setDate_cart(String date_cart) {
        this.date_cart = date_cart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
