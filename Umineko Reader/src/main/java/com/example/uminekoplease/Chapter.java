package com.example.uminekoplease;

import android.content.Intent;

public class Chapter {

    //Chapter attributes
    private String name;
    private String date;
    private Intent intent;

    //Constructor
    public Chapter(String name,String date, Intent intent)
    {
        this.name=name;
        this.date=date;
        this.intent=intent;
    }

    //Methods
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