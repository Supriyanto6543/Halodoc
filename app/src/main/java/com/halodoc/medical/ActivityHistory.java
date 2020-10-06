package com.halodoc.medical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.halodoc.medical.adapter.AdapterCart;
import com.halodoc.medical.adapter.AdapterHistory;
import com.halodoc.medical.constant.Constants;
import com.halodoc.medical.interfaces.DeleteCart;
import com.halodoc.medical.modal.ModalCart;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Supriyanto on 10/5/2020.
 * Copyright (c) 2020 . All rights reserved.
 */
public class ActivityHistory extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv_history;
    AdapterHistory adapterHistory;
    RequestQueue queue;
    ModalCart modalCart;
    ArrayList<ModalCart> modalCarts;
    GoogleSignInAccount googleSignIn;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_google))
                .requestEmail()
                .build();

        googleSignIn = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), googleSignInOptions);

        modalCarts = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
        rv_history = findViewById(R.id.rv_history);
        queue = Volley.newRequestQueue(getApplicationContext());
        rv_history.setLayoutManager(new GridLayoutManager(this, 1));

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Dokter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getHistory();

    }

    private void getHistory(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.HISTORY+googleSignIn.getId(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject object = response.getJSONObject(Constants.JSON_ROOT);
                    JSONArray array = object.getJSONArray(Constants.PRODUCT_CARTS);

                    for (int i = 0; i < array.length(); i++){
                        JSONObject object1 = array.getJSONObject(i);
                        String id = object1.getString("id_history");
                        String user_cart = object1.getString("id_user_history");
                        String image_product = object1.getString("image_product");
                        String id_product_cart = object1.getString("id_product_history");
                        String date_cart = object1.getString("date_history");
                        String name_product = object1.getString("name_product");
                        String harga = object1.getString("harga_history");
                        String qty = object1.getString("qty_history");

                        Log.d("PUPUS", "ID: " + id + " USER: " + user_cart + " ID PRODU: " + id_product_cart + " HARGA: " + harga + " QTY " + qty + "\n");

                        modalCart = new ModalCart(id, user_cart, id_product_cart, date_cart, name_product, harga, image_product, qty);
                        modalCarts.add(modalCart);

                        adapterHistory = new AdapterHistory(getApplicationContext(), modalCarts, new DeleteCart() {
                            @Override
                            public void cartDelete(String position) {
                            }
                        });
                        rv_history.setAdapter(adapterHistory);
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
    public void onBackPressed() {
        super.onBackPressed();
    }

}
