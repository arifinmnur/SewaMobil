package com.sbd12.sewamobil.Pkg_Data_Transaksi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ArieDZ_2
 */
import com.sbd12.sewamobil.Pkg_Data_Mobil.DataMobil;
import com.sbd12.sewamobil.Pkg_Data_Pegawai.Pegawai;
import com.sbd12.sewamobil.Pkg_Jenis_Mobil.JenisMobil;
import com.sbd12.sewamobil.Pkg_Merk_Mobil.MerkMobil;
import com.sbd12.sewamobil.Pkg_ProdusenMobil.ProdusenMobil;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import static javafx.scene.input.KeyCode.T;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Repository
@Service
public class DataTransaksiJDBCTemplate implements DataTransaksiDAO {

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
    
    @Value("${jdbc.url}")
    private String jdbcurl;

    public String getJdbcurl() {
        return jdbcurl;
    }
    
    
    /*private final String QUERY_PILIH_SEMUA = "SELECT dt.*,mm.nama_mobil,pg.nama_p,kt.nama_k,pm.*,jm.harga "
            + "FROM tbl_data_transaksi AS DT "
            + "JOIN tbl_data_mobil AS DM ON dt.no_pol=dm.no_pol "
            + "JOIN tbl_merk_mobil AS mm ON dm.id_merk_mobil=mm.id_merk_mobil "
            + "JOIN tbl_produsen_mobil AS pm on mm.id_produsen_mobil=pm.id_produsen_mobil "
            + "JOIN tbl_pegawai AS PG ON dt.id_pegawai=pg.id_pegawai "
            + "JOIN tbl_kostumer AS KT ON dt.id_kostumer=kt.id_kostumer "
            + "JOIN tbl_jenis_mobil AS jm ON mm.id_jenis_mobil=jm.id_jenis_mobil";*/

    private final String QUERY_PILIH_SEMUA = " SELECT dt.*,(dt.harga_sebelum_diskon-dt.harga_diskon) as harga_total,"
            + " CONCAT(kt.nama_depan_k,' ',kt.nama_belakang_k) as nama_kostumer, "
            + " CONCAT(pg.nama_depan_p,' ',pg.nama_belakang_p) as nama_petugas, "
            + " DATEDIFF(tglkembali,tglpinjam)+1 as lamaPinjam"
            + " FROM tbl_data_transaksi AS DT "
            + " INNER JOIN tbl_pegawai AS PG ON dt.id_pegawai=pg.id_pegawai "
            + " INNER JOIN tbl_kostumer AS KT ON dt.id_kostumer=kt.id_kostumer ";
    
    private final String QUERY_PILIH_SEMUA_PENGEMBALIAN = " SELECT dtp.*,"
            + " CONCAT(kt.nama_depan_k,' ',kt.nama_belakang_k) AS nama_kostumer,"
            + " CONCAT(pg.nama_depan_p,' ',pg.nama_belakang_p) AS nama_petugas, "
            + " DATEDIFF(dtp.tglpengembalian,dt.tglkembali) AS Telat,"
            + " dt.`tglkembali` AS tglkembali_seharusnya"
            + " FROM tbl_pengembalian AS dtp"
            + " INNER JOIN tbl_data_transaksi AS dt ON dt.no_transaksi=dtp.no_transaksi"
            + " INNER JOIN tbl_kostumer AS KT ON dt.id_kostumer=kt.id_kostumer"
            + " INNER JOIN tbl_pegawai AS PG ON dtp.id_pegawai=pg.id_pegawai";

    private final String QUERY_PILIH_CARI = QUERY_PILIH_SEMUA + " WHERE dt.no_transaksi=?";
    private final String QUERY_PILIH_LIKE = QUERY_PILIH_SEMUA + " WHERE kt.nama_k like ?";

    @Override
    public void tambah_pembayaran(String kode_transaksi, String jenis_pembayaran, double nominal, String keterangan) {
        final AtomicReference<Integer> maksInt = new AtomicReference<Integer>(1);
        int maksInts = 1;
        final String SQL_CEK_MAKS_PEMBAYARAN = "SELECT MAX(id_pembayaran) AS maks FROM tbl_pembayaran WHERE no_transaksi=?";
        final String SQL_TAMBAH_PEMBAYARAN = "INSERT INTO tbl_pembayaran(no_transaksi,id_pembayaran,jenis_pembayaran,jumlah,tglpembayaran,keterangan) VALUES (?,?,?,?,now(),?)";

        List<Integer> list = jdbcTemplateObject.query(SQL_CEK_MAKS_PEMBAYARAN, new ResultSetExtractor<List<Integer>>() {
            @Override
            public List<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    maksInt.set(rs.getInt("maks"));
                }
                if (rs.wasNull()) {
                    maksInt.set(0);
                }
                return null;
            }
        },kode_transaksi);
        maksInts = maksInt.get() + 1;

        jdbcTemplateObject.update(SQL_TAMBAH_PEMBAYARAN, kode_transaksi, maksInts, jenis_pembayaran,nominal ,keterangan);
        System.out.println("Masuk Fungsi 'tambah_pembayaran' dgn koode_transaksi=" + kode_transaksi + ", id_pembayaran=" + maksInts);
    }

    @Override
    public String generate_id_tranksaksi() {
        /*SELECT MAX(CONV(SUBSTRING(id_kostumer,3,7),16,10)) FROM tbl_kostumer;*/
        String generate = "";
        final AtomicReference<String> maks = new AtomicReference<String>("0000000");
        final AtomicReference<Integer> maksInt = new AtomicReference<Integer>(1);
        int maksInts = 1;
        String maxS = "";

        String SQL = "SELECT MAX(CAST(CONV(SUBSTRING(no_transaksi,4,7),16,10)as int)) as maks FROM tbl_data_Transaksi"
                + " WHERE no_transaksi LIKE 'TRX%'";
        List<DataTransaksi> list = jdbcTemplateObject.query(SQL, new ResultSetExtractor<List<DataTransaksi>>() {
            @Override
            public List<DataTransaksi> extractData(ResultSet rs) throws SQLException, DataAccessException {
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
                maxS = "TRX000000" + maxS;
                break;
            case 2:
                maxS = "TRX00000" + maxS;
                break;
            case 3:
                maxS = "TRX0000" + maxS;
                break;
            case 4:
                maxS = "TRX000" + maxS;
                break;
            case 5:
                maxS = "TRX00" + maxS;
                break;
            case 6:
                maxS = "TRX0" + maxS;
                break;
            default:
                maxS = "TRX" + maxS;
                break;
        }
        System.out.println("int :" + maksInts);
        System.out.println("Hex :" + maxS);
        return maxS;
    }

    @Override
    public void create(String no_transaksi, String id_kostumer, String id_pegawai, Date tglpinjam, int lamaPinjam, double harga, double harga_diskon, String status) {
        String SQL = "INSERT INTO tbl_data_transaksi (no_transaksi, id_kostumer, id_pegawai,tglpinjam,tglkembali,harga_sebelum_diskon,harga_diskon,tgltransaksi,status) values (?,?,?,?,ADDDATE(tglpinjam,?),?,?,now(),?)";

        jdbcTemplateObject.update(SQL, no_transaksi, id_kostumer, id_pegawai, tglpinjam, lamaPinjam, harga, harga_diskon, status);
        System.out.println("Masuk fungsi update");
        return;
    }
    
    public void create_pengembalian(String no_transaksi, String id_pegawai, double denda) {
        String SQL = "INSERT INTO tbl_pengembalian (no_transaksi,id_pegawai,denda,tglpengembalian) values (?,?,?,now())";

        jdbcTemplateObject.update(SQL, no_transaksi, id_pegawai,denda);
        System.out.println("Masuk fungsi update");
        return;
    }
    
    public void update_transaksi_selesai(String no_transaksi)
    {
        String SQL_UPDATE_TRANSAKSI_SELESAI="UPDATE tbl_data_Transaksi SET status='SELESAI' WHERE no_transaksi=?";
        jdbcTemplateObject.update(SQL_UPDATE_TRANSAKSI_SELESAI, no_transaksi);

        return;
    }
    
    public void update_detail_transaksi(String no_transaksi, String id_petugas, double denda, String catatan)
    {
        String SQL_UPDATE_DETAIL_TRANSAKSI="UPDATE detail_transaksi SET id_petugas_pengembalian=?, tglkembali=now(), denda=? ,catatan=? WHERE no_transaksi=?";
        jdbcTemplateObject.update(SQL_UPDATE_DETAIL_TRANSAKSI,id_petugas,denda,catatan, no_transaksi);

        return;
    }

    @Override
    public void tambah_detail_transaksi(String noTransaksi, ArrayList<String> list) {
        String SQL = "INSERT INTO detail_transaksi ( no_transaksi, no_pol) VALUES (?, ?)";
        for (String selectedBarang : list) {
            jdbcTemplateObject.update(SQL, noTransaksi, selectedBarang);
            System.out.println("Masuk fungsi update");
        }
        return;
    }

    @Override
    public List<DataTransaksi> listSemua() {

        List<DataTransaksi> dataTransaksis = jdbcTemplateObject.query(QUERY_PILIH_SEMUA + " ORDER BY dt.no_transaksi ASC", new DataTransaksiMapper());
        return dataTransaksis;
    }
    
    public List<DataTransaksi> listSemua_belum_selesai() {

        List<DataTransaksi> dataTransaksis = jdbcTemplateObject.query(QUERY_PILIH_SEMUA + " WHERE dt.status='BELUM SELESAI' ORDER BY dt.no_transaksi ASC", new DataTransaksiMapper());
        return dataTransaksis;
    }
    
    public List<DataPengembalian> listSemua_pengembalian() {

        List<DataPengembalian> dataPengembalians = jdbcTemplateObject.query(QUERY_PILIH_SEMUA_PENGEMBALIAN + " ORDER BY dtp.tglpengembalian ASC", new DataPengembalianMapper());
        return dataPengembalians;
    }

    public DataTransaksi pilih_data(String kode) {

        List<DataTransaksi> dataTransaksis = jdbcTemplateObject.query(QUERY_PILIH_CARI, new DataTransaksiMapper(), kode);
        if (!dataTransaksis.isEmpty()) {
            return dataTransaksis.get(0);
        } else {
            return null;
        }
    }
    
     public DataPengembalian pilih_data_pengembalian(String kode) {

        List<DataPengembalian> dataPengembalians = jdbcTemplateObject.query(QUERY_PILIH_SEMUA_PENGEMBALIAN + " WHERE dtp.no_transaksi=?", new DataPengembalianMapper(), kode);
        if (!dataPengembalians.isEmpty()) {
            return dataPengembalians.get(0);
        } else {
            return null;
        }
    }

    public List<DataTransaksi> pilih_data_like(String kode) {

        List<DataTransaksi> dataTransaksis = jdbcTemplateObject.query(QUERY_PILIH_LIKE, new DataTransaksiMapper(), "%" + kode + "%");
        return dataTransaksis;
    }
    

    @Override
    public DataTransaksi getId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DataMobil> combo_box_data_mobil(JComboBox Combo) {
        String SQL = "select mm.*,pm.nama_mobil,jm.nama_ow,jmm.harga"
                + " FROM tbl_data_mobil        AS mm "
                + " JOIN tbl_merk_mobil        AS PM   ON mm.id_merk_mobil=pm.id_merk_mobil"
                + " JOIN tbl_owner_mobil       AS jm   ON mm.id_owner=jm.id_owner"
                + " JOIN tbl_jenis_mobil AS jmm ON pm.id_jenis_mobil=jmm.id_jenis_mobil ";

        Combo.removeAllItems();
        Combo.addItem("Pilih (Mobil)");
        List<DataMobil> list = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            DataMobil dataMobil = new DataMobil();
            dataMobil.setNo_pol(rs.getString("no_pol"));
            dataMobil.setId_merk_mobil(rs.getString("id_merk_mobil"));
            dataMobil.setId_owner(rs.getString("id_owner"));
            dataMobil.setNama_mobil(rs.getString("nama_mobil"));
            dataMobil.setNamaow(rs.getString("nama_ow"));
            dataMobil.setHarga_perhari(rs.getDouble("harga"));
            Combo.addItem(rs.getString("no_pol") + "-" + rs.getString("nama_mobil"));
            return dataMobil;
        });

        return list;
    }

    @Override
    public List<DataMobil> combo_box_data_mobil_plus(JComboBox Combo, String kode) {
        String SQL = "select mm.*,pm.nama_mobil,jm.nama_ow"
                + " FROM tbl_data_mobil        AS mm "
                + " JOIN tbl_merk_mobil        AS PM   ON mm.id_merk_mobil=pm.id_merk_mobil"
                + " JOIN tbl_owner_mobil       AS jm   ON mm.id_owner=jm.id_owner where mm.id_merk_mobil=?";

        Combo.removeAllItems();
        Combo.addItem("Pilih (Mobil)");
        List<DataMobil> list = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            DataMobil dataMobil = new DataMobil();
            dataMobil.setNo_pol(rs.getString("no_pol"));
            dataMobil.setId_merk_mobil(rs.getString("id_merk_mobil"));
            dataMobil.setId_owner(rs.getString("id_owner"));
            dataMobil.setNama_mobil(rs.getString("nama_mobil"));
            dataMobil.setNamaow(rs.getString("nama_ow"));
            Combo.addItem(rs.getString("no_pol") + "-" + rs.getString("nama_mobil"));
            return dataMobil;
        }, kode);

        return list;
    }

    @Override
    public List<ProdusenMobil> combo_box_produsen_mobil(JComboBox Combo) {
        String SQL = "SELECT * FROM tbl_produsen_mobil";

        Combo.removeAllItems();
        Combo.addItem("Pilih (Produsen)");
        List<ProdusenMobil> list = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            ProdusenMobil produsenMobil = new ProdusenMobil();
            produsenMobil.setId_produsen(rs.getString("id_produsen_mobil"));
            produsenMobil.setNama_produsen(rs.getString("nama_produsen"));
            Combo.addItem(rs.getString("nama_produsen"));
            return produsenMobil;
        });

        return list;
    }

    @Override
    public List<MerkMobil> combo_box_merk_mobil(JComboBox Combo) {
        String SQL = "select mm.*,pm.nama_produsen,jm.nama_jenis"
                + " FROM tbl_merk_mobil        AS mm "
                + " JOIN tbl_produsen_mobil    AS PM   ON mm.id_produsen_mobil=pm.id_produsen_mobil"
                + " JOIN tbl_jenis_mobil       AS jm   ON mm.id_jenis_mobil=jm.id_jenis_mobil";

        Combo.removeAllItems();
        Combo.addItem("Pilih (Merk Mobil)");
        List<MerkMobil> list = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            MerkMobil merkMobil = new MerkMobil();
            merkMobil.setId_merk_mobil(rs.getString("id_Merk_mobil"));
            merkMobil.setId_produsen_mobil(rs.getString("id_produsen_mobil"));
            merkMobil.setId_jenis(rs.getString("id_jenis_mobil"));
            merkMobil.setNama_Merk_Mobil(rs.getString("nama_mobil"));
            merkMobil.setNama_produsen_mobil(rs.getString("nama_produsen"));
            merkMobil.setNama_jenis(rs.getString("nama_jenis"));
            Combo.addItem(rs.getString("nama_mobil"));
            return merkMobil;
        });

        return list;
    }

    @Override
    public List<MerkMobil> combo_box_merk_mobil_plus(JComboBox Combo, String kode) {
        String SQL = "select mm.*,pm.*,jm.nama_jenis"
                + " FROM tbl_merk_mobil        AS mm "
                + " JOIN tbl_produsen_mobil    AS PM   ON mm.id_produsen_mobil=pm.id_produsen_mobil"
                + " JOIN tbl_jenis_mobil       AS jm   ON mm.id_jenis_mobil=jm.id_jenis_mobil where mm.id_produsen_mobil=?";

        Combo.removeAllItems();
        Combo.addItem("Pilih (Merk Mobil)");
        List<MerkMobil> list = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            MerkMobil merkMobil = new MerkMobil();
            merkMobil.setId_merk_mobil(rs.getString("id_Merk_mobil"));
            merkMobil.setId_produsen_mobil(rs.getString("id_produsen_mobil"));
            merkMobil.setId_jenis(rs.getString("id_jenis_mobil"));
            merkMobil.setNama_Merk_Mobil(rs.getString("nama_mobil"));
            merkMobil.setNama_produsen_mobil(rs.getString("nama_produsen"));
            merkMobil.setNama_jenis(rs.getString("nama_jenis"));
            Combo.addItem(rs.getString("nama_mobil"));
            return merkMobil;
        }, kode);
        return list;
    }

    @Override
    public List<Pegawai> combo_box_pegawai(JComboBox Combo) {
        String SQL = "SELECT * FROM tbl_pegawai";

        Combo.removeAllItems();
        Combo.addItem("Pilih (Pegawai)");
        List<Pegawai> list = jdbcTemplateObject.query(SQL, (ResultSet rs, int i) -> {
            Pegawai pegawai = new Pegawai();
            pegawai.setId_pegawai(rs.getString("id_pegawai"));
            pegawai.setNo_ktp_p(rs.getString("no_ktp_p"));
            pegawai.setNama_lengkap_p(rs.getString("nama_depan_p"));
            pegawai.setAlamat_p(rs.getString("alamat_p"));
            pegawai.setJenis_kelamin_p(rs.getString("jenis_kelamin_p"));
            pegawai.setNo_telepon_p(rs.getString("no_telepon_p"));
            Combo.addItem(rs.getString("nama_depan_p"));
            return pegawai;
        });

        return list;
    }

    /*
    @Override
    public void delete(String id)
    {
        String SQL = "delete from tbl_merk_mobil where id_merk_mobil = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }
    
     @Override
    public void edit(String no_transaksi, String id_kostumer, String no_pol, String id_pegawai, Date tglpinjam, Date tglkembali,double hargatotal)
    {
      /*  System.out.println("Masuk fungsi update");
        String SQL="UPDATE tbl_merk_mobil SET id_produsen_mobil=?,id_jenis_mobil=?,nama_mobil=? where id_merk_mobil=?";
        
        jdbcTemplateObject.update(SQL,idProd,idJenis,namaMerk,id);
         
         return;
    }*/
    @Override
    public void edit(String no_transaksi, String id_kostumer, String no_pol, String id_pegawai, Date tglpinjam, Date tglkembali, double hargatotal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        String SQL = "delete from tbl_data_transaksi where no_transaksi = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }
    
    public void delete_pengembalian(String id) {
        String SQL = "DELETE FROM tbl_pengembalian WHERE no_transaksi = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }
    
    

    @Override
    public double ambil_selected_harga(ArrayList<String> selectedItems) throws ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class DataTransaksiMapper implements RowMapper<DataTransaksi> {

        @Override
        public DataTransaksi mapRow(ResultSet rs, int rowNum) throws SQLException {
            DataTransaksi dataTransaksi = new DataTransaksi();
            dataTransaksi.setNo_transaksi(rs.getString("no_transaksi"));
            dataTransaksi.setId_kostumer(rs.getString("id_kostumer"));
            dataTransaksi.setId_pegawai(rs.getString("id_pegawai"));
            // dataTransaksi.setId_produsen_mobil(rs.getString("id_produsen_mobil"));
            //dataTransaksi.setNama_produsen(rs.getString("nama_produsen"));
            //dataTransaksi.setNo_pol(rs.getString("no_pol"));
            dataTransaksi.setNama_kostumer(rs.getString("nama_kostumer"));
            dataTransaksi.setNama_pegawai(rs.getString("nama_petugas"));
            //dataTransaksi.setNama_mobil(rs.getString("nama_mobil"));
            dataTransaksi.setTglpinjam(rs.getDate("tglpinjam"));
            dataTransaksi.setTglkembali(rs.getDate("tglkembali"));
            dataTransaksi.setHarga(rs.getDouble("harga_sebelum_diskon"));
            dataTransaksi.setHarga_diskon(rs.getDouble("harga_diskon"));
            dataTransaksi.setHarga_total(rs.getDouble("harga_total"));
            dataTransaksi.setTgltransaksi(rs.getTimestamp("tgltransaksi"));
            dataTransaksi.setLamaPinjam(rs.getInt("lamapinjam"));
            dataTransaksi.setStatus(rs.getString("status"));

            return dataTransaksi;
        }
    }
    
    public class DataPengembalianMapper implements RowMapper<DataPengembalian> {

        @Override
        public DataPengembalian mapRow(ResultSet rs, int rowNum) throws SQLException {
            DataPengembalian dataPengembalian = new DataPengembalian();
            dataPengembalian.setNo_transaksi(rs.getString("no_transaksi"));
            dataPengembalian.setId_pegawai(rs.getString("id_pegawai"));
            dataPengembalian.setDenda(rs.getDouble("denda"));
            dataPengembalian.setTglpengembalian(rs.getTimestamp("tglpengembalian"));
            dataPengembalian.setNama_kostumer(rs.getString("nama_kostumer"));
            dataPengembalian.setNama_pegawai(rs.getString("nama_petugas"));
            
            if(rs.getInt("telat")<0)
            {
                dataPengembalian.setTelat(0);
            }
            else
            {
                dataPengembalian.setTelat(rs.getInt("telat"));
            }
            dataPengembalian.setTglkembali_seharusnya(rs.getDate("tglkembali_seharusnya"));

            return dataPengembalian;
        }
    }
}
