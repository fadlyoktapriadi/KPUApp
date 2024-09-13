package com.example.kpuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_informasi = findViewById(R.id.btn_informasi);

        btn_informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HalamanInformasi.class);
                startActivity(intent);
            }
        });

        Button form_entry = findViewById(R.id.btn_form_entry);

        form_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormEntry.class);
                startActivity(intent);
            }
        });

        Button list_pemilih = findViewById(R.id.btn_lihat_data);
        list_pemilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListPemilih.class);
                startActivity(intent);
            }
        });

        Button btn_keluar = findViewById(R.id.btn_keluar);
        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }




}