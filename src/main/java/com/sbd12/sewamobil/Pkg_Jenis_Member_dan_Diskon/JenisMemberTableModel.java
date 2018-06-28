/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbd12.sewamobil.Pkg_Jenis_Member_dan_Diskon;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class JenisMemberTableModel extends AbstractTableModel {

        private List<JenisMember> data;
        private String[] nameField = {
            "Counter",
            "Kode Jenis",
            "Nama",
            "Diskon"};
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

        public void setData(List<JenisMember> data) {
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
           formatCurrency();
            JenisMember kst = data.get(baris);
            switch (kolom) {
                case 0:
                    return kst.getCounter();
                case 1:
                    return kst.getId_jenis_member();
                case 2:
                    return kst.getNama_jenis_member();
                case 3:
                    return (kst.getDiskon()*100)+"%";
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return nameField[column];
        }

    }
