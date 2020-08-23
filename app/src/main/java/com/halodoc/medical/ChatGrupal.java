package com.halodoc.medical;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.halodoc.medical.adapter.AdapterMensajesGrupal;
import com.halodoc.medical.constant.Constants;
import com.halodoc.medical.modal.Mensaje;
import com.halodoc.medical.modal.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ChatGrupal extends AppCompatActivity {

    Usuario usuario;
    ArrayList<Mensaje> listaMensajes = new ArrayList<Mensaje>();
    RecyclerView rvMensajes;

    EditText etTexto;

    Button btnEnviar, btnRecargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_grupal);

        usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
        getSupportActionBar().setTitle(usuario.getNombre());

        rvMensajes = findViewById(R.id.rvMensajes);
        rvMensajes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        etTexto = findViewById(R.id.etTexto);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnRecargar = findViewById(R.id.btnRecargar);

        obtenerMensajesGrupal();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etTexto.getText().toString().isEmpty()) {
                    Toast.makeText(ChatGrupal.this, "Se te olvido escribir el mensaje.", Toast.LENGTH_LONG).show();
                } else {
                    enviarMensaje();
                }
            }
        });

        btnRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerMensajesGrupal();
            }
        });
    }

    public void obtenerMensajesGrupal() {
        listaMensajes.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_OBTENER_MENSAJES_GRUPAL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("mensajes");

                            for(int i = 0 ; i < jsonArray.length() ; i++) {
                                JSONObject objeto = jsonArray.getJSONObject(i);

                                Mensaje mensaje = new Mensaje(
                                        objeto.getString("id"),
                                        objeto.getString("message"),
                                        objeto.getString("userfrom"),
                                        objeto.getString("userdestination"),
                                        objeto.getString("chatdate")
                                );
                                listaMensajes.add(mensaje);
                            }

                            rvMensajes.smoothScrollToPosition (listaMensajes.size());
                            AdapterMensajesGrupal adaptador = new AdapterMensajesGrupal(ChatGrupal.this, usuario, listaMensajes);
                            rvMensajes.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(ChatGrupal.this, "ERROR EN LA CONEXIÓN", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor

                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("usuario", usuario.getUsuario());
                parametros.put("usuarioDestino", "TODOS");

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void enviarMensaje() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ENVIAR_MENSAJE_GRUPAL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                        Toast.makeText(ChatGrupal.this, response, Toast.LENGTH_LONG).show();
                        obtenerMensajesGrupal();
                        etTexto.setText("");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(ChatGrupal.this, "ERROR EN LA CONEXIÓN", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor

                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("usuario", usuario.getUsuario());
                parametros.put("usuarioDestino", "TODOS");
                parametros.put("mensaje", etTexto.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
