package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Connector {
	
    //Actually should be read from a properties file
    private static final String URL = "jdbc:mysql://localhost:3306/roomsdb?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    static {
        try {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
   
    public static Connection getConnection() throws SQLException {
	return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
   
}

    
