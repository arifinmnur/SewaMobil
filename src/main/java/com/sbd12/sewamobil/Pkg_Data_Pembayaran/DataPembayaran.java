package com.sbd12.sewamobil.Pkg_Data_Pembayaran;

import java.sql.Date;
import java.sql.Timestamp;
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
public class DataPembayaran {

    private String no_transaksi;
    private int id_pembayaran;
    private String jenis_pembayaran;
    private double jumlah;
    private java.sql.Timestamp tglpembayaran;
    private String keterangan;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    

    public String getTglTransaksiString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String reportDate = df.format(tglpembayaran);

        return reportDate;
    }

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public int getId_pembayaran() {
        return id_pembayaran;
    }

    public void setId_pembayaran(int id_pembayaran) {
        this.id_pembayaran = id_pembayaran;
    }

    public String getJenis_pembayaran() {
        return jenis_pembayaran;
    }

    public void setJenis_pembayaran(String jenis_pembayaran) {
        this.jenis_pembayaran = jenis_pembayaran;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public Timestamp getTglpembayaran() {
        return tglpembayaran;
    }

    public void setTglpembayaran(Timestamp tglpembayaran) {
        this.tglpembayaran = tglpembayaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
    
    

}
