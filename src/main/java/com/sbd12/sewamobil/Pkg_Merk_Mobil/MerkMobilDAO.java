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

import com.sbd12.sewamobil.Pkg_Jenis_Mobil.JenisMobil;
import com.sbd12.sewamobil.Pkg_ProdusenMobil.ProdusenMobil;
import java.util.List;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import org.springframework.stereotype.Repository;

public interface MerkMobilDAO {
   /** 
      * This is the method to be used to initialize
      * database resources ie. connection.
     * @param ds
   */
   public void setDataSource(DataSource ds);
   
   
   public void edit(String id,String idProd,String idJenis,String namaMerk);
   public void delete(String id);
   public void create(String id_merk_mobil, String id_produsen_mobil, String id_jenis_mobil, String nama_mobil );
   public MerkMobil getId(Integer id);
   
   /** 
     * This is the method to be used to list down
     * all the records from the Student table.
     * @return 
   */
   public List<MerkMobil> listSemua();
   public List<ProdusenMobil> combo_box_produsen_mobil(JComboBox Combo);
   public List<JenisMobil> combo_box_jenis_mobil(JComboBox Combo);
}