/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.dao;

import com.stmikwp.tokobuku.model.Karyawan;
import java.util.List;

/**
 *
 * @author andi
 */
public interface KaryawanDao {
    public void insert(Karyawan karyawan);
    public void update(Karyawan karyawan);
    public void delete(String idKaryawan);
    public List<Karyawan> getAll();
    public List<Karyawan> getByNama(String namaKaryawan);
}
