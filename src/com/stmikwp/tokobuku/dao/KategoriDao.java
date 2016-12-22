/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.dao;

import com.stmikwp.tokobuku.model.Kategori;
import java.util.List;

/**
 *
 * @author andi
 */
public interface KategoriDao {
    
    public void insert(Kategori kategori);
    public void update(Kategori kategori);
    public void delete(String idKategori);
    public List<Kategori> getAll();
    public List<Kategori> getByNama(String namaKategori);
    
}
