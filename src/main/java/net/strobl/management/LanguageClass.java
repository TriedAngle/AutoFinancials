package net.strobl.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageClass {

    //langNum = 0 -> English
    //langNum = 1 -> German
    //langNum = 2 -> French
    //langNum = 3 -> Korean
    private int langNum = 0;
    private List<String> wordList;
    private Map<String, String> mappedWords;
    private Map<String, Integer> mappedLanguages;


    public LanguageClass(int langNum) {
        setLanguage(langNum);
    }

    public void setLanguage(int langNum) {
        this.langNum = langNum;
        readLanguage();
        mapLists();
    }

    public void readLanguage() {
        wordList = new ArrayList<>();
        String lang;
        switch (langNum) {
            case 0:
                //English
                lang = Manager.getLanguages()[0];
                wordList = Manager.getJSON().readLanguage(lang);
                break;
            case 1:
                //German
                lang = Manager.getLanguages()[1];
                wordList = Manager.getJSON().readLanguage(lang);
                break;
            case 2:
                //French
                lang = Manager.getLanguages()[2];
                wordList = Manager.getJSON().readLanguage(lang);
                break;
            case 3:
                //Korean
                lang = Manager.getLanguages()[3];
                wordList = Manager.getJSON().readLanguage(lang);
                break;
        }
    }

    private void mapLists() {
        mappedWords = new HashMap<>();
        mappedLanguages = new HashMap<>();
        for (int i = 0; i < Manager.getKeys().length; i++){
            mappedWords.put(Manager.getKeys()[i], wordList.get(i));
        }
        for(int i = 0; i < Manager.getLanguages().length; i++){
            mappedLanguages.put(Manager.getLanguages()[i], i);
        }
    }

    public String getTranslation(String key) {
        return mappedWords.get(key);
    }

    public int getLangNum(String language){
        return mappedLanguages.get(language);
    }

}
