package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private Intent music;
    private boolean start;
    private int number;
    //On create
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umineko_episode01_chapter01);

        //Change the appbar Text
        TextView textView = (TextView) findViewById(R.id.Titre);
        String chapterText = getIntent().getStringExtra("ChapterName");
        textView.setText(chapterText);

        //assign Variable
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        start= getIntent().getBooleanExtra("start",true);
        music= new Intent(getApplicationContext(),SoundService.class);
        //Number of Page
        number = getIntent().getIntExtra("number", 0);

        //Initialize the List
        ArrayList<String> mArrayList = new ArrayList<>();
        //Initialize the list
        ArrayList<Integer> mArrayPage = new ArrayList<>();

        //Add Previous fragment only if there is a previous fragment
        if (!getIntent().getStringExtra("Prevclass").equals("false")) {
            int initId = getResources().getIdentifier("R.drawable.next", "drawable", getPackageName());
            mArrayPage.add(initId);
            mArrayList.add("Previous Chapter");
        }

        //add the page and the ID in array list
        for (int i = 1; i <= number; i++) {
//          Create the string to find the page
            String page;
            if (i < 10) {
                page = "0" + (i);
            } else {
                page = "" + (i);
            }
            mArrayList.add(page + "/" + number);
            String name = getIntent().getStringExtra("IDName");
            int drawableId = getResources().getIdentifier(name + page, "drawable", getPackageName());
            mArrayPage.add(drawableId);
        }

        //Next Page
        if (!getIntent().getStringExtra("Nextclass").equals("false")) {
            int initId = getResources().getIdentifier("R.drawable.next", "drawable", getPackageName());
            mArrayPage.add(initId);
            mArrayList.add("Next Chapter");
        }

        //prepare view pager
        prepareViewPager(viewPager, mArrayList, mArrayPage);

        //setup with view pager
        tabLayout.setupWithViewPager(viewPager);

        //Choose if we start at the end or not
        if (start) {
            tabLayout.selectTab(tabLayout.getTabAt(number));
            initMusic(getIntent().getIntegerArrayListExtra("musicName").get(0));
        } else {
            tabLayout.selectTab(tabLayout.getTabAt(1));
            int size = getIntent().getIntegerArrayListExtra("musicName").size();
            initMusic(getIntent().getIntegerArrayListExtra("musicName").get(size-1));
        }

        //Return Button
        ImageView imageView = (ImageView) findViewById(R.id.retour);
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
        //Initialise the music when the activity start
        listener();
    }

    private void listener() {
        //Play music depending on the page
        ArrayList<Integer> temp = getIntent().getIntegerArrayListExtra("ArrayCase");
        ArrayList<Integer> ID = getIntent().getIntegerArrayListExtra("musicName");

        //Check the page we're at
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int numTab = tab.getPosition();
                //At the end
                if (numTab == 0) {
                    try {
                        //We stop our service
                        stopService(music);
                        //We link to our next activity
                        Intent intent = new Intent(getApplicationContext(), Class.forName(getIntent().getStringExtra("Nextclass")));
                        startActivity(intent);
                        //We finish the current
                        finish();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                //At the start
                else if (numTab == number+1) {
                    try {
                        //We stop our service
                        stopService(music);
                        //We link to our next activity and add where to start
                        Intent intent = new Intent(getApplicationContext(), Class.forName(getIntent().getStringExtra("Prevclass")));
                        intent.putExtra("start", false);
                        startActivity(intent);
                        //We finish the current
                        finish();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                //For all case where I want to play Music
                for (int i = 0; i < getIntent().getIntExtra("musicNumber", 0); i++) {
                    //If the case is good
                    if (numTab == temp.get(i)) {
                        //We set music depending on the ID send
                        setMusic(music, ID.get(i));
                    }
                }
            }
        });
    }
    private void initMusic(int ID)
    {
        //If there is a start Music
       // Log.d("SHOW ME MY ID :", String.valueOf(ID));
        if(ID!=3)
        {
           // Log.d("I PLAY START MUSIC :", String.valueOf(ID));
            setMusic(music,ID);
        }
    }
    private void setMusic(Intent music,int ID)
    {
        //If my music is not the same I'm playing
            if(music.getIntExtra("ID",0)!=ID)
            {
                //Stop Service, load another music then Start Service Again
                stopService(music);
                music.removeExtra("ID");
                music.putExtra("ID",ID);
                startService(music);
                //Log.d("MUSIC SET :", String.valueOf(ID));
            }
    }
    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList,ArrayList<Integer> mArrayPage) {
        //Initialize main adapter
        Episode01Chapter03.MainAdapter adapter=new Episode01Chapter03.MainAdapter(getSupportFragmentManager());
        //Initialize main Fragment
        ChapterFragment fragment = new ChapterFragment();
        //Use for loop
        for(int i=mArrayPage.size()-1; i>=0;i--)
        {
            //Initialize bundle
            Bundle bundle = new Bundle();
            //Put string
            bundle.putString("title",arrayList.get(i));
            //put int
            bundle.putInt("page",mArrayPage.get(i));
            //set Argument
            fragment.setArguments(bundle);
            //add fragment
            adapter.addFragment(fragment,arrayList.get(i),mArrayPage.get(i));
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
        ArrayList<Integer> mArrayPage = new ArrayList<>();
        //Create constructor
        public void addFragment(Fragment fragment,String title,int page)
        {
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