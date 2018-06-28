/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Data_Mobil;

import com.sbd12.sewamobil.Pkg_Data_Pegawai.Pegawai;
import com.sbd12.sewamobil.Pkg_Merk_Mobil.MerkMobil;
import com.sbd12.sewamobil.Pkg_Owner_Mobil.OwnerMobil;
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

    private final String SQL_TAMPIL_DATA_MOBIL = "SELECT mm.*,pm.nama_mobil,pms.`nama_produsen`,jmm.nama_jenis,jm.nama_ow,jm.`no_telepon_ow`,jmm.harga "
            + " FROM tbl_data_mobil        AS mm "
            + " INNER JOIN tbl_merk_mobil        AS PM   ON mm.id_merk_mobil=pm.id_merk_mobil"
            + " INNER JOIN `tbl_produsen_mobil` AS pms ON pm.`id_produsen_mobil`=pms.`id_produsen_mobil`"
            + " INNER JOIN tbl_owner_mobil       AS jm   ON mm.id_owner=jm.id_owner"
            + " INNER JOIN tbl_jenis_mobil AS jmm ON jmm.id_jenis_mobil=pm.id_jenis_mobil";

    private final String SQL_TAMPIL_DATA_MOBIL_BERDASAR_LIKE = SQL_TAMPIL_DATA_MOBIL
            + " WHERE mm.no_pol LIKE ? "
            + " OR pm.nama_mobil LIKE ? "
            + " OR jm.nama_ow LIKE ? "
            + " OR jmm.nama_jenis LIKE ?";
    private final String QUERY_PILIH_CARI=SQL_TAMPIL_DATA_MOBIL+" WHERE no_pol=?";
    private final String SQL_TAMBAH = "INSERT INTO tbl_data_mobil (no_pol, id_merk_mobil,id_owner) values (?, ?,?)";
    private final String SQL_DELETE = "DELETE FROM tbl_data_mobil WHERE no_pol = ?";
    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String no_pol, String id_merk,String id_owner){
        try {
          jdbcTemplateObject.update(SQL_TAMBAH, no_pol, id_merk,id_owner);  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return;
        }
      
      return;
    }
    
    @Override
    public void edit(String no_pol, String id_merk,String id_owner,String old_no_pol){
        String SQL = "UPDATE tbl_data_mobil SET no_pol=?,id_merk_mobil=?, id_owner=? WHERE no_pol=?";
        try {
           jdbcTemplateObject.update(SQL, no_pol, id_merk, id_owner, old_no_pol); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return;
        }

        return;
    }
    @Override
    public void delete(String id) {
        

        try {
            jdbcTemplateObject.update(SQL_DELETE, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
        return;
    }
    @Override
    public List<DataMobil> listSemua() {
        List<DataMobil> data_mobil = null;
        try {

            data_mobil = jdbcTemplateObject.query(SQL_TAMPIL_DATA_MOBIL + " ORDER BY jm.nama_ow ASC,mm.no_pol ASC,pm.nama_mobil ASC", new DataMobilMapper());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
        return data_mobil;
    }

    @Override
    public List<DataMobil> listSemua_berdasar_noTransaksi(String noTransaksi) {
        String SQL = "SELECT dtx.*, mm.*,pm.nama_mobil,jm.nama_ow,jmm.nama_jenis,jmm.harga, pgw.id_pegawai,concat(pgw.nama_depan_p,' ',pgw.nama_belakang_p) as nama_petugas"
                + " FROM Detail_transaksi as dtx"
                + " INNER JOIN tbl_data_mobil        AS mm  on mm.no_pol=dtx.no_pol"
                + " INNER JOIN tbl_merk_mobil        AS PM   ON mm.id_merk_mobil=pm.id_merk_mobil "
                + " INNER JOIN tbl_owner_mobil       AS jm   ON mm.id_owner=jm.id_owner "
                + " INNER JOIN tbl_jenis_mobil AS jmm ON jmm.id_jenis_mobil=pm.id_jenis_mobil "
                + " LEFT JOIN tbl_pegawai AS pgw ON pgw.id_pegawai=dtx.id_petugas_pengembalian"
                + " WHERE dtx.no_transaksi=?";
        List<DataMobil> data_mobil = null;
        try {
            data_mobil = jdbcTemplateObject.query(SQL, new DataMobilDetailMapper(), noTransaksi);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
        return data_mobil;
    }
    
    @Override
    public DataMobil pilih_data(String kode) {
        List<DataMobil> dataMobils = null;
        try {
            dataMobils = jdbcTemplateObject.query(QUERY_PILIH_CARI, new DataMobilMapper(), kode);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return null;
        }
        if (!dataMobils.isEmpty()) {
            return dataMobils.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<DataMobil> pilih_data_like(String kode) {
        List<DataMobil> dataMobils = null;
        String kode_ = "%" + kode + "%";
        try {
            dataMobils = jdbcTemplateObject.query(SQL_TAMPIL_DATA_MOBIL_BERDASAR_LIKE, new DataMobilMapper(), kode_, kode_, kode_, kode_);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return null;
        }
        dataMobils = jdbcTemplateObject.query(SQL_TAMPIL_DATA_MOBIL_BERDASAR_LIKE, new DataMobilMapper(), kode_, kode_, kode_, kode_);
        return dataMobils;
    }

    @Override
    public DataMobil getNo_pol(Integer nopol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            
            MerkMobil merkMobil = new MerkMobil();
            merkMobil.setId_merk_mobil(rs.getString("id_merk_mobil"));
            merkMobil.setNama_Merk_Mobil(rs.getString("nama_mobil"));
            merkMobil.setNama_produsen_mobil(rs.getString("nama_produsen"));
            merkMobil.setNama_jenis(rs.getString("nama_jenis"));
            
            OwnerMobil ownerMobil = new OwnerMobil();
            ownerMobil.setId_owner(rs.getString("id_owner"));
            ownerMobil.setNama_ow(rs.getString("nama_ow"));
            ownerMobil.setNo_telepon_ow(rs.getString("no_telepon_ow"));
            
            dataMobil.setMerkMobil(merkMobil);
            dataMobil.setOwnerMobil(ownerMobil);
            return dataMobil;
        }
        
    }

    public class DataMobilDetailMapper implements RowMapper<DataMobil> {

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
