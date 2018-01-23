package com.sbd12.sewamobil.Pkg_Kostumer;

import java.sql.Date;
import org.w3c.dom.css.Counter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ArieDZ_2
 */
public class Jenis_Member {

    private int counter=0;
    private String id_jenis_member;
    private String nama_jenis_member;
    private double diskon;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getId_jenis_member() {
        return id_jenis_member;
    }

    public void setId_jenis_member(String id_jenis_member) {
        this.id_jenis_member = id_jenis_member;
    }

    public String getNama_jenis_member() {
        return nama_jenis_member;
    }

    public void setNama_jenis_member(String nama_jenis_member) {
        this.nama_jenis_member = nama_jenis_member;
    }

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }
    
    
    

}
