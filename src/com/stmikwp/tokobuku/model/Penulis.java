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
public class Penulis {
    
    private String idPenulis;
    private String namaPenulis;
    private String alamatJalan;
    private String kota;
    private String kodePos;
    private String telepon;
    private List<Buku>listBuku = new ArrayList<>();
    
    public String getIdPenulis() {
        return idPenulis;
    }

    public void setIdPenulis(String idPenulis) {
        this.idPenulis = idPenulis;
    }

    public String getNamaPenulis() {
        return namaPenulis;
    }
    
    public void setNamaPenulis(String namaPenulis) {
        this.namaPenulis = namaPenulis;
    }

    public String getAlamatJalan() {
        return alamatJalan;
    }

    public void setAlamatJalan(String alamatJalan) {
        this.alamatJalan = alamatJalan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public List<Buku> getListBuku() {
        return listBuku;
    }

    public void setListBuku(List<Buku> listBuku) {
        this.listBuku = listBuku;
    }
   
}
