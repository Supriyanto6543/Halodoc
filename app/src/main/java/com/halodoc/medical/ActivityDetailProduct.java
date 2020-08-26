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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.halodoc.medical.adapter.AdapterNews;
import com.halodoc.medical.constant.Constants;
import com.halodoc.medical.modal.ProductModal;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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
    TextView title, tv_qty, count;
    ImageView image, add, remove;
    int id_product, qty = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        queue = Volley.newRequestQueue(getApplicationContext());

        id_product = getIntent().getIntExtra("id_product", 0);

        rl_cart = findViewById(R.id.rl_cart);
        count = findViewById(R.id.count);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        tv_qty = findViewById(R.id.tv_qty);
        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
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
                id_product = getIntent().getIntExtra("id_product", 0);
                Intent intent = new Intent(ActivityDetailProduct.this, ActivityCart.class);
                intent.putExtra("id_product", id_product);
                startActivity(intent);
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

    private void getProduct(final TextView title, final ImageView imageView, final WebView webView, int id){
        Log.d("ANWAR", Constants.PRODUCT_DETAIL+id);
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
