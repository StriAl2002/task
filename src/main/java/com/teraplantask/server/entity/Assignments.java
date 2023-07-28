package com.teraplantask.server.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class Assignments {

    public Assignments(Statement statement) throws IOException, SQLException {

        // working with a file after correct connection
        statement.executeUpdate("DROP TABLE IF EXISTS assignments");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS assignments (id_employee INTEGER NOT NULL, id_pairing INTEGER, rank VARCHAR(4));");
        try {
            // reading a file
            String data = new String(Files.readAllBytes(Paths.get("roaster.json")));
            data = data.substring(data.indexOf("\"Assignments\":"));
            data = data.substring(data.indexOf("["));
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);

                int id_employee = object.getInt("EmployeeID");
                int id_pairing = 0;
                try {
                    id_pairing = object.getInt("PairingID");
                }
                catch (JSONException je){}

                String rank = object.getString("Rank");

                String insert = "INSERT INTO assignments (ID_EMPLOYEE, ID_PAIRING, RANK) VALUES(" + id_employee + ", " + id_pairing + ", '" + rank +"') ON CONFLICT DO NOTHING";

                // writing data in a database
                statement.executeUpdate(insert);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
