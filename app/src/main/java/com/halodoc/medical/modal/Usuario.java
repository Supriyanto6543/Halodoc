package com.halodoc.medical.modal;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String usuario;
    private String contrasena;
    private String nombre;
    private String image;

    public Usuario(String usuario, String contrasena, String nombre, String image) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.image = image;
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
}
