/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static java.lang.Long.getLong;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Bid;
import models.Biders;
import java.sql.Timestamp;

/**
 *
 * @author alicemts
 */
public class BidDaoImpl implements BidDaoIface {

    private static final String SQL_SELECT = "SELECT * from Bid";
    private static final String SQL_SELECT_BY_ID = "SELECT * from Bid where idBid = ?";
    private static final String SQL_SELECT_BY_AMOUNT = "SELECT * from Bid where amount = ?";
    private static final String SQL_SELECT_BY_TIME = "SELECT * from Bid where time = ?";
    private static final String SQL_SELECT_BY_AUCTION = "SELECT * from Bid where auction_idAuction = ?";
    private static final String SQL_SELECT_BY_BIDER = "SELECT * from Bid,Biders where biders_User_userId = ? and user_userId = biders_User_userId";
    private static final String SQL_INSERT = "INSERT INTO Bid (amount,time,auction_idAuction,biders_User_userId) VALUES (?,?,?,?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Bid where idBid = ?";

    @Override
    public List<Bid> list() throws SQLException {
        ArrayList<Bid> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
        ResultSet set = statement.executeQuery();
        
        while (set.next()) {
            Bid bid = new Bid();
            bid = find(set.getLong("idBid"));
            list.add(bid);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public Bid find(long id) throws SQLException {
        Bid bid = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        
        if (set.next()) {
            bid = new Bid();
            bid.setIdBid(set.getLong("idBid"));
            bid.setAmount(set.getFloat("amount"));
            bid.setTime(set.getTimestamp(3));
            bid.setAuction_idAuction(set.getLong("auction_idAuction"));
            bid.setBiders_User_userId(set.getLong("biders_User_userId"));
        }
        set.close();
        statement.close();
        conn.close();
        return bid;
    }

    @Override
    public List<Bid> search(String column, String value) throws SQLException {
        ArrayList<Bid> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        
        switch(column) 
        { 
            case "idBid": 
                statement = conn.prepareStatement(SQL_SELECT_BY_ID);
                statement.setLong(1, Long.parseLong(value));
                break;
            case "amount":
                statement = conn.prepareStatement(SQL_SELECT_BY_AMOUNT);
                statement.setFloat(1, Float.parseFloat(value));
                break;
            case "time":
                statement = conn.prepareStatement(SQL_SELECT_BY_TIME);
                statement.setTimestamp(1, Timestamp.valueOf(value));
                break;
            case "idAuction":
                statement = conn.prepareStatement(SQL_SELECT_BY_AUCTION);
                statement.setLong(1, Long.parseLong(value));
                break;
            case "idBider":
                statement = conn.prepareStatement(SQL_SELECT_BY_BIDER);
                statement.setLong(1, Long.parseLong(value));
                break;
            default:
                break;
        }
        
        ResultSet set = statement.executeQuery();
        
        while (set.next()) {
            Bid bid = new Bid();
            bid = find(set.getLong("idBid"));
            list.add(bid);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public boolean create(Bid bid) throws SQLException {
        boolean created = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
        rs = statement.getGeneratedKeys();
        
        statement.setFloat(1, bid.getAmount());
        statement.setTimestamp(2, bid.getTime());
        statement.setLong(3, bid.getAuction_idAuction());
        statement.setLong(4, bid.getBiders_User_userId());
        
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

    


}
