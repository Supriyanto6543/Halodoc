package com.halodoc.medical;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
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
import com.halodoc.medical.adapter.AdapterMensajes;
import com.halodoc.medical.constant.Constants;
import com.halodoc.medical.modal.Mensaje;
import com.halodoc.medical.modal.Usuario;
import com.halodoc.medical.services.ServiceChat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ChatEspecifico extends AppCompatActivity {

    Usuario usuario;
    ArrayList<Mensaje> listaMensajes = new ArrayList<Mensaje>();
    RecyclerView rvMensajes;
    EditText etTexto;
    Button btnEnviar;
    private static final String TAG = "BroadcastTest";
    private Intent intent;
    private Parcelable recyclerViewState;
    String detact_edt, s_usuario, s_usuarioDestino;
    LinearLayoutManagerWrapper wrapper;
    CustomLinearLayoutManager cllm;
    ImageView refresh;
    GoogleSignInAccount googleSignIn;
    GoogleSignInClient googleSignInClient;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_especifico);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pesan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_google))
                .requestEmail()
                .build();

        googleSignIn = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), googleSignInOptions);
        intent = new Intent(this, ServiceChat.class);
        s_usuario = getIntent().getStringExtra("usuario");
        s_usuarioDestino = getIntent().getStringExtra("usuarioDestino");

        rvMensajes = findViewById(R.id.rvMensajes);
        refresh = findViewById(R.id.refresh);

        etTexto = findViewById(R.id.etTexto);
        cllm = new CustomLinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvMensajes.setLayoutManager(new GridLayoutManager(this, 1));

        btnEnviar = findViewById(R.id.btnEnviar);
        chatRefresh();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detact_edt = etTexto.getText().toString();
                if(detact_edt.isEmpty()) {
                    Toast.makeText(ChatEspecifico.this, "Pesan tidak boleh kosong", Toast.LENGTH_LONG).show();
                } else {
                    enviarMensaje(detact_edt);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            chatRefresh();
                        }
                    }, 1000);
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerMensajes();
            }
        });
    }

    public void enviarMensaje(final String s) {
        Log.d("mensajesss", Constants.URL_ENVIAR_MENSAJE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ENVIAR_MENSAJE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        obtenerMensajes();
                        etTexto.setText("");
                        Log.d("bambang", response + "");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("errorse", error.getMessage() + "");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("usuario", googleSignIn.getDisplayName());
                parametros.put("usuarioDestino", s_usuarioDestino);
                parametros.put("mensaje", s);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void obtenerMensajes() {
        listaMensajes.clear();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constants.URL_OBTENER_MENSAJES+Constants.UNIQUE_ID +googleSignIn.getDisplayName()+ Constants.USUARIODESTINO+s_usuarioDestino, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("mensajes");

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
                    //rvMensajes.smoothScrollToPosition (listaMensajes.size());
//                            recyclerViewState = Objects.requireNonNull(rvMensajes.getLayoutManager()).onSaveInstanceState();
//                            Objects.requireNonNull(rvMensajes.getLayoutManager()).onRestoreInstanceState(recyclerViewState);
                    AdapterMensajes adaptador = new AdapterMensajes(ChatEspecifico.this, usuario, listaMensajes);
                    rvMensajes.setAdapter(adaptador);

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
                parametros.put("usuario", s_usuario);
                parametros.put("usuarioDestino", s_usuarioDestino);

                return parametros;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void chatRefresh() {
        listaMensajes.clear();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constants.URL_OBTENER_MENSAJES+Constants.UNIQUE_ID +googleSignIn.getDisplayName()+ Constants.USUARIODESTINO+s_usuarioDestino, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("mensajes");

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
//                            recyclerViewState = Objects.requireNonNull(rvMensajes.getLayoutManager()).onSaveInstanceState();
//                            Objects.requireNonNull(rvMensajes.getLayoutManager()).onRestoreInstanceState(recyclerViewState);
                    AdapterMensajes adaptador = new AdapterMensajes(ChatEspecifico.this, usuario, listaMensajes);
                    rvMensajes.setAdapter(adaptador);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            /*listaMensajes.clear();
            adaptador.notifyDataSetChanged();
            obtenerMensajes(queue);
            adaptador.notifyItemRangeInserted(0, listaMensajes.size());*/
        }
    };

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
    public void onResume() {
        super.onResume();
//        obtenerMensajes(queue);
//        startService(intent);
//        registerReceiver(broadcastReceiver, new IntentFilter(ServiceChat.BROADCAST_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
//        unregisterReceiver(broadcastReceiver);
//        stopService(intent);
    }

    public class LinearLayoutManagerWrapper extends LinearLayoutManager {

        public LinearLayoutManagerWrapper(Context context) {
            super(context);
        }

        public LinearLayoutManagerWrapper(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public LinearLayoutManagerWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    public class CustomLinearLayoutManager extends LinearLayoutManager {

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

            try {

                super.onLayoutChildren(recycler, state);

            } catch (IndexOutOfBoundsException e) {

                Log.e(TAG, "Inconsistency detected");
            }

        }

        public CustomLinearLayoutManager(Context context) {
            super(context);
        }

        public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }
    }
}
