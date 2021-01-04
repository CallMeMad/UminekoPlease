package com.example.uminekoplease;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class ChapterReader extends AppCompatActivity {

    //Initialize variable
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int pageNumber;
    private boolean startingPoint;
    private String IDName;
    private InputStream input=null;
    private MediaListener mMediaListener;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize other Data
        ArrayList<Integer> pageMusicList = new ArrayList<Integer>();
        ArrayList<String> musicToPageList = new ArrayList<String>();
        String PrevChapter="";
        String NextChapter="";
        String VolumeName  = getIntent().getStringExtra("Volume");
        String ChapterName = getIntent().getStringExtra("ChapterName");
        String Test = VolumeName + " " + ChapterName;
        Integer Cover1 = getIntent().getIntExtra("Cover1",0);
        Integer Cover2 = getIntent().getIntExtra("Cover2",0);

        //Read the File and get the data
        input=getResources().openRawResource(R.raw.data);
        try{
            if(input !=null)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String myString;
                int counter=0;
                Log.i("Test1 ",Test);
                while((!((myString=reader.readLine()).equals(Test))))
                {
                    Log.i("Test2 ",myString);
                }
                while(!(myString = reader.readLine()).equals("/"))
                {
                    if(counter==0){
                        IDName=myString;
                        Log.i("IDName ",myString);}
                    else if(counter==1){
                        pageNumber= Integer.parseInt(myString);
                        //Log.i("pageNumber ", String.valueOf(pageNumber));
                    }
                    else if(counter==2){
                        PrevChapter=myString.substring(1);
                        //Log.i("PrevChapter ",PrevChapter);
                    }
                    else if(counter==3){
                        NextChapter=myString.substring(1);
                       //  ;
                        }
                    else {
                        String[] values =myString.split(" ");
                        pageMusicList.add(Integer.parseInt(values[0]));
                        musicToPageList.add(values[1]);
                        //Log.i("value1 ",String.valueOf(Integer.parseInt(values[0])));
                        //Log.i("value2 ",String.valueOf(Integer.parseInt(values[1])));
                    }
                    counter++;
                }
                reader.close();
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.umineko_episode01_chapter01);
        //Change the appbar Text
        TextView textView = (TextView) findViewById(R.id.Titre);
        String chapterText = getIntent().getStringExtra("ChapterName");
        textView.setText(chapterText);

        //assign Variable
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        startingPoint = getIntent().getBooleanExtra("start",true);
        mMediaListener = new MediaListener(getResources());

        //Initialize the List
        ArrayList<String> mArrayList = new ArrayList<>();
        //Initialize the list
        ArrayList<Integer> mArrayPage = new ArrayList<>();

        //Add Previous fragment only if there is a previous fragment
        if (!PrevChapter.equals("false")) {
            int initId = getResources().getIdentifier("R.drawable.next", "drawable", getPackageName());
            mArrayPage.add(initId);
            mArrayList.add("Previous Chapter");
        }
        //Start Page
        mArrayPage.add(Cover1);
        mArrayList.add("StartPage");
        //add the page and the ID in array list
        for (int i = 1; i <= pageNumber; i++) {
//          Create the string to find the page
            String page;
            if (i < 10) {
                page = "0" + (i);
            } else {
                page = "" + (i);
            }
            mArrayList.add(page + "/" + pageNumber);
            int drawableId = getResources().getIdentifier(IDName + page, "drawable", getPackageName());
            mArrayPage.add(drawableId);
        }
        mArrayPage.add(Cover2);
        mArrayList.add("EndPage");
        //Next Page
        if (!NextChapter.equals("false")) {
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
        if(!NextChapter.equals("false"))
        {
            if (startingPoint) { tabLayout.selectTab(tabLayout.getTabAt(pageNumber+2));}
            else{tabLayout.selectTab(tabLayout.getTabAt(1));}
        }
        else
        {
            if(startingPoint){tabLayout.selectTab(tabLayout.getTabAt(pageNumber+1));}
            else{tabLayout.selectTab(tabLayout.getTabAt(0));}
        }

        listener(pageMusicList,musicToPageList);
        //Return Button
        ImageView imageView = (ImageView) findViewById(R.id.retour);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaListener.stopMusic();
                finish();
            }
        });
    }
    private void listener(ArrayList<Integer> temp,ArrayList<String> ID) {
        //Play music depending on the page
        //Check the page we're at
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int numTab = tab.getPosition();
                //At the end
                //For all case where I want to play Music
                for (int i = 0; i < temp.size(); i++) {
                    //Temp is my Array of case
                    //ArrayList<Integer> temp = getIntent().getIntegerArrayListExtra("ArrayCase");
                    //If the case is good
                    if (numTab == temp.get(i)) {
                        //We get the ID
                        //int ID = getIntent().getIntegerArrayListExtra("musicName").get(i);
                        //ID = 1 means next chapter ID = 2 means prev chapter ID=3 means stop music
                        if (ID.get(i).equals("1")) {
                            try {
                                mMediaListener.stopMusic();
                                Intent intent = new Intent(getApplicationContext(), Class.forName(getIntent().getStringExtra("Nextclass")));
                                startActivity(intent);
                                finish();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else if (ID.get(i).equals("2")) {
                            try {
                                mMediaListener.stopMusic();
                                Intent intent = new Intent(getApplicationContext(), Class.forName(getIntent().getStringExtra("Prevclass")));
                                intent.putExtra("start", false);
                                startActivity(intent);
                                finish();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("MUSIC SET NUMBER", String.valueOf(i));
                            setMusic(ID.get(i));
//                            mHtHandler.sendEmptyMessageDelayed(ID,300);
                        }
                    }
                }
            }
        });
    }
    private void setMusic(String ID)
    {
        int soundId = getResources().getIdentifier(ID, "raw", this.getPackageName());
        //If my music is not the same I'm playing
        if(mMediaListener.getID()!=soundId)
        {
            if(soundId == R.raw.stab)
            {
                mMediaListener.setmMediaPlayer(soundId,false);
            }
            else
            {
                mMediaListener.setmMediaPlayer(soundId,true);
            }
        }
    }
    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList,ArrayList<Integer> mArrayPage) {
        //Initialize main adapter
        ChapterReader.MainAdapter adapter=new ChapterReader.MainAdapter(getSupportFragmentManager());
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
        mMediaListener.stopMusic();
        finish();
        super.onDestroy();
    }

    private class MainAdapter extends FragmentPagerAdapter {
        //Initialize Array List
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        ArrayList<Integer> mArrayPage = new ArrayList<>();

        //Create constructor
        public void addFragment(Fragment fragment, String title, int page) {
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
