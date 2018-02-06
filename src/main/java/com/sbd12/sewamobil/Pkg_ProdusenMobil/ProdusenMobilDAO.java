package com.sbd12.sewamobil.Pkg_ProdusenMobil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ArieDZ_2
 */
import java.util.List;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

public interface ProdusenMobilDAO {
   /** 
      * This is the method to be used to initialize
      * database resources ie. connection.
     * @param ds
   */
   public void setDataSource(DataSource ds);
   
   /** 
      * This is the method to be used to create
      * a record in the Student table.
     * @param name
     * @param age
   */
   public void create(String id_prod,String nama_prod);
   public void edit(String id_prod,String nama_prod, String old_id_prod);
   public ProdusenMobil getId(Integer id);
   
   /** 
     * This is the method to be used to list down
     * all the records from the Student table.
     * @return 
   */
   public List<ProdusenMobil> listSemua();
}