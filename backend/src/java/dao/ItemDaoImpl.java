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
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import models.Item;



/**
 *
 * @author alicemts
 */
public class ItemDaoImpl implements ItemDaoIface {

    private static final String SQL_SELECT = "SELECT * from Item order by ItemID asc";  
    private static final String SQL_SELECT_BY_ID = "SELECT * from Item where ItemID = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * from Item where name = ?";    
    private static final String SQL_SELECT_BY_DESCRIPTION = "SELECT * from Item where description = ?";
    private static final String SQL_SELECT_BY_AUCTION_IDAUCTION = "SELECT * from Item where Auction_idAuction = ?";
    private static final String SQL_SELECT_BY_LOCATION_LATITUDE = "SELECT * from Item where location_latitude = ?";
    private static final String SQL_SELECT_BY_LOCATION_LONGITUDE = "SELECT * from Item where location_longitude = ?";
    private static final String SQL_INSERT = "INSERT INTO Item (name,description,Auction_idAuction,location_latitude,location_longitude)VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_NAME = "UPDATE Item SET name = ? WHERE ItemID = ?";
    private static final String SQL_UPDATE_DESCRIPTION = "UPDATE Item SET description = ? WHERE ItemID = ?";
    private static final String SQL_UPDATE_AUCTION_IDAUCTION = "UPDATE Item SET Auction_idAuction = ? WHERE ItemID = ?";
    private static final String SQL_UPDATE_LOCATION_LATITUDE = "UPDATE Item SET location_latitude = ? WHERE ItemID = ?";
    private static final String SQL_UPDATE_LOCATION_LONGITUDE = "UPDATE Item SET location_longitude = ? WHERE ItemID = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Item where ItemID = ?";
    

    @Override
    public List<Item> list() throws SQLException {
        ArrayList<Item> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            Item Item = new Item();
            
            Item.setItemId(set.getInt("ItemID"));
            Item.setName(set.getString("name"));
            Item.setDescription(set.getString("description"));
            Item.setAuction_idAuction(set.getInt("auction_idAuction"));
            Item.setLocation_latitude(set.getDouble("location_latitude"));
            Item.setLocation_longitude(set.getDouble("location_longitude"));
            list.add(Item);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }


    @Override
    public Item find(long id) throws SQLException {
        Item Item = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            Item = new Item();
            Item.setItemId(set.getInt("ItemID"));
            Item.setName(set.getString("name"));
            Item.setDescription(set.getString("description"));
            Item.setAuction_idAuction(set.getInt("auction_idAuction"));
            Item.setLocation_latitude(set.getDouble("location_latitude"));
            Item.setLocation_longitude(set.getDouble("location_longitude"));
            //list.add(Item);
        }
        set.close();
        statement.close();
        conn.close();
        return Item;
    }

        @Override
        public List<Item> search(String column, String value) throws SQLException {
        Item Item = null;
        ArrayList<Item> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        
        if(column.equals("name")){
            statement = conn.prepareStatement(SQL_SELECT_BY_NAME);
            statement.setString(1, value);
        }else if(column.equals("description")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_DESCRIPTION);
            statement.setString(1, value);
        }else if(column.equals("Auction_idAuction")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_AUCTION_IDAUCTION);
            statement.setInt(1, Integer.parseInt(value));
        }else if(column.equals("location_latitude")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_LOCATION_LATITUDE);
            statement.setDouble(1, Double.parseDouble(value));
        }else if(column.equals("location_longitude")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_LOCATION_LONGITUDE);
            statement.setDouble(1, Double.parseDouble(value));
        }
        
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Item = new Item();
            Item.setItemId(set.getInt("ItemID"));
            Item.setName(set.getString("name"));
            Item.setDescription(set.getString("description"));
            Item.setAuction_idAuction(set.getInt("auction_idAuction"));
            Item.setLocation_latitude(set.getDouble("location_latitude"));
            Item.setLocation_longitude(set.getDouble("location_longitude"));
            list.add(Item);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }


    @Override
    public boolean create(Item item) throws SQLException {
        boolean created = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        rs = statement.getGeneratedKeys();
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
            case "description": 
                statement = conn.prepareStatement(SQL_UPDATE_DESCRIPTION);
                statement.setString(1, value);
                break;
            case "auction_idAuction": 
                statement = conn.prepareStatement(SQL_UPDATE_AUCTION_IDAUCTION);
                statement.setInt(1, Integer.parseInt(value));
                break;
            case "location_latitude":
                statement = conn.prepareStatement(SQL_UPDATE_LOCATION_LATITUDE);
                statement.setDouble(1, Double.parseDouble(value));
                break;
            case "location_longitude":
                statement = conn.prepareStatement(SQL_UPDATE_LOCATION_LONGITUDE);
                statement.setDouble(1, Double.parseDouble(value));
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
