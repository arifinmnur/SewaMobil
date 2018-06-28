package com.sbd12.sewamobil.Pkg_Data_Transaksi;




import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
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
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    private DecimalFormat kursIndonesia;
    private DecimalFormatSymbols formatRp;
    
    public void formatCurrency() {
        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }
    
    private String[] nameField={
        "No Transaksi",
        "Nama Kostumer",
        "Nama Pegawawai",
        "Pinjam",
        "Kembali",
        "Harga Total",
        "Status"};
    
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
        formatCurrency();
        DataTransaksi kst=data.get(baris);
        switch(kolom)
        {
            /*case 0: return kst.getNo_transaksi();
            case 1: return kst.getNama_kostumer();
            case 2: return kst.getNama_pegawai();
            case 3: return kst.getNo_pol();
            case 4: return kst.getNama_mobil();
            case 5: return kst.getTglpinjam();
            case 6: return kst.getTglkembali();
            case 7: return kst.getHarga_total();*/
            
            case 0: return kst.getNo_transaksi();
            case 1: return kst.getNama_kostumer();
            case 2: return kst.getNama_pegawai();
            case 3: return sdf.format(kst.getTglpinjam());
            case 4: return sdf.format(kst.getTglkembali());
            case 5: return kursIndonesia.format(kst.getHarga_total());
            case 6: return kst.getStatus();
            default : return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return nameField[column];
    }

    
}
