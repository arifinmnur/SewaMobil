package com.sbd12.sewamobil.Pkg_Data_Pegawai;

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
public class Pegawai {

    private String id_pegawai;
    private String no_ktp_p;
    private String nama_lengkap_p;
    private String nama_depan_p;
    private String nama_belakang_p;
    private String jenis_kelamin_p;
    private String alamat_p;
    private java.sql.Date tanggal_lahir_p;
    private int umur;
    private String no_telepon_p;
    private String username;
    private String password;
    private java.sql.Date tanggal_join_p;

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNo_ktp_p() {
        return no_ktp_p;
    }

    public void setNo_ktp_p(String no_ktp_p) {
        this.no_ktp_p = no_ktp_p;
    }

    public String getNama_lengkap_p() {
        return nama_depan_p + " " + nama_belakang_p;
    }

    public void setNama_lengkap_p(String nama_lengkap_p) {
        this.nama_lengkap_p = nama_lengkap_p;
    }

    public String getNama_depan_p() {
        return nama_depan_p;
    }

    public void setNama_depan_p(String nama_depan_p) {
        this.nama_depan_p = nama_depan_p;
    }

    public String getNama_belakang_p() {
        return nama_belakang_p;
    }

    public void setNama_belakang_p(String nama_belakang_p) {
        this.nama_belakang_p = nama_belakang_p;
    }

    public String getJenis_kelamin_p() {
        return jenis_kelamin_p;
    }

    public void setJenis_kelamin_p(String jenis_kelamin_p) {
        this.jenis_kelamin_p = jenis_kelamin_p;
    }

    public String getAlamat_p() {
        return alamat_p;
    }

    public void setAlamat_p(String alamat_p) {
        this.alamat_p = alamat_p;
    }

    public Date getTanggal_lahir_p() {
        return tanggal_lahir_p;
    }

    public void setTanggal_lahir_p(Date tanggal_lahir_p) {
        this.tanggal_lahir_p = tanggal_lahir_p;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getNo_telepon_p() {
        return no_telepon_p;
    }

    public void setNo_telepon_p(String no_telepon_p) {
        this.no_telepon_p = no_telepon_p;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTanggal_join_p() {
        return tanggal_join_p;
    }

    public void setTanggal_join_p(Date tanggal_join_p) {
        this.tanggal_join_p = tanggal_join_p;
    }
    
    

}
