package com.sbd12.sewamobil.Pkg_Kostumer;

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
public class Kostumer {

    private String id_kostumer;
    private String no_ktp;
    private String id_member;
    private String id_jenis_member;
    private String nama_jenis_member;
    private double diskon;
    private String nama_depan_k;
    private String nama_belakang_k;
    private String nama_lengkap_k;
    private String jenis_kelamin_k;
    private String alamat_k;
    private Date tanggal_lahir_k;
    private String no_telepon_k;
    private Date tanggal_join;
    private int umur;

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }
    
    public String getNama_jenis_member() {
        return nama_jenis_member;
    }

    public void setNama_jenis_member(String nama_jenis_member) {
        this.nama_jenis_member = nama_jenis_member;
    }
    
    

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getId_kostumer() {
        return id_kostumer;
    }

    public void setId_kostumer(String id_kostumer) {
        this.id_kostumer = id_kostumer;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getId_member() {
        return id_member;
    }

    public void setId_member(String id_member) {
        this.id_member = id_member;
    }

    public String getId_jenis_member() {
        return id_jenis_member;
    }

    public void setId_jenis_member(String id_jenis_member) {
        this.id_jenis_member = id_jenis_member;
    }

    public String getNama_depan_k() {
        return nama_depan_k;
    }

    public void setNama_depan_k(String nama_depan_k) {
        this.nama_depan_k = nama_depan_k;
    }

    public String getNama_belakang_k() {
        return nama_belakang_k;
    }

    public void setNama_belakang_k(String nama_belakang_k) {
        this.nama_belakang_k = nama_belakang_k;
    }

    public String getNama_lengkap_k() {
        return nama_depan_k + " " + nama_belakang_k;
    }

    public void setNama_lengkap_k(String nama_lengkap_k) {
        this.nama_lengkap_k = nama_lengkap_k;
    }

    public String getJenis_kelamin_k() {
        return jenis_kelamin_k;
    }

    public void setJenis_kelamin_k(String jenis_kelamin_k) {
        this.jenis_kelamin_k = jenis_kelamin_k;
    }

    public String getAlamat_k() {
        return alamat_k;
    }

    public void setAlamat_k(String alamat_k) {
        this.alamat_k = alamat_k;
    }

    public Date getTanggal_lahir_k() {
        return tanggal_lahir_k;
    }

    public void setTanggal_lahir_k(Date tanggal_lahir_k) {
        this.tanggal_lahir_k = tanggal_lahir_k;
    }

    public String getNo_telepon_k() {
        return no_telepon_k;
    }

    public void setNo_telepon_k(String no_telepon_k) {
        this.no_telepon_k = no_telepon_k;
    }

    public Date getTanggal_join() {
        return tanggal_join;
    }

    public void setTanggal_join(Date tanggal_join) {
        this.tanggal_join = tanggal_join;
    }

}
