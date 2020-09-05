/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static java.lang.Long.getLong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import models.Activated_Users;
/**
 *
 * @author anastasiaeleftheriadi
 */
public class ActivatedUsersDaoImpl implements ActivatedUsersDaoIface {
    private static final String SQL_SELECT = "SELECT * from Activated_Users";
    private static final String SQL_SELECT_BY_ID = "SELECT * from Activated_Users where User_userId = ?";
    private static final String SQL_INSERT = "INSERT INTO Activated_Users (User_userId) VALUES (?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Activated_Users WHERE User_userId = ?";
    
    
    @Override
    public List<Activated_Users> list() throws SQLException {
        ArrayList<Activated_Users> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            Activated_Users Activated_Users = new Activated_Users();
            Activated_Users.setUser_userId(set.getInt("User_userId"));
            list.add(Activated_Users);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public Activated_Users find(long id) throws SQLException {
        Activated_Users activated_user = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            activated_user = new Activated_Users();
            activated_user.setUser_userId(set.getLong("User_userId"));
        }
        set.close();
        statement.close();
        conn.close();
        return activated_user;
    }

    @Override
    public List<Activated_Users> search(String column, String value) throws SQLException {
        Activated_Users Activated_User = null;
        ArrayList<Activated_Users> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        
        if(column.equals("User_userId")){
            statement = conn.prepareStatement(SQL_SELECT_BY_ID);
            statement.setString(1, value);
        }
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Activated_User = new Activated_Users();
            Activated_User.setUser_userId(set.getLong("User_userId"));
            list.add(Activated_User);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Activated_Users activated_user) throws SQLException {
        boolean created = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        rs = statement.getGeneratedKeys();
        statement.setLong(1, activated_user.getUser_userId());
        if (statement.executeUpdate() == 1)
            created = true;
        statement.close();
        conn.close();
        return created;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(long id) throws SQLException {
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
