package com.halodoc.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.halodoc.medical.ActivityBantuan;
import com.halodoc.medical.ActivityDompet;
import com.halodoc.medical.ActivityLangganan;
import com.halodoc.medical.ActivityPengaturan;
import com.halodoc.medical.R;

public class FragmentLainnya extends Fragment {

    Toolbar toolbar;
    RelativeLayout pengaturan, dompet, langganan, bantuan, pengingat, alamat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lainnya, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lainnya");

        dompet = view.findViewById(R.id.dompet);
        dompet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityDompet.class);
                startActivity(intent);
            }
        });
        langganan = view.findViewById(R.id.langganan);
        langganan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityLangganan.class);
                startActivity(intent);
            }
        });
        pengaturan = view.findViewById(R.id.pengaturan);
        pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityPengaturan.class);
                startActivity(intent);
            }
        });
        bantuan = view.findViewById(R.id.bantuan);
        bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityBantuan.class);
                startActivity(intent);
            }
        });
        pengingat = view.findViewById(R.id.pengingat);
        pengingat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Pengingat di klik", Toast.LENGTH_LONG).show();
            }
        });

        alamat = view.findViewById(R.id.alamat);
        alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Alamat di klik", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
