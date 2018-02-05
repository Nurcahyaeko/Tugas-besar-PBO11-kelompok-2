/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.controller;


import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import tugasbesarkelompok2.error.BarangException;
import tugasbesarkelompok2.model.BarangModel;
import tugasbesarkelompok2.view.BarangView;
import tugasbesarkelompok2.model.TabelCari;
import tugasbesarkelompok2.entity.Barang;
        
        
/**
 *
 * @author User
 */
public class BarangController {
    
    private BarangModel model;

    public void setModel(BarangModel model) {
        this.model = model;
    }
            
    public void resetBarang(BarangView view){
        model.resetBarang();
    }
    
    public void insertBarang(BarangView view){
        
        Integer kode = Integer.parseInt(view.getTxtTambahKode().getText());
        String namaBarang = view.getTxtTambahNamaBarang().getText();
        String jenisBarang = view.getTxtTambahJenis().getText();
        String tipeBarang = view.getTxtTambahTipe().getText();
        Integer jumlah = Integer.parseInt(view.getTxtTambahJumlah().getText());
        Integer harga = Integer.parseInt(view.getTxtTambahHarga().getText());
                
        /*if (kode == 0) {
            JOptionPane.showMessageDialog(view, "Kode tidak boleh kosong");
        } else */ if (kode.longValue() >10) {
            JOptionPane.showMessageDialog(view, "Kode tidak boleh lebih dari 10 karakter");
        } else if (namaBarang.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Nama Barang tidak boleh kosong");
        } else if (namaBarang.length()>200) {
            JOptionPane.showMessageDialog(view, "Nama Barang tidak boleh lebih dari 200 karakter");
        } else if (jenisBarang.length()>100) {
            JOptionPane.showMessageDialog(view, "Jenis Barang tidak boleh lebih dari 100 karakter");
        } else if (tipeBarang.length()>50) {
            JOptionPane.showMessageDialog(view, "Tipe Barang tidak boleh lebih dari 50 karakter");
        } else if (jumlah == 0) {
            JOptionPane.showMessageDialog(view, "Jumlah Barang tidak boleh kosong");
        } else if (jumlah.longValue()>100000000) {
            JOptionPane.showMessageDialog(view, "Jumlah Barang tidak boleh lebih dari 10 karakter");
        } else if (harga.longValue()>10000000) {
            JOptionPane.showMessageDialog(view, "Harga Barang tidak boleh lebih dari 15 karakter");
        }
        

        model.setKode(kode);
        model.setNamaBarang(namaBarang);
        model.setJenisBarang(jenisBarang);
        model.setTipeBarang(tipeBarang);
        model.setJumlah(jumlah);
        model.setHarga(harga);
     
        try {
            model.insertBarang();
            JOptionPane.showMessageDialog(view, "Barang berhasil ditambahkan");
            model.resetBarang();
        } catch (Throwable throwable) {
            JOptionPane.showMessageDialog(view, new Object[]{"Terjadi eror di Database",throwable.getMessage()});
        }
    }
    
    public void updateBarang(BarangView view){
        
        if (view.getTabelStok().getSelectedColumn()==0) {
            JOptionPane.showMessageDialog(view, "Silahkan seleksi baris data yang akan dirubah");
            return;
        }
        
        Integer kode = Integer.parseInt(view.getTxtTambahKode().getText());
        String  namaBarang = view.getTxtTambahNamaBarang().getText();
        String  jenisBarang = view.getTxtTambahJenis().getText();
        String  tipeBarang = view.getTxtTambahTipe().getText();
        Integer jumlah = Integer.parseInt(view.getTxtTambahJumlah().getText());
        Integer harga = Integer.parseInt(view.getTxtTambahHarga().getText());
                  
        if (kode == 0) {
            JOptionPane.showMessageDialog(view, "Kode tidak boleh kosong");
        } else if (kode.longValue() >10) {
            JOptionPane.showMessageDialog(view, "Kode tidak boleh lebih dari 10 karakter");
        } else if (namaBarang.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Nama Barang tidak boleh kosong");
        } else if (namaBarang.length()>200) {
            JOptionPane.showMessageDialog(view, "Nama Barang tidak boleh lebih dari 200 karakter");
        } else if (jenisBarang.length()>100) {
            JOptionPane.showMessageDialog(view, "Jenis Barang tidak boleh lebih dari 100 karakter");
        } else if (tipeBarang.length()>50) {
            JOptionPane.showMessageDialog(view, "Tipe Barang tidak boleh lebih dari 50 karakter");
        } else if (jumlah == 0) {
            JOptionPane.showMessageDialog(view, "Jumlah Barang tidak boleh kosong");
        } else if (jumlah.longValue()>10) {
            JOptionPane.showMessageDialog(view, "Jumlah Barang tidak boleh lebih dari 10 karakter");
        } else if (harga.longValue()>15) {
            JOptionPane.showMessageDialog(view, "Harga Barang tidak boleh lebih dari 15 karakter");
        }
        
        model.setKode(kode);
        model.setNamaBarang(namaBarang);
        model.setJenisBarang(jenisBarang);
        model.setTipeBarang(tipeBarang);
        model.setJumlah(jumlah);
        model.setHarga(harga);
     
        try {
            model.updateBarang();
            JOptionPane.showMessageDialog(view, "Barang berhasil diubah");
            model.resetBarang();
        } catch (Throwable throwable) {
            JOptionPane.showMessageDialog(view, new Object[]{"Terjadi eror di Database",throwable.getMessage()});
        }
       
    }
    
    public void deleteBarang(BarangView view){
        if (view.getTabelStok().getSelectedColumn()==0) {
            JOptionPane.showMessageDialog(view, "Silahkan seleksi baris data yang akan dihapus");
            return;
        }
        if (JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus data ?")==JOptionPane.OK_OPTION) {
        Integer kode = Integer.parseInt(view.getTxtTambahKode().getText());
        model.setKode(kode);
        
        try {
            model.deleteBarang();
            JOptionPane.showMessageDialog(view, "Barang berhasil dihapus");
            model.resetBarang();
        } catch (Throwable throwable) {
            JOptionPane.showMessageDialog(view, new Object[]{"Terjadi eror di Database",throwable.getMessage()});
        }
        
        
        }
    }
    public void cariBarang(BarangView view) throws SQLException, BarangException{
        String namaBarang = view.getCari().getText();
        TabelCari tabelCariCari = new TabelCari();
        model.cariBarang(namaBarang);
        view.getTabelStok().setModel(tabelCariCari);
    }

//tabel hasil cari masih disimpan di tabel stok. Solusi bikin tabel lagi atau tambah button select all
}
