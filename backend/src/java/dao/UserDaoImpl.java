/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.User;
import viewmodel.RegisterViewModel;

/**
 *
 * @author alicemts
 */
public class UserDaoImpl implements UserDaoIface {

    private static final String SQL_SELECT = "SELECT * from User order by name asc";
    private static final String SQL_SELECT_BY_ID = "SELECT * from User where id = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * from User where name = ?";
    private static final String SQL_SELECT_BY_USERNAME = "SELECT * from User where username = ?";
    private static final String SQL_SELECT_BY_LAST_NAME = "SELECT * from User where lastName = ?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * from User where email = ?";
    private static final String SQL_SELECT_BY_PHONE = "SELECT * from User where phone = ?";
    private static final String SQL_INSERT = "INSERT INTO User (username,password,name,lastName,phone,email,photo) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_NAME = "UPDATE User SET name = ? WHERE id = ?";
    private static final String SQL_UPDATE_LAST_NAME = "UPDATE User SET lastName = ? WHERE id = ?";
    private static final String SQL_UPDATE_PSWD = "UPDATE User SET password = ? WHERE id = ?";
    private static final String SQL_UPDATE_EMAIL = "UPDATE User SET email = ? WHERE id = ?";
    private static final String SQL_UPDATE_PHONE = "UPDATE User SET phone = ? WHERE id = ?";
    private static final String SQL_UPDATE_PHOTO = "UPDATE User SET photo = ? WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM User WHERE id = ?";
    private static final String SQL_SIGN_IN = "SELECT id FROM User WHERE username = ? AND password = ?";
    

    public User find(long id) throws SQLException {
        User user = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            user = new User();
            user.setId(set.getLong("id"));
            user.setUsername(set.getString("username"));
            user.setPassword(set.getString("password"));
            user.setName(set.getString("name"));
            user.setLastName(set.getString("lastName"));
            user.setEmail(set.getString("email"));
            user.setPhone(set.getString("phone"));
        }
        set.close();
        statement.close();
        conn.close();
        return user;
    }

    @Override
    public List<User> usersList() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            User user = new User();
            user = find(set.getLong("id"));
            list.add(user);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public List<User> search(String column, String value) throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        
        switch(column) 
        { 
            case "name": 
                statement = conn.prepareStatement(SQL_SELECT_BY_NAME);
                statement.setString(1, value);
                break; 
            case "username": 
                statement = conn.prepareStatement(SQL_SELECT_BY_USERNAME);
                statement.setString(1, value);
                break; 
            case "lastName": 
                statement = conn.prepareStatement(SQL_SELECT_BY_LAST_NAME);
                statement.setString(1, value);
                break;
            case "email":
                statement = conn.prepareStatement(SQL_SELECT_BY_EMAIL);
                statement.setString(1, value);
                break;
            case "phone":
                statement = conn.prepareStatement(SQL_SELECT_BY_PHONE);
                statement.setString(1, value);
                break;
            default: 
                System.out.println("no match"); 
        } 

        ResultSet set = statement.executeQuery();
        
        while (set.next()) {
            User user = new User();
            user = find(set.getLong("id"));
            list.add(user);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public boolean createUser(RegisterViewModel registerViewModel) throws SQLException {
        boolean created = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
        rs = statement.getGeneratedKeys();
        
        statement.setString(1, registerViewModel.getUsername());
        statement.setString(2, registerViewModel.getPassword());
        statement.setString(3, registerViewModel.getName());
        statement.setString(4, registerViewModel.getLastName());
        statement.setString(5, registerViewModel.getEmail());
        statement.setString(6, registerViewModel.getPhone());
        
        if (statement.executeUpdate() == 1)
            created = true;

        statement.close();
        conn.close();
        return created;
    }

    @Override
    public boolean changeData(String column, String value, long id) throws SQLException {
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        
        switch(column) 
        { 
            case "name": 
                statement = conn.prepareStatement(SQL_UPDATE_NAME);
                statement.setString(1, value);
                break; 
            case "lastName": 
                statement = conn.prepareStatement(SQL_UPDATE_LAST_NAME);
                statement.setString(1, value);
                break;
            case "password": 
                statement = conn.prepareStatement(SQL_UPDATE_PSWD);
                statement.setString(1, value);
                break;
            case "email":
                statement = conn.prepareStatement(SQL_UPDATE_EMAIL);
                statement.setString(1, value);
                break;
            case "phone":
                statement = conn.prepareStatement(SQL_UPDATE_PHONE);
                statement.setString(1, value);
                break;
            case "photo":
                statement = conn.prepareStatement(SQL_UPDATE_PHOTO);
                statement.setString(1, value);
                break;
            default: 
                System.out.println("no match"); 
        } 
        statement.setLong(2, id);
        
        if (statement.executeUpdate() == 1)
            updated = true;
        
        statement.close();
        conn.close();
        return updated;
    }

    @Override
    public boolean deleteUser(long id) throws SQLException {
        boolean delete = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
       
        if(set.next()){
            statement = conn.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1) {
                delete = true;
            }
        }
        
        statement.close();
        conn.close();
        return delete;
    }

    @Override
    public boolean signIn(String username, String pswd) throws SQLException {
        boolean signIn = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SIGN_IN);
        statement.setString(1, username);
        statement.setString(2, pswd);
        
        ResultSet set = statement.executeQuery();
        
        if (set.next())
            signIn = true;
        
        statement.close();
        conn.close();
        return signIn;
    }

    @Override
    public boolean dataExists(String column, String value) throws SQLException {
        boolean exists = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        
        switch(column) 
        { 
            case "username": 
                statement = conn.prepareStatement(SQL_SELECT_BY_USERNAME);
                break;
            case "email":
                statement = conn.prepareStatement(SQL_SELECT_BY_EMAIL);
                break;
            case "phone_number":
                statement = conn.prepareStatement(SQL_UPDATE_PHONE);
                break;
            default: 
                System.out.println("no match"); 
        }
        statement.setString(1, value);
        ResultSet set = statement.executeQuery();
        
        if (set.next())
            exists = true;

        statement.close();
        conn.close();
        return exists;
    }

}
