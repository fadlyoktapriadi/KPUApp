package com.example.kpuapp.model;

public class Data {
    private String id, nik, nama, nohp, jk, tanggal, alamat;

    public Data(){}

    public Data(String id, String nik, String nama, String nohp, String jk, String tanggal, String alamat){
        this.id = id;
        this.nik = nik;
        this.nama = nama;
        this.nohp = nohp;
        this.jk = jk;
        this.tanggal = tanggal;
        this.alamat = alamat;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setNik(String nik){
        this.nik = nik;
    }
    public String getNik(){
        return nik;
    }

    public void setNama(String nama){
        this.nama = nama;
    }
    public String getNama(){
        return nama;
    }

    public void setNohp(String nohp){
        this.nohp = nohp;
    }
    public String getNohp(){
        return nohp;
    }

    public void setJk(String jk){
        this.jk = jk;
    }
    public String getJk(){
        return jk;
    }

    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
    public String getTanggal(){
        return tanggal;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    public String getAlamat(){
        return alamat;
    }
}
