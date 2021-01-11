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
    private Intent music;
    private Intent se1;
    private Intent se2;
    private Intent voice;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize my Chapter
        ArrayList<PageJson> myChapter = new ArrayList<>();
        String path ="img/ep-"+getIntent().getStringExtra("ep")+"/vol-"+getIntent().getStringExtra("Volume")+"/ch-"+getIntent().getStringExtra("ChapterName")+"/";
        String ChapterName = "Chapter "+getIntent().getStringExtra("ChapterName");
        Volume =getIntent().getStringExtra("Volume");
        Chapter=getIntent().getStringExtra("ChapterName");

        //Get the .json
        String test = "ep"+getIntent().getStringExtra("ep");
        Log.i("TEST",test);
        JSONResourceReader jsonReader = new JSONResourceReader(getResources(), getResources().getIdentifier("ep"+getIntent().getStringExtra("ep"),"raw",getPackageName()));
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
        se2 = new Intent(this,SoundService.class);
        se1 = new Intent(this,SoundService.class);
        voice= new Intent(this,SoundService.class);

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
                           /* if (!(current.getBgmPath().equals(myPages.get(numTab).getBgmPath()))) {
                                //setMusic(myPages.get(numTab).getBgmPath());
                            }*/
                        }
                current = myPages.get(numTab);
            }
        });
    }
    private void setMusic(String ID)
    {
        //If my music is not the same I'm playing
        if(!music.getStringExtra("ID").equals(ID))
        {
            //Stop Service, load another music then Start Service Again
            //stopService(music);
            music.removeExtra("ID");
            music.putExtra("ID",ID);
            if (ID == "stab" || ID == "slaphit")
            {
                music.putExtra("looping",false);
            }
            else
            {
                music.putExtra("looping",true);
            }
            Log.i("MUSIC SET ", String.valueOf(ID));
            startService(music);
        }
    }
    private void stopallService()
    {}
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
