package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private boolean start;
    private int number;
    private Intent music;

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
        music= new Intent(this,SoundService.class);

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
        //Start Page
        mArrayPage.add(R.drawable.cover_episode1);
        mArrayList.add("StartPage");
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
        mArrayPage.add(R.drawable.endpage);
        mArrayList.add("EndPage");
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

            //If there is a next
            if(!getIntent().getStringExtra("Nextclass").equals("false"))
            {
                if (start) { tabLayout.selectTab(tabLayout.getTabAt(number+2));}
                else{tabLayout.selectTab(tabLayout.getTabAt(1));}
            }
            else
            {
                if(start){tabLayout.selectTab(tabLayout.getTabAt(number+1));}
                else{tabLayout.selectTab(tabLayout.getTabAt(0));}
            }

        //Return Button
        ImageView imageView = (ImageView) findViewById(R.id.retour);
        listener(music);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(music);
                finish();
            }
        });
    }

    private void listener(Intent music) {
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
                //For all case where I want to play Music
                for (int i = 0; i < getIntent().getIntExtra("musicNumber", 0); i++) {
                    //Temp is my Array of case
                    //ArrayList<Integer> temp = getIntent().getIntegerArrayListExtra("ArrayCase");
                    //If the case is good
                    if (numTab == temp.get(i)) {
                        //We get the ID
                        //int ID = getIntent().getIntegerArrayListExtra("musicName").get(i);
                        //ID = 1 means next chapter ID = 2 means prev chapter ID=3 means stop music
                        if (ID.get(i) == 1) {
                            try {
                                stopService(music);
                                Intent intent = new Intent(getApplicationContext(), Class.forName(getIntent().getStringExtra("Nextclass")));
                                startActivity(intent);
                                finish();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else if (ID.get(i) == 2) {
                            try {
                                stopService(music);
                                Intent intent = new Intent(getApplicationContext(), Class.forName(getIntent().getStringExtra("Prevclass")));
                                intent.putExtra("start", false);
                                startActivity(intent);
                                finish();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("MUSIC SET NUMBER", String.valueOf(i));
                            setMusic(music,ID.get(i));
//                            mHtHandler.sendEmptyMessageDelayed(ID,300);
                        }
                    }
                }
            }
        });
    }
    private void setMusic(Intent music,int ID)
    {
        //If my music is not the same I'm playing
            if(music.getIntExtra("ID",0)!=ID)
            {
                //Stop Service, load another music then Start Service Again
                //stopService(music);
                music.removeExtra("ID");
                music.putExtra("ID",ID);
                Log.i("MUSIC SET ","ALLELUIA");
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
        stopService(music);
        finish();
        super.onDestroy();
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