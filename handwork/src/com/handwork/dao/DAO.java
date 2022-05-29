package com.handwork.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    Connection conn;

    public DAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String dbURL = "jdbc:mariadb://db:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
            String dbID = "handwork";
            String dbPassword = "handwork";
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection (){
        return conn;
    }


}
