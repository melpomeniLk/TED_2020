/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Category;

/**
 *
 * @author alicemts
 */
public interface CategoryDaoIface {
    public List<Category> list() throws SQLException;
    
    public Category find(long id) throws SQLException;
    
    public List<Category> search(String column, String value) throws SQLException;

    public boolean create(Category country) throws SQLException;
    
    public boolean update(Category country) throws SQLException;
    
    public boolean delete(long id) throws SQLException;
}
