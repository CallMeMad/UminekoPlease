package com.example.uminekoplease;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.uminekoplease.json.EpisodeJson;
import com.example.uminekoplease.json.JSONResourceReader;
import com.example.uminekoplease.json.PageJson;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ChapterReader extends AppCompatActivity {

    //Initialize variable
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChapterReader.MainAdapter adapter;
    private String Chapter;
    private String Episode;
    private Intent music;
    private boolean startingPoint;
    private PageJson current;
    private HashMap<Integer, String> MediaPlayerState;
    private int start;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize my Chapter
        Chapter = getIntent().getStringExtra("ChapterName");
        Episode = getIntent().getStringExtra("ep");
        startingPoint = getIntent().getBooleanExtra("start", true);
        MediaPlayerState = new HashMap<>();
        adapter = new ChapterReader.MainAdapter(getSupportFragmentManager());
        music = new Intent(this, SoundService.class);
        current=new PageJson(null,null,null,false);
        loadingViewpager(true);
    }

    private void loadingViewpager(boolean first) {

        //Set Variable
        String path = "img/ep-" + Episode +"/ch-" + Chapter + "/";
        JSONResourceReader jsonReader = new JSONResourceReader(getResources(), getResources().getIdentifier("ep" + Episode, "raw", getPackageName()));
        EpisodeJson jsonObj = jsonReader.constructUsingGson(EpisodeJson.class);
        if (jsonObj.getNextChapter(Chapter) != null) {
            PageJson info = jsonObj.getFirstPageNextChapter(Chapter);
            jsonObj.getChapter(Chapter).add(new PageJson("cover", info.getBgmPath(), info.getSePath(), false));
        }
        if (jsonObj.getPrevChapter(Chapter) != null) {
            PageJson info = jsonObj.getLastPagePrevChapter(Chapter);
            jsonObj.getChapter(Chapter).add(0, new PageJson("cover",info.getBgmPath(),info.getSePath(),false));
        }

        //Change the appbar Text
        setContentView(R.layout.umineko_episode01_chapter01);
        TextView textView = findViewById(R.id.Titre);
        textView.setText(String.format("%s%s", getString(R.string.Chapter), Chapter));
        if (!first) {
            viewPager.removeAllViews();
        }
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        //prepare view pager
        prepareViewPager(viewPager, jsonObj, path);
        //setup with view pager
        tabLayout.setupWithViewPager(viewPager);
        if (!first) {
            viewPager.removeAllViews();
        }
        tabLayout.selectTab(tabLayout.getTabAt(start));
        listener(jsonObj);
        //Return Button
        ImageView imageView = findViewById(R.id.retour);
        imageView.setOnClickListener(v -> {
            stopService(music);
            finish();
        });
    }

    private void listener(EpisodeJson jsonObj) {
        //Play music depending on the page
        //Check the page we're at
        ArrayList<PageJson> myPages = jsonObj.getChapter(Chapter);
        Collections.reverse(myPages);
        //Set the Sound at the start
        compareAll(current,myPages.get(start));
        current = myPages.get(start);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab) {
                String NextChapterTobeDisplayed;
                super.onTabSelected(tab);
                int numTab = tab.getPosition();
                if (myPages.get(numTab).getPagePath().equals("cover")) {
                    if (numTab == 0) {
                        NextChapterTobeDisplayed = jsonObj.getNextChapter(Chapter);
                        startingPoint = true;
                    } else {
                        NextChapterTobeDisplayed = jsonObj.getPrevChapter(Chapter);
                        startingPoint = false;
                    }
                    Chapter = NextChapterTobeDisplayed;
                    loadingViewpager(false);
                } else {
                    //If the music is not the same as the one before
                    compareAll(current, myPages.get(numTab));
                }
                current = myPages.get(numTab);
            }
        });
    }

    private void compareMemoryLock(ArrayList<String> New, ArrayList<String> Old, HashMap<String, ArrayList<Integer>> MapSe) {
        ArrayList<Integer> where = new ArrayList<>();
        ArrayList<Integer> where2 = new ArrayList<>();
        //Parse Same and Different into where and where2
        for (int i = 0; i < New.size(); i++) {
            ArrayList<Integer> SeState = new ArrayList<>();
            int watcher = where.size();
            for (int j = 0; j < Old.size(); j++) {
                //Get the point were the two list are the same
                if (New.get(i).equals(Old.get(j))) {
                    for(int k=0;k<MediaPlayerState.size();k++)
                    {
                        if(MediaPlayerState.get(k)!=null)
                        {
                            if(Objects.requireNonNull(MediaPlayerState.get(k)).equals(New.get(i)))
                            {
                                SeState.add(0,k);
                                SeState.add(1, 0);
                                MapSe.put("audio/se/umilse_" + New.get(i) + ".ogg", SeState);
                                where.add(k);
                            }
                        }
                    }
                }
            }
            //if different
            if (where.size()==watcher) {
                where2.add(i);
            }
        }
        int y = 0;
        //Add all the ID that were different
        for (int i = 0; i < New.size(); i++) {
            int x = 0;
            if(where.size()!=0) {
                for (int j = 0; j < where.size(); j++) {
                    if (where.get(x) != i) {
                        x++;
                    }
                    if (x == where.size() && where2.size()!=0) {
                        ArrayList<Integer> SeState = new ArrayList<>();
                        MediaPlayerState.put(i, New.get(where2.get(y)));
                        SeState.add(0, i);
                        SeState.add(1, 2);
                        MapSe.put("audio/se/umilse_" + New.get(where2.get(y)) + ".ogg", SeState);
                        y++;
                    }
                }
            }
            else
            {
                ArrayList<Integer> SeState = new ArrayList<>();
                MediaPlayerState.put(i, New.get(i));
                SeState.add(0, i);
                SeState.add(1, 2);
                MapSe.put("audio/se/umilse_" + New.get(i) + ".ogg", SeState);
            }
        }
        //If the Map is still inferior to Old.size it means, the se left must be reseted
        if(MapSe.size()<Old.size())
        {
            for(int i=0;i<Old.size();i++)
            {
                int x=0;
                for(int j=0;j<where.size();j++)
                 {
                     if(where.get(x) != i)
                     {
                         x++;
                     }
                     if(x == where.size())
                     {
                         ArrayList<Integer> SeState = new ArrayList<>();
                         MediaPlayerState.put(i, null);
                         SeState.add(0, i);
                         SeState.add(1, 1);
                         MapSe.put(String.valueOf(i), SeState);
                     }
                 }
            }
        }
        setSe2(MapSe);
    }

    private void compareBgm(PageJson currentPage, PageJson newPage) {
        //if new = notNull we set for current=null or current=!new
        if (newPage.getBgmPath() != null) {
            if (currentPage.getBgmPath() == null) {
                setMusic2(newPage.getBgmPath(), 2);
            } else if (!currentPage.getBgmPath().equals(newPage.getBgmPath())) {
                setMusic2(newPage.getBgmPath(), 2);
            } else {
                Log.i("MUSIC", "Same");
                setMusic2(newPage.getBgmPath(), 0);
            }
        }
        if (newPage.getBgmPath() == null) {
            setMusic2("reset", 1);
        }
    }

    private void compareVoice(PageJson newPage) {
        //if voice is false reset else set the new one
        if (newPage.getVoicePath()) {
            Log.i("VoiceTrue", String.valueOf(newPage.getVoicePath()));
            setVoice(newPage.getPagePath());
        } else {
            setVoice("null");
        }
    }

    private void compareSE(PageJson currentPage, PageJson newPage) {
        HashMap<String, ArrayList<Integer>> MapSe = new HashMap<>();

        //If new is null we just reset the new one
        if (newPage.getNumberSE() == 0) {
            if (currentPage.getSePath() != null) {
                for (int i = 0; i < MediaPlayerState.size(); i++) {
                    ArrayList<Integer> SeState = new ArrayList<>();
                    MediaPlayerState.put(i, null);
                    SeState.add(0, i);
                    SeState.add(1, 1);
                    SeState.set(1, 1);
                    MapSe.put(String.valueOf(i), SeState);
                }
                setSe2(MapSe);
            }
        } else {
            if (currentPage.getNumberSE() != 0) {
                compareMemoryLock(newPage.getSePath(), currentPage.getSePath(), MapSe);
            } else {
                for (int i = 0; i < newPage.getNumberSE(); i++) {
                    ArrayList<Integer> SeState = new ArrayList<>();
                    String path = "audio/se/umilse_" + newPage.getSePath().get(i) + ".ogg";
                    MediaPlayerState.put(i, newPage.getSePath().get(i));
                    SeState.add(0, i);
                    SeState.add(1, 2);
                    MapSe.put(path, SeState);
                }
                setSe2(MapSe);
            }
        }
    }

    private void compareAll(PageJson currentPage, PageJson newPage) {
        /*Compare BGM*/
        compareBgm(currentPage, newPage);
        /*Compare Voice*/
        compareVoice(newPage);
        /*Compare all Sound Effect*/
        compareSE(currentPage, newPage);
        startService(music);
    }

    private void setMusic2(String bgm, int command) {
        music.removeExtra("ID");
        if (bgm != null) {
            music.putExtra("ID", "audio/bgm/umib_" + bgm + ".ogg");
            music.removeExtra("bgmState");
            if (command == 2) {
                music.putExtra("bgmState", 2);
            } else if (command == 1) {
                music.putExtra("bgmState", 1);
            } else {
                music.putExtra("bgmState", 0);
            }
        }
    }

    private void setSe2(HashMap<String, ArrayList<Integer>> MapSe) {
        if (MapSe != null) {
            music.removeExtra("MapSe");
            music.putExtra("MapSe", MapSe);
        }
    }

    private void setVoice(String ID) {
        //If it is true we reset and set
        if (ID != null) {
            ID = "voice/ep-" + Episode + "/ch-" + Chapter + "/" + ID + ".ogg";
            Log.i("PATH", ID);
            //we Set the new one
            music.removeExtra("voice");
            music.putExtra("voice", ID);
            music.removeExtra("voiceState");
            music.putExtra("voiceState", 2);
        } else {
            music.removeExtra("voice");
            music.removeExtra("voiceState");
            music.putExtra("voiceState", 1);
        }
    }

    private void prepareViewPager(ViewPager viewPager, EpisodeJson jsonObj, String path) {
        //Initialize main adapter
        adapter = new ChapterReader.MainAdapter(getSupportFragmentManager());
        //Initialize main Fragment
        ChapterFragment fragment = new ChapterFragment();
        String goodPath;

        //Use for loop
        for (int i = jsonObj.getChapter(Chapter).size() - 1; i >= 0; i--) {
            //Initialize bundle
            Bundle bundle = new Bundle();
            goodPath = path + jsonObj.getChapter(Chapter).get(i).getPagePath() + ".jpg";
            if(jsonObj.getChapter(Chapter).get(i).getPagePath().equals("cover") && i==0)
            {
                int chapter = Integer.parseInt(Chapter);
                chapter--;
                goodPath="img/ep-" + (Episode) +"/ch-" + chapter + "/" + jsonObj.getLastPagePrevChapter(Chapter).getPagePath() + ".jpg";
            }
            if(jsonObj.getChapter(Chapter).get(i).getPagePath().equals("cover") && i==jsonObj.getChapter(Chapter).size() - 1)
            {
                int chapter = Integer.parseInt(Chapter);
                chapter++;
                goodPath="img/ep-" + (Episode) +"/ch-" + chapter + "/" + jsonObj.getFirstPageNextChapter(Chapter).getPagePath() + ".jpg";
            }
            //Put string
            bundle.putString("title", jsonObj.getChapter(Chapter).get(i).getPagePath());
            //put String Path
            bundle.putString("page", goodPath);
            //set Argument
            fragment.setArguments(bundle);
            //add fragment
            adapter.addFragment(fragment, jsonObj.getChapter(Chapter).get(i).getPagePath(), goodPath);
            //Define new Fragment
            fragment = new ChapterFragment();
        }
        //Set adapter
        viewPager.setAdapter(adapter);
        //Set Up the StartingPoint
        if (jsonObj.getPrevChapter(Chapter) != null) {
            //Set the starting point
            if (startingPoint) {
                start = adapter.getCount() - 2;
            } else {
                if (jsonObj.getNextChapter(Chapter) != null) {
                    start = 1;
                } else start = 0;
            }
        } else {
            if (startingPoint) {
                start = adapter.getCount() - 1;
            } else {
                if (jsonObj.getNextChapter(Chapter) != null) {
                    start = 0;
                }
                start = 1;
            }
        }
    }

    protected void onDestroy() {
        stopService(music);
        finish();
        super.onDestroy();
    }

    private class MainAdapter extends FragmentStatePagerAdapter {
        //Initialize Array List
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        ArrayList<String> mArrayPage = new ArrayList<>();

        //Create constructor
        public void addFragment(Fragment fragment, String title, String page) {
            //Add to our Array
            arrayList.add(title);
            mArrayPage.add(page);
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //Return fragment position
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            //Return fragment list size
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //return array List position
            return arrayList.get(position);
        }
    }
}
