/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Data_Mobil;

import com.sbd12.sewamobil.Pkg_Data_Pegawai.Pegawai;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author resas
 */
@Repository
@Service
public class DataMobilJDBCTemplate implements DataMobilDAO {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    private final String SQL_TAMPIL_DATA_MOBIL = "select mm.*,pm.nama_mobil,jm.nama_ow,jmm.nama_jenis,jmm.harga "
            + " FROM tbl_data_mobil        AS mm  "
            + " INNER JOIN tbl_merk_mobil        AS PM   ON mm.id_merk_mobil=pm.id_merk_mobil "
            + " INNER JOIN tbl_owner_mobil       AS jm   ON mm.id_owner=jm.id_owner "
            + " INNER JOIN tbl_jenis_mobil AS jmm ON jmm.id_jenis_mobil=pm.id_jenis_mobil ";

    private final String SQL_TAMPIL_DATA_MOBIL_BERDASAR_WHERE = SQL_TAMPIL_DATA_MOBIL
            + " WHERE mm.no_pol LIKE ? "
            + " OR pm.nama_mobil LIKE ? "
            + " OR jm.nama_ow LIKE ? "
            + " OR jmm.nama_jenis LIKE ?";

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

        List<DataMobil> data_mobil = jdbcTemplateObject.query(SQL_TAMPIL_DATA_MOBIL, new DataMobilMapper());
        return data_mobil;
    }

    @Override
    public List<DataMobil> listSemua_berdasar_noTransaksi(String noTransaksi) {
        /* String SQL = "select mm.*,pm.nama_mobil,jm.nama_ow"
              + " FROM tbl_data_mobil        AS mm "
              + " JOIN tbl_merk_mobil        AS PM   ON mm.id_merk_mobil=pm.id_merk_mobil"
              + " JOIN tbl_owner_mobil       AS jm   ON mm.id_owner=jm.id_owner";*/
        String SQL = "SELECT dtx.*, mm.*,pm.nama_mobil,jm.nama_ow,jmm.nama_jenis,jmm.harga, pgw.id_pegawai,concat(pgw.nama_depan_p,' ',pgw.nama_belakang_p) as nama_petugas"
                + " FROM Detail_transaksi as dtx"
                + " INNER JOIN tbl_data_mobil        AS mm  on mm.no_pol=dtx.no_pol"
                + " INNER JOIN tbl_merk_mobil        AS PM   ON mm.id_merk_mobil=pm.id_merk_mobil "
                + " INNER JOIN tbl_owner_mobil       AS jm   ON mm.id_owner=jm.id_owner "
                + " INNER JOIN tbl_jenis_mobil AS jmm ON jmm.id_jenis_mobil=pm.id_jenis_mobil "
                + " LEFT JOIN tbl_pegawai AS pgw ON pgw.id_pegawai=dtx.id_petugas_pengembalian"
                + " WHERE dtx.no_transaksi=?";

        List<DataMobil> data_mobil = jdbcTemplateObject.query(SQL, new DataMobilMapper(), noTransaksi);
        return data_mobil;
    }

    @Override
    public List<DataMobil> pilih_data_like(String kode) {

        String kode_ = "%" + kode + "%";
        List<DataMobil> dataMobils = jdbcTemplateObject.query(SQL_TAMPIL_DATA_MOBIL_BERDASAR_WHERE, new DataMobilMapper(), kode_, kode_, kode_, kode_);
        return dataMobils;
    }

    @Override
    public DataMobil getNo_pol(Integer nopol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double ambil_selected_harga(ArrayList<String> selectedItems) throws ClassNotFoundException {

        /*double harga = 0;
            String sql_ambil_selected_harga= "select mm.*,pm.nama_mobil,jm.nama_ow,jmm.nama_jenis,jmm.harga "
                    + " FROM tbl_data_mobil        AS mm  "
                    + " INNER JOIN tbl_merk_mobil        AS PM   ON mm.id_merk_mobil=pm.id_merk_mobil "
                    + " INNER JOIN tbl_owner_mobil       AS jm   ON mm.id_owner=jm.id_owner "
                    + " INNER JOIN tbl_jenis_mobil AS jmm ON jmm.id_jenis_mobil=pm.id_jenis_mobil 
                    + " where mm.no_pol=?";"*/

 /*for (String list : selectedItems) {
                
                
                System.out.println("didalam crud"+list);
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                harga += rs.getDouble(4);
                rs.close();
            }*/
 /*List<Pegawai> list = jdbcTemplateObject.query(sql_ambil_selected_harga, (ResultSet rs, int i) -> {
            try {
                harga +=rs.getDouble("harga");
            } catch (SQLException ex) {
                Logger.getLogger(DataMobilJDBCTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return harga;*/
        return 0;
    }

    public class DataMobilMapper implements RowMapper<DataMobil> {

        @Override
        public DataMobil mapRow(ResultSet rs, int rowNum) throws SQLException {
            DataMobil dataMobil = new DataMobil();
            dataMobil.setNo_pol(rs.getString("no_pol"));
            dataMobil.setId_merk_mobil(rs.getString("id_merk_mobil"));
            dataMobil.setId_owner(rs.getString("id_owner"));
            dataMobil.setNama_mobil(rs.getString("nama_mobil"));
            dataMobil.setNamaow(rs.getString("nama_ow"));
            dataMobil.setHarga_perhari(rs.getDouble("harga"));

            String id_petugas_pengembalian = rs.getString("id_petugas_pengembalian");
            String nama_petugas_pengembalian = rs.getString("nama_petugas");
            java.sql.Timestamp tglkembali = rs.getTimestamp("tglkembali");
            dataMobil.setId_petugas_pengembalian(id_petugas_pengembalian);
            dataMobil.setNama_petugas_pengembalian(nama_petugas_pengembalian);
            dataMobil.setTglkembali(tglkembali);
            dataMobil.setDenda(rs.getDouble("denda"));

            dataMobil.setStatus(rs.getString("catatan"));

            return dataMobil;
        }

    }
}
