/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.service;

import java.util.List;
import tugasbesarkelompok2.entity.Barang;
import tugasbesarkelompok2.error.BarangException;

/**
 *
 * @author User
 */
public interface BarangDao {
    
    public void insertBarang(Barang barang) throws BarangException;
    public void updateBarang(Barang barang) throws BarangException;
    public void deleteBarang(Integer kode) throws BarangException;
    public Barang getBarang(Integer kode) throws BarangException;
    public List<Barang> selectAllBarang() throws BarangException;
    public Barang getCari(String cari) throws BarangException;
    public List<Barang> selectCariBarang(String nama) throws BarangException;
}
