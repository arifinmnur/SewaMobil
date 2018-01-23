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
import org.springframework.stereotype.Repository;
interface JenisMobilDA0 {



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
   public void create(String name, Integer age);
   public JenisMobil getId(Integer id);
   
   /** 
     * This is the method to be used to list down
     * all the records from the Student table.
     * @return 
   */
   public List<JenisMobil> listSemua();   
}
