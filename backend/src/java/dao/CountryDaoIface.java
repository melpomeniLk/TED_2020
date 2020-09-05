/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Country;

/**
 *
 * @author alicemts
 */
public interface CountryDaoIface {
    public List<Country> list() throws SQLException;
    
    public Country find(long id) throws SQLException;
    
    public List<Country> search(String column, String value) throws SQLException;

    public boolean create(Country country) throws SQLException;
    
    public boolean update(Country country) throws SQLException;
    
    public boolean delete(long id) throws SQLException;
}
