package com.halodoc.medical;

import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat Dokter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
//        getSupportActionBar().setTitle(usuario.getNombre());
//        myName = usuario.getUsuario();
//
        rvChats = findViewById(R.id.rvChats);
        rvChats.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
//
        obtenerChats();

    }

    public void obtenerChats() {
        listaUsuarios.clear();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constants.URL_USUARIOS + "?usuario=s", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("usuarios");

                    for(int i = 0 ; i < jsonArray.length() ; i++) {
                        JSONObject objeto = jsonArray.getJSONObject(i);

                        Usuario usuario = new Usuario(
                                objeto.getString("usuario"),
                                objeto.getString("contrasena"),
                                objeto.getString("nombre")
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
                Log.d("SUPRI", error.getMessage());
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
}
