package com.sbd12.sewamobil.Pkg_Kostomer;




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
public class KostumerTableModel extends AbstractTableModel{
    private List<Kostumer> data;
    private String[] nameField={"id_kostumer"," no_ktp"," nama_k"," jenis_kelamin_k","  alamat_k","  no_telepon_k"};
    
    public void setData(List<Kostumer> data)
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
        
        Kostumer kst=data.get(baris);
        switch(kolom)
        {
            case 0: return kst.getId_kostumer();
            case 1: return kst.getNo_ktp();
            case 2: return kst.getNama_k();
            case 3: return kst.getJenis_kelamin_k();
            case 4: return kst.getAlamat_k();
            case 5: return kst.getNo_telepon_k();
            default : return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return nameField[column];
    }
    
    
}
