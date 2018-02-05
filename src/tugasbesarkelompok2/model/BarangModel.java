/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.model;

import java.sql.SQLException;
import tugasbesarkelompok2.entity.Barang;
import tugasbesarkelompok2.error.BarangException;
import tugasbesarkelompok2.event.BarangListener;
import tugasbesarkelompok2.koneksi.koneksi;
import tugasbesarkelompok2.service.BarangDao;

/**
 *
 * @author User
 */
public class BarangModel {
    
    private int kode;
    private String namaBarang;
    private String jenisBarang;
    private String tipeBarang;
    private int jumlah;
    private int harga;

    private BarangListener listener;

    public BarangListener getListener() {
        return listener;
    }

    public void setListener(BarangListener listener) {
        this.listener = listener;
    }
    
    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
        fireOnChange();
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
        fireOnChange();
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
        fireOnChange();
    }

    public String getTipeBarang() {
        return tipeBarang;
    }

    public void setTipeBarang(String tipeBarang) {
        this.tipeBarang = tipeBarang;
        fireOnChange();
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
        fireOnChange();
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
        fireOnChange();
    }
    
    ///////////////////////////////////////////////////////
    protected void fireOnChange(){
        if (listener!=null) {
            listener.onChange(this);
        }
    }
    protected void fireOnInsert(Barang barang){
        if (listener!=null) {
            listener.onInsert(barang);
        }
    }
    protected void fireOnUpdate(Barang barang){
        if (listener!=null) {
            listener.onUpdate(barang);
        }
    }
    protected void fireOnDelete(){
        if (listener!=null) {
            listener.onDelete();
        }
    }

    public void insertBarang() throws SQLException, BarangException{
     
        BarangDao dao = koneksi.getBarangDao();
        Barang barang = new Barang();
        barang.setKode(kode);
        barang.setNamaBarang(namaBarang);
        barang.setJenisBarang(jenisBarang);
        barang.setTipeBarang(tipeBarang);
        barang.setJumlah(jumlah);
        barang.setHarga(harga);
        
        dao.insertBarang(barang);
        fireOnInsert(barang);
    }
    
    public void updateBarang() throws SQLException, BarangException{
     
        BarangDao dao = koneksi.getBarangDao();
        Barang barang = new Barang();
        barang.setKode(kode);
        barang.setNamaBarang(namaBarang);
        barang.setJenisBarang(jenisBarang);
        barang.setTipeBarang(tipeBarang);
        barang.setJumlah(jumlah);
        barang.setHarga(harga);
        
        dao.updateBarang(barang);
        fireOnInsert(barang);
    }
    
    public void deleteBarang() throws SQLException, BarangException{
     
        BarangDao dao = koneksi.getBarangDao();
        dao.deleteBarang(kode);
        fireOnDelete();
    }

    public void cariBarang(String nama) throws SQLException,BarangException{
        BarangDao dao = koneksi.getBarangDao();
        Barang barang = new Barang();
        dao.selectCariBarang(nama);
        fireOnInsert(barang);
    }
            
    public void resetBarang(){
        setKode(0);
        setNamaBarang("");
        setJenisBarang("");
        setTipeBarang("");
        setJumlah(0);
        setHarga(0);
        
    }
    
}
