package com.halodoc.medical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
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
import com.halodoc.medical.adapter.AdapterBank;
import com.halodoc.medical.constant.Constants;
import com.halodoc.medical.modal.ModalBank;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Supriyanto on 9/5/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class ActivityTransfer extends AppCompatActivity {

    Toolbar toolbar;
    RequestQueue queue;
    RecyclerView rv_transfer;
    AdapterBank adapterBank;
    ArrayList<ModalBank> modalBanks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        queue = Volley.newRequestQueue(getApplicationContext());
        rv_transfer = findViewById(R.id.rv_transfer);
        toolbar = findViewById(R.id.toolbar);

        modalBanks = new ArrayList<>();

        rv_transfer.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //getBank();
    }

    private void getBank(){
        Log.d("BAMBAN", Constants.BANK);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.BANK, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject object = response.getJSONObject(Constants.JSON_ROOT);
                    JSONArray array = object.getJSONArray(Constants.BANK_FIELD);

                    for (int i = 0; i < array.length(); i++){

                        JSONObject object1 = array.getJSONObject(i);
                        String id = object1.getString("id_bank");
                        String image_bank = object1.getString("image_bank");
                        String name_bank = object1.getString("name_bank");
                        String name_bank_account = object1.getString("name_bank_account");
                        String bank_number = object1.getString("bank_number");

                        ModalBank modalBank = new ModalBank(id, image_bank, name_bank, bank_number, name_bank_account);
                        modalBanks.add(modalBank);

                        adapterBank = new AdapterBank(modalBanks, getApplicationContext());

                        rv_transfer.setAdapter(adapterBank);

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
        startActivity(new Intent(ActivityTransfer.this, MainActivity.class));
        finish();
    }
}
