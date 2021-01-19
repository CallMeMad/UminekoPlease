package com.example.uminekoplease.json;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EpisodeJson {
    String title;
    String art;
    HashMap<String, ArrayList<Integer>> volumes;
    HashMap<String, ArrayList<PageJson>> chapters;

    EpisodeJson(String title,String art,  HashMap<String, ArrayList<Integer>> volumes, HashMap<String, ArrayList<PageJson>> chapter)
    {
        this.title=title;
        this.art=art;
        this.volumes=volumes;
        this.chapters=chapter;
    }

    public String getTitle() {
        return title;
    }
    public String getArt() {
        return art;
    }

    public PageJson getFirstPageNextChapter(String Chapter)
    {
        return Objects.requireNonNull(chapters.get(getNextChapter(Chapter))).get(0);
    }
    public PageJson getLastPagePrevChapter(String Chapter)
    {
        return Objects.requireNonNull(chapters.get(getPrevChapter(Chapter))).get(Objects.requireNonNull(chapters.get(getPrevChapter(Chapter))).size()-1);
    }
    public String getPrevChapter(String chapter)
    {
        if(chapter.equals("1")){
            return null;
        }
        else{
            int index =Integer.parseInt(chapter);
            index--;
            return String.valueOf(index);
        }
    }
    public String getNextChapter(String chapter)
    {
        if(chapter.equals(String.valueOf(chapters.size()))){
            return null;
        }
        else{
            int index =Integer.parseInt(chapter);
            index++;
            return String.valueOf(index);
        }
    }
    public ArrayList<PageJson> getChapter(String chapter) {
        return this.chapters.get(chapter);
    }
    public Integer getNumberChapter()
    {
        if(this.chapters!=null)
        {
            return chapters.size();
        }
        else
        {
            return 0;
        }
    }
    public String getChapterVolume(String Chapter)
    {
        String Volume="";
        Log.i("SIZE", String.valueOf(volumes.size()));
            for(int j=0;j<volumes.size();j++)
            {
                for(int i=0;i<volumes.get(String.valueOf(j+1)).size();i++)
                {
                    if(Chapter.equals(String.valueOf(volumes.get(String.valueOf(j+1)).get(i))))
                    {
                        return String.valueOf(j+1);
                    }
                }
            }
        return null;
    }


}
