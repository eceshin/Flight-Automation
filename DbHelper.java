package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    String userName = "root";
    String password = "1437";
    String dbUrl = "jdbc:mysql://localhost:3306/ucus";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, userName, password);

    }

    public void ShowErrorMessage(SQLException exception) {
        System.out.println("Error:" + exception.getMessage());
    }
}
