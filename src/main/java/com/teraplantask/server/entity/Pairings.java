package com.teraplantask.server.entity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class Pairings {

    public Pairings(Statement statement) throws IOException, SQLException {

        // working with a file after correct connection
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS pairings (id INTEGER NOT NULL PRIMARY KEY, fleet VARCHAR(16), date_time_start TIMESTAMP WITH TIME ZONE, date_time_end TIMESTAMP WITH TIME ZONE);");
        try {
            // reading a file
            String data = new String(Files.readAllBytes(Paths.get("roaster.json")));
            data = data.substring(data.indexOf("\"Pairings\":"));
            data = data.substring(data.indexOf("["), data.lastIndexOf("\"Assignments\":"));
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);

                int id = object.getInt("ID");
                String fleet = object.getString("Fleet");
                String date_time_start = object.getString("DateTimeStart");
                String date_time_end = object.getString("DateTimeEnd");

                String insert = "INSERT INTO pairings (ID, FLEET, DATE_TIME_START, DATE_TIME_END) VALUES(" + id + ", '" + fleet + "', '" + date_time_start + "', '" + date_time_end +"') ON CONFLICT DO NOTHING";

                // writing data in a database
                statement.executeUpdate(insert);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
