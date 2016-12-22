/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.dao;

import com.stmikwp.tokobuku.model.Penerbit;
import java.util.List;

/**
 *
 * @author andi
 */
public interface PenerbitDao {
    public void insert(Penerbit penerbit);
    public void update(Penerbit penerbit);
    public void delete(String idPenerbit);
    public List<Penerbit> getAll();
    public List<Penerbit> getByNama(String namaPenerbit);
}
