/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Owner_Mobil;

import com.sbd12.sewamobil.Pkg_ProdusenMobil.ProdusenMobil;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author resas
 */
public class OwnerMobilTableModel extends AbstractTableModel{
    private List<OwnerMobil> data;
    private String[] nameField={"ID","No KTP","Nama Owner",
                                "Jenis Kelamin","Alamat Owner","No Telepon"};
    
    public void setData(List<OwnerMobil> data)
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
        OwnerMobil kst = data.get(baris);
        switch (kolom) {
            case 0:
                return kst.getId_owner();
            case 1:
                return kst.getNo_ktp_ow();
            case 2:
                return kst.getNama_ow();
            case 3:
                jenisKelamin = kst.getJenis_Kelamin();
                if (jenisKelamin.equalsIgnoreCase("L")) {
                    return "Laki-laki";
                } else if (jenisKelamin.equalsIgnoreCase("P")) {
                    return "Perempuan";
                } else {
                    return "null";
                }
            case 4:
                return kst.getAlamat_ow();
            case 5:
                return kst.getNo_telepon_ow();
            default:
                return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return nameField[column];
    }
    
}
