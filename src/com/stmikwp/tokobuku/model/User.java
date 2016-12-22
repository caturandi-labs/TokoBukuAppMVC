/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.model;

/**
 *
 * @author Matryoshka
 */
public class User {
    
    private String idUser;
    private String username;
    private String password;
    private Karyawan idKaryawan;

    public Karyawan getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Karyawan idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
      


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
