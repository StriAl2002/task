package com.teraplantask.server.sqlrequests;

import com.teraplantask.server.control.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GettingAttributes {

    // taking information about tables attributes
    public String GettingAttributes(String type) throws SQLException {
        StringBuilder res = new StringBuilder();
        Statement statement = Connection.getConnection();
        if(type.equals("employees")){
            String request = "SELECT pg_typeof(e.id), pg_typeof(e.gender), pg_typeof(e.fleet) " +
                    "FROM service.employees e " +
                    "LIMIT 1";
            ResultSet rs = statement.executeQuery(request);
            res.append("Таблица работников содержит следующие поля и типы: <br />");
            while (rs.next()) {
                res.append("id -> ").append(rs.getString(1)).append("<br />");
                res.append("gender -> ").append(rs.getString(2)).append("<br />");
                res.append("fleet -> ").append(rs.getString(3)).append("<br />");
            }
        }
        else if(type.equals("flights")){
            String request = "SELECT pg_typeof(f.id), pg_typeof(f.flight_num), pg_typeof(f.station_departure), pg_typeof(f.station_arrival), pg_typeof(f.date_time_departure), pg_typeof(f.date_time_arrival) " +
                    "FROM service.flights f " +
                    "LIMIT 1";
            ResultSet rs = statement.executeQuery(request);
            res.append("Таблица рейсов содержит следующие поля и типы: <br />");
            while (rs.next()) {
                res.append("id -> ").append(rs.getString(1)).append("<br />");
                res.append("flight_num -> ").append(rs.getString(2)).append("<br />");
                res.append("station_departure -> ").append(rs.getString(3)).append("<br />");
                res.append("station_arrival -> ").append(rs.getString(4)).append("<br />");
                res.append("date_time_departure -> ").append(rs.getString(5)).append("<br />");
                res.append("date_time_arrival -> ").append(rs.getString(6)).append("<br />");
            }
        }
        else if(type.equals("pairings")){
            String request = "SELECT pg_typeof(p.id), pg_typeof(p.fleet), pg_typeof(p.date_time_start), pg_typeof(p.date_time_end) " +
                    "FROM service.pairings p " +
                    "LIMIT 1";
            ResultSet rs = statement.executeQuery(request);
            res.append("Таблица пейрингов содержит следующие поля и типы: <br />");
            while (rs.next()) {
                res.append("id -> ").append(rs.getString(1)).append("<br />");
                res.append("fleet -> ").append(rs.getString(2)).append("<br />");
                res.append("date_time_start -> ").append(rs.getString(3)).append("<br />");
                res.append("date_time_end -> ").append(rs.getString(4)).append("<br />");
            }
        }
        else if(type.equals("assignments")){
            String request = "SELECT pg_typeof(a.id_employee), pg_typeof(a.id_pairing), pg_typeof(a.rank) " +
                    "FROM service.assignments a " +
                    "LIMIT 1";
            ResultSet rs = statement.executeQuery(request);
            res.append("Типы полей: <br />");
            while (rs.next()) {
                res.append("id_employee -> ").append(rs.getString(1)).append("<br />");
                res.append("id_pairing -> ").append(rs.getString(2)).append("<br />");
                res.append("rank -> ").append(rs.getString(3)).append("<br />");
            }
        }

        return res.toString();
    }
}
