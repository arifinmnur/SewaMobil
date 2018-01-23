/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Utama;

import com.sbd12.sewamobil.Pkg_Kostumer.Jenis_Member;
import com.sbd12.sewamobil.Pkg_Kostumer.Kostumer;
import com.sbd12.sewamobil.Pkg_Kostumer.KostumerJDBCTemplate;
import com.sbd12.sewamobil.Pkg_Kostumer.KostumerTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author ArieDZ 2
 */
public class panel_kostumer extends javax.swing.JPanel {

    /**
     * Creates new form panel_data_kostumer
     */
    public KostumerTableModel tabelkostumer;

    public frm_Utama_metro form_parent;

    private Kostumer kostumer;
    private List<Kostumer> kostumers;
    private List<Jenis_Member> jenis_Members;
    private KostumerJDBCTemplate db;
    private String status;
    private String jenisKelamin;

    public panel_kostumer() throws ClassNotFoundException {
        initComponents();
        ApplicationContext context = new ClassPathXmlApplicationContext("Config-Spring.xml");
        db = (KostumerJDBCTemplate) context.getBean("kostumerJDBCTemplate");

        kostumers = db.listSemua();
        jenis_Members=db.combo_box_jenis_member(jcb_jenis_member);
        
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 1) {
                    int baris = jTable1.getSelectedRow();
                    String id_kostumer = (String) tabelkostumer.getValueAt(baris, 0);
                    System.out.println("id =" + id_kostumer);
                    Kostumer mm = db.pilih_data(id_kostumer);
                    tf_id_kostumer.setText(mm.getId_kostumer());
                    tf_no_ktp.setText(mm.getNo_ktp());
                    tf_id_member.setText(mm.getId_member());

                    tf_nama_depan.setText(mm.getNama_depan_k());
                    tf_nama_belakang.setText(mm.getNama_belakang_k());
                    tf_no_telepon.setText(mm.getNo_telepon_k());
                    tf_alamat.setText(mm.getAlamat_k());

                    tf_umur.setText(String.valueOf(mm.getUmur()));
                    jd_tanggal_lahir.setDate(mm.getTanggal_lahir_k());
                    jd_tanggal_join.setDate(mm.getTanggal_join());

                    jenisKelamin = mm.getJenis_kelamin_k();
                    status = mm.getId_jenis_member();
                    
                    Jenis_Member jm = db.pilih_data_jenis_member(status);                    
                    jcb_jenis_member.setSelectedIndex(jm.getCounter());
                    

                    if (jenisKelamin.equalsIgnoreCase("L")) {
                        jcb_jenis_kelamin.setSelectedIndex(1);
                    } else if (jenisKelamin.equalsIgnoreCase("P")) {
                        jcb_jenis_kelamin.setSelectedIndex(2);
                    } else {
                        jcb_jenis_kelamin.setSelectedIndex(0);
                    }

                }
            }
        });
        tampilData_kostumer();
        refreshData();
        disableInput();
        resetInput();
    }

    public void tampilData_kostumer() throws ClassNotFoundException {

        //tabelkostumer = new KostumerTableModel();
        tabelkostumer = new KostumerTableModel();
        tabelkostumer.setData(kostumers);
        jTable1.setModel(tabelkostumer);

    }

    public void refreshData() throws ClassNotFoundException {
        kostumers = db.listSemua();
        tabelkostumer.setData(kostumers);
        tabelkostumer.fireTableDataChanged();
        jTable1.changeSelection(0, 0, false, false);
    }

    public void disableInput() {
        tf_id_kostumer.setEnabled(false);
        tf_no_ktp.setEnabled(false);
        tf_id_member.setEnabled(false);
        tf_nama_depan.setEnabled(false);
        tf_nama_belakang.setEnabled(false);
        tf_umur.setEnabled(false);
        tf_alamat.setEnabled(false);
        tf_no_telepon.setEnabled(false);

        jcb_jenis_member.setEnabled(false);
        jcb_jenis_kelamin.setEnabled(false);

        jd_tanggal_join.setEnabled(false);
        jd_tanggal_lahir.setEnabled(false);
    }

    public void enableInput() {
        tf_id_kostumer.setEnabled(true);
        tf_no_ktp.setEnabled(true);
        tf_id_member.setEnabled(true);
        tf_nama_depan.setEnabled(true);
        tf_nama_belakang.setEnabled(true);
        tf_umur.setEnabled(true);
        tf_alamat.setEnabled(true);
        tf_no_telepon.setEnabled(true);

        jcb_jenis_member.setEnabled(true);
        jcb_jenis_kelamin.setEnabled(true);

        jd_tanggal_join.setEnabled(true);
        jd_tanggal_lahir.setEnabled(true);
    }

    public void resetInput() {
        tf_id_kostumer.setText("");
        tf_no_ktp.setText("");
        tf_id_member.setText("");
        tf_nama_depan.setText("");
        tf_nama_belakang.setText("");
        tf_umur.setText("");
        tf_alamat.setText("");
        tf_no_telepon.setText("");

        jcb_jenis_member.setSelectedIndex(0);
        jcb_jenis_kelamin.setSelectedIndex(0);

        java.util.Date tglNow = new java.util.Date();
        jd_tanggal_join.setDate(tglNow);
        jd_tanggal_lahir.setDate(tglNow);
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

            lb_id_kos.setVisible(tampil);
            tf_id_kostumer.setVisible(tampil);
            lb_id_mem.setVisible(tampil);
            tf_id_member.setVisible(tampil);
            lb_umur.setVisible(tampil);
            tf_umur.setVisible(tampil);
            lb_join.setVisible(tampil);
            jd_tanggal_join.setVisible(tampil);
        } else if (!tambah) {

            if (tampil) {
                jb_Simpan_edit.setVisible(false);
                bt_batal.setVisible(false);
            }
            if (!tampil) {
                jb_Simpan_edit.setVisible(true);
                bt_batal.setVisible(true);
            }
            tf_id_kostumer.setEnabled(tampil);
            tf_id_member.setEnabled(tampil);
            jd_tanggal_join.setEnabled(tampil);
        }

        BtEdit.setEnabled(tampil);
        BtHapus.setEnabled(tampil);
        BtCari.setEnabled(tampil);
        BtTambah.setEnabled(tampil);
        BtRefresh.setEnabled(tampil);

        jTable1.setEnabled(tampil);
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
        BtTambah = new javax.swing.JButton();
        BtEdit = new javax.swing.JButton();
        BtHapus = new javax.swing.JButton();
        BtRefresh = new javax.swing.JButton();
        BtCari = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lb_id_kos = new javax.swing.JLabel();
        tf_id_kostumer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_no_ktp = new javax.swing.JTextField();
        tf_nama_depan = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_alamat = new javax.swing.JTextArea();
        tf_no_telepon = new javax.swing.JTextField();
        jcb_jenis_kelamin = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_nama_belakang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tf_id_member = new javax.swing.JTextField();
        lb_id_mem = new javax.swing.JLabel();
        jcb_jenis_member = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jd_tanggal_lahir = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jd_tanggal_join = new com.toedter.calendar.JDateChooser();
        lb_join = new javax.swing.JLabel();
        tf_umur = new javax.swing.JTextField();
        lb_umur = new javax.swing.JLabel();
        bt_simpan = new javax.swing.JButton();
        bt_batal = new javax.swing.JButton();
        jb_Simpan_edit = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(960, 720));
        setMinimumSize(new java.awt.Dimension(960, 720));
        setPreferredSize(new java.awt.Dimension(960, 720));

        bg.setBackground(new java.awt.Color(204, 204, 204));
        bg.setMaximumSize(new java.awt.Dimension(960, 720));
        bg.setMinimumSize(new java.awt.Dimension(960, 720));
        bg.setPreferredSize(new java.awt.Dimension(960, 720));

        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(768, 331));

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
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        BtTambah.setText("Tambah");
        BtTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtTambahActionPerformed(evt);
            }
        });

        BtEdit.setText("Edit");
        BtEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtEditActionPerformed(evt);
            }
        });

        BtHapus.setText("Hapus");
        BtHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtHapusActionPerformed(evt);
            }
        });

        BtRefresh.setText("Refresh");
        BtRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtRefreshActionPerformed(evt);
            }
        });

        BtCari.setText("Cari");
        BtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtTambah)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BtEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtHapus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtRefresh, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(BtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(BtTambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtCari)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(373, 373, 373))
        );

        lb_id_kos.setText("ID Kostumer");

        tf_id_kostumer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_id_kostumerActionPerformed(evt);
            }
        });

        jLabel4.setText("Jenis Kelamin");

        jLabel2.setText("No KTP");

        jLabel3.setText("Nama Depan");

        tf_alamat.setColumns(20);
        tf_alamat.setRows(5);
        jScrollPane2.setViewportView(tf_alamat);

        jcb_jenis_kelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Laki-laki", "Perempuan" }));

        jLabel7.setText("No Telepon");

        jLabel8.setText("Alamat");

        jLabel5.setText("Nama Belakang");

        lb_id_mem.setText("ID Member");

        jcb_jenis_member.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Non-Member", "Regular", "Premium" }));

        jLabel9.setText("Jenis Member");

        jLabel10.setText("Tgl Lahir");

        lb_join.setText("Tgl Join");

        tf_umur.setEditable(false);

        lb_umur.setText("Umur");

        bt_simpan.setText("Simpan");
        bt_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_simpanActionPerformed(evt);
            }
        });

        bt_batal.setText("Batal");
        bt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_batalActionPerformed(evt);
            }
        });

        jb_Simpan_edit.setText("Simpan");
        jb_Simpan_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_Simpan_editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lb_id_kos)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(lb_id_mem)
                    .addComponent(jLabel9))
                .addGap(118, 118, 118)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jcb_jenis_member, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_id_member)
                    .addComponent(jcb_jenis_kelamin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_id_kostumer, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(tf_no_ktp)
                    .addComponent(tf_nama_depan)
                    .addComponent(tf_nama_belakang))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(bt_simpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_Simpan_edit)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(lb_join)
                            .addComponent(lb_umur))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_no_telepon)
                            .addComponent(jd_tanggal_join, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(jd_tanggal_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_umur)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(168, 168, 168))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_id_kos)
                        .addComponent(tf_id_kostumer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(jd_tanggal_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(tf_no_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_umur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_umur, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_id_member, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_id_mem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcb_jenis_member, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tf_nama_depan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_nama_belakang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jcb_jenis_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_join))
                        .addGap(66, 66, 66))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(86, 86, 86)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(tf_no_telepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jd_tanggal_join, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_simpan)
                            .addComponent(bt_batal)
                            .addComponent(jb_Simpan_edit))
                        .addGap(19, 19, 19))))
        );

        bt_simpan.setVisible(false);
        bt_batal.setVisible(false);
        jb_Simpan_edit.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bg, 948, 948, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtTambahActionPerformed
        // TODO add your handling code here:
        System.out.println("Menekan Tombol Tambah");

        enableInput();
        setTambah(false, true);
        resetInput();
    }//GEN-LAST:event_BtTambahActionPerformed

    private void BtEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtEditActionPerformed
        System.out.println("Menekan tombol edit");
        if (tf_id_kostumer.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih Baris yg akan di Edit");
        } else {
            enableInput();
            setTambah(false, false);
        }
    }//GEN-LAST:event_BtEditActionPerformed

    private void BtHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtHapusActionPerformed
        // TODO add your handling code here:

        int baris = jTable1.getSelectedRow();
        String id = (String) tabelkostumer.getValueAt(baris, 0);
        String nama = (String) tabelkostumer.getValueAt(baris, 2);

        System.out.println("Menekan tombol hapus");
        System.out.println(id);
        Object[] pilihan = {"Ya", "Tidak"};
        int jawaban = JOptionPane.showOptionDialog(null, "Anda yakin data "
                + "kostumer dengan ID:" + id + " dengan Nama Kostumer: " + nama + " akan"
                + "dihapus? ", "Peringatan", JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
        if (jawaban == 0) {
            db.delete(id);
        }
        try {
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(panel_kostumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtHapusActionPerformed

    private void BtRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRefreshActionPerformed
        try {
            // TODO add your handling code here:
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(panel_kostumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtRefreshActionPerformed

    private void BtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCariActionPerformed
        // TODO add your handling code here:
        String katakunci;
        katakunci = JOptionPane.showInputDialog(null, "Nama barang yang di cari?", "Filter/Pencarian", JOptionPane.QUESTION_MESSAGE);
        if (katakunci != null) {
            kostumers = db.pilih_data_like(katakunci);
            tabelkostumer.setData(kostumers);
            tabelkostumer.fireTableDataChanged();
            jTable1.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_BtCariActionPerformed

    private void tf_id_kostumerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_id_kostumerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_id_kostumerActionPerformed

    private void bt_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_simpanActionPerformed

        //db.create("xxx", "xx", null, "Regular", "xx", null, null, null, null, null, null);
        String id_kostumer = db.generate_id_kostumer();
        String jenis_kelamin = jcb_jenis_kelamin.getSelectedItem().toString();
        String generate_id_member = null;
        
        int posisiJenisMember =jcb_jenis_member.getSelectedIndex();
        for(Jenis_Member list:jenis_Members){
            if(list.getCounter()==posisiJenisMember)
            {
                status=list.getId_jenis_member();
            }
        }
        if (jenis_kelamin.equalsIgnoreCase("Laki-laki")) {
            jenis_kelamin = "L";
        } else if (jenis_kelamin.equalsIgnoreCase("Perempuan")) {
            jenis_kelamin = "P";
        }

        if (posisiJenisMember>1) {
            generate_id_member = status + "" + jenis_kelamin;
            generate_id_member = db.generate_id_member(generate_id_member);
        }
        

       /* if (status.equalsIgnoreCase("Regular")) {
            generate_id_member = "REG" + jenis_kelamin;
            generate_id_member = db.generate_id_member(generate_id_member);
        } else if (status.equalsIgnoreCase("Premium")) {
            generate_id_member = "PRM" + jenis_kelamin;
            generate_id_member = db.generate_id_member(generate_id_member);
        }*/
        

        java.util.Date tglNow = new java.util.Date();
        java.sql.Date tangga_lahir = new java.sql.Date(jd_tanggal_lahir.getDate().getTime());
        java.sql.Date tangga_join = new java.sql.Date(tglNow.getTime());
        System.out.println("Maks :" + db.generate_id_kostumer());

        db.create(
                id_kostumer,
                tf_no_ktp.getText(),
                generate_id_member,
                status,
                tf_nama_depan.getText(),
                tf_nama_belakang.getText(),
                jenis_kelamin,
                tf_alamat.getText(),
                tangga_lahir,
                tf_no_telepon.getText(),
                tangga_join);

        try {
            refreshData();
            // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(panel_kostumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTambah(true, true);
        disableInput();
        resetInput();
    }//GEN-LAST:event_bt_simpanActionPerformed

    private void bt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_batalActionPerformed
        resetInput();

        setTambah(true, true);
        setTambah(true, false);
        disableInput();// TODO add your handling code here:
    }//GEN-LAST:event_bt_batalActionPerformed

    private void jb_Simpan_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_Simpan_editActionPerformed

        //String status = jcb_jenis_member.getSelectedItem().toString();
        String jenis_kelamin = jcb_jenis_kelamin.getSelectedItem().toString();
        String generate_id_member = null;

        int posisiJenisMember =jcb_jenis_member.getSelectedIndex();
        for(Jenis_Member list:jenis_Members){
            if(list.getCounter()==posisiJenisMember)
            {
                status=list.getId_jenis_member();
            }
        }
        if (jenis_kelamin.equalsIgnoreCase("Laki-laki")) {
            jenis_kelamin = "L";
        } else if (jenis_kelamin.equalsIgnoreCase("Perempuan")) {
            jenis_kelamin = "P";
        }
         if (posisiJenisMember>1) {
            generate_id_member = status + "" + jenis_kelamin;
            generate_id_member = db.generate_id_member(generate_id_member);
        }
        
        java.sql.Date tangga_lahir = new java.sql.Date(jd_tanggal_lahir.getDate().getTime());
        db.edit(tf_id_kostumer.getText(), tf_no_ktp.getText(), generate_id_member, status, tf_nama_depan.getText(), tf_nama_belakang.getText(), jenis_kelamin, tf_alamat.getText(), tangga_lahir, tf_no_telepon.getText());

        try {
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(panel_kostumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTambah(true, false);
        disableInput();
        resetInput();
        
        jTable1.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_jb_Simpan_editActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtCari;
    private javax.swing.JButton BtEdit;
    private javax.swing.JButton BtHapus;
    private javax.swing.JButton BtRefresh;
    private javax.swing.JButton BtTambah;
    private javax.swing.JPanel bg;
    private javax.swing.JButton bt_batal;
    private javax.swing.JButton bt_simpan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jb_Simpan_edit;
    private javax.swing.JComboBox<String> jcb_jenis_kelamin;
    private javax.swing.JComboBox<String> jcb_jenis_member;
    private com.toedter.calendar.JDateChooser jd_tanggal_join;
    private com.toedter.calendar.JDateChooser jd_tanggal_lahir;
    private javax.swing.JLabel lb_id_kos;
    private javax.swing.JLabel lb_id_mem;
    private javax.swing.JLabel lb_join;
    private javax.swing.JLabel lb_umur;
    private javax.swing.JTextArea tf_alamat;
    private javax.swing.JTextField tf_id_kostumer;
    private javax.swing.JTextField tf_id_member;
    private javax.swing.JTextField tf_nama_belakang;
    private javax.swing.JTextField tf_nama_depan;
    private javax.swing.JTextField tf_no_ktp;
    private javax.swing.JTextField tf_no_telepon;
    private javax.swing.JTextField tf_umur;
    // End of variables declaration//GEN-END:variables
}
