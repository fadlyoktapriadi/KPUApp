package com.example.kpuapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kpuapp.adapter.Adapter;
import com.example.kpuapp.helper.DbHelper;
import com.example.kpuapp.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListPemilih extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NIK = "nik";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_NOHP = "nohp";
    public static final String TAG_JK = "jk";
    public static final String TAG_TANGGAL = "tanggal";
    public static final String TAG_ALAMAT = "alamat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pemilih);

        SQLite = new DbHelper(getApplicationContext());

        ListView listView =  findViewById(R.id.list_view);

        adapter = new Adapter(this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getId();
                final String nik = itemList.get(position).getNik();
                final String nama = itemList.get(position).getNama();
                final String nohp = itemList.get(position).getNohp();
                final String jk = itemList.get(position).getJk();
                final String tanggal = itemList.get(position).getTanggal();
                final String alamat = itemList.get(position).getAlamat();


                final CharSequence[] dialogitem = {"Lihat Detail", "Hapus"};
                dialog = new AlertDialog.Builder(ListPemilih.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(ListPemilih.this, DetailPemilih.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NIK, nik);
                                intent.putExtra(TAG_NAMA, nama);
                                intent.putExtra(TAG_NOHP, nohp);
                                intent.putExtra(TAG_JK, jk);
                                intent.putExtra(TAG_TANGGAL, tanggal);
                                intent.putExtra(TAG_ALAMAT, alamat);
                                startActivity(intent);
                                break;
                            case 1:
                            SQLite.delete(Integer.parseInt(idx));
                            itemList.clear();
                            getAllData();
                            break;
                        }
                    }
                }).show();
                return false;

            }
        });
        getAllData();
    }

    private void getAllData(){
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++){
            String id = row.get(i).get(TAG_ID);
            String poster = row.get(i).get(TAG_NAMA);
            String title = row.get(i).get(TAG_NIK);

            String nohp = row.get(i).get(TAG_NOHP);
            String jk = row.get(i).get(TAG_JK);
            String tanggal = row.get(i).get(TAG_TANGGAL);
            String alamat = row.get(i).get(TAG_ALAMAT);

            Data data = new Data();

            data.setId(id);
            data.setNama(poster);
            data.setNik(title);
            data.setNohp(nohp);
            data.setJk(jk);
            data.setTanggal(tanggal);
            data.setAlamat(alamat);

            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        itemList.clear();
        getAllData();
    }
}