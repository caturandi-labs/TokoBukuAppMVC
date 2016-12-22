/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.dao;

import com.stmikwp.tokobuku.model.Penulis;
import java.util.List;

/**
 *
 * @author andi
 */
public interface PenulisDao {
    
    public void insert(Penulis penulis);
    public void update(Penulis penulis);
    public void delete(String idPenulis);
    public List<Penulis> getAll();
    public List<Penulis> getByNama(String namaPenulis);
    
}
