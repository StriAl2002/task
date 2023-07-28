package com.teraplantask.server.entity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class Flights {

    public Flights(Statement statement) throws IOException, SQLException {

        // working with a file after correct connection
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS flights (id INTEGER NOT NULL PRIMARY KEY, flight_num INTEGER NOT NULL, station_departure VARCHAR(4), station_arrival VARCHAR(4), date_time_departure TIMESTAMP WITH TIME ZONE, date_time_arrival TIMESTAMP WITH TIME ZONE);");
        try {
            // reading a file
            String data = new String(Files.readAllBytes(Paths.get("flights.json")));
            data = data.substring(data.indexOf("\"Flights\":"));
            data = data.substring(data.indexOf("["));
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);

                int id = object.getInt("ID");
                String flight_num = object.getString("FlightNum");
                String station_departure = object.getString("StationDeparture");
                String station_arrival = object.getString("StationArrival");
                String date_time_departure = object.getString("DateTimeDeparture");
                String date_time_arrival = object.getString("DateTimeArrival");

                String insert = "INSERT INTO flights (ID, FLIGHT_NUM, STATION_DEPARTURE, STATION_ARRIVAL, DATE_TIME_DEPARTURE, DATE_TIME_ARRIVAL) VALUES(" + id + ", '" + flight_num + "', '" + station_departure + "', '" + station_arrival + "', '" + date_time_departure +"', '" + date_time_arrival + "') ON CONFLICT DO NOTHING";

                // writing data in a database
                statement.executeUpdate(insert);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
