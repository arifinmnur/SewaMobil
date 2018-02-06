/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Utama;

import com.sbd12.sewamobil.Pkg_Data_Pegawai.PegawaiJDBCTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sbd12.sewamobil.Pkg_Data_Pegawai.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ArieDZ 2
 */
/*
 Baru
*/

public class Panel_pegawai extends javax.swing.JPanel {

    /**
     * Creates new form panel_data_barang
     */
    public PegawaiTableModel tabelPegawai;

    public frm_Utama_metro form_parent;
    private List<Pegawai> pegawais;
    private PegawaiJDBCTemplate db;
    private String jenisKelamin;
    private boolean mouseDisable=false;

    public Panel_pegawai() throws ClassNotFoundException {
        initComponents();
        ApplicationContext context = new ClassPathXmlApplicationContext("Config-Spring.xml");
        db = (PegawaiJDBCTemplate) context.getBean("pegawaiJDBCTemplate");

        pegawais = db.listSemua();

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!mouseDisable) {
                    if (e.getClickCount() >= 1) {
                        int baris = jTable1.getSelectedRow();
                        String id_pegawai = (String) tabelPegawai.getValueAt(baris, 0);
                        System.out.println("id =" + id_pegawai);
                        Pegawai mm = db.pilih_data(id_pegawai);
                        tf_id_pegawai.setText(mm.getId_pegawai());
                        tf_no_ktp.setText(mm.getNo_ktp_p());
                        tf_nama_depan_p.setText(mm.getNama_depan_p());
                        tf_nama_belakang_p.setText(mm.getNama_belakang_p());

                        tf_alamat.setText(mm.getAlamat_p());
                        jd_tanggal_lahir_p.setDate(mm.getTanggal_lahir_p());
                        tf_umur.setText(String.valueOf(mm.getUmur()));
                        tf_no_telepon.setText(mm.getNo_telepon_p());

                        tf_username.setText(mm.getUsername());
                        tf_password.setText(mm.getPassword());

                        jenisKelamin = mm.getJenis_kelamin_p();
                        if (jenisKelamin.equalsIgnoreCase("L")) {
                            jcb_jenis_kelamin.setSelectedIndex(1);
                        } else if (jenisKelamin.equalsIgnoreCase("P")) {
                            jcb_jenis_kelamin.setSelectedIndex(2);
                        } else {
                            jcb_jenis_kelamin.setSelectedIndex(0);
                        }

                        jd_tanggal_join.setDate(mm.getTanggal_join_p());
                    }
                }
            }
        });
        tampilData_pegawai();
        refreshData();
        enableInput(false);
        resetInput();
    }

    public void tampilData_pegawai() throws ClassNotFoundException {

        tabelPegawai = new PegawaiTableModel();
        tabelPegawai.setData(pegawais);
        jTable1.setModel(tabelPegawai);

    }

    public void refreshData() throws ClassNotFoundException {
        pegawais = db.listSemua();
        tabelPegawai.setData(pegawais);
        tabelPegawai.fireTableDataChanged();
        jTable1.changeSelection(0, 0, false, false);
    }
    
    public void enableInput(boolean enable) {
        tf_no_ktp.setEnabled(enable);
        tf_nama_depan_p.setEnabled(enable);
        tf_nama_belakang_p.setEnabled(enable);
        tf_umur.setEnabled(enable);
        tf_alamat.setEnabled(enable);
        tf_no_telepon.setEnabled(enable);
        tf_username.setEnabled(enable);
        tf_password.setEnabled(enable);
        
        jcb_jenis_kelamin.setEnabled(enable);
        jd_tanggal_lahir_p.setEnabled(enable);
        
        
        if(enable==false){
        tf_id_pegawai.setEnabled(false);
        jd_tanggal_join.setEnabled(false);
        }
    }
    
    public void resetInput() {
        tf_id_pegawai.setText("");
        tf_no_ktp.setText("");
        tf_nama_depan_p.setText("");
        tf_nama_belakang_p.setText("");
        tf_umur.setText("");
        tf_alamat.setText("");
        tf_no_telepon.setText("");
        tf_username.setText("");
        tf_password.setText("");

        
        jcb_jenis_kelamin.setSelectedIndex(0);

        java.util.Date tglNow = new java.util.Date();
        jd_tanggal_join.setDate(tglNow);
        jd_tanggal_lahir_p.setDate(tglNow);
    }
    
    private void setTambah(Boolean tampil, Boolean tambah) {

        if (tambah) {
            if (tampil) {
                bt_simpan_tambah.setVisible(false);
                bt_batal.setVisible(false);
            }
            if (!tampil) {
                bt_simpan_tambah.setVisible(true);
                bt_batal.setVisible(true);
            }

            lb_id_pegawai.setVisible(tampil);
            tf_id_pegawai.setVisible(tampil);
            lb_umur.setVisible(tampil);
            tf_umur.setVisible(tampil);
            lb_tanggal_join.setVisible(tampil);
            jd_tanggal_join.setVisible(tampil);
        } else if (!tambah) {

            if (tampil) {
                bt_simpan_edit.setVisible(false);
                bt_batal.setVisible(false);
            }
            if (!tampil) {
                bt_simpan_edit.setVisible(true);
                bt_batal.setVisible(true);
            }
            tf_id_pegawai.setEnabled(tampil);
            jd_tanggal_join.setEnabled(tampil);
        }

        BtEdit.setEnabled(tampil);
        BtHapus.setEnabled(tampil);
        BtCari.setEnabled(tampil);
        BtTambah.setEnabled(tampil);
        BtRefresh.setEnabled(tampil);
        mouseDisable=!tampil;
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
        lb_id_pegawai = new javax.swing.JLabel();
        tf_id_pegawai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_no_ktp = new javax.swing.JTextField();
        tf_nama_depan_p = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_alamat = new javax.swing.JTextArea();
        tf_no_telepon = new javax.swing.JTextField();
        jcb_jenis_kelamin = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_nama_belakang_p = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_password = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jd_tanggal_lahir_p = new com.toedter.calendar.JDateChooser();
        lb_umur = new javax.swing.JLabel();
        tf_umur = new javax.swing.JTextField();
        lb_tanggal_join = new javax.swing.JLabel();
        jd_tanggal_join = new com.toedter.calendar.JDateChooser();
        bt_simpan_tambah = new javax.swing.JButton();
        bt_batal = new javax.swing.JButton();
        bt_simpan_edit = new javax.swing.JButton();

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtTambah)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BtEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtHapus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtRefresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(BtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(BtTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtCari))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lb_id_pegawai.setText("ID Pegawai");

        tf_id_pegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_id_pegawaiActionPerformed(evt);
            }
        });

        jLabel3.setText("Jenis Kelamin");

        jLabel4.setText("No KTP");

        jLabel5.setText("Nama Depan");

        tf_alamat.setColumns(20);
        tf_alamat.setRows(5);
        jScrollPane2.setViewportView(tf_alamat);

        jcb_jenis_kelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Laki-Laki", "Perempuan" }));

        jLabel1.setText("Alamat");

        jLabel6.setText("No Telepon");

        jLabel7.setText("Nama Belakang");

        jLabel8.setText("Username");

        jLabel9.setText("Password");

        jLabel10.setText("Tanggal Lahir");

        lb_umur.setText("Umur");

        lb_tanggal_join.setText("Tanggal Join");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lb_id_pegawai)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(lb_umur))
                .addGap(111, 111, 111)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jcb_jenis_kelamin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_nama_belakang_p)
                    .addComponent(tf_nama_depan_p)
                    .addComponent(tf_no_ktp)
                    .addComponent(tf_id_pegawai)
                    .addComponent(jd_tanggal_lahir_p, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(tf_umur))
                .addGap(108, 108, 108)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(lb_tanggal_join))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tf_no_telepon)
                    .addComponent(tf_username)
                    .addComponent(tf_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(jd_tanggal_join, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(434, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_no_telepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_id_pegawai)
                            .addComponent(tf_id_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tf_no_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tf_nama_depan_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tf_nama_belakang_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcb_jenis_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10))
                    .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jd_tanggal_lahir_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_umur)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_umur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_tanggal_join))
                    .addComponent(jd_tanggal_join, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tf_id_pegawai.setEnabled(false);
        jd_tanggal_join.setEnabled(false);

        bt_simpan_tambah.setText("Simpan");
        bt_simpan_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_simpan_tambahActionPerformed(evt);
            }
        });

        bt_batal.setText("Batal");
        bt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_batalActionPerformed(evt);
            }
        });

        bt_simpan_edit.setText("Simpan");
        bt_simpan_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_simpan_editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bg, 948, 948, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(469, 469, 469)
                        .addComponent(bt_simpan_tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_simpan_edit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_simpan_tambah)
                    .addComponent(bt_batal)
                    .addComponent(bt_simpan_edit))
                .addGap(41, 41, 41)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bt_simpan_tambah.setVisible(false);
        bt_batal.setVisible(false);
        bt_simpan_edit.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    private void BtTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtTambahActionPerformed
        // TODO add your handling code here:
        System.out.println("Menekan Tombol Tambah");

        enableInput(true);
        setTambah(false, true);
        resetInput();

    }//GEN-LAST:event_BtTambahActionPerformed

    private void BtEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtEditActionPerformed
        System.out.println("Menekan tombol edit");
        if (tf_id_pegawai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih Baris yg akan di Edit");
        } else {
            enableInput(true);
            setTambah(false, false);
        }
    }//GEN-LAST:event_BtEditActionPerformed

    private void BtHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtHapusActionPerformed
        // TODO add your handling code here:

        int baris = jTable1.getSelectedRow();
        String id = (String) tabelPegawai.getValueAt(baris, 0);
        String nama = (String) tabelPegawai.getValueAt(baris, 2);
        
        System.out.println("Menekan tombol hapus");
        System.out.println(id);
        Object[] pilihan = {"Ya", "Tidak"};
        int jawaban = JOptionPane.showOptionDialog(null, "Anda yakin data "
                + "Pegawai dengan nama" + nama
                + " akan dihapus? ","Peringatan",JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,null,pilihan,pilihan[0]
        );
            if (jawaban == 0) {
            db.delete(id);
        }
        try {
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtHapusActionPerformed

    private void BtRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRefreshActionPerformed
        try {
            // TODO add your handling code here:
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtRefreshActionPerformed

    private void BtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCariActionPerformed
        // TODO add your handling code here:
        String katakunci;
        katakunci = JOptionPane.showInputDialog(null, "Nama barang yang di cari?", "Filter/Pencarian", JOptionPane.QUESTION_MESSAGE);
        if (katakunci != null) {
            pegawais = db.pilih_data_like(katakunci);
            tabelPegawai.setData(pegawais);
            tabelPegawai.fireTableDataChanged();
            jTable1.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_BtCariActionPerformed

    private void tf_id_pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_id_pegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_id_pegawaiActionPerformed

    private void bt_simpan_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_simpan_tambahActionPerformed
        
        String id_petugas = db.generate_id_pegawai();
        String jenis_kelamin = jcb_jenis_kelamin.getSelectedItem().toString();
        
        if (jenis_kelamin.equalsIgnoreCase("Laki-laki")) {
            jenis_kelamin = "L";
        } else if (jenis_kelamin.equalsIgnoreCase("Perempuan")) {
            jenis_kelamin = "P";
        }
        
        java.util.Date tglNow = new java.util.Date();
        java.sql.Date tangga_lahir = new java.sql.Date(jd_tanggal_lahir_p.getDate().getTime());
        java.sql.Date tangga_join = new java.sql.Date(tglNow.getTime());
        
        
        
        db.create(
                id_petugas, 
                tf_no_ktp.getText(), 
                tf_nama_depan_p.getText(),
                tf_nama_belakang_p.getText(), 
                jenis_kelamin, 
                tf_alamat.getText(), 
                tangga_lahir,
                tf_no_telepon.getText(),
                tf_username.getText(),
                tf_password.getText(),
                tangga_join);
        
        try {
            refreshData();
            // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTambah(true, true);
        enableInput(false);
        resetInput();
    }//GEN-LAST:event_bt_simpan_tambahActionPerformed

    private void bt_simpan_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_simpan_editActionPerformed
        db.edit(tf_id_pegawai.getText(), tf_no_ktp.getText(), tf_nama_depan_p.getText(), "L", tf_alamat.getText(), tf_no_telepon.getText());
        setTambah(true, false);
        enableInput(false);
        resetInput();

        jTable1.setRowSelectionInterval(0, 0);
        try {
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_simpan_editActionPerformed

    private void bt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_batalActionPerformed
        resetInput();        
        setTambah(true, true);
        setTambah(true, false);
        enableInput(false);
    }//GEN-LAST:event_bt_batalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtCari;
    private javax.swing.JButton BtEdit;
    private javax.swing.JButton BtHapus;
    private javax.swing.JButton BtRefresh;
    private javax.swing.JButton BtTambah;
    private javax.swing.JPanel bg;
    private javax.swing.JButton bt_batal;
    private javax.swing.JButton bt_simpan_edit;
    private javax.swing.JButton bt_simpan_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcb_jenis_kelamin;
    private com.toedter.calendar.JDateChooser jd_tanggal_join;
    private com.toedter.calendar.JDateChooser jd_tanggal_lahir_p;
    private javax.swing.JLabel lb_id_pegawai;
    private javax.swing.JLabel lb_tanggal_join;
    private javax.swing.JLabel lb_umur;
    private javax.swing.JTextArea tf_alamat;
    private javax.swing.JTextField tf_id_pegawai;
    private javax.swing.JTextField tf_nama_belakang_p;
    private javax.swing.JTextField tf_nama_depan_p;
    private javax.swing.JTextField tf_no_ktp;
    private javax.swing.JTextField tf_no_telepon;
    private javax.swing.JTextField tf_password;
    private javax.swing.JTextField tf_umur;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
