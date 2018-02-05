/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tugasbesarkelompok2.entity.Barang;
import tugasbesarkelompok2.error.BarangException;
import tugasbesarkelompok2.service.BarangDao;



/**
 *
 * @author User
 */
public class BarangDaoImpl implements BarangDao{

    private Connection connection;

    private final String insertBarang = "INSERT INTO BARANG (kode, namaBarang, jenisBarang, tipeBarang, jumlah, harga) VALUES (?,?,?,?,?,?)";
    
    private final String updateBarang = "UPDATE BARANG SET kode=?,namaBarang=?,jenisBarang=?,tipeBarang=?,jumlah=?,harga=?";
            
    private final String deleteBarang = "DELETE FROM BARANG WHERE kode=? ";
    
    private final String getByKode = "SELECT * FROM BARANG WHERE KODE=?";
    
    private final String selectAll = "SELECT * FROM BARANG";
    
    private final String cariBarang = "SELECT * FROM BARANG WHERE namaBarang=?";

    private final String pencarian = "SELECT * FROM BARANG WHERE namaBarang LIKE '?%'";
    
    public BarangDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    
    
    @Override
    public void insertBarang(Barang barang) throws BarangException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(insertBarang);
            statement.setInt   (1, barang.getKode());
            statement.setString(2, barang.getNamaBarang());
            statement.setString(3, barang.getJenisBarang());
            statement.setString(4, barang.getTipeBarang());
            statement.setInt(5, barang.getJumlah());
            statement.setInt(6, barang.getHarga());
            statement.executeUpdate();
            
            connection.commit();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            
            throw new BarangException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
        
        
    }

    @Override
    public void updateBarang(Barang barang) throws BarangException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(updateBarang, Statement.RETURN_GENERATED_KEYS);
            statement.setInt   (1, barang.getKode());
            statement.setString(2, barang.getNamaBarang());
            statement.setString(3, barang.getJenisBarang());
            statement.setString(4, barang.getTipeBarang());
            statement.setInt(5, barang.getJumlah());
            statement.setInt(6, barang.getHarga());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            
            throw new BarangException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }    
    }

    @Override
    public void deleteBarang(Integer kode) throws BarangException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(deleteBarang);
            statement.setInt(1, kode);    
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            
            throw new BarangException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public Barang getBarang(Integer kode) throws BarangException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(getByKode, Statement.RETURN_GENERATED_KEYS);          
            statement.setInt(1, kode);
            
            ResultSet result= statement.executeQuery();
            Barang barang=null;
            
            if(result.next()){
                barang = new Barang();
                barang.setKode(result.getInt("kode"));
                barang.setNamaBarang(result.getString("namaBarang"));
                barang.setJenisBarang(result.getString("jenisBarang"));
                barang.setTipeBarang(result.getString("tipeBarang"));
                barang.setJumlah(result.getInt("jumlah"));
                barang.setHarga(result.getInt("harga"));
                
            } else{
                throw new BarangException("Barang dengan kode"+kode+"tidak ditemukan");
            }
            connection.commit();            
            return barang;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            
            throw new BarangException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }    
    }

    @Override
    public List<Barang> selectAllBarang() throws BarangException {
        Statement statement = null;
        List<Barang> list = new ArrayList<Barang>();
        
        try {
            statement = connection.createStatement();
            
            ResultSet result= statement.executeQuery(selectAll);
            Barang barang=null;
            
            while(result.next()){
                barang = new Barang();
                barang.setKode(result.getInt("KODE"));
                barang.setNamaBarang(result.getString("namaBarang"));
                barang.setJenisBarang(result.getString("jenisBarang"));
                barang.setTipeBarang(result.getString("tipeBarang"));
                barang.setJumlah(result.getInt("jumlah"));
                barang.setHarga(result.getInt("harga"));
                list.add(barang);
            } 
            connection.commit();            
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            
            throw new BarangException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public Barang getCari(String cari) throws BarangException {
PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(cariBarang);
            statement.setString(1, cari);
            
            ResultSet result = statement.executeQuery();
            Barang barang = null;
            if (result.next()) {
                barang = new Barang();
                barang.setKode(result.getInt("kode"));
                barang.setNamaBarang(result.getString("namaBarang"));
                barang.setJenisBarang(result.getString("jenisBarang"));
                barang.setTipeBarang(result.getString("tipeBarang"));
                barang.setJumlah(result.getInt("jumlah"));
                barang.setHarga(result.getInt("harga"));
            } else {
                throw new BarangException("Barang dengan kode "+cari+" tidak ditemukan");
            }
            connection.commit();
            return barang;
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw  new BarangException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!=null) {
                try {
                statement.close();
            } catch (SQLException e) {
            }
            }
        }    }

    @Override
    public List<Barang> selectCariBarang(String nama) throws BarangException {
        Statement statement = null;
    List<Barang> list = new ArrayList<Barang>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            
            ResultSet result = statement.executeQuery("SELECT * FROM BARANG WHERE namaBarang LIKE '%"+nama+"%'");
            Barang barang = null;
            while (result.next()) {
                barang = new Barang();
                barang.setKode(result.getInt("kode"));
                barang.setNamaBarang(result.getString("namaBarang"));
                barang.setJenisBarang(result.getString("jenisBarang"));
                barang.setTipeBarang(result.getString("tipeBarang"));
                barang.setJumlah(result.getInt("jumlah"));
                barang.setHarga(result.getInt("harga"));
                list.add(barang);
            }
            connection.commit();
            return list;
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw  new BarangException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!=null) {
                try {
                statement.close();
            } catch (SQLException e) {
            }
            }
        }
    
    }
    
}
