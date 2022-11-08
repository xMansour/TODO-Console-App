package com.swe.todoconsoleapp.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static config.DatabaseConfigurations.*;

public class DbContext {
    public static Connection openDbConnection() {
        Connection con;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
