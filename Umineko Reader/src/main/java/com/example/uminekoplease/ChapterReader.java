package com.example.uminekoplease;
import android.content.Intent;
import android.content.res.AssetManager;
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
    private String IDName;
    private Intent music;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize other Data
        ArrayList<Integer> pageMusicList = new ArrayList<Integer>();
        ArrayList<String> musicToPageList = new ArrayList<>();
        String PrevChapter="";
        String NextChapter="";
        String VolumeName  = getIntent().getStringExtra("Volume");
        String ChapterName = getIntent().getStringExtra("ChapterName");
        String Test = VolumeName + " " + ChapterName;
        String Cover1="";
        String Cover2="";

        //Read the File and get the data
        InputStream input = getResources().openRawResource(R.raw.data);
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
                IDName= reader.readLine();
                Log.i("IDName ",IDName);
                pageNumber= Integer.parseInt(reader.readLine());
                Log.i("pageNumber ", String.valueOf(pageNumber));
                Cover1 = reader.readLine();
                Log.i("Cover1 ", String.valueOf(Cover1));
                Cover2 = reader.readLine();
                Log.i("Cover2 ", String.valueOf(Cover2));
                PrevChapter=reader.readLine().substring(1);
                Log.i("Prev ",PrevChapter);
                NextChapter=reader.readLine().substring(1);
                Log.i("Next ",NextChapter);
                while(!(myString = reader.readLine()).equals("/"))
                {
                   String[] values =myString.split(" ");
                   pageMusicList.add(Integer.parseInt(values[0]));
                   if(values[1].equals("1") || values[1].equals("2")){musicToPageList.add(values[1]);}
                   else
                   {
                       musicToPageList.add(values[1]);
                       Log.i("value1 ",String.valueOf(Integer.parseInt(values[0])));
                       Log.i("value2 ",values[1]);
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
        boolean startingPoint = getIntent().getBooleanExtra("start", true);
        music= new Intent(this,SoundService.class);

        //Initialize the List
        ArrayList<String> mArrayList = new ArrayList<>();
        //Initialize the list
        ArrayList<String> mArrayPage = new ArrayList<>();

        //Add Previous fragment only if there is a previous fragment
        if (!PrevChapter.equals("false")) {
            mArrayPage.add("next");
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
            //int drawableId = getResources().getIdentifier(IDName + page, "drawable", getPackageName());
            String drawablePath = IDName + page;
            mArrayPage.add(drawablePath);
        }
        mArrayPage.add(Cover2);
        mArrayList.add("EndPage");
        //Next Page
        if (!NextChapter.equals("false")) {
            mArrayPage.add("next");
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
                stopService(music);
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
                    if (numTab == temp.get(i))
                    {
                        //We get the ID
                        //int ID = getIntent().getIntegerArrayListExtra("musicName").get(i);
                        //ID = 1 means next chapter ID = 2 means prev chapter ID=3 means stop music
                        if (ID.get(i)== "1") {
                                stopService(music);
                                String[] ChapterName = getIntent().getStringExtra("ChapterName").split("0");
                                if(ChapterName.length==1){ChapterName=getIntent().getStringExtra("ChapterName").split("r ");ChapterName[0]=ChapterName[0]+"r ";}
                                Log.i("CHAPTERNAME",ChapterName[0]);
                                Integer x =Integer.parseInt(ChapterName[1]);
                                x+=1;
                                String ChapterNameFin;
                                if(x>=10){ ChapterNameFin= ChapterName[0]+x;}
                                else{ChapterNameFin = ChapterName[0]+"0"+x;};
                                Log.i("STRING",ChapterNameFin);
                                Intent intent =new Intent(getApplicationContext(), ChapterReader.class).putExtra("start",true)
                                        .putExtra("Volume",getIntent().getStringExtra("Volume")).putExtra("ChapterName",ChapterNameFin);
                                startActivity(intent);
                                finish();
                        } else if (ID.get(i)== "2") {
                                stopService(music);
                                String[] ChapterName = getIntent().getStringExtra("ChapterName").split("0");
                                if(ChapterName.length==1){ChapterName=getIntent().getStringExtra("ChapterName").split("r ");ChapterName[0]=ChapterName[0]+"r ";}
                                Log.i("CHAPTERNAME",ChapterName[0]);
                                Integer x =Integer.parseInt(ChapterName[1]);
                                x-=1;
                                String ChapterNameFin;
                                if(x>=10){ ChapterNameFin= ChapterName[0]+x;}
                                else{ChapterNameFin = ChapterName[0]+"0"+x;};
                                Log.i("STRING",ChapterNameFin);
                                Intent intent =new Intent(getApplicationContext(), ChapterReader.class).putExtra("start",false)
                                        .putExtra("Volume",getIntent().getStringExtra("Volume")).putExtra("ChapterName",ChapterNameFin);
                                startActivity(intent);
                                finish();
                            }
                         else
                         {
                           Log.i("MUSIC SET NUMBER", String.valueOf(i));
                           setMusic(music, ID.get(i));
                         }
                    }
                }
            }
        });
    }
    private void setMusic(Intent music, String ID)
    {
        //If my music is not the same I'm playing
        if(music.getStringExtra("ID")!=ID)
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
            //Log.d("MUSIC SET :", String.valueOf(ID));
        }
    }
    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList,ArrayList<String> mArrayPage) {
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
            bundle.putString("page",mArrayPage.get(i));
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
