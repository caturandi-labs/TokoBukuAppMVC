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
public class Penerbit {
    
    private String idPenerbit;
    private String namaPenerbit;
    private String alamatJalan;
    private String kota;
    private String kodePos;
    private String telepon;
    private List<Buku>listBuku = new ArrayList<>();
    
    public String getIdPenerbit() {
        return idPenerbit;
    }

    public void setIdPenerbit(String idPenerbit) {
        this.idPenerbit = idPenerbit;
    }

    
    public String getNamaPenerbit() {
        return namaPenerbit;
    }

    public void setNamaPenerbit(String namaPenerbit) {
        this.namaPenerbit = namaPenerbit;
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
