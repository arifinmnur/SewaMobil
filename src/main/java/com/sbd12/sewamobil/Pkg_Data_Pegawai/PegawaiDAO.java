package com.sbd12.sewamobil.Pkg_Data_Pegawai;

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

public interface PegawaiDAO {
   /** 
      * This is the method to be used to initialize
      * database resources ie. connection.
     * @param ds
   */
   public void setDataSource(DataSource ds);
   
   
   public void edit(String id_pegawai,String no_ktp_p,String nama_p,String jenis_kelamin_p, String alamat_p, String no_telepon_p);
   public void delete(String id_pegawai);
   public void create(String id_pegawai,String no_ktp_p,String nama_p,String jenis_kelamin_p, String alamat_p, String no_telepon_p);
   public Pegawai getId(Integer id_pegawai);
   
   /** 
     * This is the method to be used to list down
     * all the records from the Student table.
     * @return 
   */
   public List<Pegawai> listSemua();
}