package com.sbd12.sewamobil.Pkg_Data_Transaksi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ArieDZ_2
 */

import com.sbd12.sewamobil.Pkg_Jenis_Mobil.JenisMobil;
import com.sbd12.sewamobil.Pkg_ProdusenMobil.ProdusenMobil;
import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import org.springframework.stereotype.Repository;

public interface DataTransaksiDAO {
   /** 
      * This is the method to be used to initialize
      * database resources ie. connection.
     * @param ds
   */
   public void setDataSource(DataSource ds);
   
   
   public void edit(String no_transaksi,String id_kostumer,String no_pol,String id_pegawai, Date tglpinjam,Date tglkembali,double hargatotal);
   public void delete(String id);
   public void create(String no_transaksi, String id_kostumer, String no_pol, String id_pegawai, Date tglpinjam, Date tglkembali,double hargatotal);
   public DataTransaksi getId(Integer id);
   
   /** 
     * This is the method to be used to list down
     * all the records from the Student table.
     * @return 
   */
   public List<DataTransaksi> listSemua();
}