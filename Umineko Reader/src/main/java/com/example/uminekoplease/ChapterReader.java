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
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.uminekoplease.json.EpisodeJson;
import com.example.uminekoplease.json.JSONResourceReader;
import com.example.uminekoplease.json.PageJson;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChapterReader extends AppCompatActivity {

    //Initialize variable
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String Chapter;
    private String NextChapter;
    private String PrevChapter;
    private String Volume;
    private String Episode;
    private Intent music;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingSoundService();
        loadingViewpager();
        //Return Button
        ImageView imageView = findViewById(R.id.retour);
        imageView.setOnClickListener(v -> {
            stopService(music);
            finish();
        });
    }

    private void loadingSoundService()
    {
        //Set Service
        music = new Intent(this, SoundService.class);
    }
    private void loadingViewpager(){
        //Initialize my Chapter
        ArrayList<PageJson> myChapter = new ArrayList<>();
        Volume = getIntent().getStringExtra("Volume");
        Chapter = getIntent().getStringExtra("ChapterName");
        Episode = getIntent().getStringExtra("ep");
        String path = "img/ep-" + Episode + "/vol-" + Volume + "/ch-" + Chapter + "/";
        String ChapterName = "Chapter " + Chapter;
        //Get the .json
        JSONResourceReader jsonReader = new JSONResourceReader(getResources(), getResources().getIdentifier("ep" + Episode, "raw", getPackageName()));
        EpisodeJson jsonObj = jsonReader.constructUsingGson(EpisodeJson.class);
        myChapter = jsonObj.getPages(Chapter);
        setContentView(R.layout.umineko_episode01_chapter01);

        //Change the appbar Text
        TextView textView = findViewById(R.id.Titre);
        textView.setText(ChapterName);
        //assign Variable
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        boolean startingPoint = getIntent().getBooleanExtra("start", true);
        //Choose if we start at the end or not
        int start = 0;
        Log.i("STARTING POINT", String.valueOf(startingPoint));
        //If there is a next
        if (jsonObj.getNextChapter(Chapter) != null) {
            myChapter.add(new PageJson("cover", "", null, false));
            NextChapter = jsonObj.getNextChapter(Chapter);
        }
        //If there is a prev
        if (jsonObj.getPrevChapter(Chapter) != null) {
            Log.i("PrevChapter", jsonObj.getPrevChapter(Chapter));
            myChapter.add(0, new PageJson("cover", "",null, false));
            PrevChapter = jsonObj.getPrevChapter(Chapter);
        }
        //prepare view pager
        prepareViewPager(viewPager, myChapter, path);
        //setup with view pager
        tabLayout.setupWithViewPager(viewPager);

        //Set Up the StartingPoint
        if (jsonObj.getNextChapter(Chapter) != null) {
            //Set the starting point
            if (startingPoint) {
                start = myChapter.size() - 1;
                if (jsonObj.getPrevChapter(Chapter) != null) {
                    start -= 1;
                }
                tabLayout.selectTab(tabLayout.getTabAt(start));
            } else {
                start = 1;
                Log.i("Start2", String.valueOf(start));
                tabLayout.selectTab(tabLayout.getTabAt(start));
            }
        } else {
            if (startingPoint) {
                start = myChapter.size() - 2;
                tabLayout.selectTab(tabLayout.getTabAt(start));
            } else {tabLayout.selectTab(tabLayout.getTabAt(0));}
        }
        listener(myChapter, start);
    }
    private void listener(ArrayList<PageJson> myPages, int startingpoint) {
        //Play music depending on the page
        //Check the page we're at
        Collections.reverse(myPages);
        boolean toggle=false;
        setAll(myPages.get(startingpoint).getBgmPath(), myPages.get(startingpoint).getSePath(),myPages.get(startingpoint).getVoicePath(),myPages.get(startingpoint).getPagePath());
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            PageJson current = myPages.get(startingpoint);
            String NextChapterTobeDisplayed="";
            boolean StartToNextChapter=true;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int numTab = tab.getPosition();
                Log.i("NUMTAB", String.valueOf(numTab));
                Log.i("SIZE", String.valueOf(myPages.size()));
                if(numTab==0){NextChapterTobeDisplayed=NextChapter;}
                else if(numTab==myPages.size()-1){NextChapterTobeDisplayed=PrevChapter;StartToNextChapter=false;}
                if (myPages.get(numTab).getPagePath().equals("cover")) {
                    Log.i("Volume", Volume);
                    Log.i("ChapterName", NextChapterTobeDisplayed);
                    getIntent().removeExtra("start");
                    getIntent().removeExtra("ep");
                    getIntent().removeExtra("Volume");
                    getIntent().removeExtra("ChapterName");
                    getIntent().putExtra("start", StartToNextChapter).putExtra("ep", Episode)
                            .putExtra("Volume", Volume).putExtra("ChapterName", NextChapterTobeDisplayed);
                    loadingViewpager();
                }
                else {
                    //If the music is not the same as the one before
                    Log.i("ISVOICEFALSE", String.valueOf(myPages.get(numTab).getVoicePath()));
                    setAll(myPages.get(numTab).getBgmPath(), myPages.get(numTab).getSePath(),myPages.get(numTab).getVoicePath(),myPages.get(numTab).getPagePath());
                }
                current = myPages.get(numTab);
            }
        });
    }

    private void setAll(String bgm, ArrayList<String> se,Boolean voice,String Currentpage) {
        if (voice) {
            setVoice(Currentpage);
        } else {
            setVoice("null");
        }
        setMusic(bgm);
        setSE(se);
        startService(music);
    }

    private void setMusic(String ID) {
        //If the song is null
        if (ID == null) {
            //We reset the song that was playing
            music.removeExtra("ID");
            music.putExtra("ID", "null");
            music.putExtra("bgm", 1);
        }
        //If the song isn't null
        else {
            ID = "audio/bgm/umib_" + ID + ".ogg";
            //if the previous was null we reset and set it
            if (music.getStringExtra("ID") == null) {
                music.putExtra("ID", ID);
                Log.i("MUSICSET", ID);
                music.putExtra("bgm", 2);
            }
            //If the previous music was not
            else {
                //if the song isn't the same we reset and set it
                if (!music.getStringExtra("ID").equals(ID)) {
                    music.removeExtra("ID");
                    music.putExtra("ID", ID);
                    music.removeExtra("bgm");
                    music.putExtra("bgm", 2);
                    Log.i("MUSIC START", ID);
                }
                //else the song is the same we do nothing
                else {
                    music.removeExtra("bgm");
                    music.putExtra("bgm", 0);
                }
            }
        }
    }

    private void setSE(ArrayList<String> ID) {
        //For our 2 Sound Effect
        if(ID!=null) {
            for (int i = 0; i < ID.size(); i++) {
                //If the ID is null we reset and don't set the next one
                if (ID.get(i) == null) {
                    music.removeExtra("se"+ (i + 1));
                    music.putExtra("se"+ (i + 1), (String) null);
                    music.removeExtra("se_"+ (i + 1));
                    music.putExtra("se_"+ (i + 1), 1);
                } else {
                    String path = "audio/se/umilse_" + ID.get(i) + ".ogg";
                    //If the prev is null we reset and set the new one
                    if (music.getStringExtra("se" + (i + 1)) == null) {
                        //We set variable
                        music.removeExtra("se" + (i + 1));
                        music.putExtra("se" + (i + 1), path);
                        music.removeExtra("se_" + (i + 1));
                        music.putExtra("se_" + (i + 1), 2);
                    }
                    //if the song are the same as the first one
                    else if (music.getStringExtra("se1").equals(ID.get(i))) {
                        music.removeExtra("se_1");
                        music.putExtra("se_1", 0);
                    }
                    //if the se is the same as the second one
                    else if( music.getStringExtra("se"+ID.size()).equals(ID.get(i)))
                    {
                        music.removeExtra("se_2");
                        music.putExtra("se_2", 0);
                    }
                    //if they are not we reset and set our
                    else {
                        music.removeExtra("se" + (i + 1));
                        music.putExtra("se" + (i + 1), path);
                        music.removeExtra("se_" + (i + 1));
                        music.putExtra("se_" + (i + 1), 2);
                    }
                }
            }
        }
        else{
            music.removeExtra("se_1");
            music.removeExtra("se1");
            music.removeExtra("se2");
            music.removeExtra("se_2");
            music.putExtra("se_1", 1);
            music.putExtra("se_2", 1);
        }
    }

    private void setVoice(String ID) {
        //If it is true we reset and set
        if (!ID.equals("null")) {
            ID = "voice/ep-" + Episode + "/ch-" + Chapter + "/" + ID + ".ogg";
            //Stop Service, load another music then Start Service Again
            music.removeExtra("voice");
            music.putExtra("voice", ID);
            music.removeExtra("voices");
            music.putExtra("voices", 2);
        } else {
            music.removeExtra("voice");
            music.putExtra("voice", ID);
            music.removeExtra("voices");
            music.putExtra("voices", 1);
        }
    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<PageJson> myPages, String path) {
        //Initialize main adapter
        ChapterReader.MainAdapter adapter = new ChapterReader.MainAdapter(getSupportFragmentManager());
        //Initialize main Fragment
        ChapterFragment fragment = new ChapterFragment();
        //Use for loop
        for (int i = myPages.size() - 1; i >= 0; i--) {
            //Initialize bundle
            Bundle bundle = new Bundle();
            String goodPath = path + myPages.get(i).getPagePath() + ".jpg";
            //Put string
            bundle.putString("title", myPages.get(i).getPagePath());
            //put String Path
            bundle.putString("page", goodPath);
            //set Argument
            fragment.setArguments(bundle);
            //add fragment

            adapter.addFragment(fragment, myPages.get(i).getPagePath(), goodPath);
            //Define new Fragment
            fragment = new ChapterFragment();
        }
        //Set adapter
        viewPager.setAdapter(adapter);
    }

    protected void onDestroy() {
        stopService(music);
        finish();
        super.onDestroy();
    }

    private class MainAdapter extends FragmentPagerAdapter {
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
