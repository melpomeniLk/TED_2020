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
import models.Pending_Users;
/**
 *
 * @author anastasiaeleftheriadi
 */
public class PendingUsersDaoImpl implements PendingUsersDaoIface {
    private static final String SQL_SELECT = "SELECT * from Pending_Users";
    private static final String SQL_SELECT_BY_ID = "SELECT * from Pending_Users where User_userId = ?";
    private static final String SQL_INSERT = "INSERT INTO Pending_Users (User_userId) VALUES (?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Pending_Users WHERE User_userId = ?";
    
    
    @Override
    public List<Pending_Users> list() throws SQLException {
        ArrayList<Pending_Users> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            Pending_Users Pending_Users = new Pending_Users();
            Pending_Users.setUser_userId(set.getInt("User_userId"));
            list.add(Pending_Users);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public Pending_Users find(long id) throws SQLException {
        Pending_Users pending_user = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            pending_user = new Pending_Users();
            pending_user.setUser_userId(set.getLong("User_userId"));
        }
        set.close();
        statement.close();
        conn.close();
        return pending_user;
    }

    @Override
    public List<Pending_Users> search(String column, String value) throws SQLException {
        Pending_Users Pending_User = null;
        ArrayList<Pending_Users> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        
        if(column.equals("User_userId")){
            statement = conn.prepareStatement(SQL_SELECT_BY_ID);
            statement.setString(1, value);
        }
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Pending_User = new Pending_Users();
            Pending_User.setUser_userId(set.getLong("User_userId"));
            list.add(Pending_User);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Pending_Users pending_user) throws SQLException {
        boolean created = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        rs = statement.getGeneratedKeys();
        statement.setLong(1, pending_user.getUser_userId());
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
