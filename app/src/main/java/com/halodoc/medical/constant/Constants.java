package com.halodoc.medical.constant;

/**
 * Created by Supriyanto on 7/27/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class Constants {

    public static String ROOT_URL = "http://192.168.2.53/EjemploChat/";
    public static String URL_LOGIN = ROOT_URL + "login.php";
    public static String URL_USUARIOS = ROOT_URL + "obtenerUsuarios.php";
    public static String URL_ENVIAR_MENSAJE = ROOT_URL + "insertarMensaje.php";
    public static String URL_ENVIAR_MENSAJE_GRUPAL = ROOT_URL + "insertarMensajeGrupal.php";
    public static String URL_OBTENER_MENSAJES = ROOT_URL + "obtenerMensajes.php";
    public static String URL_OBTENER_MENSAJES_GRUPAL = ROOT_URL + "obtenerMensajesGrupales.php";

    public static String USUARIO = "?usuario=";
    public static String USUARIODESTINO = "&usuarioDestino=";

    public static final String GOOGLE_ACCOUNT = "google_account";
    public static final String ROOT = "http://192.168.2.53/halodoc/";
    public static final String FIELD = ROOT + "field/";

    public static final String JSON_ROOT = "HALODOC";
    public static final String API = "api.php?";

    public static final String PRODUCT_FIELD = "product";
    public static final String NEWS_FIELD = "news";
    public static final String CATEGORY_FIELD = "category";

    public static final String PRODUCT = ROOT + API + PRODUCT_FIELD;
    public static final String NEWS = ROOT + API + NEWS_FIELD;
    public static final String CATEGORY = ROOT + API + CATEGORY_FIELD;

    public static final String REGISTER = FIELD + "register.php";
    public static final String CHECK_LOGIN = FIELD + "check_login.php";

    public static final String EMAIL = "?email=";

}
