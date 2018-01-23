/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Jenis_Mobil;

import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author Anonymous
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class JenisMobilMapper implements RowMapper<JenisMobil> {
    @Override
    public JenisMobil mapRow(ResultSet rs, int i) throws SQLException {
      JenisMobil jenisMobil = new JenisMobil();
      jenisMobil.setId_jenis_mobil(rs.getString("Id_jenis_mobil"));
      jenisMobil.setNama_jenis_mobil(rs.getString("Nama_Jenis"));
      jenisMobil.setHarga_mobil(rs.getString("Harga"));
     
      
      
      
      return jenisMobil;
   }
    }
