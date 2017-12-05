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
public class PegawaiJDBCTemplate implements PegawaiDAO {

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
    private final String QUERY_PILIH_SEMUA = "select * from tbl_pegawai";
    
    private final String QUERY_PILIH_CARI =QUERY_PILIH_SEMUA+" where id_pegawai=?";
    private final String QUERY_PILIH_LIKE =QUERY_PILIH_SEMUA+" where nama_p like ?";
    
    @Override
    public void create(String id_pegawai,String no_ktp_p,String nama_p,String jenis_kelamin_p, String alamat_p, String no_telepon_p) {
        String SQL = "insert into tbl_pegawai ( id_pegawai, no_ktp_p, nama_p, jenis_kelamin_p,  alamat_p,  no_telepon_p) values (?, ?,?,?,?,?)";

        jdbcTemplateObject.update(SQL,  id_pegawai, no_ktp_p, nama_p, jenis_kelamin_p,  alamat_p,  no_telepon_p);
        System.out.println("Masuk fungsi update");
        return;
    }

    @Override
    public List<Pegawai> listSemua() {
        
        List<Pegawai> pegawais = jdbcTemplateObject.query(QUERY_PILIH_SEMUA, new PegawaiMapper());
        return pegawais;
    }

    public Pegawai pilih_data(String kode) {

        List<Pegawai> pegawais = jdbcTemplateObject.query(QUERY_PILIH_CARI, new PegawaiMapper(), kode);
        return pegawais.get(0);
    }
    
    public List<Pegawai> pilih_data_like(String kode) {
        
        List<Pegawai> pegawais = jdbcTemplateObject.query(QUERY_PILIH_LIKE, new PegawaiMapper(), "%"+kode+"%");
        return pegawais;
    }

    @Override
    public Pegawai getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void delete(String id)
    {
        String SQL = "delete from tbl_pegawai where id_pegawai = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }
    
     @Override
    public void edit(String id_pegawai,String no_ktp_p,String nama_p,String jenis_kelamin_p, String alamat_p, String no_telepon_p)
    {
        System.out.println("Masuk fungsi update");
        String SQL="UPDATE tbl_pegawai SET no_ktp_p=?,nama_p=?, jenis_kelamin_p=?, alamat_p=?, no_telepon_p=? where id_pegawai=?";
        
        jdbcTemplateObject.update(SQL, no_ktp_p, nama_p, jenis_kelamin_p,  alamat_p,  no_telepon_p,id_pegawai);
         
         return;
    }
    
    
  public class PegawaiMapper implements RowMapper<Pegawai> {
   @Override
   public Pegawai mapRow(ResultSet rs, int rowNum) throws SQLException {
      Pegawai pegawai = new Pegawai();
      pegawai.setId_pegawai(rs.getString("id_pegawai"));
      pegawai.setNo_ktp_p(rs.getString("no_ktp_p"));
      pegawai.setNama_p(rs.getString("nama_p"));
      pegawai.setAlamat_p(rs.getString("alamat_p"));
      pegawai.setJenis_kelamin_p(rs.getString("jenis_kelamin_p"));
      pegawai.setNo_telepon_p(rs.getString("no_telepon_p"));
      return pegawai;
   }
}    
}
