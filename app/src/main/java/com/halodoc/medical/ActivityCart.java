package com.halodoc.medical;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.halodoc.medical.adapter.AdapterCart;
import com.halodoc.medical.constant.Constants;
import com.halodoc.medical.interfaces.DeleteCart;
import com.halodoc.medical.modal.ModalCart;
import com.squareup.picasso.Picasso;
import com.xendit.AuthenticationCallback;
import com.xendit.Models.Authentication;
import com.xendit.Models.Card;
import com.xendit.Models.Token;
import com.xendit.Models.XenditError;
import com.xendit.TokenCallback;
import com.xendit.Xendit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Supriyanto on 8/26/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class ActivityCart extends AppCompatActivity {

    Toolbar toolbar;
    int id_product;
    RequestQueue queue;
    AdapterCart adapterCart;
    ArrayList<ModalCart> modalCarts;
    Button checkout;
    ModalCart modalCart;
    RecyclerView rv_cart;
    GoogleSignInAccount googleSignIn;
    GoogleSignInClient googleSignInClient;
    TextView msg, price, empty;
    PaymentGateway pg;
    Xendit xendit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.toolbar);
        checkout = findViewById(R.id.checkout);
        xendit = new Xendit(this, "a38f77ab285e5a69f66d671c4a86c2b06387f75d6598e8e8af07cfc4b3a99167");
        msg = findViewById(R.id.msg);
        price = findViewById(R.id.price);
        empty = findViewById(R.id.empty);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_google))
                .requestEmail()
                .build();

        googleSignIn = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), googleSignInOptions);

        modalCarts = new ArrayList<>();
        rv_cart = findViewById(R.id.rv_cart);

        queue = Volley.newRequestQueue(getApplicationContext());
        id_product = getIntent().getIntExtra("id_product", 0);

        rv_cart.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (modalCarts.size() > 0){
                    SenderAgents senderAgents = new SenderAgents(googleSignIn.getEmail(), "Konfirmasi Detail Pembayaran", "SILAHKAN TRANSFER KE DETAIL BANK DIBAWAH INI: " + "\n" + "BANK NAME: BCA " + "\n" + "BANK ACCOUNT: ADE ERDIN " + "\n" + "BANK NUMBER: 0928817371937832", ActivityCart.this);
                    senderAgents.execute();
                    history();
                }else{
                    Toast.makeText(getApplicationContext(), "Keranjang Anda Kosong", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (googleSignIn != null){
            getProduct();
            getTotal();
        }else{
            msg.setVisibility(View.VISIBLE);
        }

    }

    private void history(){
        StringRequest request = new StringRequest(Request.Method.POST, Constants.URL_HISTORY_CART+Constants.ID_USER_CART+googleSignIn.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("BINGUNG", response + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("WONGS", error.getMessage() + "");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_user_cart", String.valueOf(googleSignIn.getId()));
                return map;
            }
        };

        queue.add(request);
    }

    private void getProduct(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.CART+googleSignIn.getId(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject object = response.getJSONObject(Constants.JSON_ROOT);
                    JSONArray array = object.getJSONArray(Constants.PRODUCT_CARTS);

                    for (int i = 0; i < array.length(); i++){
                        JSONObject object1 = array.getJSONObject(i);
                        String id = object1.getString("id_cart");
                        String user_cart = object1.getString("id_user_cart");
                        String image_product = object1.getString("image_product");
                        String id_product_cart = object1.getString("id_product_cart");
                        String date_cart = object1.getString("date_cart");
                        String name_product = object1.getString("name_product");
                        String harga = object1.getString("harga");
                        String qty = object1.getString("qty");
                        String discount = object1.getString("discount");

                        Log.d("PUPUS", "ID: " + id + " USER: " + user_cart + " ID PRODU: " + id_product_cart + " HARGA: " + harga + " QTY " + qty + "\n");

                        modalCart = new ModalCart(id, user_cart, id_product_cart, date_cart, name_product, harga, image_product, qty);
                        modalCarts.add(modalCart);

                        adapterCart = new AdapterCart(getApplicationContext(), modalCarts, new DeleteCart() {
                            @Override
                            public void cartDelete(String position) {
                                deleteCart(position);
                                recreate();
                            }
                        });
                        rv_cart.setAdapter(adapterCart);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void getTotal(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.CART_TOTAL_COUNT+googleSignIn.getId(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String total = response.getString("total");
                    try{
                        int cv_total = Integer.parseInt(total + "");
                        if (cv_total > 0){
                            price.setVisibility(View.VISIBLE);
                            checkout.setVisibility(View.VISIBLE);
                            price.setText("Total Rp: " + total);
                            checkout.setVisibility(View.VISIBLE);
                        }else{
                            empty.setVisibility(View.VISIBLE);
                            price.setVisibility(View.GONE);
                            checkout.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        Log.d("SUPRI", e.getMessage() + "");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void deleteCart(String delete){
        StringRequest request = new StringRequest(Request.Method.POST, Constants.DELETE_CART+delete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("FAJAR", response + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("irfan", error.getMessage() + "");
            }
        });
        queue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class SenderAgents extends AsyncTask<Void,Void, Void> {

        private String mail;
        private String subject;
        private String message;

        private Context context;
        private Session session;

        private ProgressDialog progressDialog;
        RequestQueue queue;

        public SenderAgents(String mail, String subject, String message, Context context) {
            queue = Volley.newRequestQueue(context);
            this.mail = mail;
            this.subject = subject;
            this.message = message;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(context, "Please wait. . .", "", false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Properties props = new Properties();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("supriyanto6543@gmail.com", "4kubocahsonggo123@");
                }
            });

            try{

                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress("supriyanto6543@gmail.com"));
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(String.valueOf(message));
                Transport.send(mimeMessage);

            }catch (MessagingException m){
                m.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            context.startActivity(new Intent(context, ActivityTransfer.class));
        }
    }
}
