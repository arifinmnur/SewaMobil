/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Jenis_Mobil;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Anonymous
 */
public class JenisMobilTableModel extends AbstractTableModel{
    
    private List<JenisMobil> data;
    private String[] nameField={"ID Jenis","Jenis Mobil","Harga"};
    
    public void setData(List<JenisMobil> data)
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
        
    
       JenisMobil kst=data.get(baris);
        switch(kolom)
        {
            case 0: return kst.getId_jenis_mobil();
            case 1: return kst.getNama_jenis_mobil();
            case 2: return kst.getHarga_mobil();
            default : return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return nameField[column];
    }
}
