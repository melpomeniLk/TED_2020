/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Bid;

/**
 *
 * @author alicemts
 */
public interface BidDaoIface {
    public List<Bid> list() throws SQLException;
    
    public Bid find(long id) throws SQLException;
    
    public List<Bid> search(String column, String value) throws SQLException;

    public boolean create(Bid bid) throws SQLException;
    
    public boolean delete(long id) throws SQLException;
    
}
