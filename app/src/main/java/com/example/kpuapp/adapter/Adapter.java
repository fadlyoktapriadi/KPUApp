package com.example.kpuapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kpuapp.R;
import com.example.kpuapp.model.Data;

import java.util.List;


public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_row, null);
        }

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView nik = (TextView) convertView.findViewById(R.id.nik);

        Data data = items.get(position);

        id.setText(data.getId());
        nama.setText(data.getNama());
        nik.setText(data.getNik());

        return convertView;
    }

}
