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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Repository
@Service

public class JenisMobilJDBCTemplate implements JenisMobilDAO {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    private final String SQL_PILIH_SEMUA = "SELECT * FROM tbl_jenis_mobil";
    private final String QUERY_PILIH_CARI = SQL_PILIH_SEMUA + " WHERE id_jenis_mobil=?";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String Id_jenis_mobil, String Nama_jenis_mobil, double Harga_mobil) {
        String SQL = "INSERT INTO tbl_jenis_mobil (id_jenis_mobil, nama_jenis, harga) values (?, ? ,?)";

        jdbcTemplateObject.update(SQL, Id_jenis_mobil, Nama_jenis_mobil, Harga_mobil);
        return;
    }

    public List<JenisMobil> listSemua() {
        List<JenisMobil> jenis_mobils = jdbcTemplateObject.query(SQL_PILIH_SEMUA, new JenisMobilMapper());
        return jenis_mobils;
    }

    @Override
    public JenisMobil getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(String id_jenis_mobil, String nama_jenis_mobil, double harga_mobil, String old_Id_jenis_mobil) {
        System.out.println("Masuk fungsi update");
        String SQL = "UPDATE tbl_jenis_mobil SET id_jenis_mobil=?,nama_jenis=?, harga=?  WHERE id_jenis_mobil=?";

        jdbcTemplateObject.update(SQL, id_jenis_mobil, nama_jenis_mobil, harga_mobil, old_Id_jenis_mobil);

        return;
    }

    public void delete(String id) {
        String SQL = "DELETE FROM tbl_jenis_mobil where id_jenis_mobil = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }

    public JenisMobil pilih_data(String kode) {

        List<JenisMobil> jenisMobils = jdbcTemplateObject.query(QUERY_PILIH_CARI, new JenisMobilMapper(), kode);
        //return pegawais.get(0);
        if (!jenisMobils.isEmpty()) {
            return jenisMobils.get(0);
        } else {
            return null;
        }
    }

    public class JenisMobilMapper implements RowMapper<JenisMobil> {

        @Override
        public JenisMobil mapRow(ResultSet rs, int i) throws SQLException {
            JenisMobil jenisMobil = new JenisMobil();
            jenisMobil.setId_jenis_mobil(rs.getString("Id_jenis_mobil"));
            jenisMobil.setNama_jenis_mobil(rs.getString("Nama_Jenis"));
            jenisMobil.setHarga_mobil(rs.getDouble("Harga"));

            return jenisMobil;
        }
    }
}
