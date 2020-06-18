package com.halodoc.medical;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.adapter.AdapterDokter;
import com.halodoc.medical.modal.DokterModal;

import java.util.ArrayList;

public class ActivityDokter extends AppCompatActivity {

    Toolbar toolbar;
    ArrayList<DokterModal> dm;
    AdapterDokter adapterDokter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Dokter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initsDokter();

    }

    private void initsDokter(){
        recyclerView = findViewById(R.id.recycler);
        dm = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapterDokter = new AdapterDokter(addingItemDokter(dm), this);
        recyclerView.setAdapter(adapterDokter);
    }

    private ArrayList<DokterModal> addingItemDokter(ArrayList<DokterModal> dokter){

        dokter.add(new DokterModal(R.drawable.docotrel1, "Dr. Anneke Holly M.Kes", "Dokter Spesialis", "Rp 35000", "75%", "12 Tahun"));
        dokter.add(new DokterModal(R.drawable.doctorel2, "Dr. Valencia Julita Rana Sp.A", "Spesialis Anak", "Rp 45000", "70%", "4 Tahun"));
        dokter.add(new DokterModal(R.drawable.doctorel3, "Dr. Charles M.Sc., Sp.A", "Spesialis Anak", "Rp 50000", "69%", "7 Tahun"));
        dokter.add(new DokterModal(R.drawable.doctor4, "Dr. Andreas Steven Wibowo", "Dokter Umum", "Rp 85000", "55%", "6 Tahun"));
        dokter.add(new DokterModal(R.drawable.doctorel5, "Dr. Kristian Felix Andre", "Dokter Umum", "Rp 55000", "60%", "10 Tahun"));

        return dokter;
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
