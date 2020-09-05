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
import models.Auction;
import models.Country;
import models.User;

/**
 *
 * @author alicemts
 */
public class UserHasHistoryDaoImpl implements UserHasHistoryDaoIface {

    private static final String SQL_SELECT_HISTORY = "SELECT seen_idAuction FROM User_Has_History WHERE User_userId =?";
    private static final String SQL_INSERT_HISTORY = "INSERT INTO User_Has_History (User_userId,seen_idAuction) VALUES (?,?)";
    private static final String SQL_DELETE_HISTORY = "DELETE FROM User_Has_History where User_userId = ?";
    private static final String SQL_DELETE_AUCTION = "DELETE FROM User_Has_History where seen_idAuction = ? and User_userId = ?";

    @Override
    public List<Auction> userHistory(long userId) throws SQLException {
        List<Auction> auctions;
        auctions = new ArrayList<>();
        AuctionDaoIface dao = new AuctionDaoImpl();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_HISTORY);
        statement.setLong(1, userId);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Auction auction = new Auction();
            long id =set.getLong("seen_idAuction");
            auction = dao.find(id);
            auctions.add(auction);
        }
        set.close();
        statement.close();
        conn.close();
        return auctions;
    }

    @Override
    public boolean userViewsAuction(long userId, long idAuction) throws SQLException {
        boolean inserted = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement( SQL_INSERT_HISTORY);
        statement.setLong(1, userId);
        statement.setLong(2, idAuction);
        if (statement.executeUpdate() == 1)
            inserted = true;

        statement.close();
        conn.close();
        return inserted;
    }

    @Override
    public boolean deleteHistory(long userId) throws SQLException {
        boolean delete = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_HISTORY);
        statement.setLong(1, userId);
        ResultSet set = statement.executeQuery();
       
        if(set.next()){
            statement = conn.prepareStatement(SQL_DELETE_HISTORY);
            statement.setLong(1, userId);
            if (statement.executeUpdate() == 1) {
           
            delete = true;
            }
        }
        
        statement.close();
        conn.close();
        return delete;
    
    }

    @Override
    public boolean deleteAuctionFromHistory(long userId, long idAuction) throws SQLException {
        boolean delete = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_HISTORY);
        statement.setLong(1, userId);
        ResultSet set = statement.executeQuery();
       
        if(set.next()){
            statement = conn.prepareStatement(SQL_DELETE_AUCTION);
            statement.setLong(1, idAuction);
            statement.setLong(2, userId);
            if (statement.executeUpdate() == 1) {
           
            delete = true;
            }
        }
        
        statement.close();
        conn.close();
        return delete;
    
    }

}
