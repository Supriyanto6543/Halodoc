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

import com.halodoc.medical.adapter.AdapterNews;
import com.halodoc.medical.modal.NewsModal;

import java.util.ArrayList;

public class ActivityDetailTag extends AppCompatActivity {

    Toolbar toolbar;
    ArrayList<NewsModal> nm;
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

    private ArrayList<NewsModal> addingItemNews(ArrayList<NewsModal> newsModals){

        newsModals.add(new NewsModal(R.drawable.banner3, "Doctor", "Berotabat Langsung ke Dokter Apabila Sakit"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Kesehatan", "Jangan Lupa jaga Kesehatan di Pandemi ini"));
        newsModals.add(new NewsModal(R.drawable.banner2, "Makanan", "Makan dan Minum yang Membuat Tubuh Menjadi Sehat"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Berobat", "Jangan Lupa Meminum Obat atau Resep dari Dokter"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Protein", "Protein adalah Sumber Kekuatan Bagi Manusia"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Doctor", "Berotabat Langsung ke Dokter Apabila Sakit"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Kesehatan", "Jangan Lupa jaga Kesehatan di Pandemi ini"));
        newsModals.add(new NewsModal(R.drawable.banner2, "Makanan", "Makan dan Minum yang Membuat Tubuh Menjadi Sehat"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Berobat", "Jangan Lupa Meminum Obat atau Resep dari Dokter"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Protein", "Protein adalah Sumber Kekuatan Bagi Manusia"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Doctor", "Berotabat Langsung ke Dokter Apabila Sakit"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Kesehatan", "Jangan Lupa jaga Kesehatan di Pandemi ini"));
        newsModals.add(new NewsModal(R.drawable.banner2, "Makanan", "Makan dan Minum yang Membuat Tubuh Menjadi Sehat"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Berobat", "Jangan Lupa Meminum Obat atau Resep dari Dokter"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Protein", "Protein adalah Sumber Kekuatan Bagi Manusia"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Doctor", "Berotabat Langsung ke Dokter Apabila Sakit"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Kesehatan", "Jangan Lupa jaga Kesehatan di Pandemi ini"));
        newsModals.add(new NewsModal(R.drawable.banner2, "Makanan", "Makan dan Minum yang Membuat Tubuh Menjadi Sehat"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Berobat", "Jangan Lupa Meminum Obat atau Resep dari Dokter"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Protein", "Protein adalah Sumber Kekuatan Bagi Manusia"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Doctor", "Berotabat Langsung ke Dokter Apabila Sakit"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Kesehatan", "Jangan Lupa jaga Kesehatan di Pandemi ini"));
        newsModals.add(new NewsModal(R.drawable.banner2, "Makanan", "Makan dan Minum yang Membuat Tubuh Menjadi Sehat"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Berobat", "Jangan Lupa Meminum Obat atau Resep dari Dokter"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Protein", "Protein adalah Sumber Kekuatan Bagi Manusia"));

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
