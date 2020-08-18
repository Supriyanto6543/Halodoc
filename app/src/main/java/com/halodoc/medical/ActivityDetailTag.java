package com.halodoc.medical;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.adapter.AdapterNews;
import com.halodoc.medical.modal.ProductModal;

import java.util.ArrayList;

public class ActivityDetailTag extends AppCompatActivity {

    Toolbar toolbar;
    ArrayList<ProductModal> nm;
    RecyclerView recyclerView;
    AdapterNews adapterNews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tag);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initsNews();

    }

    private void initsNews(){
        recyclerView = findViewById(R.id.recycler);
        nm = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        adapterNews = new AdapterNews(addingItemNews(nm), getApplicationContext());
        recyclerView.setAdapter(adapterNews);
    }

    private ArrayList<ProductModal> addingItemNews(ArrayList<ProductModal> newsModals){


        return newsModals;
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
