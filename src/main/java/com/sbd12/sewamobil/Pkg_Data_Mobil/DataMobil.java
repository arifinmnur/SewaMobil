/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Data_Mobil;

import com.sbd12.sewamobil.Pkg_Merk_Mobil.MerkMobil;
import com.sbd12.sewamobil.Pkg_Owner_Mobil.OwnerMobil;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author resas
 */
public class DataMobil {

    private String no_pol;
    private String id_merk_mobil;
    private String id_owner;
    private String nama_mobil;
    private String nama_ow;
    private double harga_perhari;
    private double harga_total;

    private String id_petugas_pengembalian;
    private String nama_petugas_pengembalian;

    private java.sql.Timestamp tglkembali;
    private double denda = 0.0;
    private String status;
    
    private MerkMobil merkMobil;
    private OwnerMobil ownerMobil;

    public MerkMobil getMerkMobil() {
        return merkMobil;
    }

    public void setMerkMobil(MerkMobil merkMobil) {
        this.merkMobil = merkMobil;
    }

    public OwnerMobil getOwnerMobil() {
        return ownerMobil;
    }

    public void setOwnerMobil(OwnerMobil ownerMobil) {
        this.ownerMobil = ownerMobil;
    }
    
    

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }
    
    
    public String getId_petugas_pengembalian() {
        return id_petugas_pengembalian;
    }

    public void setId_petugas_pengembalian(String id_petugas_pengembalian) {
        this.id_petugas_pengembalian = id_petugas_pengembalian;
    }

    public String getNama_petugas_pengembalian() {
        return nama_petugas_pengembalian;
    }

    public void setNama_petugas_pengembalian(String nama_petugas_pengembalian) {
        this.nama_petugas_pengembalian = nama_petugas_pengembalian;
    }

    public String getTglkembali() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String reportDate = "";
        if (tglkembali != null) {
            reportDate = df.format(tglkembali);

        }
        return reportDate;
    }

    public void setTglkembali(Timestamp tglkembali) {
        this.tglkembali = tglkembali;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getHarga_total() {
        return harga_total;
    }

    public void setHarga_total(double harga_total) {
        this.harga_total = harga_total;
    }

    public double getHarga_perhari() {
        return harga_perhari;
    }

    public void setHarga_perhari(double harga_perhari) {
        this.harga_perhari = harga_perhari;
    }

    public void setNo_pol(String nopol) {
        this.no_pol = nopol;
    }

    public String getNo_pol() {
        return no_pol;
    }

    public void setId_merk_mobil(String id_merk) {
        this.id_merk_mobil = id_merk;
    }

    public String getId_merk_mobil() {
        return id_merk_mobil;
    }

    public void setNama_mobil(String nama) {
        this.nama_mobil = nama;
    }

    public String getNama_mobil() {
        return nama_mobil;
    }

    public void setNamaow(String nama_owner) {
        this.nama_ow = nama_owner;
    }

    public String getNama_ow() {
        return nama_ow;
    }

    public void setId_owner(String id_owner) {
        this.id_owner = id_owner;
    }

    public String getId_owner() {
        return id_owner;
    }

}
