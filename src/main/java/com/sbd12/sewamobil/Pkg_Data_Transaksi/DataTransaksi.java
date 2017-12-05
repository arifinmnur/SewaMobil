package com.sbd12.sewamobil.Pkg_Data_Transaksi;

import java.sql.Date;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ArieDZ_2
 */
public class DataTransaksi {
   private String no_transaksi;
   private String id_kostumer;
   private String nama_kostumer;
   private String no_pol;
   private String nama_mobil;
   private String id_pegawai;
   private String nama_pegawai;
   private Date tglpinjam;
   private Date tglkembali;
   private double harga_total;

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public String getId_kostumer() {
        return id_kostumer;
    }

    public void setId_kostumer(String id_kostumer) {
        this.id_kostumer = id_kostumer;
    }

    public String getNama_kostumer() {
        return nama_kostumer;
    }

    public void setNama_kostumer(String nama_kostumer) {
        this.nama_kostumer = nama_kostumer;
    }

    public String getNo_pol() {
        return no_pol;
    }

    public void setNo_pol(String no_pol) {
        this.no_pol = no_pol;
    }

    public String getNama_mobil() {
        return nama_mobil;
    }

    public void setNama_mobil(String nama_mobil) {
        this.nama_mobil = nama_mobil;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public Date getTglpinjam() {
        return tglpinjam;
    }

    public void setTglpinjam(Date tglpinjam) {
        this.tglpinjam = tglpinjam;
    }

    public Date getTglkembali() {
        return tglkembali;
    }

    public void setTglkembali(Date tglkembali) {
        this.tglkembali = tglkembali;
    }

    public double getHarga_total() {
        return harga_total;
    }

    public void setHarga_total(double harga_total) {
        this.harga_total = harga_total;
    }
   
   
}
