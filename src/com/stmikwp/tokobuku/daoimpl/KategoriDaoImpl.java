/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.daoimpl;

import com.stmikwp.tokobuku.dao.KategoriDao;
import com.stmikwp.tokobuku.database.DatabaseUtil;
import com.stmikwp.tokobuku.model.Kategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andi
 */
public class KategoriDaoImpl implements KategoriDao{
    
    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    private Kategori kategori = null;
    private List<Kategori>listKategori = null;
    
    // Query Database
    private final String insert = "INSERT INTO kategori VALUES(?,?)";
    private final String update = "UPDATE kategori SET nama_kategori=? WHERE id_kategori=?";
    private final String delete = "DELETE FROM kategori WHERE id_kategori=?";
    private final String getAll = "SELECT * FROM kategori";
    private final String getByNama = "SELECT * FROM kategori WHERE nama_kategori LIKE ?";

    public KategoriDaoImpl() {
        this.connection = DatabaseUtil.getConnection();
        listKategori = new ArrayList<>();
    }
    
    @Override
    public void insert(Kategori kategori) {
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, kategori.getIdKategori());
            statement.setString(2, kategori.getNamaKategori());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Kategori kategori) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(update);
            statement.setString(1, kategori.getNamaKategori());
            statement.setString(2,kategori.getIdKategori());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String idKategori) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setString(1, idKategori);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            
            try{
                if(statement!=null){
                    statement.close();
                }
                connection.setAutoCommit(true);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Kategori> getAll() {
        try {
            statement = connection.prepareStatement(getAll);
            result = statement.executeQuery();
            
            while(result.next()){
                kategori = new Kategori();
                kategori.setIdKategori(result.getString("id_kategori"));
                kategori.setNamaKategori(result.getString("nama_kategori"));
                listKategori.add(kategori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
                if(result!=null){
                    result.close();
                }
            } catch (SQLException e) {
            }
        }
        return listKategori;
    }

    @Override
    public List<Kategori> getByNama(String namaKategori) {
        try {
            statement = connection.prepareStatement(getByNama);
            statement.setString(1, namaKategori);
            result = statement.executeQuery();
            
            if(result.next()){
                kategori = new Kategori();
                kategori.setIdKategori(result.getString("id_kategori"));
                kategori.setNamaKategori(result.getString("nama_kategori"));
                listKategori.add(kategori);
            }else{
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
                if(result!=null){
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listKategori;
    }
    
}
