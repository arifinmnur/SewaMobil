package com.sbd12.sewamobil.Pkg_Kostumer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ArieDZ_2
 */
import com.sbd12.sewamobil.Pkg_Jenis_Member_dan_Diskon.JenisMember;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import static javafx.scene.input.KeyCode.T;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
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
    private final String QUERY_PILIH_SEMUA = "SELECT kst.*,TIMESTAMPDIFF(YEAR, kst.tanggal_lahir_k, CURDATE()) AS age, jm.nama_jenis_member,jm.diskon "
            + " FROM tbl_kostumer AS kst"
            + " INNER JOIN tbl_jenis_member_dan_diskon AS jm ON jm.id_jenis_member=kst.jenis_member";
    private final String QUERY_ORDER_BY = " ORDER BY id_kostumer ASC";
    private final String QUERY_PILIH_CARI = QUERY_PILIH_SEMUA + " WHERE id_kostumer=?";
    private final String QUERY_PILIH_LIKE = QUERY_PILIH_SEMUA + " WHERE nama_k like ?";

    private final String SQL_INPUT_BARU = "INSERT INTO tbl_kostumer ( "
            + "id_kostumer, "
            + "no_ktp, "
            + "id_member, "
            + "jenis_member, "
            + "nama_depan_k, "
            + "nama_belakang_k, "
            + "jenis_kelamin_k,  "
            + "alamat_k,  "
            + "tanggal_lahir_k,  "
            + "no_telepon_k, "
            + "tanggal_join) values (?,?,?,?,?,?,?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE tbl_kostumer SET "
            + " no_ktp=?, "
            + " id_member=?, "
            + " jenis_member=?, "
            + " nama_depan_k=?, "
            + " nama_belakang_k=?, "
            + " jenis_kelamin_k=?, "
            + " alamat_k=?, "
            + " tanggal_lahir_k=?, "
            + " no_telepon_k=? "
            + " where id_kostumer=?";

    @Override
    public String generate_id_kostumer() {
        /*SELECT MAX(CONV(SUBSTRING(id_kostumer,3,7),16,10)) FROM tbl_kostumer;*/
        String generate = "";
        final AtomicReference<String> maks = new AtomicReference<String>("0000000");
        final AtomicReference<Integer> maksInt = new AtomicReference<Integer>(1);
        int maksInts = 1;
        String maxS = "";

        String SQL = "SELECT MAX(CAST(CONV(SUBSTRING(id_kostumer,3,7),16,10)as int)) as maks FROM tbl_kostumer"
                + " WHERE id_kostumer LIKE 'KZ%'";
        List<Kostumer> list = jdbcTemplateObject.query(SQL, new ResultSetExtractor<List<Kostumer>>() {
            @Override
            public List<Kostumer> extractData(ResultSet rs) throws SQLException, DataAccessException {
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
                maxS = "KZ000000" + maxS;
                break;
            case 2:
                maxS = "KZ00000" + maxS;
                break;
            case 3:
                maxS = "KZ0000" + maxS;
                break;
            case 4:
                maxS = "KZ000" + maxS;
                break;
            case 5:
                maxS = "KZ00" + maxS;
                break;
            case 6:
                maxS = "KZ0" + maxS;
                break;
            default:
                maxS = "KZ" + maxS;
                break;
        }
        System.out.println("int :" + maksInts);
        System.out.println("Hex :" + maxS);
        return maxS;
    }

    @Override
    public String generate_id_member(String kode) {
        /*SELECT MAX(CONV(SUBSTRING(id_kostumer,3,7),16,10)) FROM tbl_kostumer;*/
        String generate = "";
        final AtomicReference<String> maks = new AtomicReference<String>("0000000");
        final AtomicReference<Integer> maksInt = new AtomicReference<Integer>(1);
        int maksInts = 1;
        String maxS = "";

        String SQL = "SELECT MAX(CAST(CONV(SUBSTRING(id_member,5,5),16,10)as int)) as maks FROM tbl_kostumer"
                + " WHERE id_member LIKE '" + kode + "%' ";
        List<Kostumer> list = jdbcTemplateObject.query(SQL, new ResultSetExtractor<List<Kostumer>>() {
            @Override
            public List<Kostumer> extractData(ResultSet rs) throws SQLException, DataAccessException {
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
                maxS = kode + "0000" + maxS;
                break;
            case 2:
                maxS = kode + "000" + maxS;
                break;
            case 3:
                maxS = kode + "00" + maxS;
                break;
            case 4:
                maxS = kode + "0" + maxS;
                break;
            default:
                maxS = kode + "" + maxS;
                break;
        }
        System.out.println("int :" + maksInts);
        System.out.println("Hex :" + maxS);
        return maxS;
    }

    @Override
    public void create(
            String id_kostumer,
            String no_ktp,
            String id_member,
            String status_member,
            String nama_depan_k,
            String nama_belakang_k,
            String jenis_kelamin_k,
            String alamat_k,
            java.sql.Date tanggal_lahir_k,
            String no_telepon_k,
            java.sql.Date tanggal_join) {

        jdbcTemplateObject.update(SQL_INPUT_BARU, id_kostumer,
                no_ktp,
                id_member,
                status_member,
                nama_depan_k,
                nama_belakang_k,
                jenis_kelamin_k,
                alamat_k,
                tanggal_lahir_k,
                no_telepon_k,
                tanggal_join);
        System.out.println("Masuk fungsi update");
        return;
    }

    @Override
    public List<Kostumer> listSemua() {

        List<Kostumer> kostumers = jdbcTemplateObject.query(QUERY_PILIH_SEMUA + QUERY_ORDER_BY, new KostumerMapper());
        return kostumers;
    }

    public Kostumer pilih_data(String kode) {

        List<Kostumer> kostumers = jdbcTemplateObject.query(QUERY_PILIH_CARI, new KostumerMapper(), kode);
        //return kostumers.get(0);
        
        if (!kostumers.isEmpty()) {
            return kostumers.get(0);
        } else {
            return null;
        }
    }

    public List<Kostumer> pilih_data_like(String kode) {

        List<Kostumer> kostumers = jdbcTemplateObject.query(QUERY_PILIH_LIKE, new KostumerMapper(), "%" + kode + "%");
        return kostumers;
    }

    @Override
    public Kostumer getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        String SQL = "delete from tbl_kostumer where id_kostumer = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }

    @Override
    public void edit(
            String id_kostumer,
            String no_ktp,
            String id_member,
            String status_member,
            String nama_depan_k,
            String nama_belakang_k,
            String jenis_kelamin_k,
            String alamat_k,
            java.sql.Date tanggal_lahir_k,
            String no_telepon_k) {

        System.out.println("Masuk fungsi update");

        jdbcTemplateObject.update(SQL_UPDATE, no_ktp, id_member, status_member, nama_depan_k, nama_belakang_k, jenis_kelamin_k, alamat_k, tanggal_lahir_k, no_telepon_k, id_kostumer);

        return;
    }

    @Override
    public List<JenisMember> combo_box_jenis_member(JComboBox Combo) {
        String SQL = "SELECT * FROM tbl_jenis_member_dan_diskon ORDER BY counter ASC";

        Combo.removeAllItems();
        Combo.addItem("Pilih (Jenis Member)");
        List<JenisMember> list = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            JenisMember jenis_Member = new JenisMember();
            jenis_Member.setCounter(rs.getInt("counter"));
            jenis_Member.setId_jenis_member(rs.getString("id_jenis_member"));
            jenis_Member.setNama_jenis_member(rs.getString("nama_jenis_member"));
            jenis_Member.setDiskon(rs.getDouble("diskon"));
            Combo.addItem(rs.getString("nama_jenis_member"));
            return jenis_Member;
        });

        return list;
    }

    @Override
    public JenisMember pilih_data_jenis_member(String kode) {
        String SQL = "SELECT * FROM tbl_jenis_member_dan_diskon WHERE id_jenis_member=?  ORDER BY counter ASC ";
        List<JenisMember> jenis_Members = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            JenisMember jenis_Member = new JenisMember();
            jenis_Member.setCounter(rs.getInt("counter"));
            jenis_Member.setId_jenis_member(rs.getString("id_jenis_member"));
            jenis_Member.setNama_jenis_member(rs.getString("nama_jenis_member"));
            jenis_Member.setDiskon(rs.getDouble("diskon"));
            return jenis_Member;
        }, kode);
        return jenis_Members.get(0);
    }

    public class KostumerMapper implements RowMapper<Kostumer> {

        @Override
        public Kostumer mapRow(ResultSet rs, int rowNum) throws SQLException {

            Kostumer kostumer = new Kostumer();
            kostumer.setId_kostumer(rs.getString("id_kostumer"));
            kostumer.setNo_ktp(rs.getString("no_ktp"));
            kostumer.setId_member(rs.getString("id_member"));
            kostumer.setId_jenis_member(rs.getString("jenis_member"));
            kostumer.setNama_jenis_member(rs.getString("nama_jenis_member"));
            kostumer.setNama_depan_k(rs.getString("nama_depan_k"));
            kostumer.setNama_belakang_k(rs.getString("nama_belakang_k"));
            kostumer.setJenis_kelamin_k(rs.getString("jenis_kelamin_k"));
            kostumer.setAlamat_k(rs.getString("alamat_k"));
            kostumer.setTanggal_lahir_k(rs.getDate("tanggal_lahir_k"));
            kostumer.setNo_telepon_k(rs.getString("no_telepon_k"));
            kostumer.setTanggal_join(rs.getDate("tanggal_join"));
            kostumer.setUmur(rs.getInt("age"));
            kostumer.setDiskon(rs.getDouble("diskon"));
            return kostumer;
        }
    }

    
}
