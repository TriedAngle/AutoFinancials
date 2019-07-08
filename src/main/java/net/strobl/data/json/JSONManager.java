package net.strobl.data.json;

import net.strobl.management.Manager;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONManager {
    private static String USER_DATA_LOCATION = "src/main/java/net/strobl/data/json/UserData.json";
    private static String SETTINGS_LOCATION = "src/main/java/net/strobl/data/json/Settings.json";
    private static String LANGUAGE_FILE_LOCATION = "src/main/java/net/strobl/management/languages/";

    public static Boolean isEmpty(){
        try {
            FileReader fr = new FileReader("src/main/java/net/strobl/data/json/UserData.json");
            BufferedReader br = new BufferedReader(fr);
        } catch (IOException e) {
            return true;
        }
        return false;
    }

    public static String[] readCredentials(){
        String[] credentials = new String[4];
        try {
            String content = new String((Files.readAllBytes(Paths.get(USER_DATA_LOCATION))));
            JSONObject jsonObject = new JSONObject(content);
            credentials[0] = jsonObject.getString("url");
            credentials[1] = jsonObject.getString("username");
            credentials[2] = jsonObject.getString("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return credentials;
    }

    public static void writeCredentials(String url, String username, String password){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", url);
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            try (FileWriter file = new FileWriter(USER_DATA_LOCATION)) {
                file.write(jsonObject.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readLanguageNum(){
        int langNum = 0;
        try {
            String content = new String((Files.readAllBytes(Paths.get(SETTINGS_LOCATION))));
            JSONObject jsonObject = new JSONObject(content);
            langNum = jsonObject.getInt("language");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return langNum;
    }

    public void writeSettings(int languageNumber, boolean darkmode){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("language", languageNumber);
            jsonObject.put("darkmode", darkmode);
            try (FileWriter file = new FileWriter(SETTINGS_LOCATION)) {
                file.write(jsonObject.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readLanguage(String language){
        List<String> lang = new ArrayList<>();
        try {
            String content = new String((Files.readAllBytes(Paths.get(LANGUAGE_FILE_LOCATION + language + ".json"))));
            JSONObject jsonObject = new JSONObject(content);
            for(String key : Manager.getKeys()){
                lang.add(jsonObject.getString(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lang;
    }

}
