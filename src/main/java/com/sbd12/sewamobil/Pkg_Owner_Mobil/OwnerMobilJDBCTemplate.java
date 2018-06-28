package com.sbd12.sewamobil.Pkg_Owner_Mobil;

import com.sbd12.sewamobil.Pkg_Merk_Mobil.MerkMobil;
import com.sbd12.sewamobil.Pkg_ProdusenMobil.ProdusenMobil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author resas
 */
@Repository
@Service
public class OwnerMobilJDBCTemplate implements OwnerMobilDAO {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    private final String SQL_PILIH_SEMUA = "SELECT * FROM tbl_owner_mobil";
    private final String SQL_PILIH_CARI = SQL_PILIH_SEMUA + " WHERE id_owner=?";
    private final String SQL_TAMBAH = "INSERT INTO tbl_owner_mobil (id_owner, no_ktp_ow,nama_ow,jenis_kelamin_ow,alamat_ow,no_telepon_ow) values (?, ?,?,?,?,?)";
    private final String SQL_EDIT = "UPDATE tbl_owner_mobil SET no_ktp_ow=?,nama_ow=?, jenis_kelamin_ow=?, alamat_ow=?, no_telepon_ow=? where id_owner=? ";  
    private final String SQL_HAPUS = "DELETE FROM tbl_owner_mobil where id_owner = ?";  
    private final String QUERY_PILIH_LIKE = SQL_PILIH_SEMUA + " WHERE id_owner like ? OR nama_ow like ? OR no_telepon_ow like ?";
    /**
     * Note
     *
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
    public void create(String id_owner, String no_ktp_ow,String nama_ow,String jenis_kelamin_ow,String alamat_ow,String no_telepon_ow) {
      jdbcTemplateObject.update( SQL_TAMBAH, id_owner, no_ktp_ow,nama_ow,jenis_kelamin_ow,alamat_ow,no_telepon_ow);
      return;
    }
    
    public List<OwnerMobil> pilih_data_like(String kode) {
        String id = "%" + kode + "%";
        List<OwnerMobil> ownerMobils = jdbcTemplateObject.query(QUERY_PILIH_LIKE, new OwnerMobilMapper(), id,id,id);
        return ownerMobils;
    }
    
    @Override
    public List<OwnerMobil> listSemua() {

        List<OwnerMobil> ownerMobils = jdbcTemplateObject.query(SQL_PILIH_SEMUA, new OwnerMobilMapper());
        return ownerMobils;
    }

    @Override
    public void edit(String id_owner, String no_ktp_ow, String nama_ow, String jenis_kelamin_ow, String alamat_ow, String no_telepon_ow) {
     System.out.println("Masuk fungsi update");
        
        jdbcTemplateObject.update(SQL_EDIT, no_ktp_ow, nama_ow, jenis_kelamin_ow, alamat_ow, no_telepon_ow, id_owner);

        return;
    }

    @Override
    public OwnerMobil pilih_data(String id) {
       List<OwnerMobil> ownerMobils = jdbcTemplateObject.query(SQL_PILIH_CARI, new OwnerMobilMapper(), id);
        
        if (!ownerMobils.isEmpty()) {
            return ownerMobils.get(0);
        } else {
            return null;
        }
    }
    
    public void delete(String id) {
        
        jdbcTemplateObject.update(SQL_HAPUS, id);
        return;
    }
    

    @Override
    public OwnerMobil getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String generate_id_owner() {
        /*SELECT MAX(CONV(SUBSTRING(id_kostumer,3,7),16,10)) FROM tbl_kostumer;*/
        String generate = "";
        final AtomicReference<String> maks = new AtomicReference<String>("0000000");
        final AtomicReference<Integer> maksInt = new AtomicReference<Integer>(1);
        int maksInts = 1;
        String maxS = "";

        String SQL = "SELECT MAX(CAST(CONV(SUBSTRING(id_owner,3,2),16,10)as int)) as maks FROM tbl_owner_mobil"
                + " WHERE id_owner LIKE 'OW%'";
        List<OwnerMobil> list = jdbcTemplateObject.query(SQL, new ResultSetExtractor<List<OwnerMobil>>() {
            @Override
            public List<OwnerMobil> extractData(ResultSet rs) throws SQLException, DataAccessException {
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
                maxS = "OW0" + maxS;
                break;
            case 2:
                maxS = "OW" + maxS;
                break;
            default:
                maxS = "OW" + maxS;
                break;
        }
        System.out.println("int :" + maksInts);
        System.out.println("Hex :" + maxS);
        return maxS;
    }
    public class OwnerMobilMapper implements RowMapper<OwnerMobil> {

        /**
         *
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public OwnerMobil mapRow(ResultSet rs, int rowNum) throws SQLException {
            OwnerMobil owner = new OwnerMobil();
            owner.setId_owner(rs.getString(1));
            owner.setNo_ktp_ow(rs.getString(2));
            owner.setNama_ow(rs.getString(3));
            owner.setJenis_Kelamin(rs.getString(4));
            owner.setAlamat_ow(rs.getString(5));
            owner.setNo_telepon_ow(rs.getString(6));
            return owner;
        }

    }
}
