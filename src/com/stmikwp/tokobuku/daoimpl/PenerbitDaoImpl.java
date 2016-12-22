/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.daoimpl;

import com.stmikwp.tokobuku.dao.PenerbitDao;
import com.stmikwp.tokobuku.database.DatabaseUtil;
import com.stmikwp.tokobuku.model.Penerbit;
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
public class PenerbitDaoImpl implements PenerbitDao{
    
    private Connection connection;
    private Penerbit penerbit;
    private List<Penerbit>listPenerbit;
    private PreparedStatement statement;
    private ResultSet result;
    
    // Query Database
    private final String insert = "INSERT INTO penerbit VALUES(?,?,?,?,?,?)";
    private final String update = "UPDATE kategori SET nama_penerbit=? , alamat_jalan=?,kota=?,kodepos=?,telepon=? WHERE id_penulis=?";
    private final String delete = "DELETE FROM penerbit WHERE id_penerbit=?";
    private final String getAll = "SELECT * FROM penerbit";
    private final String getByNama = "SELECT * FROM penulis WHERE nama_penulis LIKE ?";

    public PenerbitDaoImpl() {
        this.connection = DatabaseUtil.getConnection();
        listPenerbit = new ArrayList<>();
    }
    
    
    
    @Override
    public void insert(Penerbit penerbit) {
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1,penerbit.getIdPenerbit());
            statement.setString(2,penerbit.getNamaPenerbit());
            statement.setString(3,penerbit.getAlamatJalan());
            statement.setString(4,penerbit.getKota());
            statement.setString(5,penerbit.getKodePos());
            statement.setString(6,penerbit.getTelepon());
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
    public void update(Penerbit penerbit) {
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1,penerbit.getNamaPenerbit());
            statement.setString(2,penerbit.getAlamatJalan());
            statement.setString(3,penerbit.getKota());
            statement.setString(4,penerbit.getKodePos());
            statement.setString(5,penerbit.getTelepon());
            statement.setString(6,penerbit.getIdPenerbit());
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
    public void delete(String idPenerbit) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setString(1, idPenerbit);
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
    public List<Penerbit> getAll() {
        try {
            statement = connection.prepareStatement(getAll);
            result = statement.executeQuery();
            
            while(result.next()){
                penerbit = new Penerbit();
                penerbit.setIdPenerbit(result.getString("id_penerbit"));
                penerbit.setNamaPenerbit(result.getString("nama_penerbit"));
                penerbit.setAlamatJalan(result.getString("alamat_jalan"));
                penerbit.setKota(result.getString("kota"));
                penerbit.setKodePos(result.getString("kodepos"));
                penerbit.setTelepon(result.getString("telepon"));
                listPenerbit.add(penerbit);
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
        return listPenerbit;
    }

    @Override
    public List<Penerbit> getByNama(String namaPenerbit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
