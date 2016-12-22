/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.dao;

import com.stmikwp.tokobuku.model.Buku;
import com.stmikwp.tokobuku.model.Kategori;
import com.stmikwp.tokobuku.model.Penerbit;
import com.stmikwp.tokobuku.model.Penulis;
import com.stmikwp.tokobuku.model.Supplier;
import java.util.List;

/**
 *
 * @author andi
 */
public interface BukuDao {
    
    public void insert(Buku buku);
    public void update(Buku buku);
    public void delete(String isbn);
    public List<Buku> getAll();
    public List<Buku> getByNama(String judulBuku);
    public List<Kategori>getAllKategori();
    public List<Penulis>getAllPenulis();
    public List<Penerbit>getAllPenerbit();
    public List<Supplier>getAllSupplier();

}
