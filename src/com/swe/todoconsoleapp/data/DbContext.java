package com.swe.todoconsoleapp.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbContext {
    public static void openDbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "1234");

            //here sonoo is the database name, root is the username and root is the password
            Statement stmt = con.createStatement();


            ResultSet rs = stmt.executeQuery("select * from employees");


            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2));

            con.close();
        } catch (
                Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        openDbConnection();
    }
}
