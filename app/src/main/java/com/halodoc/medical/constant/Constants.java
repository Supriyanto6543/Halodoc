package com.halodoc.medical.constant;

/**
 * Created by Supriyanto on 7/27/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class Constants {

    //public static String ROOT_URL = "http://halodoc.go-cow.com/chat/";
    public static String ROOT_URL = "http://192.168.43.210/halodoc/chat/";
    public static String URL_LOGIN = ROOT_URL + "login.php";
    public static String URL_USUARIOS = ROOT_URL + "listdokter.php";
    public static String URL_ENVIAR_MENSAJE = ROOT_URL + "insertarMensaje.php";
    public static String URL_ENVIAR_MENSAJE_GRUPAL = ROOT_URL + "insertarMensajeGrupal.php";
    public static String URL_OBTENER_MENSAJES = ROOT_URL + "obtenerMensajes.php";
    public static String URL_OBTENER_MENSAJES_GRUPAL = ROOT_URL + "obtenerMensajesGrupales.php";
    public static String URL_INSERT_CART = ROOT_URL + "insertcart.php";
    public static String URL_HISTORY_CART = ROOT_URL + "history.php";
    public static String URL_CHECK_CART = ROOT_URL + "checkcart.php";
    public static String URL_UPDATE_CART = ROOT_URL + "updatecart.php";

    public static String UNIQUE_ID = "?unique_id=";
    public static String USUARIODESTINO = "&usuarioDestino=";

    public static final String GOOGLE_ACCOUNT = "google_account";
    //public static final String ROOT = "http://halodoc.go-cow.com/";
    public static final String ROOT = "http://192.168.43.210/halodoc/";
    public static final String FIELD = ROOT + "field/";
    public static final String URL_DOKTER_HOME = ROOT + "api.php?dokter_home&";

    public static final String JSON_ROOT = "HALODOC";
    public static final String API = "api.php?";
    public static final String CART_FIELD = "cart=";
    public static final String ID_USER_CART = "?id_user_cart=";
    public static final String ID_USER_CARTS = "&id_user_cart=";
    public static final String CHECK_CART = "?check_product=";
    public static final String ID_PRODUCT_CART = "&id_product_cart=";
    public static final String QTY = "&qty=";
    public static final String BANK = ROOT + API + "bank";
    public static final String CART_TOTAL = ROOT + API + "cart_count=";
    public static final String CART_TOTAL_COUNT = ROOT + API + "cart_total=";
    public static final String  CART_PRODUCT = "&cart_product=";

    public static final String PRODUCT_FIELD_DETAIL = "detail_product=";
    public static final String PRODUCT_FIELD = "product";
    public static final String BANK_FIELD = "bank";

    public static final String PRODUCT_CARTS = "cart";
    public static final String PRODUCT_DELETE_CARTS = "delete_cart=";
    public static final String NEWS_FIELD = "news";
    public static final String CATEGORY_FIELD = "category";

    public static final String PRODUCT_DETAIL = ROOT + API + PRODUCT_FIELD_DETAIL;
    public static final String PRODUCT = ROOT + API + PRODUCT_FIELD;
    public static final String NEWS = ROOT + API + NEWS_FIELD;
    public static final String CATEGORY = ROOT + API + CATEGORY_FIELD;
    public static final String CART = ROOT + API + CART_FIELD;
    public static final String DELETE_CART = ROOT + API + PRODUCT_DELETE_CARTS;

    public static final String REGISTER = FIELD + "register.php";
    public static final String CHECK_LOGIN = FIELD + "check_login.php";

    public static final String EMAIL = "?email=";

}
