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

/**
 *
 * @author alicemts
 */
public class CountryDaoImpl implements CountryDaoIface {

    private static final String SQL_SELECT = "SELECT * from Country order by name asc";
    private static final String SQL_SELECT_BY_ID = "SELECT * from Country where idCountry = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * from Country where name = ?";
    private static final String SQL_INSERT = "INSERT INTO Country (name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE Country SET name = ? WHERE idCountry = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Country where idCountry = ?";
    

    @Override
    public List<Country> list() throws SQLException {
        ArrayList<Country> list = new ArrayList<>();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            Country country = new Country();
            country.setIdCountry(set.getLong("idCountry"));
            country.setName(set.getString("name"));
            list.add(country);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public Country find(long id) throws SQLException {
        Country country = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_ID);
        statement.setLong(1, id);
        
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            country = new Country();
            country.setIdCountry(set.getLong("idCountry"));
            country.setName(set.getString("name"));
        }
        set.close();
        statement.close();
        conn.close();
        return country;
    }

    @Override
    public List<Country> search(String column, String value) throws SQLException {
        ArrayList<Country> list = new ArrayList<>();
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
            Country country = new Country();
            country.setIdCountry(set.getLong("idCountry"));
            country.setName(set.getString("name"));
            list.add(country);
        }
        set.close();
        statement.close();
        conn.close();
        return list;
    }

    @Override
    public boolean create(Country country) throws SQLException {
        boolean created = false;
        ResultSet rs = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement =  conn.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
        statement.setString(1, country.getName());
        rs = statement.getGeneratedKeys();
        if (statement.executeUpdate() == 1)
            created = true;

        statement.close();
        conn.close();
        return created;
    }

    @Override
    public boolean update(Country country) throws SQLException {
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_UPDATE);
        statement.setString(1, country.getName());
        statement.setLong(2, country.getIdCountry());
        if (statement.executeUpdate() == 1)
            updated = true;

        statement.close();
        conn.close();
        return updated;
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
    }

}
