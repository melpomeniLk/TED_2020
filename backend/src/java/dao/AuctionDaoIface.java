/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Auction;
/**
 *
 * @author anastasiaeleftheriadi
 */
public interface AuctionDaoIface {
    public List<Auction> list() throws SQLException;
    public List<Auction> listActiveAuctions(long userId) throws SQLException;
    public List<Auction> listInactiveAuctions(long userId) throws SQLException;
    public Auction find(long id) throws SQLException;
    public List<Auction> search(String column, String value) throws SQLException;
    public boolean create(Auction Category) throws SQLException;
    public boolean delete(long id) throws SQLException;    
    public boolean changeData(String column, String value, long id) throws SQLException;
    public boolean auctionHasBids(long id) throws SQLException;    
    public boolean increaseBids(long id) throws SQLException;    
}
