/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static java.lang.Long.getLong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Country;
import models.User;

/**
 *
 * @author alicemts
 */
public class UserHasCountryDaoImpl implements UserHasCountryDaoIface {

    private static final String SQL_SELECT_COUNTRY = "SELECT Country_idCountry FROM User_Has_Country WHERE User_userId =?";
    private static final String SQL_SELECT_USERS = "SELECT User_userId FROM User_Has_Country WHERE Country_idCountry =?";
    private static final String SQL_INSERT_COUNTRY = "INSERT INTO User_Has_Country (User_userId,Country_idCountry) VALUES (?,?)";
    private static final String SQL_UPDATE_COUNTRY = "UPDATE User_Has_Country SET Country_idCountry = ? WHERE User_userId = ?";
    private static final String SQL_DELETE = "DELETE FROM User_Has_Country where User_userId = ?";
    
    @Override
    public List<User> countryHasUsers(long idCountry) throws SQLException {
        List<User> users;
        users = new ArrayList<>();
        UserDaoIface dao = new UserDaoImpl();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_USERS);
        statement.setLong(1, idCountry);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            User user = new User();
            long id =set.getLong("User_userId");
            user = dao.find(id);
            users.add(user);
        }
        set.close();
        statement.close();
        conn.close();
        return users;
    }

    @Override
    public List<Country> userHasCountry(long userId) throws SQLException {
        List<Country> countries = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_COUNTRY);
        statement.setLong(1, userId);
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
    public boolean userInsertsCountry(long userId, long idCountry) throws SQLException {
        boolean inserted = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement( SQL_INSERT_COUNTRY);
        statement.setLong(1, userId);
        statement.setLong(2, idCountry);
        if (statement.executeUpdate() == 1)
            inserted = true;

        statement.close();
        conn.close();
        return inserted;
    }

    @Override
    public boolean userChangesCountry(long userId, long idCountry) throws SQLException {
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_UPDATE_COUNTRY);
        statement.setLong(1, idCountry);
        statement.setLong(2, userId);
        if (statement.executeUpdate() == 1)
            updated = true;

        statement.close();
        conn.close();
        return updated;
    }

    @Override
    public boolean deleteUserFromCountry(long userId) throws SQLException {
        boolean delete = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_COUNTRY);
        statement.setLong(1, userId);
        ResultSet set = statement.executeQuery();
       
        if(set.next()){
            statement = conn.prepareStatement(SQL_DELETE);
            statement.setLong(1, userId);
            if (statement.executeUpdate() == 1) {
           
            delete = true;
            }
        }
        
        statement.close();
        conn.close();
        return delete;
    
    }

}
