/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author andi
 */
public class DatabaseUtil {
    
    private static Connection connection;

    public static Connection getConnection() {
        if(connection == null){
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setDatabaseName("db_tokobuku");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        }
        return connection;
    }
    
    
}
