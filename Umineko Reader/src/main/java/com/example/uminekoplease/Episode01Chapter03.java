package com.example.uminekoplease;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Episode01Chapter03 extends AppCompatActivity  {

    //Initialize variable
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView imageView;
    private Intent music;
    private boolean start;
    //private SensorService sensorService;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umineko_episode01_chapter01);

        //Change the appbar Text
        TextView textView = (TextView) findViewById(R.id.Titre);
        String chapterText = getIntent().getStringExtra("ChapterName");
        textView.setText(chapterText);

        //assign Variable
        this.tabLayout = findViewById(R.id.tab_layout);
        this.viewPager = findViewById(R.id.view_pager);
        this.start= getIntent().getBooleanExtra("start",true);
        this.music= new Intent(getApplicationContext(),SoundService.class);

        //Initialize the List
        ArrayList<String> arrayList = new ArrayList<>();

        //Initialize the list
        ArrayList<Integer> arrayRessource = new ArrayList<Integer>();

                      //Link to chapter 1
                int initId1 = getResources().getIdentifier("R.drawable.next","drawable",getPackageName());
                if(!getIntent().getStringExtra("Prevclass").equals("false"))
                {
                    arrayRessource.add(initId1);
                    //Add le fragment qui nous permettra de passer au chapitre suivant
                    arrayList.add("Previous Chapter");
                }
                //Number of Page
                int number = getIntent().getIntExtra("number",0);

                //add the page and the ID in array list
                for(int i=1;i<=number;i++)
                {
//          Create the string to find the page
                    String page;
                    if(i<10)
                    {
                        page ="0"+(i);
                    }
                    else
                    {
                        page=""+(i);
                    }
                    arrayList.add(page + "/"+number);
                    String name = getIntent().getStringExtra("IDName");
                    int drawableId = getResources().getIdentifier(name+page,"drawable",getPackageName());
                    arrayRessource.add(drawableId);
                }
                //Page suivant
                int initId = getResources().getIdentifier("R.drawable.next","drawable",getPackageName());
                arrayRessource.add(initId);
                //Add le fragment qui nous permettra de passer au chapitre suivant
                arrayList.add("Next Chapter");

                //prepare view pager
                prepareViewPager(viewPager, arrayList, arrayRessource);
                //setup with view pager
                tabLayout.setupWithViewPager(viewPager);
                if (start==true)
                {
                    tabLayout.selectTab(tabLayout.getTabAt(number));
                }
                else
                {
                    tabLayout.selectTab(tabLayout.getTabAt(1));
                }
                //Bouton retour de la toolbar
                imageView=(ImageView)findViewById(R.id.retour);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stopService(music);
                        finish();
                    }
                });
    }

    public void onStart() {

        super.onStart();
        initMusic(getIntent().getIntegerArrayListExtra("musicName").get(1));
        listener();
    }
    private void listener()
    {
        //Joue de la musique en fonction de la page
        //int startID=getIntent().getIntegerArrayListExtra("musicName").get(1);
        ArrayList<Integer> temp = getIntent().getIntegerArrayListExtra("ArrayCase");
        ArrayList<Integer> ID = getIntent().getIntegerArrayListExtra("musicName");
        //Log.d("SHOW ME MY ID YOU BASTARD STP I NEED IT :", String.valueOf(ID.get(2)));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                Log.d("HELLO I AM HERE",String.valueOf(ID.get(1)));
                int numTab = tab.getPosition();
                //For all case where I want to play Music
                for(int i=0;i<getIntent().getIntExtra("musicNumber",0);i++)
                {
                    //Temp is my Array of case
                    //ArrayList<Integer> temp = getIntent().getIntegerArrayListExtra("ArrayCase");
                    //If the case is good
                    if(numTab==temp.get(i))
                    {
                        //We get the ID
                        //int ID = getIntent().getIntegerArrayListExtra("musicName").get(i);
                        //ID = 1 means next chapter ID = 2 means prev chapter ID=3 means stop music
                        if(ID.get(i)==1)
                        {
                            try {
                                Intent intent = new Intent(getApplicationContext(),Class.forName(getIntent().getStringExtra("Nextclass")));
                                startActivity(intent);
                                finish();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        else if(ID.get(i)==2)
                        {
                            try {
                                Intent intent = new Intent(getApplicationContext(),Class.forName(getIntent().getStringExtra("Prevclass")));
                                intent.putExtra("start",false);
                                startActivity(intent);
                                finish();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            setMusic(music,ID.get(i));
//                            mHtHandler.sendEmptyMessageDelayed(ID,300);
                        }
                    }
                }
            }
        });
    }
    private void initMusic(int ID)
    {
        Log.d("SHOW ME MY ID YOU BASTARD STP I NEED IT :", String.valueOf(ID));
        if(ID!=3)
        {
            Log.d("I PLAY START MUSIC :", String.valueOf(ID));
            setMusic(music,ID);
        }
    }
    private void setMusic(Intent music,int ID)
    {
            if(music.getIntExtra("ID",0)!=ID)
            {
                stopService(music);
                music.removeExtra("ID");
                music.putExtra("ID",ID);
                startService(music);
                Log.d("MUSIC SET :", String.valueOf(ID));
            }
    }
    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList,ArrayList<Integer> arrayRessource) {
        //Initialize main adapter
        Episode01Chapter03.MainAdapter adapter=new Episode01Chapter03.MainAdapter(getSupportFragmentManager());
        //Initialize main Fragment
        ChapterFragment fragment = new ChapterFragment();
        //Use for loop
        for(int i=arrayRessource.size()-1; i>=0;i--)
        {
            //Initialize bundle
            Bundle bundle = new Bundle();
            //Put string
            bundle.putString("title",arrayList.get(i));
            //put int
            bundle.putInt("page",arrayRessource.get(i));
            //set Argument
            fragment.setArguments(bundle);
            //add fragment
            adapter.addFragment(fragment,arrayList.get(i),arrayRessource.get(i));
            //Define new Fragment
            fragment = new ChapterFragment();
        }
        //Set adapter
        viewPager.setAdapter(adapter);
    }

    protected void onDestroy() {

        super.onDestroy();
        stopService(music);
        finish();
    }

    private class MainAdapter extends FragmentPagerAdapter {
        //Initialize Array List
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        ArrayList<Integer> arrayRessource = new ArrayList<>();
        //Create constructor
        public void addFragment(Fragment fragment,String title,int ressource)
        {
            //Add to our Array
            arrayList.add(title);
            arrayRessource.add(ressource);
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