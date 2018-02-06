/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_ProdusenMobil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import static javafx.scene.input.KeyCode.T;
import javax.sql.DataSource;
import javax.swing.JComboBox;
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
public class ProdusenMobilJDBCTemplate implements ProdusenMobilDAO {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;
    
    private final String QUERY_PILIH_SEMUA ="SELECT * FROM tbl_produsen_mobil";
    private final String QUERY_PILIH_CARI = QUERY_PILIH_SEMUA + " where id_produsen_mobil=?";
    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String id_prod, String nama_prod) {
        String SQL = "INSERT INTO tbl_produsen_mobil ( id_produsen_mobil,nama_produsen) values (?,?)";

        jdbcTemplateObject.update(SQL, id_prod, nama_prod);
        System.out.println("Masuk fungsi update");
        return;
    }

    @Override
    public void edit(String id_prod, String nama_prod, String old_id_prod) {
        System.out.println("Masuk fungsi update");
        String SQL = "UPDATE tbl_produsen_mobil SET id_produsen_mobil=?,nama_produsen=? where id_produsen_mobil=?";
        jdbcTemplateObject.update(SQL, id_prod, nama_prod, old_id_prod);
        return;
    }
    
    

    @Override
    public ProdusenMobil getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProdusenMobil> listSemua() {
      return jdbcTemplateObject.query(QUERY_PILIH_SEMUA, new ProdusenMobilMapper());
    }
    
    public ProdusenMobil pilih_data(String kode) {
        List<ProdusenMobil> produsenMobils = jdbcTemplateObject.query(QUERY_PILIH_CARI, new ProdusenMobilMapper(), kode);
        
        if (!produsenMobils.isEmpty()) {
            return produsenMobils.get(0);
        } else {
            return null;
        }
    }
    
    public void delete(String id) {
        String SQL = "DELETE FROM tbl_produsen_mobil where id_produsen_mobil = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }

    public class ProdusenMobilMapper implements RowMapper<ProdusenMobil> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public ProdusenMobil mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProdusenMobil produsen = new ProdusenMobil();
            produsen.setId_produsen(rs.getString("id_produsen_mobil"));
            produsen.setNama_produsen(rs.getString("nama_produsen"));
            return produsen;
        }
    }

}
