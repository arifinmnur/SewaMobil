/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Jenis_Member_dan_Diskon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author ArieDZ_2
 */
@Repository
@Service
public class JenisMemberJDBCTemplate implements JenisMemberDAO {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;
    private final String QUERY_PILIH_SEMUA = "SELECT * FROM tbl_jenis_member_dan_diskon";
    private final String QUERY_PILIH = QUERY_PILIH_SEMUA + " WHERE id_jenis_member=?";

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void edit(int counter, String id_jenis_member, String nama_jenis_member, double diskon,String old_id_jenis_member) {
        System.out.println("Masuk fungsi update");
        String SQL = "UPDATE tbl_jenis_member_dan_diskon SET id_jenis_member=?,nama_jenis_member=?, diskon=?  WHERE id_jenis_member=?";

        jdbcTemplateObject.update(SQL, id_jenis_member, nama_jenis_member, diskon, old_id_jenis_member);

        return;}

    @Override
    public void delete(String id) {
        String SQL = "DELETE FROM tbl_jenis_member_dan_diskon WHERE id_jenis_member = ?";
        jdbcTemplateObject.update(SQL, id);
        return;}

    @Override
    public void create(int counter, String id_jenis_member, String nama_jenis_member, double diskon) {
        String SQL = "INSERT INTO tbl_jenis_member_dan_diskon (id_jenis_member, nama_jenis_member, diskon) values (?, ? ,?)";

        jdbcTemplateObject.update(SQL, id_jenis_member, nama_jenis_member, diskon);
        return; }

    @Override
    public String generate_id_kostumer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generate_id_member(String kode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JenisMember pilih_data_jenis_member(String kode) {
        List<JenisMember> jenisMembers = jdbcTemplateObject.query(QUERY_PILIH, new JenisMemberMapper(), kode);
        //return kostumers.get(0);

        if (!jenisMembers.isEmpty()) {
            return jenisMembers.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<JenisMember> listSemua() {

        List<JenisMember> jenisMembers = jdbcTemplateObject.query(QUERY_PILIH_SEMUA+" ORDER BY Counter ASC", new JenisMemberMapper());
        return jenisMembers;

    }

    public class JenisMemberMapper implements RowMapper<JenisMember> {

        @Override
        public JenisMember mapRow(ResultSet rs, int i) throws SQLException {
            JenisMember jenisMember = new JenisMember();
            jenisMember.setCounter(rs.getInt("counter"));
            jenisMember.setId_jenis_member(rs.getString("id_jenis_member"));
            jenisMember.setNama_jenis_member(rs.getString("nama_jenis_member"));
            jenisMember.setDiskon(rs.getDouble("diskon"));
            return jenisMember;
        }
    }
}
