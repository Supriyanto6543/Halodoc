package com.halodoc.medical.modal;

import java.io.Serializable;

public class ProductModal implements Serializable {

    private int id_product;
    private String name_product, image_product, deskripsi, price_product, discount;

    public ProductModal(int id_product, String name_product, String image_product, String deskripsi, String price_product, String discount) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.image_product = image_product;
        this.deskripsi = deskripsi;
        this.price_product = price_product;
        this.discount = discount;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPrice_product() {
        return price_product;
    }

    public void setPrice_product(String price_product) {
        this.price_product = price_product;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
