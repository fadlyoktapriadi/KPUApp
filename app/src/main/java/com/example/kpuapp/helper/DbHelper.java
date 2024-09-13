package com.example.kpuapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 2;
    static final String DATABASE_NAME = "kpuappxx.db";
    public static final String TABLE_SQLite = "sqlite";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NIK = "nik";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_NOHP = "nohp";
    public static final String COLUMN_JK = "jk";
    public static final String COLUMN_TANGGAL = "tanggal";
    public static final String COLUMN_ALAMAT = "alamat";


    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_NIK + " TEXT NOT NULL, " + COLUMN_NAMA + " TEXT NOT NULL, " + COLUMN_NOHP + " " +
                "TEXT NOT NULL, "+ COLUMN_JK + " TEXT NOT NULL," + COLUMN_TANGGAL + " TEXT NOT NULL," + COLUMN_ALAMAT + " TEXT NOT NULL"+ " )";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NIK, cursor.getString(1));
                map.put(COLUMN_NAMA, cursor.getString(2));
                map.put(COLUMN_NOHP, cursor.getString(3));
                map.put(COLUMN_JK, cursor.getString(4));
                map.put(COLUMN_TANGGAL, cursor.getString(5));
                map.put(COLUMN_ALAMAT, cursor.getString(6));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String nik, String nama, String nohp, String jk, String tanggal, String alamat){

        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (nik, nama, nohp, jk, tanggal, alamat) " + " VALUES ('" + nik
                + "', '" + nama + "', '" + nohp + "', '" + jk + "', '" + tanggal + "' , '" + alamat + "' )";
        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("delete sqlite ", "" + deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }
}
