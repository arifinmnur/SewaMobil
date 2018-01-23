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
public class MerkMobilJDBCTemplate implements MerkMobilDAO {

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
    private final String QUERY_PILIH_SEMUA = "select mm.*,pm.nama_produsen,jm.nama_jenis"
                + " FROM tbl_merk_mobil        AS mm "
                + " JOIN tbl_produsen_mobil    AS PM   ON mm.id_produsen_mobil=pm.id_produsen_mobil"
                + " JOIN tbl_jenis_mobil       AS jm   ON mm.id_jenis_mobil=jm.id_jenis_mobil";
    
    private final String QUERY_PILIH_CARI =QUERY_PILIH_SEMUA+" where mm.id_merk_mobil=?";
    private final String QUERY_PILIH_LIKE =QUERY_PILIH_SEMUA+" where mm.nama_mobil like ?";
    
    @Override
    public void create(String id_merk_mobil, String id_produsen_mobil, String id_jenis_mobil, String nama_mobil) {
        String SQL = "insert into tbl_merk_mobil (id_merk_mobil, id_produsen_mobil,id_jenis_mobil, nama_mobil) values (?, ?,?,?)";

        jdbcTemplateObject.update(SQL, id_merk_mobil, id_produsen_mobil, id_jenis_mobil, nama_mobil);
        System.out.println("Masuk fungsi update");
        return;
    }

    @Override
    public List<MerkMobil> listSemua() {
        return jdbcTemplateObject.query(QUERY_PILIH_SEMUA, new MerkMobilMapper());
    }

    public MerkMobil pilih_data(String kode) {

        List<MerkMobil> merkmobil = jdbcTemplateObject.query(QUERY_PILIH_CARI, new MerkMobilMapper(), kode);
        return merkmobil.get(0);
    }
    
    public List<MerkMobil> pilih_data_like(String kode) {
        
        List<MerkMobil> merkmobil = jdbcTemplateObject.query(QUERY_PILIH_LIKE, new MerkMobilMapper(), "%"+kode+"%");
        return merkmobil;
    }

    @Override
    public MerkMobil getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<JenisMobil> combo_box_jenis_mobil(JComboBox Combo){
        String SQL = "SELECT * FROM tbl_jenis_mobil";
        
        Combo.removeAllItems();
        Combo.addItem("Pilih");
        List<JenisMobil> list = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            JenisMobil jenisMobil = new JenisMobil();
            jenisMobil.setId_jenis_mobil(rs.getString("id_jenis_mobil"));
            jenisMobil.setHarga_mobil(rs.getString("harga"));
            jenisMobil.setNama_jenis_mobil(rs.getString("nama_jenis"));
            Combo.addItem(rs.getString("nama_jenis"));
            return jenisMobil;
        });

        return list;
    } 
    @Override
    public List<ProdusenMobil> combo_box_produsen_mobil(JComboBox Combo){
        String SQL = "SELECT * FROM tbl_produsen_mobil";
        
        Combo.removeAllItems();
        Combo.addItem("Pilih");
        List<ProdusenMobil> list = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            ProdusenMobil produsenMobil = new ProdusenMobil();
            produsenMobil.setId_produsen(rs.getString("id_produsen_mobil"));
            produsenMobil.setNama_produsen(rs.getString("nama_produsen"));
            Combo.addItem(rs.getString("nama_produsen"));
            return produsenMobil;
        });

        return list;
    } 
    
    @Override
    public void delete(String id)
    {
        String SQL = "delete from tbl_merk_mobil where id_merk_mobil = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }
    
     @Override
    public void edit(String id,String idProd,String idJenis,String namaMerk)
    {
        System.out.println("Masuk fungsi update");
        String SQL="UPDATE tbl_merk_mobil SET id_produsen_mobil=?,id_jenis_mobil=?,nama_mobil=? where id_merk_mobil=?";
        
        jdbcTemplateObject.update(SQL,idProd,idJenis,namaMerk,id);
         
         return;
    }
    
    
  public class MerkMobilMapper implements RowMapper<MerkMobil> {
   @Override
   public MerkMobil mapRow(ResultSet rs, int rowNum) throws SQLException {
      MerkMobil merkMobil = new MerkMobil();
      merkMobil.setId_merk_mobil(rs.getString("id_Merk_mobil"));
      merkMobil.setId_produsen_mobil(rs.getString("id_produsen_mobil"));
      merkMobil.setId_jenis(rs.getString("id_jenis_mobil"));
      merkMobil.setNama_Merk_Mobil(rs.getString("nama_mobil"));
      merkMobil.setNama_produsen_mobil(rs.getString("nama_produsen"));
      merkMobil.setNama_jenis(rs.getString("nama_jenis"));
      return merkMobil;
   }
}    
}
