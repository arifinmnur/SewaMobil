/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Owner_Mobil;

import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author resas
 */
public interface OwnerMobilDAO {

    /**
     * This is the method to be used to initialize database resources ie.
     * connection.
     *
     * @param ds
     */
    public void setDataSource(DataSource ds);

    /**
     * This is the method to be used to create a record in the Student table.
     *
     * @param name
     * @param age
     */
    public void create(String id_owner, String no_ktp_ow, String nama_ow, String jenis_kelamin_ow, String alamat_ow, String no_telepon_ow);

    public void edit(String id_owner, String no_ktp_ow, String nama_ow, String jenis_kelamin_ow, String alamat_ow, String no_telepon_ow);

    public OwnerMobil getId(Integer id);

    public OwnerMobil pilih_data(String id);

    /**
     * This is the method to be used to list down all the records from the
     * Student table.
     *
     * @return
     */
    public List<OwnerMobil> listSemua();

}
