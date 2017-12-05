/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Utama;


import java.awt.LayoutManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ArieDZ 2
 */
public class frm_Utama_metro extends javax.swing.JFrame {

    /**
     * Creates new form frm_Utama_metro
     */
     panel_merk_mobil pdb;
     panel_produsen_mobil pdm;
     panel_pegawai pgw;
     panel_kostumer kst;
     
     /*panel baru misal*/
//     panel_kostumer pk;
    /*panel_data_transaksi pdt;
    panel_data_kostumer pdk; 
     panel_data_petugas pdp; */      
    public frm_Utama_metro() throws ClassNotFoundException {
        initComponents();
         pdb=new panel_merk_mobil();
         pdm=new panel_produsen_mobil();
         pgw=new panel_pegawai();
         kst=new panel_kostumer();
         
        
    }
 public void ganti_panel() throws  ClassNotFoundException
 {      jP_Barang.removeAll();
        jP_Barang.add(pdb);
        jP_Barang.repaint();
        jP_Barang.revalidate();
        
      /*  jP_Kostumer.removeAll();
        jP_Kostumer.add(pdk);
        jP_Kostumer.repaint();;
        jP_Kostumer.revalidate();
        
        jP_Petugas.removeAll();
        jP_Petugas.add(pdp);
        jP_Petugas.repaint();;
        jP_Petugas.revalidate();
        
        jP_Transaksi.removeAll();
        jP_Transaksi.add(pdt);
        jP_Transaksi.repaint();;
        jP_Transaksi.revalidate();*/
 }
 public void swap_panel(JPanel panel){
        Layout_tengah.removeAll();
        Layout_tengah.add(panel);
        Layout_tengah.repaint();
        Layout_tengah.revalidate();
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPan_bg = new javax.swing.JPanel();
        sidepanel = new javax.swing.JPanel();
        btn_tb_merk_mobil = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_produsen_mobil = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_tb_pegawai = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btn_tbl_kostumer = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Layout_tengah = new javax.swing.JPanel();
        jP_Kostumer = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jP_Barang = new javax.swing.JPanel();
        jP_Transaksi = new javax.swing.JPanel();
        jP_Petugas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPan_bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidepanel.setBackground(new java.awt.Color(0, 102, 102));
        sidepanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_tb_merk_mobil.setBackground(new java.awt.Color(0, 80, 104));
        btn_tb_merk_mobil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tb_merk_mobilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_tb_merk_mobilMouseEntered(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Merk Mobil");

        javax.swing.GroupLayout btn_tb_merk_mobilLayout = new javax.swing.GroupLayout(btn_tb_merk_mobil);
        btn_tb_merk_mobil.setLayout(btn_tb_merk_mobilLayout);
        btn_tb_merk_mobilLayout.setHorizontalGroup(
            btn_tb_merk_mobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_tb_merk_mobilLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btn_tb_merk_mobilLayout.setVerticalGroup(
            btn_tb_merk_mobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_tb_merk_mobilLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        sidepanel.add(btn_tb_merk_mobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        btn_produsen_mobil.setBackground(new java.awt.Color(0, 80, 104));
        btn_produsen_mobil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_produsen_mobilMousePressed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Produsen Mobil");

        javax.swing.GroupLayout btn_produsen_mobilLayout = new javax.swing.GroupLayout(btn_produsen_mobil);
        btn_produsen_mobil.setLayout(btn_produsen_mobilLayout);
        btn_produsen_mobilLayout.setHorizontalGroup(
            btn_produsen_mobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_produsen_mobilLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btn_produsen_mobilLayout.setVerticalGroup(
            btn_produsen_mobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_produsen_mobilLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        sidepanel.add(btn_produsen_mobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        btn_tb_pegawai.setBackground(new java.awt.Color(0, 80, 104));
        btn_tb_pegawai.setForeground(new java.awt.Color(255, 255, 255));
        btn_tb_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tb_pegawaiMousePressed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pegawai");

        javax.swing.GroupLayout btn_tb_pegawaiLayout = new javax.swing.GroupLayout(btn_tb_pegawai);
        btn_tb_pegawai.setLayout(btn_tb_pegawaiLayout);
        btn_tb_pegawaiLayout.setHorizontalGroup(
            btn_tb_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_tb_pegawaiLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btn_tb_pegawaiLayout.setVerticalGroup(
            btn_tb_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_tb_pegawaiLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        sidepanel.add(btn_tb_pegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, -1, -1));

        btn_tbl_kostumer.setBackground(new java.awt.Color(0, 80, 104));
        btn_tbl_kostumer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tbl_kostumerMousePressed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kostumer");

        javax.swing.GroupLayout btn_tbl_kostumerLayout = new javax.swing.GroupLayout(btn_tbl_kostumer);
        btn_tbl_kostumer.setLayout(btn_tbl_kostumerLayout);
        btn_tbl_kostumerLayout.setHorizontalGroup(
            btn_tbl_kostumerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_tbl_kostumerLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btn_tbl_kostumerLayout.setVerticalGroup(
            btn_tbl_kostumerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_tbl_kostumerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        sidepanel.add(btn_tbl_kostumer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

        jPan_bg.add(sidepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 510));

        Layout_tengah.setBackground(new java.awt.Color(0, 102, 102));
        Layout_tengah.setLayout(new java.awt.CardLayout());

        jP_Kostumer.setForeground(new java.awt.Color(0, 153, 153));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel9.setText("PENGOLAHAN DATABASE ");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel10.setText("PENYEWAAN MOBIL");

        javax.swing.GroupLayout jP_KostumerLayout = new javax.swing.GroupLayout(jP_Kostumer);
        jP_Kostumer.setLayout(jP_KostumerLayout);
        jP_KostumerLayout.setHorizontalGroup(
            jP_KostumerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_KostumerLayout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addGroup(jP_KostumerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_KostumerLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(262, 262, 262))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_KostumerLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158))))
        );
        jP_KostumerLayout.setVerticalGroup(
            jP_KostumerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_KostumerLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        Layout_tengah.add(jP_Kostumer, "card5");

        javax.swing.GroupLayout jP_BarangLayout = new javax.swing.GroupLayout(jP_Barang);
        jP_Barang.setLayout(jP_BarangLayout);
        jP_BarangLayout.setHorizontalGroup(
            jP_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jP_BarangLayout.setVerticalGroup(
            jP_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        Layout_tengah.add(jP_Barang, "card3");

        javax.swing.GroupLayout jP_TransaksiLayout = new javax.swing.GroupLayout(jP_Transaksi);
        jP_Transaksi.setLayout(jP_TransaksiLayout);
        jP_TransaksiLayout.setHorizontalGroup(
            jP_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jP_TransaksiLayout.setVerticalGroup(
            jP_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        Layout_tengah.add(jP_Transaksi, "card4");

        javax.swing.GroupLayout jP_PetugasLayout = new javax.swing.GroupLayout(jP_Petugas);
        jP_Petugas.setLayout(jP_PetugasLayout);
        jP_PetugasLayout.setHorizontalGroup(
            jP_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jP_PetugasLayout.setVerticalGroup(
            jP_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        Layout_tengah.add(jP_Petugas, "card2");

        jPan_bg.add(Layout_tengah, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 960, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPan_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPan_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_produsen_mobilMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_produsen_mobilMousePressed
        // TODO add your handling code here:
       // swap_panel(pdp);
        swap_panel(pdm);
       this.setTitle("DATA PETUGAS");
    }//GEN-LAST:event_btn_produsen_mobilMousePressed

    private void btn_tb_pegawaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tb_pegawaiMousePressed
        // TODO add your handling code here:
        //swap_panel(pdk);
        swap_panel(pgw);
        this.setTitle("DATA PEGAWAI");
    }//GEN-LAST:event_btn_tb_pegawaiMousePressed

    private void btn_tbl_kostumerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tbl_kostumerMousePressed
        // TODO add your handling code here:
        //swap_panel(pdt);
        swap_panel(kst);
        this.setTitle("DATA KOSTUMER");
    }//GEN-LAST:event_btn_tbl_kostumerMousePressed

    private void btn_tb_merk_mobilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tb_merk_mobilMouseClicked
        // TODO add your handling code here:
        swap_panel(pdb);
        this.setTitle("DATA BARANG");
    }//GEN-LAST:event_btn_tb_merk_mobilMouseClicked

    private void btn_tb_merk_mobilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tb_merk_mobilMouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btn_tb_merk_mobilMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_Utama_metro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Utama_metro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Utama_metro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Utama_metro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new frm_Utama_metro().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(frm_Utama_metro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Layout_tengah;
    private javax.swing.JPanel btn_produsen_mobil;
    private javax.swing.JPanel btn_tb_merk_mobil;
    private javax.swing.JPanel btn_tb_pegawai;
    private javax.swing.JPanel btn_tbl_kostumer;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jP_Barang;
    private javax.swing.JPanel jP_Kostumer;
    private javax.swing.JPanel jP_Petugas;
    private javax.swing.JPanel jP_Transaksi;
    private javax.swing.JPanel jPan_bg;
    private javax.swing.JPanel sidepanel;
    // End of variables declaration//GEN-END:variables
}
