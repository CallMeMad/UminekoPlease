package com.example.uminekoplease.json;

import java.util.ArrayList;

public class PageJson {
    String page;
    String bgm;
    ArrayList<String> se;
    Boolean voice;

    public PageJson( String page,  String bgm,  ArrayList<String> se,  Boolean voice)
    {
        this.page=page;
        this.bgm=bgm;
        this.se=se;
        this.voice=voice;
    }

    public String getBgmPath() {
        return bgm;
    }

    public String getPagePath() {
        return page;
    }
    public int getNumberSE(){
        if(se!=null)
        {
            return se.size();
        }
        return 0;}

    public ArrayList<String> getSePath() {
        return se;
    }

    public Boolean getVoicePath() {
        return voice;
    }

    public String getVoicePage()
    {
        if(voice){return page;}
        else return null;
    }
}

