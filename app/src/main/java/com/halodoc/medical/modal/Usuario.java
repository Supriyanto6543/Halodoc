package com.halodoc.medical.modal;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String usuario;
    private String contrasena;
    private String nombre;
    private String image;
    private String suka;
    private String pengetahuan;
    private String temp_praktik;
    private String no_str;

    public Usuario(String usuario, String contrasena, String nombre, String image, String suka, String pengetahuan, String temp_praktik, String no_str) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.image = image;
        this.suka = suka;
        this.pengetahuan = pengetahuan;
        this.temp_praktik = temp_praktik;
        this.no_str = no_str;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSuka() {
        return suka;
    }

    public void setSuka(String suka) {
        this.suka = suka;
    }

    public String getPengetahuan() {
        return pengetahuan;
    }

    public void setPengetahuan(String pengetahuan) {
        this.pengetahuan = pengetahuan;
    }

    public String getTemp_praktik() {
        return temp_praktik;
    }

    public void setTemp_praktik(String temp_praktik) {
        this.temp_praktik = temp_praktik;
    }

    public String getNo_str() {
        return no_str;
    }

    public void setNo_str(String no_str) {
        this.no_str = no_str;
    }
}
