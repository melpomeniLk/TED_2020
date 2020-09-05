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
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Item;

/**
 *
 * @author alicemts
 */
public class ItemHasCategoryDaoImpl implements ItemHasCategoryDaoIface {

    private static final String SQL_SELECT_CATEGORY = "SELECT category_idCategory FROM Item_Has_Category WHERE item_itemId =?";
    private static final String SQL_SELECT_ITEMS = "SELECT item_itemId =? FROM Item_Has_Category WHERE category_idCategory =?";
    private static final String SQL_INSERT_CATEGORY = "INSERT INTO Item_Has_Category (item_itemId,category_idCategory) VALUES (?,?)";
    private static final String SQL_UPDATE_CATEGORY = "UPDATE Item_Has_Category SET category_idCategory = ? WHERE item_itemId = ?";
    private static final String SQL_DELETE = "DELETE FROM Item_Has_Category where item_itemId = ?";
    private static final String SQL_DELETE_CATEGORY = "DELETE FROM Item_Has_Category where item_itemId = ? and category_idCategory = ?";
  

    @Override
    public List<Category> itemHasCategories(long itemId) throws SQLException {
        List<Category> categories;
        categories = new ArrayList<>();
        CategoryDaoIface dao = new CategoryDaoImpl();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_CATEGORY);
        statement.setLong(1, itemId);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Category category = new Category();
            long id =set.getLong("category_idCategory");
            category = dao.find(id);
            categories.add(category);
        }
        set.close();
        statement.close();
        conn.close();
        return categories;
    }

    @Override
    public List<Item> categoryHasItems(long idCategory) throws SQLException {
        List<Item> items;
        items = new ArrayList<>();
        //CategoryDaoIface dao = new CategoryDaoImpl();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_ITEMS);
        statement.setLong(1, idCategory);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Item item = new Item();
            long id =set.getLong("item_itemId");
            //category = dao.find(itemId);
            items.add(item);
        }
        set.close();
        statement.close();
        conn.close();
        return items;
    }

    @Override
    public boolean itemInsertsCategory(long itemId, long idCategory) throws SQLException {
        boolean inserted = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement( SQL_INSERT_CATEGORY);
        statement.setLong(1, itemId);
        statement.setLong(2, idCategory);
        if (statement.executeUpdate() == 1)
            inserted = true;

        statement.close();
        conn.close();
        return inserted;
    }

    @Override
    public boolean itemChangesCategory(long itemId, long idCategory) throws SQLException {
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_UPDATE_CATEGORY);
        statement.setLong(1, itemId);
        statement.setLong(2, idCategory);
        if (statement.executeUpdate() == 1)
            updated = true;

        statement.close();
        conn.close();
        return updated;
    }

    @Override
    public boolean deleteItemFromCategory(long itemId, long idCategory) throws SQLException {
        boolean delete = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_CATEGORY);
        statement.setLong(1,itemId );
        ResultSet set = statement.executeQuery();
       
        if(set.next()){
            statement = conn.prepareStatement(SQL_DELETE_CATEGORY);
            statement.setLong(1, itemId);
            statement.setLong(2, idCategory);
            if (statement.executeUpdate() == 1) {
           
            delete = true;
            }
        }
        
        statement.close();
        conn.close();
        return delete;
    
    }

    @Override
    public boolean deleteItem(long itemId) throws SQLException {
        boolean delete = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement ;
       
//        if(set.next()){
            statement = conn.prepareStatement(SQL_DELETE);
            statement.setLong(1, itemId);
            if (statement.executeUpdate() == 1) {
           
            delete = true;
            }
       // }
        
        statement.close();
        conn.close();
        return delete;
    }

}
