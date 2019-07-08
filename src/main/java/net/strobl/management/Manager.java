package net.strobl.management;

import net.strobl.data.json.JSONManager;
import net.strobl.processing.BillListProcessor;

import java.io.IOException;
import java.util.Map;

public class Manager {
    private static App app;
    private static DataManager dataManager;
    private static BillListProcessor billListProcessor;
    private static JSONManager jsonManager;
    private static LanguageClass languageClass;
    private static String[] languages = {
            "english",              //1
            "german",               //2
            "french",               //3
            "korean"                //4
    };
    private static String[] keys = {
            "please_connect",       //0
            "connect_now",          //1
            "current_money",        //2
            "last_project",         //3
            "money_over_time",      //4
            "compare_projects",     //5
            "specify_input",        //6
            "hostname",             //7
            "username",             //8
            "password",             //9
            "select_language",      //10
            "test_connection"       //11
    };

    public Manager(App app){
        Manager.app = app;
        dataManager = new DataManager(this);
        billListProcessor = new BillListProcessor();
        jsonManager = new JSONManager();
        languageClass = new LanguageClass(jsonManager.readLanguageNum());
    }

    public static App getApp() {
        return app;
    }
    public static DataManager getDataManager(){
        return dataManager;
    }
    public static BillListProcessor getBillListProcessor(){
        return billListProcessor;
    }
    public static JSONManager getJSON(){
        return jsonManager;
    }
    public static LanguageClass getLang(){
        return languageClass;
    }
    public static String[] getKeys(){
        return keys;
    }
    public static String[] getLanguages() {
        return languages;
    }
}
