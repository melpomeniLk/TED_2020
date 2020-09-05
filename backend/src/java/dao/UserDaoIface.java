/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.User;
import viewmodel.RegisterViewModel;

/**
 *
 * @author alicemts
 */
public interface UserDaoIface {
    
    public List<User> usersList() throws SQLException;
    
    public User find(long id) throws SQLException;
    
    public List<User> search(String column, String value) throws SQLException;
    
    public boolean createUser (RegisterViewModel registerViewModel) throws SQLException;
    
    public boolean changeData (String column, String value, long userId) throws SQLException;
    
    public boolean deleteUser(long userId) throws SQLException;
    
    public boolean signIn(String username, String pswd) throws SQLException;
    
    public boolean dataExists(String column, String value) throws SQLException;
    
}
