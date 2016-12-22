/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.dao;

import com.stmikwp.tokobuku.model.Supplier;
import java.util.List;

/**
 *
 * @author Matryoshka
 */
public interface SupplierDao {
    
    public void insert(Supplier supplier);
    public void update(Supplier supplier);
    public void delete(String idSupplier);
    public List<Supplier> getAll();
    public List<Supplier> getByNama(String namaSupplier);
    
}
