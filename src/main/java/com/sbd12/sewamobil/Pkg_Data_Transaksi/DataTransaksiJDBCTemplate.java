package com.sbd12.sewamobil.Pkg_Data_Transaksi;

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
import java.sql.Date;

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
public class DataTransaksiJDBCTemplate implements DataTransaksiDAO {

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
    private final String QUERY_PILIH_SEMUA = "SELECT dt.*,mm.nama_mobil,pg.nama_p,kt.nama_k "
            + "FROM tbl_data_transaksi AS DT "
            + "JOIN tbl_data_mobil AS DM ON dt.no_pol=dm.no_pol "
            + "JOIN tbl_merk_mobil AS mm ON dm.id_merk_mobil=mm.id_merk_mobil "
            + "JOIN tbl_pegawai AS PG ON dt.id_pegawai=pg.id_pegawai "
            + "JOIN tbl_kostumer AS KT ON dt.id_kostumer=kt.id_kostumer";
    
    private final String QUERY_PILIH_CARI =QUERY_PILIH_SEMUA+" where dt.no_transaksi=?";
    private final String QUERY_PILIH_LIKE =QUERY_PILIH_SEMUA+" where dt.nama_mobil like ?";
    
    @Override
    public void create(String no_transaksi, String id_kostumer, String no_pol, String id_pegawai, Date tglpinjam, Date tglkembali,double hargatotal) {
        String SQL = "INSERT INTO tbl_data_transaksi (no_transaksi, id_kostumer,no_pol, id_pegawai,tglpinjam,tglkembali,hargatotal) values (?, ?,?,?,?,?,?)";

        jdbcTemplateObject.update(SQL, no_transaksi, id_kostumer,no_pol, id_pegawai,tglpinjam,tglkembali,hargatotal);
        System.out.println("Masuk fungsi update");
        return;
    }

    @Override
    public List<DataTransaksi> listSemua() {
        
        List<DataTransaksi> dataTransaksis = jdbcTemplateObject.query(QUERY_PILIH_SEMUA, new DataTransaksiMapper());
        return dataTransaksis;
    }

    public DataTransaksi pilih_data(String kode) {

        List<DataTransaksi> dataTransaksis = jdbcTemplateObject.query(QUERY_PILIH_CARI, new DataTransaksiMapper(), kode);
        return dataTransaksis.get(0);
    }
    
    public List<DataTransaksi> pilih_data_like(String kode) {
        
        List<DataTransaksi> dataTransaksis = jdbcTemplateObject.query(QUERY_PILIH_LIKE, new DataTransaksiMapper(), "%"+kode+"%");
        return dataTransaksis;
    }

    @Override
    public DataTransaksi getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /*@Override
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
    } */
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
    /*
    @Override
    public void delete(String id)
    {
        String SQL = "delete from tbl_merk_mobil where id_merk_mobil = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }
    
     @Override
    public void edit(String no_transaksi, String id_kostumer, String no_pol, String id_pegawai, Date tglpinjam, Date tglkembali,double hargatotal)
    {
      /*  System.out.println("Masuk fungsi update");
        String SQL="UPDATE tbl_merk_mobil SET id_produsen_mobil=?,id_jenis_mobil=?,nama_mobil=? where id_merk_mobil=?";
        
        jdbcTemplateObject.update(SQL,idProd,idJenis,namaMerk,id);
         
         return;*/
    }
    
    
  public class DataTransaksiMapper implements RowMapper<DataTransaksi> {
   @Override
   public DataTransaksi mapRow(ResultSet rs, int rowNum) throws SQLException {
      DataTransaksi dataTransaksi = new DataTransaksi();
      dataTransaksi.setNo_transaksi(rs.getString("no_transaksi"));
      dataTransaksi.setId_kostumer(rs.getString("id_kostumer"));
      dataTransaksi.setId_pegawai(rs.getString("id_pegawai"));
      dataTransaksi.setNo_pol(rs.getString("no_pol"));
      dataTransaksi.setNama_kostumer(rs.getString("nama_k"));
      dataTransaksi.setNama_pegawai(rs.getString("nama_p"));
      dataTransaksi.setNama_mobil(rs.getString("nama_mobil"));
      dataTransaksi.setTglpinjam(rs.getDate("tglpinjam"));
      dataTransaksi.setTglkembali(rs.getDate("tglkembali"));
      dataTransaksi.setHarga_total(rs.getDouble("harga_total"));
      return dataTransaksi;
   }
}    
}
