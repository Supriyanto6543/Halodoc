package com.halodoc.medical;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.halodoc.medical.constant.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.halodoc.medical.constant.Constants.EMAIL;
import static com.halodoc.medical.constant.Constants.GOOGLE_ACCOUNT;

/**
 * Created by Supriyanto on 7/27/2020.
 * Copyright (c) 2020. All rights reserved.
 */
public class LoginGmail extends AppCompatActivity {

    //Google login
    LinearLayout ll_google;
    GoogleSignInClient googleSignInClient;
    ImageView close;
    RequestQueue queue;
    GoogleSignInAccount gsia;
    Uri photoUrl;
    String tokens, username, unique_id, email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gmail);

        queue = Volley.newRequestQueue(getApplicationContext());
        close = findViewById(R.id.close);
        ll_google = findViewById(R.id.ll_google);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_google))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), googleSignInOptions);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ll_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == 101) {
                try {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    gsia = task.getResult(ApiException.class);
                    tokens = gsia.getIdToken();
                    username = gsia.getDisplayName();
                    email = gsia.getEmail();
                    unique_id = gsia.getId();
                    photoUrl = gsia.getPhotoUrl();
                    getData(unique_id, username, email, photoUrl, tokens, 0, gsia);
                } catch (ApiException api) {
                    api.printStackTrace();
                }
            }

    }

    private void loggedNews(GoogleSignInAccount signInAccount){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(GOOGLE_ACCOUNT, signInAccount);
        startActivity(intent);
        finish();
    }

    private void storeData(final String unique_id, final String username, final String email, final Uri photo, final String token, final int like_post, final GoogleSignInAccount signInAccount){
        StringRequest request = new StringRequest(Request.Method.POST, Constants.REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if(response.equals("1")){
                        loggedNews(signInAccount);
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("unique_id", unique_id);
                params.put("username", username);
                params.put("email", email);
                params.put("photo", String.valueOf(photo));
                params.put("token", token);
                params.put("status_user", String.valueOf(like_post));

                return params;
            }
        };
        queue.add(request);
    }

    private void getData(final String unique_id, final String username, final String email, final Uri photo, final String token, final int like_post, final GoogleSignInAccount gsias){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constants.CHECK_LOGIN + EMAIL + gsias.getEmail(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String emailEdt = gsias.getEmail();
                    String emailResponse = response.getString("email");

                    if (emailEdt.equals(emailResponse)) {
                        loggedNews(gsias);
                    } else {
                        storeData(unique_id, username, email, photo, token, like_post, gsias);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
            }
        });
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
