package com.halodoc.medical;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.adapter.AdapterCategory;
import com.halodoc.medical.modal.CategoryModal;

import java.util.ArrayList;

public class ActivityCategoryAll extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CategoryModal> cm;
    AdapterCategory adapterCategory;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_all);
        initsCategory();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void initsCategory(){
        recyclerView = findViewById(R.id.recycler);
        cm = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        adapterCategory = new AdapterCategory(addingItem(cm), getApplicationContext());
        recyclerView.setAdapter(adapterCategory);
    }

    private ArrayList<CategoryModal> addingItem(ArrayList<CategoryModal> modals){

        modals.add(new CategoryModal(R.drawable.nurse1, "Chat dengan Dokter", "Dokter terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse3, "Beli Obat", "Apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse4, "Tes Covid 19", "Dari apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Kesehatan Jiwa", "Temukan jawabanmu"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Chat dengan Dokter", "Dokter terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse3, "Beli Obat", "Apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse4, "Tes Covid 19", "Dari apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Kesehatan Jiwa", "Temukan jawabanmu"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Chat dengan Dokter", "Dokter terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse3, "Beli Obat", "Apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse4, "Tes Covid 19", "Dari apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Kesehatan Jiwa", "Temukan jawabanmu"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Chat dengan Dokter", "Dokter terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse3, "Beli Obat", "Apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse4, "Tes Covid 19", "Dari apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Kesehatan Jiwa", "Temukan jawabanmu"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Chat dengan Dokter", "Dokter terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse3, "Beli Obat", "Apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse4, "Tes Covid 19", "Dari apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Kesehatan Jiwa", "Temukan jawabanmu"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Chat dengan Dokter", "Dokter terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse3, "Beli Obat", "Apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse4, "Tes Covid 19", "Dari apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Kesehatan Jiwa", "Temukan jawabanmu"));

        return modals;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
