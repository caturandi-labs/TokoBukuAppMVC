/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.dao;

import com.stmikwp.tokobuku.model.Pelanggan;
import java.util.List;

/**
 *
 * @author Matryoshka
 */
public interface PelangganDao {
    
    public void insert(Pelanggan pelanggan);
    public void update(Pelanggan pelanggan);
    public void delete(String idPelanggan);
    public List<Pelanggan> getAll();
    public List<Pelanggan> getByNama(String namaPelanggan);
    
}
