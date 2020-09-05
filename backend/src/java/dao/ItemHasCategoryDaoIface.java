/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Item;

/**
 *
 * @author alicemts
 */
public interface ItemHasCategoryDaoIface {
    public List<Category> itemHasCategories(long itemId) throws SQLException;
    
    public List<Item> categoryHasItems(long idCategory) throws SQLException;

    public boolean itemInsertsCategory(long itemId,long idCategory) throws SQLException;
    
    public boolean itemChangesCategory(long itemId,long idCategory) throws SQLException;
    
    public boolean deleteItem(long itemId) throws SQLException;
    
    public boolean deleteItemFromCategory(long itemId,long idCategory) throws SQLException;
}
