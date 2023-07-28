package com.teraplantask.server.control;

import java.sql.*;

public class Connection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Statement getConnection(){

        // making connection with a database "postgres" as "root"
        java.sql.Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            if(!connection.isClosed()){
                System.out.println("connection is available");
            }
            statement.executeUpdate("SET search_path TO service;");
            return statement;

        } catch (SQLException e) {
            System.out.println("Connection error");
            throw new RuntimeException(e);
        }
    }
}
