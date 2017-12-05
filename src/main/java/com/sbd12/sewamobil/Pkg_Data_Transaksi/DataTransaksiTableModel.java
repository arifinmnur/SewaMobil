package com.sbd12.sewamobil.Pkg_Data_Transaksi;




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
public class DataTransaksiTableModel extends AbstractTableModel{
    private List<DataTransaksi> data;
    private String[] nameField={
        "No Transaksi",
        "Nama Kostumer",
        "Nama Pegawawai",
        "NoPol",
        "Mobil",
        "Pinjam",
        "Kembali",
        "Harga Total"};
    
    public void setData(List<DataTransaksi> data)
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
        
        DataTransaksi kst=data.get(baris);
        switch(kolom)
        {
            case 0: return kst.getNo_transaksi();
            case 1: return kst.getNama_kostumer();
            case 2: return kst.getNama_pegawai();
            case 3: return kst.getNo_pol();
            case 4: return kst.getNama_mobil();
            case 5: return kst.getTglpinjam();
            case 6: return kst.getTglkembali();
            case 7: return kst.getHarga_total();
            default : return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return nameField[column];
    }
    
    
}
