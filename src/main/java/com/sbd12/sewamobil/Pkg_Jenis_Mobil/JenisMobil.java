/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Jenis_Mobil;

/**
 *
 * @author Anonymous
 */
public class JenisMobil {
   
   private String id_jenis_mobil;
   private String nama_jenis_mobil;
   private double harga_mobil;
  

   public void setId_jenis_mobil(String id) {
      this.id_jenis_mobil = id;
   }
   public String getId_jenis_mobil() {
      return id_jenis_mobil;
   }
   public void setNama_jenis_mobil(String nama) {
      this.nama_jenis_mobil = nama;
   }
   public String getNama_jenis_mobil() {
      return nama_jenis_mobil;
   }
public void setHarga_mobil(double harga) {
      this.harga_mobil = harga;
   }
   public double getHarga_mobil() {
      return harga_mobil;
   }
   
}
