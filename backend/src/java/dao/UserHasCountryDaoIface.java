/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Country;
import models.User;

/**
 *
 * @author alicemts
 */
public interface UserHasCountryDaoIface {
    public List<Country> userHasCountry(long userId) throws SQLException;
    
    public List<User> countryHasUsers(long idCountry) throws SQLException;

    public boolean userInsertsCountry(long userId,long idCountry) throws SQLException;
    
    public boolean userChangesCountry(long userId,long idCountry) throws SQLException;
    
    public boolean deleteUserFromCountry(long userId) throws SQLException;
}
