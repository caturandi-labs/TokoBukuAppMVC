/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.daoimpl;

import com.stmikwp.tokobuku.dao.UserDao;
import com.stmikwp.tokobuku.database.DatabaseUtil;
import com.stmikwp.tokobuku.model.User;
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
public class UserDaoImpl implements UserDao {
    
    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    private User user = null;
    private List<User>listUser = null;
    
    // Query Database
    private final String insert = "INSERT INTO user VALUES(?,?,?,?)";
    private final String update = "UPDATE user SET username=? , password=? WHERE id_user=?";
    private final String delete = "DELETE FROM user WHERE id_user=?";
    private final String getAll = "SELECT * FROM user";
    
    public UserDaoImpl() {
        this.connection = DatabaseUtil.getConnection();
        listUser = new ArrayList<>();
    }
    
    @Override
    public void insert(User user) {
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1,user.getIdUser());
            statement.setString(2,user.getUsername());
            statement.setString(3,user.getPassword());
                        
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
    public void update(User user) {
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getIdUser());          
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
    public void delete(String idUser) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setString(1, idUser);
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
    public List<User> getAll() {
        try {
            statement = connection.prepareStatement(getAll);
            result = statement.executeQuery();
            
            while(result.next()){
                user = new User();
                user.setIdUser(result.getString("id_user"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                listUser.add(user);
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
        return listUser;
    }
    
    
}
