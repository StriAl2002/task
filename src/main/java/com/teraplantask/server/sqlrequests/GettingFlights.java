package com.teraplantask.server.sqlrequests;

import com.teraplantask.server.control.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GettingFlights {

    // taking information about employee's flights
    public String GettingFlights(String id) throws SQLException {
        String request = "SELECT f " +
                "FROM service.employees e, service.assignments a, service.pairings p, service.flights f " +
                "WHERE e.id = a.id_employee " +
                "AND a.id_pairing = p.id " +
                "AND (p.date_time_start = f.date_time_departure OR p.date_time_end = f.date_time_arrival) " +
                "AND e.id = " + id +
                " ORDER BY date_time_departure";
        Statement statement = Connection.getConnection();
        ResultSet rs = statement.executeQuery(request);
        StringBuilder res = new StringBuilder();
        res.append("Работник ").append(id).append(" принимает участие в следующих рейсах:<br />");
        res.append("id|flight_num|derart_port|arriv_port|time_date_departure|time_date_arrival<br />");
        while (rs.next()) {
            res.append(rs.getString(1)).append("<br />");
        }
        return res.toString();
    }
}
