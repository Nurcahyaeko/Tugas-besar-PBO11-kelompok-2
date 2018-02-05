/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.entity;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Barang {
    private Integer kode;
    private String namaBarang;
    private String jenisBarang;
    private String tipeBarang;
    private Integer jumlah;
    private Integer harga;

    public Barang(){
        
    }

    public Barang(Integer kode, String namaBarang, String jenisBarang, String tipeBarang, Integer jumlah, Integer harga) {
        this.kode = kode;
        this.namaBarang = namaBarang;
        this.jenisBarang = jenisBarang;
        this.tipeBarang = tipeBarang;
        this.jumlah = jumlah;
        this.harga = harga;
    }
    
    
    
    public Integer getKode() {
        return kode;
    }

    public void setKode(Integer kode) {
        this.kode = kode;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public String getTipeBarang() {
        return tipeBarang;
    }

    public void setTipeBarang(String tipeBarang) {
        this.tipeBarang = tipeBarang;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.kode);
        hash = 41 * hash + Objects.hashCode(this.namaBarang);
        hash = 41 * hash + Objects.hashCode(this.jenisBarang);
        hash = 41 * hash + Objects.hashCode(this.tipeBarang);
        hash = 41 * hash + Objects.hashCode(this.jumlah);
        hash = 41 * hash + Objects.hashCode(this.harga);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Barang other = (Barang) obj;
        if (!Objects.equals(this.namaBarang, other.namaBarang)) {
            return false;
        }
        if (!Objects.equals(this.jenisBarang, other.jenisBarang)) {
            return false;
        }
        if (!Objects.equals(this.tipeBarang, other.tipeBarang)) {
            return false;
        }
        if (!Objects.equals(this.kode, other.kode)) {
            return false;
        }
        if (!Objects.equals(this.jumlah, other.jumlah)) {
            return false;
        }
        if (!Objects.equals(this.harga, other.harga)) {
            return false;
        }
        return true;
    }
    

    
}
