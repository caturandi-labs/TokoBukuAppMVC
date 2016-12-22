/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matryoshka
 */
public class Kategori {
    
    private String idKategori;
    private String namaKategori;
    private List<Buku>listBuku = new ArrayList<>();

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }


    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
 
}
