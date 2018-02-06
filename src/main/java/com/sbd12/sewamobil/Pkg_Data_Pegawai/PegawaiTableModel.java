package com.sbd12.sewamobil.Pkg_Data_Pegawai;



import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ArieDZ
 */
public class PegawaiTableModel extends AbstractTableModel{
    private List<Pegawai> data;
    private String[] nameField={"ID Pegawai"," KTP"," Nama Lengkap"," Jenis Kelamin","  Alamat","  Telepon","Username","Password"};
    
    public void setData(List<Pegawai> data)
    {
        this.data=data;
    }
    
    @Override
    public int getRowCount() {
      return data.size();
    }

    @Override
    public int getColumnCount() {
      return nameField.length; }

    @Override
    public Object getValueAt(int baris, int kolom) {
        String jenisKelamin;
        Pegawai pg = data.get(baris);
        switch (kolom) {
            case 0:
                return pg.getId_pegawai();
            case 1:
                return pg.getNo_ktp_p();
            case 2:
                return pg.getNama_lengkap_p();
            case 3:
                jenisKelamin = pg.getJenis_kelamin_p();
                if (jenisKelamin.equalsIgnoreCase("L")) {
                    return "Laki-laki";
                } else if (jenisKelamin.equalsIgnoreCase("P")) {
                    return "Perempuan";
                } else {
                    return "null";
                }
            case 4:
                return pg.getAlamat_p();
            case 5: return pg.getNo_telepon_p();
            case 6: return pg.getUsername();
            case 7: return pg.getPassword();
            default : return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return nameField[column];
    }
    
    
}
