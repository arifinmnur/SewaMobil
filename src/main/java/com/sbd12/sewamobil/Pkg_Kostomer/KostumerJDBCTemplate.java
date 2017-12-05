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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Repository
@Service
public class KostumerJDBCTemplate implements KostumerDAO {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    private final String QUERY_PILIH_SEMUA = "select * from tbl_kostumer";
    
    private final String QUERY_PILIH_CARI =QUERY_PILIH_SEMUA+" where id_kostumer=?";
    private final String QUERY_PILIH_LIKE =QUERY_PILIH_SEMUA+" where nama_k like ?";
    
    @Override
    public void create(String id_kostumer,String no_ktp,String nama_k,String jenis_kelamin_k, String alamat_k, String no_telepon_k) {
        String SQL = "insert into tbl_kostumer ( id_kostumer, no_ktp, nama_k, jenis_kelamin_k,  alamat_k,  no_telepon_k) values (?, ?,?,?,?,?)";

        jdbcTemplateObject.update(SQL,  id_kostumer, no_ktp, nama_k, jenis_kelamin_k,  alamat_k,  no_telepon_k);
        System.out.println("Masuk fungsi update");
        return;
    }

    @Override
    public List<Kostumer> listSemua() {
        
        List<Kostumer> kostumers = jdbcTemplateObject.query(QUERY_PILIH_SEMUA, new KostumerMapper());
        return kostumers;
    }

    public Kostumer pilih_data(String kode) {

        List<Kostumer> kostumers = jdbcTemplateObject.query(QUERY_PILIH_CARI, new KostumerMapper(), kode);
        return kostumers.get(0);
    }
    
    public List<Kostumer> pilih_data_like(String kode) {
        
        List<Kostumer> kostumers = jdbcTemplateObject.query(QUERY_PILIH_LIKE, new KostumerMapper(), "%"+kode+"%");
        return kostumers;
    }

    @Override
    public Kostumer getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void delete(String id)
    {
        String SQL = "delete from tbl_kostumer where id_kostumer = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }
    
     @Override
    public void edit(String id_kostumer,String no_ktp,String nama_k,String jenis_kelamin_k, String alamat_k, String no_telepon_k)
    {
        System.out.println("Masuk fungsi update");
        String SQL="UPDATE tbl_kostumer SET no_ktp=?,nama_k=?, jenis_kelamin_k=?, alamat_k=?, no_telepon_k=? where id_kostumer=?";
        
        jdbcTemplateObject.update(SQL, no_ktp, nama_k, jenis_kelamin_k,  alamat_k,  no_telepon_k,id_kostumer);
         
         return;
    }
    
    
  public class KostumerMapper implements RowMapper<Kostumer> {
   @Override
   public Kostumer mapRow(ResultSet rs, int rowNum) throws SQLException {
      Kostumer kostumer = new Kostumer();
      kostumer.setId_kostumer(rs.getString("id_kostumer"));
      kostumer.setNo_ktp(rs.getString("no_ktp"));
      kostumer.setNama_k(rs.getString("nama_k"));
      kostumer.setAlamat_k(rs.getString("alamat_k"));
      kostumer.setJenis_kelamin_k(rs.getString("jenis_kelamin_k"));
      kostumer.setNo_telepon_k(rs.getString("no_telepon_k"));
      return kostumer;
   }
}    
}
