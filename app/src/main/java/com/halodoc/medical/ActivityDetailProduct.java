package com.halodoc.medical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.halodoc.medical.adapter.AdapterNews;
import com.halodoc.medical.constant.Constants;
import com.halodoc.medical.modal.ProductModal;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Supriyanto on 8/26/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class ActivityDetailProduct extends AppCompatActivity {

    Toolbar toolbar;
    AdapterNews adapterNews;
    ArrayList<ProductModal> productModals;
    RequestQueue queue;
    RelativeLayout rl_cart;
    WebView webView;
    TextView title, tv_qty, count, price;
    ImageView image, add, remove;
    int id_product, qty = 1;
    GoogleSignInAccount googleSignIn;
    GoogleSignInClient googleSignInClient;
    String getPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        queue = Volley.newRequestQueue(getApplicationContext());
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_google))
                .requestEmail()
                .build();

        googleSignIn = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), googleSignInOptions);

        id_product = getIntent().getIntExtra("id_product", 0);

        rl_cart = findViewById(R.id.rl_cart);
        count = findViewById(R.id.count);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        tv_qty = findViewById(R.id.tv_qty);
        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        webView = findViewById(R.id.webview);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getProduct(title, image, webView, id_product);

        rl_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleSignIn != null){
                    id_product = getIntent().getIntExtra("id_product", 0);
                    getPrice = price.getText().toString();
                    int total = Integer.valueOf(getPrice) * qty;
                    //sendCart(total, qty);
                    checkCart(id_product, total, qty);
                    Intent intent = new Intent(ActivityDetailProduct.this, ActivityCart.class);
                    intent.putExtra("id_product", id_product);
                    intent.putExtra("price", getPrice);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Kamu harus login dulu", Toast.LENGTH_LONG).show();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty == 9){
                    Toast.makeText(getApplicationContext(), "Maximum 9 pemesanan", Toast.LENGTH_LONG).show();
                    tv_qty.setText(qty + "");
                    count.setText(qty + "");
                }else{
                    qty++;
                    tv_qty.setText(qty + "");
                    count.setText(qty + "");
                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty == 1){
                    Toast.makeText(getApplicationContext(), "Minimum 1 pemesanan", Toast.LENGTH_LONG).show();
                    tv_qty.setText(qty + "");
                    count.setText(qty + "");
                }else{
                    qty--;
                    tv_qty.setText(qty + "");
                    count.setText(qty + "");
                }
            }
        });

    }

    private void checkCart(final int id_cart, final int total, final int qty){
        Log.d("SALASA", Constants.URL_CHECK_CART + Constants.CHECK_CART + id_cart + Constants.ID_USER_CARTS + googleSignIn.getId());
        StringRequest request = new StringRequest(Request.Method.POST, Constants.URL_CHECK_CART + Constants.CHECK_CART + id_cart + Constants.ID_USER_CARTS + googleSignIn.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("benar")){
                    Log.d("UPDATEDATA", "BENAR");
                    updateCart(id_cart, total, qty);
                }else{
                    Log.d("DATASALAH", "SALAH");
                    sendCart(total, qty);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ASSALAM", error.getMessage());
            }
        });
        queue.add(request);
    }

    private void updateCart(int id_cart, final int total, final int qty){
        Log.d("UPDATEBRO", Constants.URL_UPDATE_CART + Constants.ID_USER_CART + googleSignIn.getId() + Constants.ID_PRODUCT_CART+id_cart);
        StringRequest request = new StringRequest(Request.Method.POST, Constants.URL_UPDATE_CART + Constants.ID_USER_CART + googleSignIn.getId() + Constants.ID_PRODUCT_CART+id_cart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ROZI", "rozi");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ASSALAM", error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("harga", String.valueOf(total));
                map.put("qty", String.valueOf(qty));
                return map;
            }
        };
        queue.add(request);
    }

    private void sendCart(final int price, final int qty){
        StringRequest request = new StringRequest(Request.Method.POST, Constants.URL_INSERT_CART+Constants.ID_USER_CART+googleSignIn.getId()+Constants.ID_PRODUCT_CART+id_product, new Response.Listener<String>() {
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
                map.put("id_product_cart", String.valueOf(id_product));
                map.put("harga", String.valueOf(price));
                map.put("qty", String.valueOf(qty));
                return map;
            }
        };

        queue.add(request);
    }

    private void getProduct(final TextView title, final ImageView imageView, final WebView webView, int id){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.PRODUCT_DETAIL+id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject object = response.getJSONObject(Constants.JSON_ROOT);
                    JSONArray array = object.getJSONArray(Constants.PRODUCT_FIELD);

                    for (int i = 0; i < array.length(); i++){
                        JSONObject object1 = array.getJSONObject(i);
                        String id_product = object1.getString("id_product");
                        String name_product = object1.getString("name_product");
                        String image_product = object1.getString("image_product");
                        String descriptions = object1.getString("description");
                        String price_product = object1.getString("price_product");
                        String discount = object1.getString("discount");

                        Log.d("WEDOK", name_product + descriptions);

                        title.setText(name_product);
                        price.setText(price_product);
                        Picasso.get().load(image_product).into(imageView);
                        String html = "<style>img{display: inline; height: auto; max-width: 100%;}</style>";
                        webView.loadDataWithBaseURL("", html+descriptions, "text/html", "UTF-8", "");

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
}
