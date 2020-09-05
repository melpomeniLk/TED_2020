/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.Country;
import models.Item;

/**
 *
 * @author alicemts
 */
public interface ItemHasCountryDaoIface {
    public List<Country> ItemHasCountry(long ItemID) throws SQLException;
    
    public List<Item> countryHasItems(long idCountry) throws SQLException;

    public boolean itemInsertsCountry(long ItemID,long idCountry) throws SQLException;
    
    public boolean itemChangesCountry(long ItemID,long idCountry) throws SQLException;
    
    public boolean deleteItemFromCountry(long ItemID) throws SQLException;
}
