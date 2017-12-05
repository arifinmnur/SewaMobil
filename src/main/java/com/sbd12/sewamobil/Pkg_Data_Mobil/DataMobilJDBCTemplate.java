/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Data_Mobil;


import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author resas
 */
@Repository
@Service
public class DataMobilJDBCTemplate implements DataMobilDAO{
   @Autowired private DataSource dataSource;
   @Autowired private JdbcTemplate jdbcTemplateObject;
   
   @Autowired
   @Override
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }
   @Override
   public void create(String id_produsen, Integer nama_produsen) {
      /*String SQL = "insert into tbl_produsen_mobil (id_produsen_mobil, nama_produsen) values (?, ?)";
      
      jdbcTemplateObject.update( SQL, id_produsen, nama_produsen);
      System.out.println("Created Record Name = " + id_produsen + " Age = " + nama_produsen);
      return;*/
   }
   
   @Override
   public List<DataMobil> listSemua() {
      String SQL = "select mm.*,pm.nama_mobil,jm.nama_ow"
              + " FROM tbl_data_mobil        AS mm "
              + " JOIN tbl_merk_mobil        AS PM   ON mm.id_merk_mobil=pm.id_merk_mobil"
              + " JOIN tbl_owner_mobil       AS jm   ON mm.id_owner=jm.id_owner";
      List <DataMobil> data_mobil = jdbcTemplateObject.query(SQL, new DataMobilMapper());
      return data_mobil;
   }

   @Override
    public DataMobil getNo_pol(Integer nopol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
