package com.sbd12.sewamobil.Pkg_Merk_Mobil;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ArieDZ_2
 */
public class MerkMobil {
   private String id_merk_mobil;
   private String nama_Merk_Mobil;
   private String id_produsen_mobil;
   private String nama_produsen_mobil;
   private String id_jenis;
   private String nama_jenis;

   public void setId_merk_mobil(String id) {
      this.id_merk_mobil = id;
   }
   public String getId_merk_mobil() {
      return id_merk_mobil;
   }
   public void setNama_Merk_Mobil(String nama) {
      this.nama_Merk_Mobil = nama;
   }
   public String getNama_Merk_Mobil() {
      return nama_Merk_Mobil;
   }
   
   public void setId_produsen_mobil(String id_produsen_mobil) {
      this.id_produsen_mobil = id_produsen_mobil;
   }
   public String getId_produsen_mobil() {
      return id_produsen_mobil;
   }
   
   public void setNama_produsen_mobil(String nama_produsen_mobil) {
      this.nama_produsen_mobil = nama_produsen_mobil;
   }
   public String getNama_produsen_mobil() {
      return nama_produsen_mobil;
   }
   
   public void setId_jenis(String id_jenis) {
      this.id_jenis = id_jenis;
   }
   public String getId_jenis() {
      return id_jenis;
   }
   
   public void setNama_jenis(String nama_jenis) {
      this.nama_jenis = nama_jenis;
   }
   public String getNama_jenis() {
      return nama_jenis;
   }

    Object getNama_jenis_mobil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getHarga_mobil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
