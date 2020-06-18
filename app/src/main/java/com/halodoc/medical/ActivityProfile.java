package com.halodoc.medical;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityProfile extends AppCompatActivity {

    Toolbar toolbar;
    TextView name, ttl, hp, email, berat, tiggi;
    String names, ttls, hps, emails, berats, tinggis;
    Button chat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar);
        chat = findViewById(R.id.chat);
        name = findViewById(R.id.name);
        ttl = findViewById(R.id.ttl);
        hp = findViewById(R.id.hp);
        email = findViewById(R.id.email);
        berat = findViewById(R.id.berat);
        tiggi = findViewById(R.id.tinggi);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names = name.getText().toString();
                ttls = ttl.getText().toString();
                hps = hp.getText().toString();
                emails = email.getText().toString();
                berats = berat.getText().toString();
                tinggis = tiggi.getText().toString();

                if (names.isEmpty() && ttls.isEmpty() && berats.isEmpty() &&  hps.isEmpty() && emails.isEmpty() && tinggis.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Silahkan lengkapi semua data", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "BERHASIL EDIT DATA", Toast.LENGTH_LONG).show();
                }
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lengkapi Profile");
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
