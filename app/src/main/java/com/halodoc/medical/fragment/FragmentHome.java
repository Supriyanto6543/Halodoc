package com.halodoc.medical.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.halodoc.medical.ActivityCategoryAll;
import com.halodoc.medical.ActivityChats;
import com.halodoc.medical.LoginGmail;
import com.halodoc.medical.R;
import com.halodoc.medical.adapter.AdapterCategory;
import com.halodoc.medical.adapter.AdapterChats;
import com.halodoc.medical.adapter.AdapterNews;
import com.halodoc.medical.adapter.AdapterSlider;
import com.halodoc.medical.constant.Constants;
import com.halodoc.medical.modal.CategoryModal;
import com.halodoc.medical.modal.ProductModal;
import com.halodoc.medical.modal.SliderModal;
import com.halodoc.medical.modal.TagModal;
import com.halodoc.medical.modal.Usuario;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class FragmentHome extends Fragment {

    ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    Usuario usuario;
    RecyclerView recyclerView, rv_doctor, recyclerView1;
    AdapterCategory adapterCategory;
    AdapterNews adapterNews;
    ArrayList<CategoryModal> cm;
    ArrayList<SliderModal> arrayList;
    ArrayList<TagModal> tm;
    ArrayList<ProductModal> productModals;
    AdapterSlider adapterSlider;
    TextView lihat_semua;
    FirebaseUser fu;
    FirebaseAuth auth;
    TextView email, doctor;
    SliderView imageSlider;
    RequestQueue queue;
    GoogleSignInAccount googleSignIn;
    GoogleSignInClient googleSignInClient;
    LinearLayout ll_login, ll_tv;
    Button btn_login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        queue = Volley.newRequestQueue(getActivity());
        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_google))
                .requestEmail()
                .build();

        googleSignIn = GoogleSignIn.getLastSignedInAccount(getActivity());
        googleSignInClient = GoogleSignIn.getClient(getActivity(), googleSignInOptions);

        initsCategory(view);
        initsNews(view);

        ll_tv = view.findViewById(R.id.ll_tv);
        ll_login = view.findViewById(R.id.ll_login);
        btn_login = view.findViewById(R.id.btn_login);
        imageSlider = view.findViewById(R.id.imageSlider);
        doctor = view.findViewById(R.id.doctor);
        arrayList = new ArrayList<>();
        getSlider();
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorSelectedColor(Color.WHITE);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        imageSlider.startAutoCycle();

        rv_doctor = view.findViewById(R.id.rv_doctor);
        lihat_semua = view.findViewById(R.id.lihat_semua);
        email = view.findViewById(R.id.email);

        rv_doctor.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        lihat_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCategoryAll.class);
                startActivity(intent);
            }
        });

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityChats.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginGmail.class));
                Log.d("MAIL", "MAIL");
            }
        });

        fu = auth.getCurrentUser();
        getProduct();

        if (googleSignIn != null){
            ll_tv.setVisibility(View.VISIBLE);
            rv_doctor.setVisibility(View.VISIBLE);
            ll_login.setVisibility(View.GONE);
            obtenerChats();
        }else{
            ll_tv.setVisibility(View.GONE);
            rv_doctor.setVisibility(View.GONE);
            ll_login.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void initsCategory(View view){
        recyclerView = view.findViewById(R.id.category);
        cm = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getCategory();
    }


    private void initsNews(View view){
        recyclerView1 = view.findViewById(R.id.recycler1);
        productModals = new ArrayList<>();
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }

    private void getSlider(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.NEWS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject object = response.getJSONObject(Constants.JSON_ROOT);
                    JSONArray array = object.getJSONArray(Constants.NEWS_FIELD);

                    for (int i = 0; i < array.length(); i++){
                        JSONObject object1 = array.getJSONObject(i);
                        String image_cat = object1.getString("image_cat");
                        SliderModal sm = new SliderModal(image_cat);
                        arrayList.add(sm);
                        adapterSlider = new AdapterSlider(getActivity(), arrayList);
                        imageSlider.setSliderAdapter(adapterSlider);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void getCategory(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.CATEGORY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject object = response.getJSONObject(Constants.JSON_ROOT);
                    JSONArray array = object.getJSONArray(Constants.CATEGORY_FIELD);

                    for (int i = 0; i < array.length(); i++){
                        JSONObject object1 = array.getJSONObject(i);

                        String id_cat = object1.getString("id_cat");
                        String name_cat = object1.getString("name_cat");
                        String image_cat = object1.getString("image_cat");

                       CategoryModal cmm = new CategoryModal(Integer.valueOf(id_cat), name_cat, image_cat);
                       cm.add(cmm);
                       adapterCategory = new AdapterCategory(cm, getActivity());
                       recyclerView.setAdapter(adapterCategory);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void obtenerChats() {
        listaUsuarios.clear();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constants.URL_DOKTER_HOME + Constants.UNIQUE_ID + googleSignIn.getId(), null, new Response.Listener<JSONObject>() {
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

                    AdapterChats adaptador = new AdapterChats(getActivity(), usuario, listaUsuarios);
                    rv_doctor.setAdapter(adaptador);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    private void getProduct(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.PRODUCT, null, new Response.Listener<JSONObject>() {
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
                        String deskripsi = object1.getString("deskripsi");
                        String price_product = object1.getString("price_product");
                        String discount = object1.getString("discount");

                        ProductModal pm = new ProductModal(Integer.parseInt(id_product), name_product, image_product, deskripsi, price_product, discount);
                        productModals.add(pm);
                        adapterNews = new AdapterNews(productModals, getActivity());
                        recyclerView1.setAdapter(adapterNews);
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
}
