package com.halodoc.medical;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.toolbar);
        checkout = findViewById(R.id.checkout);

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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), ActivityTransfer.class));
                        finish();
                    }
                }, 1000);
            }
        });

        getProduct();

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
                        String qty = object1.getString("qty");
                        String discount = object1.getString("discount");

                        modalCart = new ModalCart(id, user_cart, id_product_cart, date_cart, name_product, discount, image_product, qty);
                        modalCarts.add(modalCart);

                        adapterCart = new AdapterCart(getApplicationContext(), modalCarts, new DeleteCart() {
                            @Override
                            public void cartDelete(String position) {
                                deleteCart(position);
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

    @Override
    public void recreate() {
        super.recreate();
        getProduct();
    }
}
