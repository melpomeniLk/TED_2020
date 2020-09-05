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
import models.Auction;
/**
 *
 * @author anastasiaeleftheriadi
 */
public class AuctionDaoImpl implements AuctionDaoIface {
    private static final String SQL_SELECT = "SELECT * from Auction order by idAuction asc";
    private static final String SQL_SELECT_BY_ID = "SELECT * from Auction where idAuction = ?";
    private static final String SQL_SELECT_BY_SELLERS_USER_USERID = "SELECT * from Auction where Sellers_User_userId = ?";
    private static final String SQL_SELECT_BY_IS_ACTIVE = "SELECT * from Auction where isActive = ?";
    private static final String SQL_SELECT_BY_STARTED = "SELECT * from Auction where started = ?";
    private static final String SQL_SELECT_BY_BUY_PRICE = "SELECT * from Auction where buy_price = ?";
    private static final String SQL_SELECT_BY_NUMBER_OF_BIDS= "SELECT * from Auction where number_of_bids = ?";
    private static final String SQL_SELECT_BY_MINIMUM_BID= "SELECT * from Auction where minimum_bid = ?";
    private static final String SQL_SELECT_BY_DESCRIPTION = "SELECT * from Auction where description = ?";
    private static final String SQL_SELECT_BY_ENDS = "SELECT * from Auction where ends = ?";
    private static final String SQL_SELECT_BY_CURRENTLY_BID_ID = "SELECT * from Auction where currently_bid_ID = ?";
    private static final String SQL_INSERT = "INSERT INTO Auction (started,ends,buy_price,number_of_bids,currently_bid_ID,Sellers_User_userId,minimum_bid,isActive,description) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_BUY_PRICE = "UPDATE Auction SET buy_price = ? WHERE idAuction = ?";
    private static final String SQL_UPDATE_STARTED = "UPDATE Auction SET started = ? WHERE idAuction = ?";
    private static final String SQL_UPDATE_ENDS = "UPDATE Auction SET ends = ? WHERE idAuction = ?";
    private static final String SQL_UPDATE_NUMBER_OF_BIDS = "UPDATE Auction SET number_of_bids = ? WHERE idAuction = ?";
    private static final String SQL_UPDATE_CURRENTLY_BID_ID = "UPDATE Auction SET currently_bid_ID = ? WHERE idAuction = ?";
    private static final String SQL_UPDATE_IS_ACTIVE = "UPDATE Auction SET isActive = ? WHERE idAuction = ?";
    private static final String SQL_UPDATE_MINIMUM_BID = "UPDATE Auction SET minimum_bid = ? WHERE idAuction = ?";
    private static final String SQL_UPDATE_DESCRIPTION = "UPDATE Auction SET description = ? WHERE idAuction = ?";
    private static final String SQL_UPDATE_SELLERS_USER_USERID = "UPDATE Auction SET Sellers_User_userId = ? WHERE idAuction = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Auction WHERE idAuction = ?";
    private static final String SQL_INCREASE_NUMBER_OF_BIDS = "UPDATE Auction SET number_of_bids = number_of_bids + 1 WHERE idAuction = ?";

    
    @Override
    public List<Auction> list() throws SQLException {
        ArrayList<Auction> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            Auction Auction = new Auction();
            
            Auction.setIdAuction(set.getInt("idAuction"));
            Auction.setStarted(set.getTimestamp("started"));
            Auction.setEnds(set.getTimestamp("ends"));
            Auction.setBuy_price(set.getFloat("buy_price"));
            Auction.setMinimum_bid(set.getFloat("minimum_bid"));
            Auction.setIsActive(set.getBoolean("isActive"));
            Auction.setCurrently_bid_ID(set.getInt("currently_bid_ID"));
            Auction.setNumber_of_bids(set.getInt("number_of_bids"));
            Auction.setSellers_user_userId(set.getInt("Sellers_User_userId"));
            Auction.setDescription(set.getString("description"));
            list.add(Auction);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public Auction find(long id) throws SQLException {
        Auction Auction = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            Auction = new Auction();
            Auction.setIdAuction(set.getInt("idAuction"));
            Auction.setStarted(set.getTimestamp("started"));
            Auction.setEnds(set.getTimestamp("ends"));
            Auction.setBuy_price(set.getFloat("buy_price"));
            Auction.setMinimum_bid(set.getFloat("minimum_bid"));
            Auction.setIsActive(set.getBoolean("isActive"));
            Auction.setCurrently_bid_ID(set.getInt("currently_bid_ID"));
            Auction.setNumber_of_bids(set.getInt("number_of_bids"));
            Auction.setSellers_user_userId(set.getInt("Sellers_User_userId"));
            Auction.setDescription(set.getString("description"));
            //list.add(Auction);
        }
        set.close();
        statement.close();
        conn.close();
        return Auction;
    }

    @Override
    public List<Auction> search(String column, String value) throws SQLException {
        Auction Auction = null;
        ArrayList<Auction> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        
        if(column.equals("Sellers_User_userId")){
            statement = conn.prepareStatement(SQL_SELECT_BY_SELLERS_USER_USERID);
            statement.setString(1, value);
        }else if(column.equals("isActive")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_IS_ACTIVE);
            statement.setBoolean(1, Boolean.parseBoolean(value));
        }else if(column.equals("idAuction")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_ID);
            statement.setBoolean(1, Boolean.parseBoolean(value));
        }else if(column.equals("started")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_STARTED);
            statement.setTimestamp(1, Timestamp.valueOf(value));
        }else if(column.equals("ends")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_ENDS);
            statement.setTimestamp(1, Timestamp.valueOf(value));
        }else if(column.equals("currently_bid_ID")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_CURRENTLY_BID_ID);
            statement.setInt(1, Integer.parseInt(value));
        }else if(column.equals("buy_price")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_CURRENTLY_BID_ID);
            statement.setFloat(1, Float.parseFloat(value));
        }else if(column.equals("number_of_bids")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_NUMBER_OF_BIDS);
            statement.setInt(1, Integer.parseInt(value));
        }else if(column.equals("minimum_bid")) {
            statement = conn.prepareStatement(SQL_SELECT_BY_MINIMUM_BID);
            statement.setFloat(1, Float.parseFloat(value));
        }else if(column.equals("description")){
            statement = conn.prepareStatement(SQL_SELECT_BY_DESCRIPTION);
            statement.setString(1, value);
        }
        
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Auction = new Auction();
            Auction.setIdAuction(set.getInt("idAuction"));
            Auction.setStarted(set.getTimestamp("started"));
            Auction.setEnds(set.getTimestamp("ends"));
            Auction.setBuy_price(set.getFloat("buy_price"));
            Auction.setMinimum_bid(set.getFloat("minimum_bid"));
            Auction.setIsActive(set.getBoolean("isActive"));
            Auction.setCurrently_bid_ID(set.getInt("currently_bid_ID"));
            Auction.setNumber_of_bids(set.getInt("number_of_bids"));
            Auction.setSellers_user_userId(set.getInt("Sellers_User_userId"));
            Auction.setDescription(set.getString("description"));
            list.add(Auction);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Auction Category) throws SQLException {
        boolean created = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
//        statement.setString(1, );
        rs = statement.getGeneratedKeys();
        if (statement.executeUpdate() == 1)
            created = true;

        statement.close();
        conn.close();
        return created;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeData(String column, String value, long id) throws SQLException {
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        switch(column) 
        { 
            case "started": 
                statement = conn.prepareStatement(SQL_UPDATE_STARTED);
                statement.setTimestamp(1, Timestamp.valueOf(value));
                break; 
            case "ends": 
                statement = conn.prepareStatement(SQL_UPDATE_ENDS);
                statement.setTimestamp(1, Timestamp.valueOf(value));
                break;
            case "buy_price": 
                statement = conn.prepareStatement(SQL_UPDATE_BUY_PRICE);
                statement.setFloat(1, Float.parseFloat(value));
                break;
            case "minimum_bid":
                statement = conn.prepareStatement(SQL_UPDATE_MINIMUM_BID);
                statement.setFloat(1, Float.parseFloat(value));
                break;
            case "isActive":
                statement = conn.prepareStatement(SQL_UPDATE_IS_ACTIVE);
                statement.setBoolean(1, Boolean.valueOf(value));
                break;
            case "currently_bid_ID":
                statement = conn.prepareStatement(SQL_UPDATE_CURRENTLY_BID_ID);
                statement.setInt(1, Integer.parseInt(value));
                break;
            case "number_of_bids":
                statement = conn.prepareStatement(SQL_UPDATE_NUMBER_OF_BIDS);
                statement.setInt(1, Integer.parseInt(value));
                break;
            case "Sellers_User_userId":
                statement = conn.prepareStatement(SQL_UPDATE_SELLERS_USER_USERID);
                statement.setInt(1, Integer.parseInt(value));
                break;
            case "description":
                statement = conn.prepareStatement(SQL_UPDATE_DESCRIPTION);
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

    @Override
    public List<Auction> listActiveAuctions(long userId) throws SQLException {
        ArrayList<Auction> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_SELLERS_USER_USERID);
        statement.setLong(1, userId);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            if(set.getBoolean("isActive")){
                Auction Auction = new Auction();

                Auction.setIdAuction(set.getInt("idAuction"));
                Auction.setStarted(set.getTimestamp("started"));
                Auction.setEnds(set.getTimestamp("ends"));
                Auction.setBuy_price(set.getFloat("buy_price"));
                Auction.setMinimum_bid(set.getFloat("minimum_bid"));
                Auction.setIsActive(set.getBoolean("isActive"));
                Auction.setCurrently_bid_ID(set.getInt("currently_bid_ID"));
                Auction.setNumber_of_bids(set.getInt("number_of_bids"));
                Auction.setSellers_user_userId(set.getInt("Sellers_User_userId"));
                Auction.setDescription(set.getString("description"));
                list.add(Auction);
            }
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public List<Auction> listInactiveAuctions(long userId) throws SQLException {
        ArrayList<Auction> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_SELLERS_USER_USERID);
        statement.setLong(1, userId);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            if(set.getBoolean("isActive") == false){
                Auction Auction = new Auction();

                Auction.setIdAuction(set.getInt("idAuction"));
                Auction.setStarted(set.getTimestamp("started"));
                Auction.setEnds(set.getTimestamp("ends"));
                Auction.setBuy_price(set.getFloat("buy_price"));
                Auction.setMinimum_bid(set.getFloat("minimum_bid"));
                Auction.setIsActive(set.getBoolean("isActive"));
                Auction.setCurrently_bid_ID(set.getInt("currently_bid_ID"));
                Auction.setNumber_of_bids(set.getInt("number_of_bids"));
                Auction.setSellers_user_userId(set.getInt("Sellers_User_userId"));
                Auction.setDescription(set.getString("description"));
                list.add(Auction);
            }
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public boolean auctionHasBids(long id) throws SQLException {
        boolean flag = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
       
        if(set.next()){
            
            if (set.getInt("number_of_bids") != 0) {
                flag = true;
            }
        }
        statement.close();
        conn.close();
        return flag;
    }

    @Override
    public boolean increaseBids(long id) throws SQLException {
        
        boolean flag = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_INCREASE_NUMBER_OF_BIDS);
        statement.setLong(1, id);
        
        if(statement.executeUpdate() == 1)
            flag = true;
 
        statement.close();
        conn.close();
        return flag;
    }

    
}
