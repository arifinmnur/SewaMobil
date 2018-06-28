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
    public void create(String name, Integer age);

    public DataMobil getNo_pol(Integer nopol);

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
