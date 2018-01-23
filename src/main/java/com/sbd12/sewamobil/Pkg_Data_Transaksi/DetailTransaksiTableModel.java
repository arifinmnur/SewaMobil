/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Data_Transaksi;

import com.sbd12.sewamobil.Pkg_Data_Mobil.DataMobil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.springframework.util.StringUtils;

/**
 *
 * @author resas
 */
public class DetailTransaksiTableModel extends AbstractTableModel {

    private List<DataMobil> data;
    private DecimalFormat kursIndonesia;
    private DecimalFormatSymbols formatRp;
    private String[] nameField = {"No Polisi", "Merk", "Harga", "Petugas Pengembalian", "Tgl Kembali", "Denda", "Catatan"};

    public void setData(List<DataMobil> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return nameField.length;
    }

    @Override
    public Object getValueAt(int baris, int kolom) {

        DataMobil kst = data.get(baris);
        switch (kolom) {
            case 0:
                return kst.getNo_pol();
            case 1:
                return kst.getNama_mobil();
            case 2:
                return kst.getHarga_perhari();
            case 3:
                if (StringUtils.isEmpty(kst.getNama_petugas_pengembalian())) {
                    return "-";
                } else {
                    return kst.getNama_petugas_pengembalian();
                }
            case 4:
                if (StringUtils.isEmpty(kst.getTglkembali())) {
                    return "-";
                } else {
                    return kst.getTglkembali();
                }
            case 5: return kst.getDenda();
            //case 5: return kursIndonesia.format(kst.getHarga_total());
            case 6:
                if (StringUtils.isEmpty(kst.getStatus())) {
                    return "-";
                } else {
                    return kst.getStatus();
                }
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
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
