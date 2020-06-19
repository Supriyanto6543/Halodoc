package com.halodoc.medical;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityDompet extends AppCompatActivity {

    Toolbar toolbar;
    EditText edt_dompet;
    TextView saldo, saldo1, saldo2, saldo3;
    Button chat;
    String saldo_add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dompet);

        toolbar = findViewById(R.id.toolbar);
        edt_dompet = findViewById(R.id.edt_dompet);
        saldo = findViewById(R.id.saldo);
        saldo1 = findViewById(R.id.saldo1);
        saldo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Rp 50.000 ditambahkan", Toast.LENGTH_LONG).show();
            }
        });
        saldo2 = findViewById(R.id.saldo2);
        saldo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Rp 100.000 ditambahkan", Toast.LENGTH_LONG).show();
            }
        });
        saldo3 = findViewById(R.id.saldo3);
        saldo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Rp 150.000 ditambahkan", Toast.LENGTH_LONG).show();
            }
        });
        chat = findViewById(R.id.chat);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saldo_add = edt_dompet.getText().toString();

                saldo.setText("Rp " + saldo_add);


            }
        });


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dompet Saya");
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
