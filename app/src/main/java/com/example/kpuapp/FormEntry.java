package com.example.kpuapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.kpuapp.helper.DbHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FormEntry extends AppCompatActivity {

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    RadioButton radioBtn;


    DbHelper SQLite = new DbHelper(this);

    // GPS
    FusedLocationProviderClient fusedLocationProviderClient;
    private  final  static int REQUEST_CODE=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_entry);


        // Calender Auto
        TextView dateTimeDisplay = findViewById(R.id.txt_tanggal);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        // GPS
        Button btn_cek_lokasi = findViewById(R.id.btn_cek_lokasi);


        btn_cek_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
            }
        });

        // Input Data
        Button btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDataPemilih();
            }
        });

    }

    private void inputDataPemilih() {
        TextView txt_nik = findViewById(R.id.txt_nik);
        TextView txt_nama = findViewById(R.id.txt_nama);
        TextView txt_nohp = findViewById(R.id.txt_nohp);
        RadioGroup radio_jk = findViewById(R.id.radio_jk);
        TextView txt_tanggal = findViewById(R.id.txt_tanggal);
        TextView txt_alamat = findViewById(R.id.txt_alamat);

        int radioId = radio_jk.getCheckedRadioButtonId();
        radioBtn = findViewById(radioId);

        if (String.valueOf(txt_nik.getText()).equals(null) || String.valueOf(txt_nik.getText()).equals("")
                || String.valueOf(txt_nama.getText()).equals(null) || String.valueOf(txt_nama.getText()).equals("")
                || String.valueOf(txt_nohp.getText()).equals(null) || String.valueOf(txt_nohp.getText()).equals("")
                || String.valueOf(radioBtn.getText()).equals(null) || String.valueOf(radioBtn.getText()).equals("")
                || String.valueOf(txt_tanggal.getText()).equals(null) || String.valueOf(txt_tanggal.getText()).equals("")
                || String.valueOf(txt_alamat.getText()).equals(null) || String.valueOf(txt_alamat.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan Lengkapi kolom yang kosong!", Toast.LENGTH_SHORT).show();
        }else{

                SQLite.insert(txt_nik.getText().toString().trim(), txt_nama.getText().toString().trim(),
                        txt_nohp.getText().toString().trim(),radioBtn.getText().toString().trim(),
                        txt_tanggal.getText().toString().trim(),
                        txt_alamat.getText().toString().trim());
                finish();
        }

    }

    private void getLastLocation() {
        TextView txt_alamat = findViewById(R.id.txt_alamat);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location !=null){
                                Geocoder geocoder=new Geocoder(FormEntry.this, Locale.getDefault());
                                List<Address> addresses= null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                    String gps = addresses.get(0).getAddressLine(0);
                                    txt_alamat.setText(gps);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
        }else
        {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(FormEntry.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getLastLocation();
            } else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }

    }
}