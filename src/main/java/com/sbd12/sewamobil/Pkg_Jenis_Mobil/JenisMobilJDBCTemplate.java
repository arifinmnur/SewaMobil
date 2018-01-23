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

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Repository
@Service

public class JenisMobilJDBCTemplate implements JenisMobilDA0 {
    
    @Autowired private DataSource dataSource;
   @Autowired private JdbcTemplate jdbcTemplateObject;
   
   @Autowired
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }
   public void create(String Id_jenis_mobil, String Nama_jenis_mobil, String Harga_mobil) {
      String SQL = "insert into tbl_jenis_mobil (id_jenis_mobil, nama_jenis_mobil, harga) values (?, ? ,?)";
      
      jdbcTemplateObject.update( SQL, Id_jenis_mobil, Nama_jenis_mobil,Harga_mobil);
      System.out.println("Created Record Name = " + Id_jenis_mobil + "Age = " + Nama_jenis_mobil );
      return;
   }
   
   public List<JenisMobil> listSemua() {
       String SQL = "select * from tbl_jenis_mobil";
      List <JenisMobil> jenis_mobils = jdbcTemplateObject.query(SQL, new JenisMobilMapper());
      return jenis_mobils;
   }

    @Override
    public JenisMobil getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(String name, Integer age) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
