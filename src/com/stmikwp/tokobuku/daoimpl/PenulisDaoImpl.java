/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.daoimpl;

import com.stmikwp.tokobuku.dao.PenulisDao;
import com.stmikwp.tokobuku.database.DatabaseUtil;
import com.stmikwp.tokobuku.model.Penulis;
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
public class PenulisDaoImpl implements PenulisDao {
    
    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    private Penulis penulis = null;
    private List<Penulis>listPenulis = null;
    
    // Query Database
    private final String insert = "INSERT INTO penulis VALUES(?,?,?,?,?,?)";
    private final String update = "UPDATE kategori SET nama_penulis=? , alamat_jalan=?,kota=?,kodepos=?,telepon=? WHERE id_penulis=?";
    private final String delete = "DELETE FROM penulis WHERE id_penulis=?";
    private final String getAll = "SELECT * FROM penulis";
    private final String getByNama = "SELECT * FROM penulis WHERE nama_penulis LIKE ?";

    public PenulisDaoImpl() {
        this.connection = DatabaseUtil.getConnection();
        listPenulis = new ArrayList<>();
    }

    @Override
    public void insert(Penulis penulis) {
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1,penulis.getIdPenulis());
            statement.setString(2,penulis.getNamaPenulis());
            statement.setString(3,penulis.getAlamatJalan());
            statement.setString(4,penulis.getKota());
            statement.setString(5,penulis.getKodePos());
            statement.setString(6,penulis.getTelepon());
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
    public void update(Penulis penulis) {
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1,penulis.getNamaPenulis());
            statement.setString(2,penulis.getAlamatJalan());
            statement.setString(3,penulis.getKota());
            statement.setString(4,penulis.getKodePos());
            statement.setString(5,penulis.getTelepon());
            statement.setString(6,penulis.getIdPenulis());
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
    public void delete(String idPenulis) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setString(1, idPenulis);
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
    public List<Penulis> getAll() {
        try {
            statement = connection.prepareStatement(getAll);
            result = statement.executeQuery();
            
            while(result.next()){
                penulis = new Penulis();
                penulis.setIdPenulis(result.getString("id_penulis"));
                penulis.setNamaPenulis(result.getString("nama_penulis"));
                penulis.setAlamatJalan(result.getString("alamat_jalan"));
                penulis.setKota(result.getString("kota"));
                penulis.setKodePos(result.getString("kodepos"));
                penulis.setTelepon(result.getString("telepon"));
                listPenulis.add(penulis);
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
        return listPenulis;
    }

    @Override
    public List<Penulis> getByNama(String namaPenulis) {
        try {
            statement = connection.prepareStatement(getByNama);
            result = statement.executeQuery();
            
            while(result.next()){
                penulis = new Penulis();
                penulis.setIdPenulis(result.getString("id_penulis"));
                penulis.setNamaPenulis(result.getString("nama_penulis"));
                penulis.setAlamatJalan(result.getString("alamat_jalan"));
                penulis.setKota(result.getString("kota"));
                penulis.setKodePos(result.getString("kodepos"));
                penulis.setTelepon(result.getString("telepon"));
                listPenulis.add(penulis);
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
        return listPenulis;
    }
    
    
    
    
    
}
