package com.halodoc.medical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.halodoc.medical.adapter.AdapterChats;
import com.halodoc.medical.constant.Constants;
import com.halodoc.medical.modal.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ActivityChats extends AppCompatActivity {

    ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    RecyclerView rvChats;
    Usuario usuario;
    String myName;
    Toolbar toolbar;
    GoogleSignInAccount googleSignIn;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_google))
                .requestEmail()
                .build();

        googleSignIn = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), googleSignInOptions);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat Dokter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rvChats = findViewById(R.id.rvChats);
        rvChats.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
//
        if (googleSignIn != null){
            obtenerChats();
        }else{
            startActivity(new Intent(getApplicationContext(), LoginGmail.class));
        }

    }

    public void obtenerChats() {
        listaUsuarios.clear();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constants.URL_USUARIOS + Constants.UNIQUE_ID + googleSignIn.getId(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("usuarios");

                    for(int i = 0 ; i < jsonArray.length() ; i++) {
                        JSONObject objeto = jsonArray.getJSONObject(i);

                        usuario = new Usuario(
                                objeto.getString("unique_id"),
                                objeto.getString("email"),
                                objeto.getString("username"),
                                objeto.getString("photo")
                        );

                        listaUsuarios.add(usuario);
                    }

                    AdapterChats adaptador = new AdapterChats(ActivityChats.this, usuario, listaUsuarios);
                    rvChats.setAdapter(adaptador);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("usuario", usuario.getUsuario().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
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
