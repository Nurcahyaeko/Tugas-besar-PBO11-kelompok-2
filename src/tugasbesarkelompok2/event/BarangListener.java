/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.event;

import tugasbesarkelompok2.entity.Barang;
import tugasbesarkelompok2.model.BarangModel;

/**
 *
 * @author User
 */
public interface BarangListener {
    
    public void onChange(BarangModel model);
    public void onInsert(Barang barang);
    public void onDelete();
    public void onUpdate(Barang barang);
    
}
