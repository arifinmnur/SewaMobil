package com.sbd12.sewamobil.Pkg_Data_Pembayaran;





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
public class DataPembayaranTableModel extends AbstractTableModel{
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    private List<DataPembayaran> data;
    private DecimalFormat kursIndonesia;
    private DecimalFormatSymbols formatRp;
    
    private String[] nameField={
        "No Transaksi",
        "No Pembayaran",
        "Jenis",
        "Jumlah",
        "Tgl Pembayaran",
        "Keterangan"};
    
    public void setData(List<DataPembayaran> data)
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
        DataPembayaran kst=data.get(baris);
        switch(kolom)
        {
            
            case 0: return kst.getNo_transaksi();
            case 1: return kst.getId_pembayaran();
            case 2: return kst.getJenis_pembayaran();
            case 3: return kursIndonesia.format(kst.getJumlah());
            case 4: return kst.getTglTransaksiString();
            case 5: return kst.getKeterangan();
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
