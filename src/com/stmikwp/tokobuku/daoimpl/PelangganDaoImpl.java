/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.daoimpl;

import com.stmikwp.tokobuku.dao.PelangganDao;
import com.stmikwp.tokobuku.database.DatabaseUtil;
import com.stmikwp.tokobuku.model.Pelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matryoshka
 */
public class PelangganDaoImpl implements PelangganDao {
    
    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    private Pelanggan pelanggan = null;
    private List<Pelanggan>listPelanggan = null;
    
    // Query Database
    private final String insert = "INSERT INTO pelanggan VALUES(?,?,?,?,?,?)";
    private final String update = "UPDATE pelanggan SET nama_pelanggan=? , alamat_jalan=? , kota=? , kode_pos=? , telepon=? WHERE id_pelanggan=?";
    private final String delete = "DELETE FROM pelanggan WHERE id_pelanggan=?";
    private final String getAll = "SELECT * FROM pelanggan";
    private final String getByNama = "SELECT * FROM pelanggan WHERE nama_pelanggan LIKE ?";
    
    public PelangganDaoImpl() {
        this.connection = DatabaseUtil.getConnection();
        listPelanggan = new ArrayList<>();
    }

    @Override
    public void insert(Pelanggan pelanggan) {
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1,pelanggan.getIdPelanggan());
            statement.setString(2,pelanggan.getNama());
            statement.setString(3,pelanggan.getAlamatJalan());
            statement.setString(4,pelanggan.getKota());
            statement.setString(5,pelanggan.getKodePos());
            statement.setString(6,pelanggan.getTelepon());
                        
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
    public void update(Pelanggan pelanggan) {
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1,pelanggan.getNama());            
            statement.setString(2,pelanggan.getAlamatJalan());
            statement.setString(3,pelanggan.getKota());
            statement.setString(4,pelanggan.getKodePos());
            statement.setString(5,pelanggan.getTelepon());
            statement.setString(6,pelanggan.getIdPelanggan());
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
    public void delete(String idPelanggan) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setString(1, idPelanggan);
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
    public List<Pelanggan> getAll() {
        try {
            statement = connection.prepareStatement(getAll);
            result = statement.executeQuery();
            
            while(result.next()){
                pelanggan = new Pelanggan();
                pelanggan.setIdPelanggan(result.getString("id_pelanggan"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamatJalan(result.getString("alamat_jalan"));
                pelanggan.setKota(result.getString("kota"));
                pelanggan.setKodePos(result.getString("kodepos"));
                pelanggan.setTelepon(result.getString("telepon"));
                listPelanggan.add(pelanggan);
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
        return listPelanggan;
    }

    @Override
    public List<Pelanggan> getByNama(String nama) {
        try {
            statement = connection.prepareStatement(getByNama);
            result = statement.executeQuery();
            
            while(result.next()){
                pelanggan = new Pelanggan();
                pelanggan.setIdPelanggan(result.getString("id_pelanggan"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamatJalan(result.getString("alamat_jalan"));
                pelanggan.setKota(result.getString("kota"));
                pelanggan.setKodePos(result.getString("kodepos"));
                pelanggan.setTelepon(result.getString("telepon"));
                listPelanggan.add(pelanggan);
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
        return listPelanggan;
    }
}
