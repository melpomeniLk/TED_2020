/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Category;

/**
 *
 * @author alicemts
 */
public class CategoryDaoImpl implements CategoryDaoIface {

    private static final String SQL_SELECT = "SELECT * from Category order by name asc";
    private static final String SQL_SELECT_BY_ID = "SELECT * from Category where idCategory = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * from Category where name = ?";
    private static final String SQL_INSERT = "INSERT INTO Category (name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE Category SET name = ? WHERE idCategory = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Category where idCategory = ?";
    
    
    @Override
    public List<Category> list() throws SQLException {
        ArrayList<Category> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            Category Category = new Category();
            Category.setIdCategory(set.getLong("idCategory"));
            Category.setName(set.getString("name"));
            list.add(Category);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public Category find(long id) throws SQLException {
        Category Category = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            Category = new Category();
            Category.setIdCategory(set.getLong("idCategory"));
            Category.setName(set.getString("name"));
        }
        set.close();
        statement.close();
        conn.close();
        return Category;
    }

    @Override
    public List<Category> search(String column, String value) throws SQLException {
        ArrayList<Category> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement;
        if(column.equals("name")){
            statement = conn.prepareStatement(SQL_SELECT_BY_NAME);
            statement.setString(1, value);
        }else{
            statement = conn.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, Long.parseLong(value));
        }
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Category category = new Category();
            category.setIdCategory(set.getLong("idCategory"));
            category.setName(set.getString("name"));
            list.add(category);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
        

    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Category Category) throws SQLException {
        boolean created = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
        statement.setString(1, Category.getName());
        rs = statement.getGeneratedKeys();
        if (statement.executeUpdate() == 1)
            created = true;

        statement.close();
        conn.close();
        return created;
        

    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Category Category) throws SQLException {
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_UPDATE);
        statement.setString(1, Category.getName());
        statement.setLong(2, Category.getIdCategory());
        if (statement.executeUpdate() == 1)
            updated = true;

        statement.close();
        conn.close();
        return updated;


        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
