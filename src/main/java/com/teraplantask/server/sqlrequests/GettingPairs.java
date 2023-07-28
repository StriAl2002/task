package com.teraplantask.server.sqlrequests;

import com.teraplantask.server.control.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GettingPairs {

    // taking information about employee's pairings
    public String GettingPairs(String id) throws SQLException {
        String request = "SELECT p " +
                "FROM service.employees e, service.assignments a, service.pairings p " +
                "WHERE e.id = a.id_employee " +
                "AND a.id_pairing = p.id " +
                "AND e.id = " + id;
        Statement statement = Connection.getConnection();
        ResultSet rs = statement.executeQuery(request);
        StringBuilder res = new StringBuilder();
        res.append("Работник ").append(id).append(" принимает участие в следующих пейрингах:<br />");
        res.append("id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|fleet|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;time_date_start&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;time_date_end<br />");
        while (rs.next()) {
            res.append(rs.getString(1)).append("<br />");
        }
        return res.toString();
    }
}
