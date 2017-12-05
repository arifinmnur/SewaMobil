
package com.sbd12.sewamobil.Pkg_Owner_Mobil;

import com.sbd12.sewamobil.Pkg_Merk_Mobil.MerkMobil;
import com.sbd12.sewamobil.Pkg_ProdusenMobil.ProdusenMobil;
import com.sbd12.sewamobil.Pkg_ProdusenMobil.ProdusenMobilMapper;
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
public class OwnerMobilJDBCTemplate implements OwnerMobilDAO{
  @Autowired private DataSource dataSource;
  @Autowired private JdbcTemplate jdbcTemplateObject;


   /**Note
    * @Autowired berguna untuk Depedency Injection pada Spring
    * @param dataSource
    */
   @Autowired
   @Override
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }
   @Override
   public void create(String id_owner, Integer nama_produsen) {
     /* String SQL = "insert into tbl_owner_mobil (id_owner, no_ktp_ow,nama_ow,jenis_kelamin_ow,alamat_ow,no_telepon_ow) values (?, ?)";
      
      jdbcTemplateObject.update( SQL, id_owner, no_ktp_ow,nama_ow,jenis_kelamin_ow,alamat_ow,no_telepon_ow);
      System.out.println("Created Record Name = " + id_produsen + " Age = " + nama_produsen);
      return;*/
   }
   @Override
   public List<OwnerMobil> listSemua() {
      String SQL = "select * from tbl_owner_mobil";
      List <OwnerMobil> owner = jdbcTemplateObject.query(SQL, new OwnerMobilMapper());
      return owner;
   }

   @Override
    public OwnerMobil getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
