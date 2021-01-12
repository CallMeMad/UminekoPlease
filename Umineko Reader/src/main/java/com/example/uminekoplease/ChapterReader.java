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

        //Initialize my Chapter
        ArrayList<PageJson> myChapter = new ArrayList<>();
        Volume =getIntent().getStringExtra("Volume");
        Chapter=getIntent().getStringExtra("ChapterName");
        Episode =getIntent().getStringExtra("ep");
        String path ="img/ep-"+ Episode +"/vol-"+Volume+"/ch-"+Chapter+"/";
        String ChapterName = "Chapter "+Chapter;
        //Get the .json
        JSONResourceReader jsonReader = new JSONResourceReader(getResources(), getResources().getIdentifier("ep"+ Episode,"raw",getPackageName()));
        EpisodeJson jsonObj = jsonReader.constructUsingGson(EpisodeJson.class);
        String title= jsonObj.getTitle();
        String art=jsonObj.getArt();
        String Volume = jsonObj.getChapterVolume(Chapter);
        myChapter=jsonObj.getPages(Chapter);
        Log.i("title",title);
        Log.i("Art",art);
        Log.i("Volume", String.valueOf(Volume));
        Log.i("chapter",String.valueOf(myChapter.size()));
        setContentView(R.layout.umineko_episode01_chapter01);

        //Change the appbar Text
        TextView textView = (TextView) findViewById(R.id.Titre);
        textView.setText(ChapterName);

        //assign Variable
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        boolean startingPoint = getIntent().getBooleanExtra("start", true);

        //Set Service
        music= new Intent(this,SoundService.class);

        //add the page and the ID in array list
        myChapter.add(new PageJson("cover2","",null,false));
        myChapter.add(0,new PageJson("cover","",null,false));

        //Choose if we start at the end or not
        int start =0;
        Log.i("STARTING POINT", String.valueOf(startingPoint));
        //If there is a next
        if(jsonObj.getNextChapter(Chapter)!=null)
        {
            myChapter.add(new PageJson("next","",null,false));
            NextChapter=jsonObj.getNextChapter(Chapter);
        }
        //If there is a prev
        if(jsonObj.getPrevChapter(Chapter)!=null)
        {
            Log.i("PrevChapter",jsonObj.getPrevChapter(Chapter));
            myChapter.add(0,new PageJson("prev","",null,false));
            PrevChapter=jsonObj.getPrevChapter(Chapter);
        }


        //prepare view pager
        prepareViewPager(viewPager, myChapter,path);

        //setup with view pager
        tabLayout.setupWithViewPager(viewPager);

        //If there is a next
        if(jsonObj.getNextChapter(Chapter)!=null)
        {
            //Set the starting point
            if (startingPoint) {
                start=myChapter.size()-1;
                if(jsonObj.getPrevChapter(Chapter)!=null){start-=1;}
                tabLayout.selectTab(tabLayout.getTabAt(start));}
            else{
                start=1;
                Log.i("Start2", String.valueOf(start));
                tabLayout.selectTab(tabLayout.getTabAt(start));}
        }
        else
        {
            if(startingPoint){
                start=myChapter.size()-2;
                tabLayout.selectTab(tabLayout.getTabAt(start));}
            else{tabLayout.selectTab(tabLayout.getTabAt(0));}
        }

        listener(myChapter,start);
        //Return Button
        ImageView imageView = findViewById(R.id.retour);
        imageView.setOnClickListener(v -> {
            stopService(music);
            finish();
        });
    }
    private void listener(ArrayList<PageJson> myPages,int startingpoint) {
        //Play music depending on the page
        //Check the page we're at
        Collections.reverse(myPages);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            PageJson current = myPages.get(startingpoint);
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int numTab = tab.getPosition();
                Log.i("NUMTAB", String.valueOf(numTab));
                Log.i("PAGEPATH",myPages.get(numTab).getPagePath());
                        if (myPages.get(numTab).getPagePath().equals("next")) {
                            Log.i("Volume",Volume);
                            Log.i("ChapterName",NextChapter);
                                Intent intent =new Intent(getApplicationContext(), ChapterReader.class).putExtra("start",true).putExtra("ep",getIntent().getStringExtra("ep"))
                                        .putExtra("Volume",Volume).putExtra("ChapterName",NextChapter);
                                startActivity(intent);
                                finish();
                        } else if (myPages.get(numTab).getPagePath().equals("prev")) {
                            Log.i("Volume",Volume);
                            Log.i("ChapterName",PrevChapter);
                                Intent intent =new Intent(getApplicationContext(), ChapterReader.class).putExtra("start",false).putExtra("ep",getIntent().getStringExtra("ep"))
                                        .putExtra("Volume",Volume).putExtra("ChapterName",PrevChapter);
                                startActivity(intent);
                                finish();
                            }
                         else {
                             //If the music is not the same as the one before
                            setAll(myPages.get(numTab).getBgmPath(),myPages.get(numTab).getSePath(),myPages.get(numTab).getPagePath());
                        }
                current = myPages.get(numTab);
            }
        });
    }
    private void setAll(String ID,ArrayList<String>se,String voice)
    {
        setMusic(ID);
        setSE(se);
        setVoice(voice);
        startService(music);
    }
    private void setMusic(String ID)
    {
            ID = "audio/bgm/umib_" + ID + ".ogg";
            //if the previous music has no music
            if (music.getStringExtra("ID") == null) {
                music.removeExtra("ID");
                music.putExtra("ID", ID);
                Log.i("MUSICSET",ID);
            }
            //If the previous music has music
            else {
                //We compare the two music
                if (!music.getStringExtra("ID").equals(ID)) {
                    music.removeExtra("ID");
                    music.putExtra("ID", ID);
                    music.putExtra("bgm",true);
                    Log.i("MUSIC START",ID);
                }
                else
                {
                    music.putExtra("bgm",false);
                }
            }
    }
    private void setSE(ArrayList<String>ID)
    {
        for(int i=0;i<ID.size();i++)
        {
            ID.set(i,"audio/se/umilse_" + ID.get(i) + ".ogg");
             if(i==0)
            {
                if(music.getStringExtra("se1")==null){
                    if(ID.get(i)!=null)
                    {
                        music.removeExtra("se1");
                        music.putExtra("se1",ID.get(i));
                    }
                }
                else if(ID.get(i)!=null)
                {
                    //Stop Service, load another music then Start Service Again
                    if(!music.getStringExtra("se1").equals(ID.get(i)))
                    {
                        music.removeExtra("se1");
                        music.putExtra("se1",ID);
                    }
                }
            }
            else
            {
                if(music.getStringExtra("se2")==null){
                    if(ID.get(i)!=null)
                    {
                        music.removeExtra("se2");
                        music.putExtra("se2",ID.get(i));
                    }
                }
                else if(ID.get(i)!=null)
                {
                    //Stop Service, load another music then Start Service Again
                    if(!music.getStringExtra("se2").equals(ID.get(i)))
                    {
                        music.removeExtra("se2");
                        music.putExtra("se2",ID);
                    }
                }
            }
        }
    }
    private void setVoice(String ID)
    {
        ID = "voice/ep-"+Episode+"/ch-"+Chapter+"/"+ID+".ogg";
        //Stop Service, load another music then Start Service Again
        music.removeExtra("voice");
        music.putExtra("voice",ID);
        Log.i("VOICE START",ID);
    }
    private void prepareViewPager(ViewPager viewPager,ArrayList<PageJson> myPages,String path) {
        //Initialize main adapter
        ChapterReader.MainAdapter adapter=new ChapterReader.MainAdapter(getSupportFragmentManager());
        //Initialize main Fragment
        ChapterFragment fragment = new ChapterFragment();
        //Use for loop
        for(int i=myPages.size()-1; i>=0;i--)
        {
            //Initialize bundle
            Bundle bundle = new Bundle();
            String goodPath = path+myPages.get(i).getPagePath()+".jpg";
            //Put string
            bundle.putString("title",myPages.get(i).getPagePath());
            //put String Path
            bundle.putString("page",goodPath);
            //set Argument
            fragment.setArguments(bundle);
            //add fragment

            adapter.addFragment(fragment,myPages.get(i).getPagePath(),goodPath);
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
