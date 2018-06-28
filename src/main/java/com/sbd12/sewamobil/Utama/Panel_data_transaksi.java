/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Utama;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sbd12.sewamobil.Pkg_Merk_Mobil.*;
import com.sbd12.sewamobil.Pkg_ProdusenMobil.*;
import com.sbd12.sewamobil.Pkg_Data_Mobil.*;
import com.sbd12.sewamobil.Pkg_Data_Pegawai.Pegawai;
import com.sbd12.sewamobil.Pkg_Jenis_Mobil.*;
import com.sbd12.sewamobil.Pkg_Data_Transaksi.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ArieDZ 2
 */
public class Panel_data_transaksi extends javax.swing.JPanel {

    /**
     * Creates new form panel_data_barang
     */
    public DataTransaksiTableModel tableTransaksi;
    public FD_Barang formMobil;

    public frm_Utama_metro form_parent;

    private DecimalFormat kursIndonesia;
    private DecimalFormatSymbols formatRp;
    private List<DataTransaksi> dataTransaksis;
    private boolean mouseDisable = false;

    private ArrayList<String> listMobil = new ArrayList<>();

    public DataTransaksiJDBCTemplate db;

    private double harga = 0;
    private double harga_total = 0;
    private double harga_diskon = 0;
    private double harga_hari = 0;
    private String kode_kostumer = "0";
    private String nama_kostumer = "";
    private String id;
    private String nama;
    private double diskon = 0;
    private boolean ownerLogin = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void printNama() {
        System.out.println("ID " + id + ",Nama " + nama);
    }

    /*implements ActionListener*/
    public Panel_data_transaksi(boolean isOwnerLogin) throws ClassNotFoundException {
        initComponents();
        ApplicationContext context = new ClassPathXmlApplicationContext("Config-Spring.xml");
        db = (DataTransaksiJDBCTemplate) context.getBean("dataTransaksiJDBCTemplate");

        dataTransaksis = db.listSemua();

        System.out.println("JDBC URL =" + db.getJdbcurl());

        tampilData_Transaksi();
        formatCurrency();
        jTable1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent me) {
                int row = jTable1.getSelectedRow();
                String kode = (String) tableTransaksi.getValueAt(row, 0);
                String nama = (String) tableTransaksi.getValueAt(row, 2);
                String order_id = (String) tableTransaksi.getValueAt(row, 0);
                if ((me.getClickCount() == 2) && (row != -1)) {
                    System.out.println("Kode :" + kode + "\nNama :" + nama);

                    try {
                        formMobil = new FD_Barang(form_parent, true, order_id);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Panel_data_transaksi.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    formMobil.setVisible(true);
                }
            }
        });
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!mouseDisable) {
                    if (e.getClickCount() >= 1) {
                        int baris = jTable1.getSelectedRow();
                        String no_transaksi = (String) tableTransaksi.getValueAt(baris, 0);
                        System.out.println("Klik di Table = id =" + no_transaksi);
                        DataTransaksi mm = db.pilih_data(no_transaksi);
                        tf_no_transaksi.setText(mm.getNo_transaksi());
                        tf_nama_kostumer.setText(mm.getNama_kostumer());
                        Date date1 = mm.getTglpinjam();
                        Date date2 = mm.getTglkembali();
                        jDate_tglpinjam.setDate(date1);
                        jDate_tglkembali.setDate(date2);
                        tf_no_pol.setText(mm.getNo_pol());
                        tf_harga.setText(kursIndonesia.format(mm.getHarga()));
                        tf_diskon.setText(kursIndonesia.format(mm.getHarga_diskon()));
                        tf_harga_sudah_diskon.setText(kursIndonesia.format(mm.getHarga_total()));
                        tf_lama_pinjam1.setText(String.valueOf(mm.getLamaPinjam()));
                        tf_tgltransaksi.setText(mm.getTglTransaksiString());/*
                        jCb_nama_pegawai.setSelectedItem(mm.getNama_pegawai());
                        jCb_produsen_mobil.setSelectedItem(mm.getNama_produsen());
                        jCb_Merk_mobil.setSelectedItem(mm.getNama_mobil());
                        jCb_no_pol.setSelectedItem(mm.getNo_pol() + "-" + mm.getNama_mobil());/*
                        DateTime dateTime1 = new DateTime(date1);
                        DateTime dateTime2 = new DateTime(date2);
                        int days = Days.daysBetween(dateTime1, dateTime2).getDays();

                        tf_lama_pinjam1.setText(String.valueOf(days) + "-Hari");
                        /*tf_id_merk.setText(mm.getId_merk_mobil());
                    jCb_produsen.setSelectedItem(mm.getNama_produsen_mobil());
                    jCb_jenis.setSelectedItem(mm.getNama_jenis());
                    tf_nama_merk.setText(mm.getNama_Merk_Mobil()); */

                    }
                }
            }
        });
        /*        jCb_produsen_mobil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String produsen = jCb_produsen_mobil.getSelectedItem().toString();
                String id_produsenPilih = "";

                System.out.println("Listener Produsen =" + jCb_produsen_mobil.getSelectedIndex() + " " + produsen);
                if (!produsen.equalsIgnoreCase("Pilih")) {
                    for (ProdusenMobil produsenMobil : produsens) {
                        if (produsenMobil.getNama_produsen().equals(produsen)) {
                            id_produsenPilih = produsenMobil.getId_produsen();
                            System.out.print("--idProdusen-" + id_produsenPilih + "\n");
                            db.combo_box_merk_mobil_plus(jCb_Merk_mobil, id_produsenPilih);
                        }
                    }
                }
            }
        });
        jCb_Merk_mobil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((jCb_Merk_mobil.getSelectedIndex() == -1) && (jCb_Merk_mobil.getItemCount() != 0)) {

                } else {
                    String merk = "";
                    if (jCb_Merk_mobil.getItemCount() != 0) {
                        merk = jCb_Merk_mobil.getSelectedItem().toString();
                    }
                    String id_merkPilih = "";

                    System.out.println("Listener MerkMobil =" + jCb_Merk_mobil.getSelectedIndex() + " " + merk);
                    if ((jCb_Merk_mobil.getSelectedIndex() != -1) && (merk != null)) {
                        for (MerkMobil MerkMobil : merk_Mobils) {
                            if (MerkMobil.getNama_Merk_Mobil().equals(merk)) {
                                id_merkPilih = MerkMobil.getId_merk_mobil();
                                System.out.print("--idProdusen-" + id_merkPilih + "\n");
                                db.combo_box_data_mobil_plus(jCb_no_pol, id_merkPilih);
                            }
                        }
                    }
                }
            }
        });
        jCb_no_pol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((jCb_no_pol.getSelectedIndex() == -1) && (jCb_no_pol.getItemCount() != 0)) {

                } else {
                    String nopol = "12345678";
                    if (jCb_no_pol.getItemCount() != 0) {
                        nopol = jCb_no_pol.getSelectedItem().toString();
                    }
                    String nopolSub = nopol.substring(0, 7);
                    String id_nopolPilih = "";

                    System.out.println("Listener NoPol =" + jCb_no_pol.getSelectedIndex() + " " + nopolSub);
                    if ((jCb_no_pol.getSelectedIndex() != -1) && (nopolSub != null)) {
                        for (DataMobil dataMobil : dataMobils) {
                            if (dataMobil.getNo_pol().equals(nopolSub)) {
                                id_nopolPilih = dataMobil.getNo_pol();
                                System.out.print("--noPol-" + id_nopolPilih + "\n");
//                                tf_harga_hari.setText(String.valueOf(dataMobil.getHarga_perhari()));

                            }
                        }
                    }
                }
            }
        });*/

        enableInput(false);
        resetInput();
    }

    public void tampilData_Transaksi() throws ClassNotFoundException {

        tableTransaksi = new DataTransaksiTableModel();
        tableTransaksi.setData(dataTransaksis);
        jTable1.setModel(tableTransaksi);
        jTable1.changeSelection(0, 0, false, false);
        jTable1.setSelectionModel(new ForcedListSelectionModel());

    }

    public void refreshData() throws ClassNotFoundException {
        dataTransaksis = db.listSemua();
        tableTransaksi.setData(dataTransaksis);
        tableTransaksi.fireTableDataChanged();
        resetInput();
        jTable1.changeSelection(0, 0, false, false);
    }

    public void disableInput() {
        tf_no_transaksi.setEnabled(false);
        tf_nama_kostumer.setEnabled(false);
        tf_lama_pinjam1.setEnabled(false);
        tf_harga.setEnabled(false);
        BtTambah.setVisible(true);
        bt_Batal.setVisible(false);
        BtSimpan.setVisible(false);
        Bt_pilih_barang.setEnabled(false);
        jb_pilih_kostumer.setEnabled(false);

        jDate_tglpinjam.setEnabled(false);
        jDate_tglkembali.setEnabled(false);

        /*jCb_Merk_mobil.setEnabled(false);
        jCb_nama_pegawai.setEnabled(false);
        jCb_no_pol.setEnabled(false);
        jCb_produsen_mobil.setEnabled(false);*/
    }

    public void enableInput() {
        tf_no_transaksi.setEnabled(true);
        tf_nama_kostumer.setEnabled(true);
        tf_lama_pinjam1.setEnabled(true);
        tf_harga.setEnabled(true);

        Bt_pilih_barang.setEnabled(true);
        jb_pilih_kostumer.setEnabled(true);

        jDate_tglpinjam.setEnabled(true);
        jDate_tglkembali.setEnabled(true);

        /*jCb_Merk_mobil.setEnabled(true);
        jCb_nama_pegawai.setEnabled(true);
        jCb_no_pol.setEnabled(true);
        jCb_produsen_mobil.setEnabled(true);*/
        BtTambah.setVisible(false);
        bt_Batal.setVisible(true);
        BtSimpan.setVisible(true);
    }

    public void enableInput(boolean tampil) {
        //tf_no_transaksi.setEnabled(true);
        tf_nama_kostumer.setEnabled(tampil);
        tf_lama_pinjam1.setEnabled(tampil);
        tf_harga.setEnabled(tampil);
        tf_diskon.setEnabled(tampil);
        tf_harga_sudah_diskon.setEnabled(tampil);

        Bt_pilih_barang.setEnabled(tampil);
        tf_list_barang.setEnabled(tampil);
        jb_pilih_kostumer.setEnabled(tampil);

        jDate_tglpinjam.setEnabled(tampil);
        //jDate_tglkembali.setEnabled(tampil);

        /*jCb_Merk_mobil.setEnabled(true);
        jCb_nama_pegawai.setEnabled(true);
        jCb_no_pol.setEnabled(true);
        jCb_produsen_mobil.setEnabled(true);*/
    }

    private void setTambah(Boolean tampil, Boolean tambah) {

        if (tambah) {
            if (tampil) {
                BtSimpan.setVisible(false);
                bt_Batal.setVisible(false);
            }
            if (!tampil) {
                BtSimpan.setVisible(true);
                bt_Batal.setVisible(true);
            }

            lb_notrans.setVisible(tampil);
            tf_no_transaksi.setVisible(tampil);
            lb_tanggal_kembali.setVisible(tampil);
            jDate_tglkembali.setVisible(tampil);

            lb_tgltransaksi.setVisible(tampil);
            tf_tgltransaksi.setVisible(tampil);
        } else if (!tambah) {

            if (tampil) {
                //bt_simpan_edit.setVisible(false);
                bt_Batal.setVisible(false);
            }
            if (!tampil) {
                //bt_simpan_edit.setVisible(true);
                bt_Batal.setVisible(true);
            }
            //tf_no_transaksi.setEnabled(tampil);
            //jDate_tglkembali.setEnabled(tampil);
        }

        //BtEdit.setEnabled(tampil);
        BtHapus.setEnabled(tampil);
        BtCari.setEnabled(tampil);
        BtTambah.setEnabled(tampil);
        BtRefresh.setEnabled(tampil);

        jTable1.setEnabled(tampil);
    }

    public void formatCurrency() {
        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }

    public class ForcedListSelectionModel extends DefaultListSelectionModel {

        public ForcedListSelectionModel() {
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        @Override
        public void clearSelection() {
        }

        @Override
        public void removeSelectionInterval(int index0, int index1) {
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BtCari = new javax.swing.JButton();
        BtHapus = new javax.swing.JButton();
        BtRefresh = new javax.swing.JButton();
        bt_laporan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lb_notrans = new javax.swing.JLabel();
        tf_no_transaksi = new javax.swing.JTextField();
        tf_no_pol = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_nama_kostumer = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDate_tglpinjam = new com.toedter.calendar.JDateChooser();
        lb_tanggal_kembali = new javax.swing.JLabel();
        jDate_tglkembali = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        tf_lama_pinjam1 = new javax.swing.JTextField();
        BtTambah = new javax.swing.JButton();
        BtSimpan = new javax.swing.JButton();
        jb_pilih_kostumer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_list_barang = new javax.swing.JTextArea();
        bt_Batal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lb_tgltransaksi = new javax.swing.JLabel();
        tf_tgltransaksi = new javax.swing.JTextField();
        tf_harga = new javax.swing.JFormattedTextField();
        tf_diskon = new javax.swing.JFormattedTextField();
        tf_harga_sudah_diskon = new javax.swing.JFormattedTextField();
        Bt_pilih_barang = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(960, 720));
        setMinimumSize(new java.awt.Dimension(960, 720));
        setPreferredSize(new java.awt.Dimension(960, 720));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(960, 510));

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(768, 331));

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        BtCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_24px.png"))); // NOI18N
        BtCari.setText("Cari");
        BtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCariActionPerformed(evt);
            }
        });

        BtHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Waste_24px.png"))); // NOI18N
        BtHapus.setText("Hapus");
        BtHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtHapusActionPerformed(evt);
            }
        });

        BtRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Reset_24px.png"))); // NOI18N
        BtRefresh.setText("Refresh");
        BtRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtRefreshActionPerformed(evt);
            }
        });

        bt_laporan.setText("Laporan");
        bt_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_laporanActionPerformed(evt);
            }
        });

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane3.setOpaque(false);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(768, 331));

        jTable2.setBackground(new java.awt.Color(204, 204, 204));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_laporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(BtRefresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtCari))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(BtHapus)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_laporan))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lb_notrans.setText("No Transaksi");

        tf_no_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_no_transaksiActionPerformed(evt);
            }
        });

        tf_no_pol.setEditable(false);

        jLabel3.setText("Nama Konstumer");

        jLabel6.setText("Mobil");

        tf_nama_kostumer.setEditable(false);

        jLabel7.setText("Lama Pinjam");

        jLabel8.setText("Tanggal Pinjam");

        jDate_tglpinjam.setDateFormatString("dd-MMM-yyyy");

        lb_tanggal_kembali.setText("Tanggal Kembali");

        jLabel5.setText("Harga");

        tf_lama_pinjam1.setText("0");

        BtTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Plus_24px_3.png"))); // NOI18N
        BtTambah.setText("Tambah Data");
        BtTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtTambahActionPerformed(evt);
            }
        });

        BtSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Save_24px.png"))); // NOI18N
        BtSimpan.setText("Simpan");
        BtSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSimpanActionPerformed(evt);
            }
        });

        jb_pilih_kostumer.setText("Pilih");
        jb_pilih_kostumer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_pilih_kostumerActionPerformed(evt);
            }
        });

        tf_list_barang.setEditable(false);
        tf_list_barang.setColumns(20);
        tf_list_barang.setRows(5);
        jScrollPane2.setViewportView(tf_list_barang);

        bt_Batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cancel_24px.png"))); // NOI18N
        bt_Batal.setText("Batal");
        bt_Batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_BatalActionPerformed(evt);
            }
        });

        jLabel1.setText("Klik 2 kali pada baris u/ Detail");

        jLabel2.setText("Diskon");

        jLabel4.setText("Total");

        lb_tgltransaksi.setText("Tanggal Transaksi");

        tf_harga.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        tf_harga.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        tf_diskon.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        tf_diskon.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        tf_harga_sudah_diskon.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        tf_harga_sudah_diskon.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtTambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_Batal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_notrans)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(lb_tanggal_kembali)
                    .addComponent(lb_tgltransaksi))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_nama_kostumer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_lama_pinjam1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_pilih_kostumer))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jDate_tglkembali, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addComponent(jDate_tglpinjam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tf_tgltransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_no_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_harga_sudah_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(182, 182, 182)
                                    .addComponent(tf_no_pol, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tf_diskon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(tf_harga, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tf_no_pol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_notrans)
                                .addComponent(tf_no_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(tf_nama_kostumer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jb_pilih_kostumer))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(tf_lama_pinjam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jDate_tglpinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_tanggal_kembali)
                                    .addComponent(jDate_tglkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(tf_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(tf_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tf_harga_sudah_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_tgltransaksi)
                            .addComponent(tf_tgltransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_Batal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tf_no_transaksi.setEnabled(false);
        tf_no_pol.setVisible(false);
        jDate_tglkembali.setEnabled(false);
        BtSimpan.setVisible(false);
        bt_Batal.setVisible(false);
        tf_tgltransaksi.setEnabled(false);
        tf_harga.setEditable(false);
        tf_diskon.setEditable(false);
        tf_harga_sudah_diskon.setEditable(false);

        Bt_pilih_barang.setText("Pilih Mobil");
        Bt_pilih_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_pilih_barangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                        .addGap(81, 81, 81))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Bt_pilih_barang)
                        .addContainerGap(104, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(Bt_pilih_barang)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetInput() {
        tf_no_transaksi.setText("");
        tf_nama_kostumer.setText("");
        tf_no_pol.setText("");
        tf_harga.setText("");
        tf_list_barang.setText("");

        tf_lama_pinjam1.setText("0");
        java.util.Date tglNow = new java.util.Date();
        jDate_tglpinjam.setDate(tglNow);
        jDate_tglkembali.setDate(tglNow);

        harga = 0;
        harga_diskon = 0;
        harga_total = 0;
        diskon = 0;

        tf_harga.setText("0");
        tf_diskon.setText("0");
        tf_harga_sudah_diskon.setText("0");/*
        jCb_Merk_mobil.setSelectedIndex(0);
        jCb_produsen_mobil.setSelectedIndex(0);
        jCb_nama_pegawai.setSelectedIndex(0);
        jCb_no_pol.setSelectedIndex(0);*/
    }
    private void BtTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtTambahActionPerformed
        // TODO add your handling code here:
        System.out.println("Menekan Tombol Tambah");
        //resetInput();
        //enableInput();
        enableInput(true);
        setTambah(false, true);
        resetInput();
        mouseDisable = true;


    }//GEN-LAST:event_BtTambahActionPerformed

    private void BtHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtHapusActionPerformed
        // TODO add your handling code here:
        System.out.println("Menekan tombol hapus");
        int baris = jTable1.getSelectedRow();
        if(baris<=-1){
            JOptionPane.showMessageDialog(this, "Pilih Baris yang akan dihapus");
            return;
        }
        String id = (String) tableTransaksi.getValueAt(baris, 0);
        System.out.println(id);

        Object[] pilihan = {"Ya", "Tidak"};
        int jawaban = JOptionPane.showOptionDialog(null, "Anda akan menghapus data dengan No_Transaksi:" + id + ", Klik Ya utk Melanjutkan atau Tidak utk Membatalkan", "Peringatan", JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
        if (jawaban == 0) {
            db.delete(id);
        }

        try {
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_merk_mobil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtHapusActionPerformed

    private void BtRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRefreshActionPerformed
        try {
            // TODO add your handling code here:
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_merk_mobil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtRefreshActionPerformed

    private void BtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCariActionPerformed
        // TODO add your handling code here:
        String katakunci;
        katakunci = JOptionPane.showInputDialog(null, "Nama barang yang di cari?", "Filter/Pencarian", JOptionPane.QUESTION_MESSAGE);
        if (katakunci != null) {
            dataTransaksis = db.pilih_data_like(katakunci);
            tableTransaksi.setData(dataTransaksis);
            tableTransaksi.fireTableDataChanged();
            jTable1.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_BtCariActionPerformed

    private void tf_no_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_no_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_no_transaksiActionPerformed

    private void BtSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSimpanActionPerformed
        // TODO add your handling code here:
        System.out.println("Menekan tombol simpan");
        //String namaPgawai = jCb_nama_pegawai.getSelectedItem().toString();
        String id_namaPgawai = "-";
        //String merkMobil = jCb_no_pol.getSelectedItem().toString();
        //String no_pol = merkMobil.substring(0, 7);
        String id_no_pol = "-";
        //double harga_total = 0;
        //double harga_hari = Double.parseDouble(tf_harga_total.getText());
        //harga_total = harga_hari * (Double.parseDouble(tf_lama_pinjam1.getText()));

        //System.out.println("Harga hari: " + harga_hari);
        System.out.println("Harga Total: " + harga_total);
        /* for (DataMobil dataMobil : dataMobils) {
            if (dataMobil.getNo_pol().equals(no_pol)) {
                id_no_pol = dataMobil.getNo_pol();
            }

        }*/

        harga = harga_hari * (Double.parseDouble(tf_lama_pinjam1.getText()));
        harga_diskon = harga * diskon;
        harga_total = harga - harga_diskon;

        tf_harga.setText(kursIndonesia.format(harga));
        tf_diskon.setText(kursIndonesia.format(harga_diskon));
        tf_harga_sudah_diskon.setText(kursIndonesia.format(harga_total));

        int lamaPinjam = Integer.valueOf(tf_lama_pinjam1.getText());
        String kode_transaksi = "";
        kode_transaksi = db.generate_id_tranksaksi();

        //System.out.println("nama : "+nama_produsenPilih+" id : "+id_produsenPilih);
        //System.out.println("nama : "+nama_jenisPilih+" id : "+id_jenisPilih);
        java.sql.Date tglpinjam1 = new java.sql.Date(jDate_tglpinjam.getDate().getTime());
        java.sql.Date tglpinjam2 = new java.sql.Date(jDate_tglpinjam.getDate().getTime());

        if ((kode_kostumer != null)
                && (id != null)
                && (lamaPinjam > 0)
                && (!listMobil.isEmpty())
                && (harga > 0)
                && (harga_diskon >= 0)) {

            int reply = JOptionPane.showConfirmDialog(this, "Diskon :" + harga_diskon + "\nTotal Harga :" + harga_total + "\nTekan 'Yes' u/melanjutkan dan 'No' u/Input kembali", "Pembayaran", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {

                Dialog_Pembayaran dialog = new Dialog_Pembayaran(form_parent, true);
                dialog.setForm(harga, harga_diskon, harga_total);
                dialog.setVisible(true);
                if (dialog.isIsTombolBatal()) {
                    return;
                }
                System.out.println(dialog.getUangdiBayar());

                Boolean berhasil_tambah_order = false;
                try {
                    db.create(kode_transaksi, kode_kostumer, id, tglpinjam1, lamaPinjam - 1, harga, harga_diskon, "BELUM SELESAI");
                    berhasil_tambah_order = true;
                } catch (Exception e) {
                    System.out.println("Excepton Tambah Data_order : " + e);
                }

                if (berhasil_tambah_order) {

                    String keterangan = "Pelunasan kode_transaksi=" + kode_transaksi + " Nama Kostumer=" + nama_kostumer + "";
                    System.out.println(keterangan);
                    db.tambah_detail_transaksi(kode_transaksi, listMobil);
                    db.tambah_pembayaran(kode_transaksi, "PELUNASAN", dialog.getUangdiBayar(), keterangan);

                }

                mouseDisable = false;
                //BtSimpan.setVisible(false);
                //BtTambah.setVisible(true);
                //tf_list_barang.setText("");
                //resetInput();
                //disableInput();

                try {
                    refreshData();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Panel_data_transaksi.class.getName()).log(Level.SEVERE, null, ex);
                }
                setTambah(true, true);
                enableInput(false);
                resetInput();
                dialog.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(form_parent, "Data Belum Lengkap, Mohon diisi Semua");
        }
    }//GEN-LAST:event_BtSimpanActionPerformed

    private void jb_pilih_kostumerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_pilih_kostumerActionPerformed
        try {
            // TODO add your handling code here:

            FD_Tampil_Kostumer tampilKostumer = new FD_Tampil_Kostumer(form_parent, true);
            tampilKostumer.setTitle("Pilih Kostumer");
            tampilKostumer.setVisible(true);
            nama_kostumer = tampilKostumer.getNama_kostumer();
            tf_nama_kostumer.setText(nama_kostumer);
            kode_kostumer = tampilKostumer.getKode_kostumer();
            diskon = tampilKostumer.getDiskon();

            tampilKostumer.dispose();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_data_transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jb_pilih_kostumerActionPerformed

    private void Bt_pilih_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_pilih_barangActionPerformed
        try {
            formMobil = new FD_Barang(form_parent, true);
            formMobil.setVisible(true);
            tf_list_barang.setText(formMobil.getList_barang());

            harga_hari = formMobil.getHarga_total();
            harga = harga_hari * (Double.parseDouble(tf_lama_pinjam1.getText()));
            harga_diskon = harga * diskon;
            harga_total = harga - harga_diskon;

            tf_harga.setText(kursIndonesia.format(harga));
            tf_diskon.setText(kursIndonesia.format(harga_diskon));
            tf_harga_sudah_diskon.setText(kursIndonesia.format(harga_total));
            listMobil = new ArrayList<>(formMobil.getList());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_data_transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Bt_pilih_barangActionPerformed

    private void bt_BatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_BatalActionPerformed
        resetInput();
        setTambah(true, true);
        setTambah(true, false);
        enableInput(false);
        mouseDisable = false;// TODO add your handling code here:
    }//GEN-LAST:event_bt_BatalActionPerformed

    private void bt_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_laporanActionPerformed

        try {

            //crud_Transaksi db = new crud_Transaksi();
            Connection connection = db.getConnection();
            JasperDesign jasperDesign = null;
             JasperReport jasperReport;
            // JasperReport jasperReport;
            //JRResultSetDataSource jrRS = new JRResultSetDataSource (rs);
            File file = new File("./src/main/resources/reports/DataTransaksiReport.jrxml");
            File file2 = new File("classes/reports/DataTransaksiReport.jrxml");

            try {
                jasperDesign = JRXmlLoader.load(file);
                
            } catch (net.sf.jasperreports.engine.JRException e) {
                System.out.println("File 1 error : " + e);
                try {
                    jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/classes/reports/DataTransaksiReport.jrxml"));
                  
                } catch (net.sf.jasperreports.engine.JRException d) {
                    System.out.println("File 2 error :" + d);
                    return;
                }
            }
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            //param.clear();
            //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
           
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
            JasperViewer.viewReport(jasperPrint, false);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }// TODO add your handling code here:
    }//GEN-LAST:event_bt_laporanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtCari;
    private javax.swing.JButton BtHapus;
    private javax.swing.JButton BtRefresh;
    private javax.swing.JButton BtSimpan;
    private javax.swing.JButton BtTambah;
    private javax.swing.JButton Bt_pilih_barang;
    private javax.swing.JPanel bg;
    private javax.swing.JButton bt_Batal;
    private javax.swing.JButton bt_laporan;
    private com.toedter.calendar.JDateChooser jDate_tglkembali;
    private com.toedter.calendar.JDateChooser jDate_tglpinjam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton jb_pilih_kostumer;
    private javax.swing.JLabel lb_notrans;
    private javax.swing.JLabel lb_tanggal_kembali;
    private javax.swing.JLabel lb_tgltransaksi;
    private javax.swing.JFormattedTextField tf_diskon;
    private javax.swing.JFormattedTextField tf_harga;
    private javax.swing.JFormattedTextField tf_harga_sudah_diskon;
    private javax.swing.JTextField tf_lama_pinjam1;
    private javax.swing.JTextArea tf_list_barang;
    private javax.swing.JTextField tf_nama_kostumer;
    private javax.swing.JTextField tf_no_pol;
    private javax.swing.JTextField tf_no_transaksi;
    private javax.swing.JTextField tf_tgltransaksi;
    // End of variables declaration//GEN-END:variables
}
