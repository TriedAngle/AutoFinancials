package net.strobl.data.json;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONManager {
    private static String JSON_LOCATION = "src/net/strobl/data/json/UserData.json";

    public static String[] readCredentials(){
        String[] credentials = new String[4];
        try {
            String content = new String((Files.readAllBytes(Paths.get(JSON_LOCATION))));
            JSONObject jsonObject = new JSONObject(content);
            credentials[0] = jsonObject.getString("url");
            credentials[1] = jsonObject.getString("table");
            credentials[2] = jsonObject.getString("username");
            credentials[3] = jsonObject.getString("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return credentials;
    }

    public static void writeCredentials(String url, String table, String username, String password){
        try {
            String content = new String((Files.readAllBytes(Paths.get(JSON_LOCATION))));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", url);
            jsonObject.put("table", table);
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            try (FileWriter file = new FileWriter("src/net/strobl/data/json/UserData.json")) {
                file.write(jsonObject.toString());
                System.out.println("Successfully Copied JSON Object to File...");
                System.out.println("\nJSON Object: " + jsonObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
