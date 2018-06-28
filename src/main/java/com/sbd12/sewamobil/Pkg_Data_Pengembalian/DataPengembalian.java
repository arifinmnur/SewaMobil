package com.sbd12.sewamobil.Pkg_Data_Pengembalian;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.joda.time.DateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ArieDZ_2
 */
public class DataPengembalian {

    private String no_transaksi;
    private String nama_produsen;
    private String nama_kostumer;
    private String id_pegawai;
    private String nama_pegawai;
    private Date tglkembali_seharusnya;
    private int telat = 0;
    private double denda = 0;
    private java.sql.Timestamp tglpengembalian;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    public int getTelat() {
        return telat;
    }

    public void setTelat(int telat) {
        this.telat = telat;
    }

    public String getNama_produsen() {
        return nama_produsen;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }
    
    public void setNama_produsen(String nama_produsen) {
        this.nama_produsen = nama_produsen;
    }

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public String getNama_kostumer() {
        return nama_kostumer;
    }

    public void setNama_kostumer(String nama_kostumer) {
        this.nama_kostumer = nama_kostumer;
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

    public Date getTglkembali_seharusnya() {
        return tglkembali_seharusnya;
    }

    public void setTglkembali_seharusnya(Date tglkembali_seharusnya) {
        this.tglkembali_seharusnya = tglkembali_seharusnya;
    }

    public java.sql.Timestamp getTglpengembalian() {
        return tglpengembalian;
    }

    public void setTglpengembalian(java.sql.Timestamp tglpengembalian) {
        this.tglpengembalian = tglpengembalian;
    }

    public String getTglPengembalianString() {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String reportDate = df.format(tglpengembalian);

        return reportDate;
    }

}
