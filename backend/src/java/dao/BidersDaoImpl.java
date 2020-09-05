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
import java.util.ArrayList;
import java.util.List;
import models.Biders;

/**
 *
 * @author alicemts
 */
public class BidersDaoImpl implements BidersDaoIface {

    private static final String SQL_SELECT = "SELECT * FROM Biders";
    private static final String SQL_SELECT_BY_ID = "SELECT * from Biders where user_userId = ?";
    private static final String SQL_SELECT_BY_RATING = "SELECT * FROM Biders WHERE rating = ?";
    private static final String SQL_SELECT_GREATER_THAN_RATING = "SELECT * FROM Biders WHERE rating >= ? ORDER BY rating";
    private static final String SQL_SELECT_LESS_THAN_RATING = "SELECT * FROM Biders WHERE rating <= ? ORDER BY rating";
    private static final String SQL_INSERT = "INSERT INTO Biders (user_userId) VALUES (?)";
    private static final String SQL_UPDATE_INCREASE = "UPDATE Biders SET rating = rating + 5 WHERE user_userId = ?";
    private static final String SQL_UPDATE_DECREASE = "UPDATE Biders SET rating = rating - 5 WHERE user_userId = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Biders WHERE user_userId = ?";

    @Override
    public List<Biders> listBiders() throws SQLException {
        List<Biders> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            Biders bider = new Biders();
            bider = find(set.getLong("user_userId"));
            list.add(bider);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public Biders find(long id) throws SQLException {
        Biders bider = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            bider = new Biders();
            bider.setRating(set.getLong("rating"));
            bider.setUser_userId(set.getLong("user_userId"));
        }
        set.close();
        statement.close();
        conn.close();
        return bider;
    }

    @Override
    public List<Biders> search(String column, String value) throws SQLException {
        ArrayList<Biders> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement;
        if(column.equals("rating")){
            statement = conn.prepareStatement(SQL_SELECT_BY_RATING);
            statement.setLong(1, Long.parseLong(value));
        }else{
            statement = conn.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, Long.parseLong(value));
        }
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Biders bider = new Biders();
            bider = find(set.getLong("user_userId"));
            list.add(bider);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public boolean create(long id) throws SQLException {
        boolean created = false;
        
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement( SQL_INSERT );
        statement.setLong(1, id);
        
        if (statement.executeUpdate() == 1)
            created = true;

        statement.close();
        conn.close();
        return created;
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
    }

    @Override
    public boolean voteUp(long id) throws SQLException {
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
       
        if(set.next()){
            statement = conn.prepareStatement(SQL_UPDATE_INCREASE);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1) {
           
            updated = true;
            }
        }
        
        statement.close();
        conn.close();
        return updated;
    
    }

    @Override
    public boolean voteDown(long id) throws SQLException {
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
       
        if(set.next()){
            statement = conn.prepareStatement(SQL_UPDATE_DECREASE);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1) {
           
            updated = true;
            }
        }
        
        statement.close();
        conn.close();
        return updated;
    
    }
    

    

}
