/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Sellers;

/**
 *
 * @author alicemts
 */
public interface SellersDaoIface {
    public List<Sellers> listSellers() throws SQLException;
    
    public Sellers find(long id) throws SQLException;
    
    public List<Sellers> search(String column, String value) throws SQLException;

    public boolean create(long id) throws SQLException;
    
    public boolean update(long id) throws SQLException;
    
    public boolean voteUp(long id) throws SQLException;
    
    public boolean voteDown(long id) throws SQLException;
   
    public boolean delete(long id) throws SQLException;
}
