/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.daoimpl;

import com.stmikwp.tokobuku.dao.SupplierDao;
import com.stmikwp.tokobuku.database.DatabaseUtil;
import com.stmikwp.tokobuku.model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matryoshka
 */
public class SupplierDaoImpl implements SupplierDao {
    
    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    private Supplier supplier = null;
    private List<Supplier>listSupplier = null;
    
    // Query Database
    private final String insert = "INSERT INTO supplier VALUES(?,?,?,?,?,?)";
    private final String update = "UPDATE supplier SET nama_supplier=? , alamat_jalan=? , kota=? , kodepos=? , telepon=? WHERE id_supplier=?";
    private final String delete = "DELETE FROM supplier WHERE id_supplier=?";
    private final String getAll = "SELECT * FROM supplier";
    private final String getByNama = "SELECT * FROM supplier WHERE nama_supplier LIKE ?";
    
    public SupplierDaoImpl() {
        this.connection = DatabaseUtil.getConnection();
        listSupplier = new ArrayList<>();
    }

    @Override
    public void insert(Supplier supplier) {
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1,supplier.getIdSupplier());
            statement.setString(2,supplier.getNamaSupplier());
            statement.setString(3,supplier.getAlamatJalan());
            statement.setString(4,supplier.getKota());
            statement.setString(5,supplier.getKodePos());
            statement.setString(6,supplier.getTelepon());
                        
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
    public void update(Supplier supplier) {
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1,supplier.getNamaSupplier());
            statement.setString(2,supplier.getAlamatJalan());
            statement.setString(3,supplier.getKota());
            statement.setString(4,supplier.getKodePos());
            statement.setString(5,supplier.getTelepon());
            statement.setString(6,supplier.getIdSupplier());
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
    public void delete(String idSupplier) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setString(1, idSupplier);
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
    public List<Supplier> getAll() {
        try {
            statement = connection.prepareStatement(getAll);
            result = statement.executeQuery();
            
            while(result.next()){
                supplier = new Supplier();
                supplier.setIdSupplier(result.getString("id_supplier"));
                supplier.setNamaSupplier(result.getString("nama_supplier"));
                supplier.setAlamatJalan(result.getString("alamat_jalan"));
                supplier.setKota(result.getString("kota"));
                supplier.setKodePos(result.getString("kodepos"));
                supplier.setTelepon(result.getString("telepon"));
                listSupplier.add(supplier);
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
        return listSupplier;
    }

    @Override
    public List<Supplier> getByNama(String nama) {
        try {
            statement = connection.prepareStatement(getByNama);
            result = statement.executeQuery();
            
            while(result.next()){
                supplier = new Supplier();
                supplier.setIdSupplier(result.getString("id_supplier"));
                supplier.setNamaSupplier(result.getString("nama_supplier"));
                supplier.setAlamatJalan(result.getString("alamat_jalan"));
                supplier.setKota(result.getString("kota"));
                supplier.setKodePos(result.getString("kodepos"));
                supplier.setTelepon(result.getString("telepon"));
                listSupplier.add(supplier);
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
        return listSupplier;
    }

   
}
