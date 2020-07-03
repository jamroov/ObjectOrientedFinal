package com.company.Database;

import java.sql.*;
import java.util.Properties;

public class Connector {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/CarMonopoly";
    static final String USER = "postgres";
    static final String PASS = "mati12346";
    static private Connection CONNECTION;

    public static void connect() throws SQLException, ClassNotFoundException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);
        CONNECTION = DriverManager.getConnection(DB_URL, props);
        System.out.println("Connected");
    }

    public static Statement getStatement() throws SQLException {
        return CONNECTION.createStatement();
    }

    public static ResultSet executeSQL(String sql) throws SQLException {
        CONNECTION.createStatement().execute(sql);
        return CONNECTION.createStatement().getResultSet();
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        Statement stmnt = CONNECTION.createStatement();
        ResultSet res = stmnt.executeQuery(sql);
        return res;
    }

    public static Integer getNumRows(String table) throws SQLException {
        String sql = "select count(*) from " + table;
        Statement stmnt = CONNECTION.createStatement();
        ResultSet res = stmnt.executeQuery(sql);
        res.next();
        return res.getInt(1);
    }
}
