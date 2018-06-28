/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Utama;

import com.sbd12.sewamobil.Pkg_Data_Pengembalian.DataPengembalianTableModel;
import com.sbd12.sewamobil.Pkg_Data_Pengembalian.DataPengembalian;
import com.sbd12.sewamobil.Pkg_Data_Mobil.DataMobil;
import com.sbd12.sewamobil.Pkg_Data_Mobil.DataMobilJDBCTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sbd12.sewamobil.Pkg_Data_Transaksi.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import org.joda.time.LocalDate;

/**
 *
 * @author ArieDZ 2
 */
public class Panel_data_pengembalian extends javax.swing.JPanel {

    /**
     *
     */
    public DataPengembalianTableModel tablePengembalian;
    public FD_Barang formMobil;

    public frm_Utama_metro form_parent;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    private DecimalFormat kursIndonesia;
    private DecimalFormatSymbols formatRp;
    private List<DataPengembalian> dataPengembalians;
    private List<DataMobil> dataMobils;
    private DataMobil dataMobil;
    private DataTransaksi dataTransaksi;
    private boolean mouseDisable = false;

    private ArrayList<String> listMobil = new ArrayList<>();

    public DataTransaksiJDBCTemplate db;
    public DataMobilJDBCTemplate db_mobil;

    private double hargaMobilSemua;
    private double denda = 0;
    private int telat = 0;
    private String no_transaksi;
    private String kode_kostumer = "0";
    private String nama_kostumer = "";
    private String id_petugas;
    private String nama_petugas;
    private boolean ownerLogin = false;
    private Date rencana_tglkembali;
    private Date tglsekarang;

    public String getId_petugas() {
        return id_petugas;
    }

    public void setId_petugas(String id_petugas) {
        this.id_petugas = id_petugas;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public void setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
    }

    public void printNama() {
        System.out.println("Data PEngembalian ID " + id_petugas + ",Nama " + nama_petugas);
    }

    /*implements ActionListener*/
    public Panel_data_pengembalian(boolean isOwnerLogin) throws ClassNotFoundException {
        initComponents();
        ApplicationContext context = new ClassPathXmlApplicationContext("Config-Spring.xml");
        db = (DataTransaksiJDBCTemplate) context.getBean("dataTransaksiJDBCTemplate");

        ApplicationContext context2 = new ClassPathXmlApplicationContext("Config-Spring.xml");
        db_mobil = (DataMobilJDBCTemplate) context2.getBean("dataMobilJDBCTemplate");

        dataPengembalians = db.listSemua_pengembalian();
        tampilData_Pengembalian();
        
        
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!mouseDisable) {
                    if (e.getClickCount() >= 1) {
                        int baris = jTable1.getSelectedRow();
                        String no_transaksi = (String) tablePengembalian.getValueAt(baris, 0);
                        System.out.println("Klik di Table = id =" + no_transaksi);
                        DataPengembalian mm = db.pilih_data_pengembalian(no_transaksi);
                        tf_no_transaksi.setText(mm.getNo_transaksi());
                        tf_nama_kostumer.setText(mm.getNama_kostumer());
                        tf_nama_petugas.setText(mm.getNama_pegawai());
                        tf_rencana_tgl_kembali.setText( sdf.format(mm.getTglkembali_seharusnya() ));
                        tf_tgl_kembali.setText( mm.getTglPengembalianString());
                        tf_telat.setText(String.valueOf(mm.getTelat()));
                        tf_denda.setText(kursIndonesia.format(mm.getDenda()));
                    }
                }
            }
        });
        formatCurrency();

        enableInput(false);
        resetInput();
    }

    public void tampilData_Pengembalian() throws ClassNotFoundException {

        tablePengembalian = new DataPengembalianTableModel();
        tablePengembalian.setData(dataPengembalians);
        jTable1.setModel(tablePengembalian);
        jTable1.setSelectionModel(new ForcedListSelectionModel());

    }

    public void refreshData() throws ClassNotFoundException {
        dataPengembalians = db.listSemua_pengembalian();
        tablePengembalian.setData(dataPengembalians);
        tablePengembalian.fireTableDataChanged();
        resetInput();
        jTable1.changeSelection(0, 0, false, false);
    }

    private void setTambah(Boolean tampil, Boolean tambah) {

        if (tambah) {
            if (tampil) {
                bt_simpan.setVisible(false);
                bt_batal.setVisible(false);
            }
            if (!tampil) {
                bt_simpan.setVisible(true);
                bt_batal.setVisible(true);
            }
        } else if (!tambah) {

            if (tampil) {
                //bt_simpan_edit.setVisible(false);
                bt_batal.setVisible(false);
            }
            if (!tampil) {
                //bt_simpan_edit.setVisible(true);
                bt_batal.setVisible(true);
            }
            //tf_no_transaksi.setEnabled(tampil);
            //jDate_tglkembali.setEnabled(tampil);
        }

        //BtEdit.setEnabled(tampil);
        jb_pengembalian.setEnabled(tampil);
        jb_cari.setEnabled(tampil);
        jb_refresh.setEnabled(tampil);

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

    private void enableInput(boolean enable) {
        tf_no_transaksi.setEnabled(enable);
        tf_nama_kostumer.setEnabled(enable);
        tf_nama_petugas.setEnabled(enable);
        tf_rencana_tgl_kembali.setEnabled(enable);
        tf_tgl_kembali.setEnabled(enable);
        tf_telat.setEnabled(enable);
        tf_denda.setEnabled(enable);

        tfield_mobil.setEnabled(enable);
    }

    private void resetInput() {

        tf_no_transaksi.setText("");
        tf_nama_kostumer.setText("");
        tf_nama_petugas.setText("");
        tf_rencana_tgl_kembali.setText("");
        tf_tgl_kembali.setText("");
        tf_telat.setText("");
        tf_denda.setText(kursIndonesia.format(0));

        denda = 0;

        
        tfield_mobil.setText("");

        no_transaksi = "";
        nama_kostumer = "";
        telat = 0;

    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tf_no_transaksi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_nama_kostumer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_nama_petugas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tf_rencana_tgl_kembali = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_tgl_kembali = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tf_telat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_denda = new javax.swing.JTextField();
        bt_simpan = new javax.swing.JButton();
        bt_batal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfield_mobil = new javax.swing.JTextArea();
        jb_pengembalian = new javax.swing.JButton();
        jb_refresh = new javax.swing.JButton();
        jb_cari = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(960, 720));
        setMinimumSize(new java.awt.Dimension(960, 720));
        setPreferredSize(new java.awt.Dimension(960, 720));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel1.setText("No Transaksi");

        tf_no_transaksi.setEditable(false);

        jLabel3.setText("Nama Konstumer");

        tf_nama_kostumer.setEditable(false);

        jLabel4.setText("Nama Petugas");

        tf_nama_petugas.setEditable(false);

        jLabel5.setText("Rencana Tgl Kembali");

        tf_rencana_tgl_kembali.setEditable(false);

        jLabel6.setText("Tgl Pengembalian");

        tf_tgl_kembali.setEditable(false);

        jLabel7.setText("Telat");

        tf_telat.setEditable(false);

        jLabel8.setText("Denda");

        tf_denda.setEditable(false);

        bt_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Save_24px.png"))); // NOI18N
        bt_simpan.setText("Simpan");
        bt_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_simpanActionPerformed(evt);
            }
        });

        bt_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cancel_24px.png"))); // NOI18N
        bt_batal.setText("Batal");
        bt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_batalActionPerformed(evt);
            }
        });

        jLabel2.setText("List Mobil");

        tfield_mobil.setEditable(false);
        tfield_mobil.setColumns(20);
        tfield_mobil.setRows(5);
        jScrollPane2.setViewportView(tfield_mobil);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_tgl_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_rencana_tgl_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_nama_kostumer, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tf_no_transaksi)
                            .addComponent(tf_nama_petugas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(77, 77, 77)
                        .addComponent(jLabel2))
                    .addComponent(tf_telat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_denda, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(bt_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tf_no_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tf_nama_kostumer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_nama_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tf_rencana_tgl_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tf_tgl_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tf_telat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tf_denda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_simpan)
                            .addComponent(bt_batal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bt_simpan.setVisible(false);
        bt_batal.setVisible(false);

        jb_pengembalian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Plus_24px_3.png"))); // NOI18N
        jb_pengembalian.setText("Pengembalian");
        jb_pengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_pengembalianActionPerformed(evt);
            }
        });

        jb_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Reset_24px.png"))); // NOI18N
        jb_refresh.setText("Refresh");
        jb_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_refreshActionPerformed(evt);
            }
        });

        jb_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_24px.png"))); // NOI18N
        jb_cari.setText("Cari");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jb_pengembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jb_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jb_cari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jb_pengembalian)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_cari)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jb_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_refreshActionPerformed
        // TODO add your handling code here:

        try {
            // TODO add your handling code here:
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_merk_mobil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jb_refreshActionPerformed

    private void jb_pengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_pengembalianActionPerformed
        // TODO add your handling code here:
        resetInput();
        FD_Tampil_Data_transaksi tampildata_transaksi;
        String datamobilString = "";
        
        try {
            tampildata_transaksi = new FD_Tampil_Data_transaksi(form_parent, true);
            tampildata_transaksi.setTitle("Pilih Transaksi");
            tampildata_transaksi.setVisible(true);

            no_transaksi = tampildata_transaksi.getKode_transaksi();
            if (no_transaksi == null || no_transaksi.isEmpty()) {
                tampildata_transaksi.dispose();
                return;
            }
            dataTransaksi = db.pilih_data(no_transaksi);
            tglsekarang = new Date();

            if (dataTransaksi != null) {
                rencana_tglkembali = dataTransaksi.getTglkembali();
                kode_kostumer = dataTransaksi.getId_kostumer();
                nama_kostumer = dataTransaksi.getNama_kostumer();
            }
            dataMobils = db_mobil.listSemua_berdasar_noTransaksi(no_transaksi);

            for (DataMobil dm : dataMobils) {
                datamobilString += dm.getNo_pol() + "-" + dm.getNama_mobil() + "-@" + kursIndonesia.format(dm.getHarga_perhari()) + "/hari \n";
                hargaMobilSemua += dm.getHarga_perhari();
            }

            telat = (int) getDifferenceDays(rencana_tglkembali, tglsekarang);
            if (telat < 0) {
                telat = 0;
            }

            denda = telat * hargaMobilSemua;

            tf_no_transaksi.setText(no_transaksi);
            tf_nama_kostumer.setText(nama_kostumer);
            tf_nama_petugas.setText(nama_petugas);
            tf_rencana_tgl_kembali.setText(sdf.format(rencana_tglkembali));
            tf_tgl_kembali.setText(sdf.format(tglsekarang));
            tf_telat.setText(String.valueOf(telat));
            tf_denda.setText(kursIndonesia.format(denda));

            datamobilString += "\n\nTotal Denda =" + kursIndonesia.format(denda);
            tfield_mobil.setText(datamobilString);
            System.out.println("No Tx" + no_transaksi
                    + "\n NamaKonsumer=" + nama_kostumer
                    + "\n NamaPegawai=" + nama_petugas
                    + "\n HargaSemua=" + hargaMobilSemua
                    + "\n telat=" + telat
                    + "\n Denda=" + denda);

            tampildata_transaksi.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_data_pengembalian.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Menekan Tombol pengembalian, no_Transaksi=" + no_transaksi);
        enableInput(true);
        setTambah(false, true);
        mouseDisable = true;


    }//GEN-LAST:event_jb_pengembalianActionPerformed

    private void bt_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_simpanActionPerformed
        // TODO add your handling code here:
        boolean berhasil_pengembalian = false;
        if ((no_transaksi != null)
                && (nama_kostumer != null)
                && (id_petugas != null)) {

            try {
                db.create_pengembalian(no_transaksi, id_petugas, denda);
                berhasil_pengembalian = true;
            } catch (Exception e) {
                System.out.println("Excepton Pengembalian : " + e);
            }

            if (berhasil_pengembalian) {
                db.update_transaksi_selesai(no_transaksi);
                if(denda>0)
                db.tambah_pembayaran(no_transaksi, "Denda", denda, "Denda dgn telat " + telat + "hari");
                db.update_detail_transaksi(no_transaksi, id_petugas, denda, "");
            }
            mouseDisable = false;

            try {
                refreshData();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Panel_data_transaksi.class.getName()).log(Level.SEVERE, null, ex);
            }
            setTambah(true, true);
            enableInput(false);
            resetInput();
        }
    }//GEN-LAST:event_bt_simpanActionPerformed

    private void bt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_batalActionPerformed
        // TODO add your handling code here:
        resetInput();
        setTambah(true, true);
        setTambah(true, false);
        enableInput(false);
        mouseDisable = false;
    }//GEN-LAST:event_bt_batalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_batal;
    private javax.swing.JButton bt_simpan;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jb_cari;
    private javax.swing.JButton jb_pengembalian;
    private javax.swing.JButton jb_refresh;
    private javax.swing.JTextField tf_denda;
    private javax.swing.JTextField tf_nama_kostumer;
    private javax.swing.JTextField tf_nama_petugas;
    private javax.swing.JTextField tf_no_transaksi;
    private javax.swing.JTextField tf_rencana_tgl_kembali;
    private javax.swing.JTextField tf_telat;
    private javax.swing.JTextField tf_tgl_kembali;
    private javax.swing.JTextArea tfield_mobil;
    // End of variables declaration//GEN-END:variables
}
