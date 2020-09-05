/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Item;

/**
 *
 * @author alicemts
 */
public interface ItemDaoIface {
    public List<Item> list() throws SQLException;
    
    public Item find(long id) throws SQLException;
    
    public List<Item> search(String column, String value) throws SQLException;

    public boolean create(Item item) throws SQLException;
        
    public boolean changeData(String column, String value, long id) throws SQLException;
    
    public boolean delete(long id) throws SQLException;
}                                                                                              
