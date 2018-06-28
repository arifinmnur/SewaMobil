package com.sbd12.sewamobil.Pkg_Data_Mobil;

import com.sbd12.sewamobil.Pkg_Merk_Mobil.MerkMobil;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author resas
 */
public interface DataMobilDAO {

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
    public void create(String no_pol, String id_merk,String id_owner);
    public void delete(String id);
    public void edit(String no_pol, String id_merk,String id_owner,String old_no_pol);
    public DataMobil getNo_pol(Integer nopol);
    public DataMobil pilih_data(String kode);
    /**
     * This is the method to be used to list down all the records from the
     * Student table.
     *
     * @return
     */
    public List<DataMobil> listSemua();

    public List<DataMobil> listSemua_berdasar_noTransaksi(String noTransaksi);

    public List<DataMobil> pilih_data_like(String kode);

}
