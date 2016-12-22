/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.daoimpl;

import com.stmikwp.tokobuku.dao.KaryawanDao;
import com.stmikwp.tokobuku.database.DatabaseUtil;
import com.stmikwp.tokobuku.model.Karyawan;
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
public class KaryawanDaoImpl implements KaryawanDao {

    private final Connection connection;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    private Karyawan karyawan = null;
    private List<Karyawan>listKaryawan = null;
    
    // Query Database
    private final String insert = "INSERT INTO karyawan VALUES(?,?,?,?,?,?)";
    private final String update = "UPDATE karyawan SET nama_karyawan=? , alamat_jalan=? , kota=? , kode_pos=? , telepon=? WHERE id_karyawan=?";
    private final String delete = "DELETE FROM karyawan WHERE id_karyawan=?";
    private final String getAll = "SELECT * FROM karyawan";
    private final String getByNama = "SELECT * FROM karyawan WHERE nama_karyawan LIKE ?";
    
    public KaryawanDaoImpl() {
        this.connection = DatabaseUtil.getConnection();
        listKaryawan = new ArrayList<>();
    }
    
    @Override
    public void insert(Karyawan karyawan) {
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1,karyawan.getIdKaryawan());
            statement.setString(2,karyawan.getNamaKaryawan());
            statement.setString(3,karyawan.getAlamatJalan());
            statement.setString(4,karyawan.getKota());
            statement.setString(5,karyawan.getKodePos());
            statement.setString(6,karyawan.getTelepon());
                        
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
    public void update(Karyawan karyawan) {
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1,karyawan.getNamaKaryawan());            
            statement.setString(2,karyawan.getAlamatJalan());
            statement.setString(3,karyawan.getKota());
            statement.setString(4,karyawan.getKodePos());
            statement.setString(5,karyawan.getTelepon());
            statement.setString(6,karyawan.getIdKaryawan());
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
    public void delete(String idKaryawan) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setString(1, idKaryawan);
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
    public List<Karyawan> getAll() {
        try {
            statement = connection.prepareStatement(getAll);
            result = statement.executeQuery();
            
            while(result.next()){
                karyawan = new Karyawan();
                karyawan.setIdKaryawan(result.getString("id_karyawan"));
                karyawan.setNamaKaryawan(result.getString("nama_karyawan"));
                karyawan.setAlamatJalan(result.getString("alamat_jalan"));
                karyawan.setKota(result.getString("kota"));
                karyawan.setKodePos(result.getString("kodepos"));
                karyawan.setTelepon(result.getString("telepon"));
                listKaryawan.add(karyawan);
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
        return listKaryawan;
    }

    @Override
    public List<Karyawan> getByNama(String namaKaryawan) {
        try {
            statement = connection.prepareStatement(getByNama);
            result = statement.executeQuery();
            
            while(result.next()){
                karyawan = new Karyawan();
                karyawan.setIdKaryawan(result.getString("id_karyawan"));
                karyawan.setNamaKaryawan(result.getString("nama_karyawan"));
                karyawan.setAlamatJalan(result.getString("alamat_jalan"));
                karyawan.setKota(result.getString("kota"));
                karyawan.setKodePos(result.getString("kodepos"));
                karyawan.setTelepon(result.getString("telepon"));
                listKaryawan.add(karyawan);
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
        return listKaryawan;
    }
    
}
