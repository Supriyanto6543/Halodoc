package com.halodoc.medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class ActivityDokterDetail extends AppCompatActivity {

    Toolbar toolbar;
    Button chat;
    TextView title, child_title, rating, experience, praktik, no_str;
    ImageView image;
    String sTitle, sImage, sSuka, sPeng, sTemp, sNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_detail);

        toolbar = findViewById(R.id.toolbar);
        chat = findViewById(R.id.chat);
        title = findViewById(R.id.title);
        image = findViewById(R.id.image);
        praktik = findViewById(R.id.praktik);
        no_str = findViewById(R.id.no_str);
        rating = findViewById(R.id.rating);
        experience = findViewById(R.id.experience);
        child_title = findViewById(R.id.child_title);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityChats.class);
                startActivity(intent);
            }
        });

        sTitle = getIntent().getStringExtra("name");
        sImage = getIntent().getStringExtra("image");
        sSuka = getIntent().getStringExtra("suka");
        sPeng = getIntent().getStringExtra("pengetahuan");
        sTemp = getIntent().getStringExtra("temp_prak");
        sNo = getIntent().getStringExtra("no_str");

        title.setText(sTitle);
        child_title.setText(sTitle);
        rating.setText(sSuka);
        experience.setText(sPeng);
        praktik.setText(sTemp);
        no_str.setText(sNo);
        Picasso.get().load(sImage).into(image);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Dokter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
