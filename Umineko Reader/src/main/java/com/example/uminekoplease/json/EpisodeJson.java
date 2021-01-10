package com.example.uminekoplease.json;

import java.util.ArrayList;
import java.util.HashMap;

public class EpisodeJson {
    String title;
    String art;
    HashMap<String, ArrayList<Integer>> volumes;
    HashMap<String, ArrayList<PageJson>> chapters;

    public ArrayList<PageJson> getPages(String chapter) {
        return this.chapters.get(chapter);
    }

    // TODO
    // String getTitle()
    // String getArt()
    // String|null getPrevChapter(String chapter); // null if there is no prev chapter, we need to go back to prev ep in this case
    // String|null getNextChapter(String chapter); // null if there is no next chapter, we need to move on to next ep in this case
    // getChapterVolume(String chapter) // Find the volume of a given chapter number
}
