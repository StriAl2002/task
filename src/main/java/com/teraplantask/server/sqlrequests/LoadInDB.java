package com.teraplantask.server.sqlrequests;

import com.teraplantask.server.control.Connection;
import com.teraplantask.server.entity.Assignments;
import com.teraplantask.server.entity.Employees;
import com.teraplantask.server.entity.Flights;
import com.teraplantask.server.entity.Pairings;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadInDB {

    //updating or loading database
    public LoadInDB() throws SQLException, IOException {
        Statement statement = Connection.getConnection();
        new Employees(statement);
        new Flights(statement);
        new Pairings(statement);
        new Assignments(statement);
    }
}
