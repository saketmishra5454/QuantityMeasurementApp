package com.apps.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/quantitymeasurementdb";
    public static final String USER = "root";
    public static final String PASSWORD = "12345";


    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
