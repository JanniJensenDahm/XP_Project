package com.adventure;

import java.sql.*;
@SuppressWarnings("Duplicates")
public class AccessDB {
    static AccessDB instance = new AccessDB();
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://adventurevalley.crq7o79i1nyl.us-east-2.rds.amazonaws.com:3306/AdventureValley?useSSL=false";
    static Connection con;

    private AccessDB() {
    }


    public static Connection getConnection() {
        con = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "master123", "master123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    public static AccessDB getInstance() {
        return instance;
    }

}