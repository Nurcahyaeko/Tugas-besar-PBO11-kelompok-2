/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.main;

import tugasbesarkelompok2.koneksi.koneksi;
import tugasbesarkelompok2.view.BarangView;
import java.sql.SQLException;
import tugasbesarkelompok2.service.BarangDao;
import tugasbesarkelompok2.entity.Barang;
import tugasbesarkelompok2.error.BarangException;

/**
 *
 * @author User
 */
public class TugasBesarKelompok2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, BarangException{
        // TODO code application logic here
        
        BarangDao dao = koneksi.getBarangDao();
        dao.deleteBarang(1);
    }
    
}
