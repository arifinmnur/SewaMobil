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
import java.util.concurrent.atomic.AtomicReference;
import static javafx.scene.input.KeyCode.T;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
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
    private final String QUERY_PILIH_SEMUA = "SELECT *,TIMESTAMPDIFF(YEAR, tanggal_lahir_p, CURDATE()) AS age FROM tbl_pegawai";

    private final String QUERY_PILIH_CARI = QUERY_PILIH_SEMUA + " where id_pegawai=?";
    private final String QUERY_LOGIN = QUERY_PILIH_SEMUA + " WHERE username=? AND password=?";
    private final String QUERY_PILIH_LIKE = QUERY_PILIH_SEMUA + " where nama_p like ?";
    private final String SQL_TAMBAH = "insert into tbl_pegawai ( id_pegawai, no_ktp_p, nama_depan_p,nama_belakang_p,jenis_kelamin_p,alamat_p, tanggal_lahir_p,no_telepon_p,username,password,tanggal_join_p) values (?,?,?,?,?,?,?,?,?,?,?)";
    private final String SQL_DELETE = "delete from tbl_pegawai where id_pegawai = ?";
    private final String SQL_EDIT = "UPDATE tbl_pegawai SET no_ktp_p=?,nama_p=?, jenis_kelamin_p=?, alamat_p=?, no_telepon_p=? where id_pegawai=?";

    @Override
    public void create(
            String id_pegawai,
            String no_ktp_p,
            String nama_depan_p,
            String nama_belakang_p,
            String jenis_kelamin_p,
            String alamat_p,
            java.sql.Date tanggal_lahir_p,
            String no_telepon_p,
            String username,
            String password,
            java.sql.Date tanggal_join) {

        try {
            jdbcTemplateObject.update(SQL_TAMBAH, id_pegawai, no_ktp_p, nama_depan_p, nama_belakang_p, jenis_kelamin_p, alamat_p, tanggal_lahir_p, no_telepon_p, username, password, tanggal_join);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return;

        }
        System.out.println("Masuk fungsi update");
        return;
    }

    @Override
    public String generate_id_pegawai() {
        /*SELECT MAX(CONV(SUBSTRING(id_kostumer,3,7),16,10)) FROM tbl_kostumer;*/
        String generate = "";
        final AtomicReference<String> maks = new AtomicReference<String>("0000000");
        final AtomicReference<Integer> maksInt = new AtomicReference<Integer>(1);
        int maksInts = 1;
        String maxS = "";

        String SQL = "SELECT MAX(CAST(CONV(SUBSTRING(id_pegawai,3,3),16,10)as int)) as maks FROM tbl_pegawai"
                + " WHERE id_pegawai LIKE 'PG%'";
        List<Pegawai> list = jdbcTemplateObject.query(SQL, new ResultSetExtractor<List<Pegawai>>() {
            @Override
            public List<Pegawai> extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    maksInt.set(rs.getInt("maks"));
                }
                if (rs.wasNull()) {
                    maksInt.set(0);
                }
                return null;
            }
        });

        maksInts = maksInt.get() + 1;
        maxS = Integer.toHexString(maksInts).toUpperCase();

        int panjangMaxs = maxS.length();

        switch (panjangMaxs) {
            case 1:
                maxS = "PG00" + maxS;
                break;
            case 2:
                maxS = "PG0" + maxS;
                break;
            default:
                maxS = "PG" + maxS;
                break;
        }
        System.out.println("int :" + maksInts);
        System.out.println("Hex :" + maxS);
        return maxS;
    }

    @Override
    public List<Pegawai> listSemua() {
        List<Pegawai> pegawais = null;

        try {
            pegawais = jdbcTemplateObject.query(QUERY_PILIH_SEMUA, new PegawaiMapper());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return null;
        }
        return pegawais;
    }

    @Override
    public Pegawai pilih_data(String kode) {
        List<Pegawai> pegawais = null;

        try {
            pegawais = jdbcTemplateObject.query(QUERY_PILIH_CARI, new PegawaiMapper(), kode);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return null;
        }
        if (!pegawais.isEmpty()) {
            return pegawais.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Pegawai> pilih_data_like(String kode) {
        List<Pegawai> pegawais = null;
        try {
            pegawais = jdbcTemplateObject.query(QUERY_PILIH_LIKE, new PegawaiMapper(), "%" + kode + "%");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return null;
        }
        return pegawais;
    }

    @Override
    public Pegawai getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        try {
           jdbcTemplateObject.update(SQL_DELETE, id); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return;
        }
        
        return;
    }

    @Override
    public void edit(String id_pegawai, String no_ktp_p, String nama_p, String jenis_kelamin_p, String alamat_p, String no_telepon_p) {
        System.out.println("Masuk fungsi update");
        try {
            jdbcTemplateObject.update(SQL_EDIT, no_ktp_p, nama_p, jenis_kelamin_p, alamat_p, no_telepon_p, id_pegawai);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            return;

        }

        return;
    }

    @Override
    public Pegawai pilih_data_login(String username, String password) {
        List<Pegawai> pegawais=null;
        try {
           pegawais = jdbcTemplateObject.query(QUERY_LOGIN, new PegawaiMapper(), username, password); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception :" + e);
            return null;
        }
        
        if (!pegawais.isEmpty()) {
            return pegawais.get(0);
        } else {
            return null;
        }
        
    }

    public class PegawaiMapper implements RowMapper<Pegawai> {

        String admin = "";

        public PegawaiMapper(String admin) {

            this.admin = admin;
        }

        public PegawaiMapper() {
        }

        @Override
        public Pegawai mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pegawai pegawai = new Pegawai();
            pegawai.setId_pegawai(rs.getString("id_pegawai"));
            pegawai.setNo_ktp_p(rs.getString("no_ktp_p"));
            pegawai.setNama_depan_p(rs.getString("nama_depan_p"));
            pegawai.setNama_belakang_p(rs.getString("nama_belakang_p"));
            pegawai.setAlamat_p(rs.getString("alamat_p"));
            pegawai.setJenis_kelamin_p(rs.getString("jenis_kelamin_p"));
            pegawai.setTanggal_lahir_p(rs.getDate("tanggal_lahir_p"));
            pegawai.setUmur(rs.getInt("age"));
            pegawai.setNo_telepon_p(rs.getString("no_telepon_p"));
            if (this.admin.equalsIgnoreCase("Admin")) {
                pegawai.setUsername(rs.getString("username"));
                pegawai.setPassword(rs.getString("password"));
            }
            pegawai.setTanggal_join_p(rs.getDate("tanggal_join_p"));
            return pegawai;
        }
    }
}
