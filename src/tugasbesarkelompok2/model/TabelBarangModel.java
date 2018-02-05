/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import tugasbesarkelompok2.entity.Barang;

/**
 *
 * @author User
 */
public class TabelBarangModel extends AbstractTableModel{

    private List<Barang> list = new ArrayList<Barang>();

    public void setList(List<Barang> list) {
        this.list = list;
    }
    
    
    @Override
    public int getRowCount() {
        
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    
    
    public boolean add(Barang e) {
        try {
            return list.add(e);
        } finally {
            fireTableRowsInserted(getRowCount()-1,getRowCount()-1);
        }
    }

    public Barang get(int index) {
        return list.get(index);
    }

    public Barang set(int index, Barang element) {
        try {
            return list.set(index, element);
        } finally {
            fireTableRowsUpdated(index, index);
        }
        
    }

    public Barang remove(int index) {
        try {
        return list.remove(index);    
        } finally {
            fireTableRowsDeleted(index, index);
        }
        
        
    }

    
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0 : return "KODE";
            case 1 : return "NAMA BARANG";
            case 2 : return "JENIS";
            case 3 : return "TIPE";
            case 4 : return "JUMLAH";
            case 5 : return "HARGA";
            default: return null;
        }
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return list.get(rowIndex).getKode();
            case 1 : return list.get(rowIndex).getNamaBarang();
            case 2 : return list.get(rowIndex).getJenisBarang();
            case 3 : return list.get(rowIndex).getTipeBarang();
            case 4 : return list.get(rowIndex).getJumlah();
            case 5 : return list.get(rowIndex).getHarga();
            default: return null;
            
        }
        
    }
    
}
