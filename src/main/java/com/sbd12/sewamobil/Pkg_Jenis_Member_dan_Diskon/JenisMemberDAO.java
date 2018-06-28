/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Jenis_Member_dan_Diskon;

/**
 *
 * @author ArieDZ_2
 */
import java.util.List;
import javax.sql.DataSource;
import javax.swing.JComboBox;
public interface JenisMemberDAO {
     /**
     * This is the method to be used to initialize database resources ie.
     * connection.
     *
     * @param ds
     */
    public void setDataSource(DataSource ds);

    public void edit(int counter,String id_jenis_member,String nama_jenis_member,double diskon,String old_id_jenis_member);

    public void delete(String id);

    public void create(int counter,String id_jenis_member,String nama_jenis_member,double diskon);

    public String generate_id_kostumer();

    public String generate_id_member(String kode);
    
    public JenisMember pilih_data_jenis_member(String kode);

    /**
     * This is the method to be used to list down all the records from the
     * Student table.
     *
     * @return
     */
    public List<JenisMember> listSemua();
}
