/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Auction;
import models.User;

/**
 *
 * @author alicemts
 */
public interface UserHasHistoryDaoIface {
    public List<Auction> userHistory(long userId) throws SQLException;

    public boolean userViewsAuction(long userId,long idAuction) throws SQLException;
    
    public boolean deleteHistory(long userId) throws SQLException;
    
    public boolean deleteAuctionFromHistory(long userId,long idAuction) throws SQLException;
}
