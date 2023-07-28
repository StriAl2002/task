package com.teraplantask.server.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees {

    public Employees(Statement statement) throws IOException, SQLException {

        // working with a file after correct connection
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS employees (id INTEGER NOT NULL PRIMARY KEY, gender VARCHAR(16), fleet VARCHAR(16));");
        try {
            // reading a file
            String data = new String(Files.readAllBytes(Paths.get("employees.json")));
            data = data.substring(data.indexOf("\"Employees\":"));
            data = data.substring(data.indexOf("["));
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);

                int id = object.getInt("ID");
                String gender = object.getString("Gender");
                String fleet = "";
                try {
                    fleet = object.getString("Fleet");
                } catch (JSONException je){
                    try {
                        JSONArray temp = object.getJSONArray("FleetsPers");
                        JSONObject temp1 = temp.getJSONObject(0);
                        JSONObject temp2 = temp.getJSONObject(1);
                        fleet = temp1.getString("Fleet") + "_" + temp2.getString("Fleet");
                    } catch (JSONException e) {                    }
                }

                String insert = "INSERT INTO employees (ID, GENDER, FLEET) VALUES(" + id + ", '" + gender + "', '" + fleet + "') ON CONFLICT DO NOTHING";

                // writing data in a database
                statement.executeUpdate(insert);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
