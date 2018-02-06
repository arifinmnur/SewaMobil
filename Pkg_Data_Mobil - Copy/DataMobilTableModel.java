/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Data_Mobil;

import com.sbd12.sewamobil.Pkg_Merk_Mobil.MerkMobil;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author resas
 */
public class DataMobilTableModel extends AbstractTableModel{
    private List<DataMobil> data;
    private String[] nameField={"No Polisi","Merk","Owner","Harga"};
    
    public void setData(List<DataMobil> data)
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
        
        DataMobil kst=data.get(baris);
        switch(kolom)
        {
            case 0: return kst.getNo_pol();
            case 1: return kst.getNama_mobil();
            case 2: return kst.getNama_ow();
            case 3: return kst.getHarga_perhari();
            default : return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return nameField[column];
    }
}
