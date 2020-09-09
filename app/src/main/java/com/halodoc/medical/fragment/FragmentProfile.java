package com.halodoc.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.halodoc.medical.ActivityLangganan;
import com.halodoc.medical.LoginGmail;
import com.halodoc.medical.MainActivity;
import com.halodoc.medical.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static com.halodoc.medical.constant.Constants.GOOGLE_ACCOUNT;

public class FragmentProfile extends Fragment {

    Button chat;
    Toolbar toolbar;
    GoogleSignInAccount googleSignIn;
    GoogleSignInClient googleSignInClient;
    TextView email, name, tv_desc;
    ImageView imageView;
    LinearLayout login_field, logout_field, ll_langganan, ll_history;
    CardView cv, desc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_google))
                .requestEmail()
                .build();

        googleSignIn = GoogleSignIn.getLastSignedInAccount(getActivity());
        googleSignInClient = GoogleSignIn.getClient(getActivity(), googleSignInOptions);

        imageView = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        login_field = view.findViewById(R.id.login_field);
        logout_field = view.findViewById(R.id.logout_field);
        ll_langganan = view.findViewById(R.id.ll_langganan);
        ll_history = view.findViewById(R.id.ll_history);
        tv_desc = view.findViewById(R.id.tv_desc);
        cv = view.findViewById(R.id.cv_profile);
        desc = view.findViewById(R.id.desc);

        ll_langganan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityLangganan.class));
            }
        });

        if (googleSignIn != null){
            name.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            login_field.setVisibility(View.GONE);
            logout_field.setVisibility(View.VISIBLE);
            ll_history.setVisibility(View.VISIBLE);
            tv_desc.setVisibility(View.VISIBLE);
            ll_langganan.setVisibility(View.VISIBLE);
            cv.setVisibility(View.VISIBLE);
            desc.setVisibility(View.VISIBLE);
        }else{
            logout_field.setVisibility(View.GONE);
            login_field.setVisibility(View.VISIBLE);
            ll_langganan.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            ll_history.setVisibility(View.GONE);
            tv_desc.setVisibility(View.GONE);
            cv.setVisibility(View.GONE);
            desc.setVisibility(View.GONE);
        }

        try{
            Picasso.get().load(Objects.requireNonNull(googleSignIn).getPhotoUrl()).into(imageView);
            name.setText(googleSignIn.getDisplayName());
            email.setText(googleSignIn.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }

        login_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginGmail.class));
            }
        });

        logout_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent =  new Intent(getActivity(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
            }
        });

        return view;
    }

}
