package com.sbd12.sewamobil.Pkg_Kostomer;

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
import java.util.List;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import org.springframework.stereotype.Repository;

public interface KostumerDAO {
   /** 
      * This is the method to be used to initialize
      * database resources ie. connection.
     * @param ds
   */
   public void setDataSource(DataSource ds);
   
   
   public void edit(String id_kostumer,String no_ktp,String nama_k,String jenis_kelamin_k, String alamat_k, String no_telepon_k);
   public void delete(String id_kostumer);
   public void create(String id_kostumer,String no_ktp,String nama_k,String jenis_kelamin_k, String alamat_k, String no_telepon_k);
   public Kostumer getId(Integer id_kostumer);
   
   /** 
     * This is the method to be used to list down
     * all the records from the Student table.
     * @return 
   */
   public List<Kostumer> listSemua();
}