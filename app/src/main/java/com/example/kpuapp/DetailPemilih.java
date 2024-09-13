package com.example.kpuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailPemilih extends AppCompatActivity {


    String id, nik, nama, nohp, jk, tanggal, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemilih);

        id = getIntent().getStringExtra(ListPemilih.TAG_ID);
        nik = getIntent().getStringExtra(ListPemilih.TAG_NIK);
        nama = getIntent().getStringExtra(ListPemilih.TAG_NAMA);
        nohp = getIntent().getStringExtra(ListPemilih.TAG_NOHP);
        jk = getIntent().getStringExtra(ListPemilih.TAG_JK);
        tanggal = getIntent().getStringExtra(ListPemilih.TAG_TANGGAL);
        alamat = getIntent().getStringExtra(ListPemilih.TAG_ALAMAT);


        TextView txx_nik = findViewById(R.id.txx_nik);
        TextView txx_nama = findViewById(R.id.txx_nama);
        TextView txx_nohp = findViewById(R.id.txx_nohp);
        TextView txx_jk = findViewById(R.id.txx_jk);
        TextView txx_tanggal = findViewById(R.id.txx_tanggal);
        TextView txx_alamat = findViewById(R.id.txx_alamat);


        txx_nik.setText(nik);
        txx_nama.setText(nama);
        txx_nohp.setText(nohp);
        txx_jk.setText(jk);
        txx_tanggal.setText(tanggal);
        txx_alamat.setText(alamat);


    }
}