/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Biders;
import models.User;

/**
 *
 * @author alicemts
 */
public interface BidersDaoIface {
    public List<Biders> listBiders() throws SQLException;
    
    public Biders find(long id) throws SQLException;
    
    public List<Biders> search(String column, String value) throws SQLException;

    public boolean create(long id) throws SQLException;
    
    public boolean delete(long id) throws SQLException;
    
    public boolean voteUp(long id) throws SQLException;
    
    public boolean voteDown(long id) throws SQLException;
}
