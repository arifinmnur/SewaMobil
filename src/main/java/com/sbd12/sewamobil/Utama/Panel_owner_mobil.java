/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Utama;




import java.util.logging.Level;
import java.util.logging.Logger;
/*import tab_barang.FTambahBarang;
import tab_barang.FUpdateBarang;
import tab_barang.crud_Barang;
import tab_barang.dt_Barang;
import tab_barang.barangTableModel;*/

import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sbd12.sewamobil.Pkg_Jenis_Mobil.*;
import com.sbd12.sewamobil.Pkg_Owner_Mobil.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ArieDZ 2
 */
public class Panel_owner_mobil extends javax.swing.JPanel {

   
    public OwnerMobilTableModel tabelOwner;
    public frm_Utama_metro form_parent;
    public List<OwnerMobil> ownerMobils;
    public OwnerMobilJDBCTemplate db;
    private String old_id_jenis_mobil;
    private String jenisKelamin;
    private boolean mouseDisable=false;

//    public ProdusenmobilJDBCTemplate produsenmobilJDBCTemplate;

    public Panel_owner_mobil() throws ClassNotFoundException {
        initComponents();
        ApplicationContext context = new ClassPathXmlApplicationContext("Config-Spring.xml");
        db = (OwnerMobilJDBCTemplate) context.getBean("ownerMobilJDBCTemplate");
        ownerMobils = db.listSemua();

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!mouseDisable) {
                    if (e.getClickCount() >= 1) {
                        int baris = jTable1.getSelectedRow();
                        String id_owner = (String) tabelOwner.getValueAt(baris, 0);
                        System.out.println("id =" + id_owner);
                        OwnerMobil mm = db.pilih_data(id_owner);
                        tf_id_owner.setText(mm.getId_owner());
                        tf_no_ktp.setText(mm.getNo_ktp_ow());
                        tf_nama_owner.setText(mm.getNama_ow());

                        tf_alamat.setText(mm.getAlamat_ow());
                        tf_no_telepon.setText(mm.getNo_telepon_ow());
                        jenisKelamin = mm.getJenis_Kelamin();
                        if (jenisKelamin.equalsIgnoreCase("L")) {
                            jcb_jenis_kelamin.setSelectedIndex(1);
                        } else if (jenisKelamin.equalsIgnoreCase("P")) {
                            jcb_jenis_kelamin.setSelectedIndex(2);
                        } else {
                            jcb_jenis_kelamin.setSelectedIndex(0);
                        }
                    }
                }
            }
        });
        tampilData_produsen();
        refreshData();
        enableInput(false);
        resetInput();
    }
    public void tampilData_produsen() throws ClassNotFoundException
    {

        tabelOwner = new OwnerMobilTableModel();
        tabelOwner.setData(ownerMobils);
        jTable1.setModel(tabelOwner);

    }
    
    public void enableInput(boolean enable) {
        tf_id_owner.setEnabled(enable);
        tf_nama_owner.setEnabled(enable);
        tf_no_ktp.setEnabled(enable);
        jcb_jenis_kelamin.setEnabled(enable);
        tf_alamat.setEnabled(enable);
        tf_no_telepon.setEnabled(enable);

        if (enable == false) {
        }
    }

    public void resetInput() {
        tf_id_owner.setText("");
        tf_nama_owner.setText("");
        tf_no_ktp.setText("");
        jcb_jenis_kelamin.setSelectedIndex(0);
        tf_alamat.setText("");
        tf_no_telepon.setText("");
    }

    public void refreshData() throws ClassNotFoundException {
        ownerMobils = db.listSemua();
        tabelOwner.setData(ownerMobils);
        tabelOwner.fireTableDataChanged();
        jTable1.changeSelection(0, 0, false, false);
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
            lb_id_owner.setVisible(tampil);
            tf_id_owner.setVisible(tampil);
        } else if (!tambah) {

            if (tampil) {
                bt_simpan_edit.setVisible(false);
                bt_batal.setVisible(false);
            }
            if (!tampil) {
                bt_simpan_edit.setVisible(true);
                bt_batal.setVisible(true);
            }
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
        lb_id_owner = new javax.swing.JLabel();
        tf_id_owner = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_nama_owner = new javax.swing.JTextField();
        bt_simpan_tambah = new javax.swing.JButton();
        bt_batal = new javax.swing.JButton();
        bt_simpan_edit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tf_no_ktp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jcb_jenis_kelamin = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        tf_alamat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_no_telepon = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(960, 720));
        setMinimumSize(new java.awt.Dimension(960, 720));
        setPreferredSize(new java.awt.Dimension(960, 720));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setMaximumSize(new java.awt.Dimension(960, 720));
        bg.setMinimumSize(new java.awt.Dimension(960, 720));
        bg.setPreferredSize(new java.awt.Dimension(960, 720));

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

        BtTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Plus_24px_3.png"))); // NOI18N
        BtTambah.setText("Tambah");
        BtTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtTambahActionPerformed(evt);
            }
        });

        BtEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Edit_File_24px.png"))); // NOI18N
        BtEdit.setText("Edit");
        BtEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtEditActionPerformed(evt);
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

        BtCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_24px.png"))); // NOI18N
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
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(70, 70, 70))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(BtTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtCari)))
                .addGap(354, 354, 354))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lb_id_owner.setText("ID Owner");

        tf_id_owner.setEditable(false);
        tf_id_owner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_id_ownerActionPerformed(evt);
            }
        });

        jLabel4.setText("Nama Owner");

        tf_nama_owner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nama_ownerActionPerformed(evt);
            }
        });

        bt_simpan_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Save_24px.png"))); // NOI18N
        bt_simpan_tambah.setText("Simpan");
        bt_simpan_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_simpan_tambahActionPerformed(evt);
            }
        });

        bt_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cancel_24px.png"))); // NOI18N
        bt_batal.setText("Batal");
        bt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_batalActionPerformed(evt);
            }
        });

        bt_simpan_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Save_as_24px_2.png"))); // NOI18N
        bt_simpan_edit.setText("Simpan");
        bt_simpan_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_simpan_editActionPerformed(evt);
            }
        });

        jLabel5.setText("No KTP");

        tf_no_ktp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_no_ktpActionPerformed(evt);
            }
        });

        jLabel6.setText("Jenis Kelamin");

        jcb_jenis_kelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Laki-Laki", "Perempuan" }));

        jLabel7.setText("Alamat");

        jLabel8.setText("No Telepon");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_id_owner)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tf_id_owner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(tf_no_ktp)
                    .addComponent(tf_nama_owner)
                    .addComponent(jcb_jenis_kelamin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_simpan_tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_batal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_no_telepon))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_simpan_edit)
                .addGap(252, 252, 252))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_id_owner)
                            .addComponent(tf_id_owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tf_no_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(tf_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_nama_owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(tf_no_telepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcb_jenis_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_simpan_tambah)
                    .addComponent(bt_batal)
                    .addComponent(bt_simpan_edit))
                .addGap(14, 14, 14))
        );

        bt_simpan_tambah.setVisible(false);
        bt_batal .setVisible(false);
        bt_simpan_edit.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bg, 948, 948, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtTambahActionPerformed
        System.out.println("Menekan Tombol Tambah");
        enableInput(true);
        setTambah(false, true);
        resetInput();   // TODO add your handling code here:
    }//GEN-LAST:event_BtTambahActionPerformed

    private void BtEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtEditActionPerformed
        System.out.println("Menekan tombol edit");
        if (tf_id_owner.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih Baris yg akan di Edit");
        } else {
            enableInput(true);
            setTambah(false, false);
        }
    }//GEN-LAST:event_BtEditActionPerformed

    private void BtHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtHapusActionPerformed
        // TODO add your handling code here:
        
        int baris = jTable1.getSelectedRow();
        String id = (String) tabelOwner.getValueAt(baris, 0);
        String nama = (String) tabelOwner.getValueAt(baris, 1);
        
        System.out.println("Menekan tombol hapus");
        System.out.println(id);
        Object[] pilihan = {"Ya", "Tidak"};
        int jawaban = JOptionPane.showOptionDialog(null, "Anda yakin data "
                + " Produsen dengan ID='" + id +"' dan Nama='"+nama+"'"
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
            Logger.getLogger(Panel_merk_mobil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtRefreshActionPerformed

    private void BtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtCariActionPerformed
        // TODO add your handling code here:
       /* String katakunci;
        katakunci=JOptionPane.showInputDialog(null,"Nama produsen yang di cari?","Filter/Pencarian",JOptionPane.QUESTION_MESSAGE);
        if(katakunci!=null)
        {
            try {
                tabelProdusen.setData(db.filter(katakunci));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(panel_produsen_mobil.class.getName()).log(Level.SEVERE, null, ex);
            }
            tabelProdusen.fireTableDataChanged();
        }*/
    }//GEN-LAST:event_BtCariActionPerformed


    private void tf_id_ownerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_id_ownerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_id_ownerActionPerformed

    private void tf_nama_ownerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nama_ownerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nama_ownerActionPerformed

    private void bt_simpan_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_simpan_tambahActionPerformed
        String id_owner_generate = db.generate_id_owner();
        int jenis_kelamin_int = jcb_jenis_kelamin.getSelectedIndex();

        if (jenis_kelamin_int == 1) {
            jenisKelamin = "L";
        } else if (jenis_kelamin_int == 2) {
            jenisKelamin = "P";
        } else {
            JOptionPane.showMessageDialog(this, "Pilih Jenis Kelamin");
            return;
        }

        db.create(id_owner_generate.toUpperCase(),
                tf_no_ktp.getText(),
                tf_nama_owner.getText(),
                jenisKelamin,
                tf_alamat.getText(),
                tf_no_telepon.getText());
        try {
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_merk_mobil.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTambah(true, true);
        enableInput(false);
        resetInput();        // TODO add your handling code here:
    }//GEN-LAST:event_bt_simpan_tambahActionPerformed

    private void bt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_batalActionPerformed
        resetInput();
        setTambah(true, true);
        setTambah(true, false);
        enableInput(false);     // TODO add your handling code here:
    }//GEN-LAST:event_bt_batalActionPerformed

    private void bt_simpan_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_simpan_editActionPerformed
        String id_owner_generate = db.generate_id_owner();
        int jenis_kelamin_int = jcb_jenis_kelamin.getSelectedIndex();

        if (jenis_kelamin_int == 1) {
            jenisKelamin = "L";
        } else if (jenis_kelamin_int == 2) {
            jenisKelamin = "P";
        } else {
            JOptionPane.showMessageDialog(this, "Pilih Jenis Kelamin");
            return;
        }
//db.edit(tf_id_owner.getText().toUpperCase(), tf_nama_owner.getText(),Double.valueOf(tf_no_ktp.getText()), old_id_jenis_mobil);
        try {
            refreshData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Panel_merk_mobil.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTambah(true, false);
        enableInput(false);
        resetInput();

        jTable1.setRowSelectionInterval(0, 0);             // TODO add your handling code here:
    }//GEN-LAST:event_bt_simpan_editActionPerformed

    private void tf_no_ktpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_no_ktpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_no_ktpActionPerformed


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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcb_jenis_kelamin;
    private javax.swing.JLabel lb_id_owner;
    private javax.swing.JTextField tf_alamat;
    private javax.swing.JTextField tf_id_owner;
    private javax.swing.JTextField tf_nama_owner;
    private javax.swing.JTextField tf_no_ktp;
    private javax.swing.JTextField tf_no_telepon;
    // End of variables declaration//GEN-END:variables
}
