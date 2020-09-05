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
import models.Country;
import models.Item;

/**
 *
 * @author alicemts
 */
public class ItemHasCountryDaoImpl implements ItemHasCountryDaoIface {

    private static final String SQL_SELECT_COUNTRY = "SELECT Country_idCountry FROM Item_Has_Country WHERE Item_ItemID =?";   
    private static final String SQL_SELECT_ITEMS = "SELECT Item_ItemID FROM Item_Has_Country WHERE Country_idCountry =?";
    private static final String SQL_INSERT_COUNTRY = "INSERT INTO Item_Has_Country (Country_idCountry,Item_ItemID) VALUES (?,?)";
    private static final String SQL_UPDATE_COUNTRY = "UPDATE Item_Has_Country SET Country_idCountry = ? WHERE User_userId = ?";
    private static final String SQL_DELETE = "DELETE FROM User_Has_Country where User_userId = ?";

    @Override 
    public List<Country> ItemHasCountry(long ItemID) throws SQLException {
        List<Country> countries = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_COUNTRY);
        statement.setLong(1, ItemID);
        ResultSet set = statement.executeQuery();
        if(set.next()){
            String idCountry = set.getString("Country_idCountry");
        
            CountryDaoIface dao = new CountryDaoImpl();
            countries = dao.search("idCountry",idCountry);
         }
        set.close();
        statement.close();
        conn.close();
        return countries;
    }


    @Override
    public List<Item> countryHasItems(long idCountry) throws SQLException {
    List<Item> items;
        items = new ArrayList<>();
        ItemHasCountryDaoIface dao;
        dao = new ItemHasCountryDaoImpl();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_ITEMS);
        statement.setLong(1, idCountry);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Item item = new Item();
            long id =set.getLong("Item_ItemID");
            //item = dao.find(id);
            items.add(item);
        }
        set.close();
        statement.close();
        conn.close();
        return items;

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean itemInsertsCountry(long ItemID, long idCountry) throws SQLException {
        boolean inserted = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement( SQL_INSERT_COUNTRY);
        statement.setLong(1, ItemID);
        statement.setLong(2, idCountry);
        if (statement.executeUpdate() == 1)
            inserted = true;

        statement.close();
        conn.close();
        return inserted;        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean itemChangesCountry(long ItemID, long idCountry) throws SQLException {
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_UPDATE_COUNTRY);
        statement.setLong(1, ItemID);
        statement.setLong(2, idCountry);
        if (statement.executeUpdate() == 1)
            updated = true;

        statement.close();
        conn.close();
        return updated;
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteItemFromCountry(long ItemID) throws SQLException {
    boolean delete = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_COUNTRY);
        statement.setLong(1, ItemID);
        ResultSet set = statement.executeQuery();
       
        if(set.next()){
            statement = conn.prepareStatement(SQL_DELETE);
            statement.setLong(1, ItemID);
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
