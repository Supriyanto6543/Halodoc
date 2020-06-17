package com.halodoc.medical.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.R;
import com.halodoc.medical.adapter.AdapterCategory;
import com.halodoc.medical.modal.CategoryModal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class ActivityHome extends Fragment {

    RecyclerView recyclerView;
    AdapterCategory adapterCategory;
    ArrayList<CategoryModal> cm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        inits(view);

        return view;
    }

    private void inits(View view){
        recyclerView = view.findViewById(R.id.recycler);
        cm = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapterCategory = new AdapterCategory(addingItem(cm), getActivity());
        recyclerView.setAdapter(adapterCategory);
    }

    private ArrayList<CategoryModal> addingItem(ArrayList<CategoryModal> modals){

        modals.add(new CategoryModal(R.drawable.nurse1, "Chat dengan Dokter", "Dokter terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse3, "Beli Obat", "Apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse4, "Tes Covid 19", "Dari apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Kesehatan Jiwa", "Temukan jawabanmu"));

        return modals;
    }
}
