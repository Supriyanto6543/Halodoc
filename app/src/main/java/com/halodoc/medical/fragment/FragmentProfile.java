package com.halodoc.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.halodoc.medical.LoginGmail;
import com.halodoc.medical.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static com.halodoc.medical.constant.Constants.GOOGLE_ACCOUNT;

public class FragmentProfile extends Fragment {

    Button chat;
    Toolbar toolbar;
    GoogleSignInAccount googleSignIn;
    GoogleSignInClient googleSignInClient;
    TextView email, name;
    ImageView imageView;
    Button login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lengkapi Profile");

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_google))
                .requestEmail()
                .build();

        googleSignIn = GoogleSignIn.getLastSignedInAccount(getActivity());
        googleSignInClient = GoogleSignIn.getClient(getActivity(), googleSignInOptions);

        imageView = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        try{
            Picasso.get().load(Objects.requireNonNull(googleSignIn).getPhotoUrl()).into(imageView);
            Log.d("jujuk", googleSignIn.getPhotoUrl() + googleSignIn.getDisplayName() + googleSignIn.getEmail());
            name.setText(googleSignIn.getDisplayName());
            email.setText(googleSignIn.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

}
