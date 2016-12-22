/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.dao;

import com.stmikwp.tokobuku.model.User;
import java.util.List;
/**
 *
 * @author Matryoshka
 */
public interface UserDao {
    
    public void insert(User user);
    public void update(User user);
    public void delete(String idUser);
    public List<User> getAll();
    
}
