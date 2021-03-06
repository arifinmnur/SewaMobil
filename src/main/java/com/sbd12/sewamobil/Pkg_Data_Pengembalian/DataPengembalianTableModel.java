package com.sbd12.sewamobil.Pkg_Data_Pengembalian;




import com.sbd12.sewamobil.Pkg_Data_Pengembalian.DataPengembalian;
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
public class DataPengembalianTableModel extends AbstractTableModel{
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    private List<DataPengembalian> data;
    private DecimalFormat kursIndonesia;
    private DecimalFormatSymbols formatRp;
    
    private String[] nameField={
        "No Transaksi",
        "Kostumer",
        "Pegawai Pengembalian",
        "Perkiraan Kembali",
        "Tgl Pengembalian",
        "Telat",
        "Denda"};
    
    public void setData(List<DataPengembalian> data)
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
        DataPengembalian kst=data.get(baris);
        switch(kolom)
        {
            
            case 0: return kst.getNo_transaksi();
            case 1: return kst.getNama_kostumer();
            case 2: return kst.getNama_pegawai();
            case 3: return sdf.format(kst.getTglkembali_seharusnya());
            case 4: return kst.getTglPengembalianString();
            case 5: return kst.getTelat()+"Hari";
            case 6: return kursIndonesia.format(kst.getDenda());
            default : return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return nameField[column];
    }

    public void formatCurrency() {
        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }

}
