package com.example.uminekoplease;

import android.content.Intent;

public class Chapter {
    //Mes attributs
    private String name;
    private String date;
    private Intent intent;
    //Constructeur
    public Chapter(String name,String date, Intent intent)
    {
        this.name=name;
        this.date=date;
        this.intent=intent;
    }

    //Méthodes
    public String getName()
    {
        return  name;
    }

    public String getDate()
    {
        return date;
    }

    public Intent getIntent(){return intent;}
}