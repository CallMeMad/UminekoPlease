package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ChapListEp1 extends AppCompatActivity {

    //Variable Declaration
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView textToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap_list_ep1);

        //Set up ToolBar
        this.toolbar=findViewById(R.id.generalToolBar);
        setSupportActionBar(toolbar);
        this.textToolBar=(TextView)findViewById(R.id.Titre);
        textToolBar.setText(R.string.ToolBarChapList);

        //Initialize Chapter List
        List<Chapter> chapterList = new ArrayList<>();

        //Add our chapter to the LIst
        chapterList.add(new Chapter("Chapter 01","02/12/2020",new Intent(getApplicationContext(), DummyChapter01.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 02","09/12/2020",new Intent(getApplicationContext(), DummyChapter02.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 03","10/12/2020",new Intent(getApplicationContext(), DummyChapter03.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 04","10/12/2020",new Intent(getApplicationContext(), DummyChapter04.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 05","12/12/2020",new Intent(getApplicationContext(), DummyChapter05.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 06","12/12/2020",new Intent(getApplicationContext(), DummyChapter06.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 07","12/12/2020",new Intent(getApplicationContext(), DummyChapter07.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 08","13/12/2020",new Intent(getApplicationContext(), DummyChapter08.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 09","13/12/2020",new Intent(getApplicationContext(), DummyChapter09.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 10","17/12/2020",new Intent(getApplicationContext(), DummyChapter10.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 11","17/12/2020",new Intent(getApplicationContext(), DummyChapter11.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 12","17/12/2020",new Intent(getApplicationContext(), DummyChapter12.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 13","18/12/2020",new Intent(getApplicationContext(), DummyChapter13.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 14","24/12/2020",new Intent(getApplicationContext(), DummyChapter14.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 15","30/12/2020",new Intent(getApplicationContext(), DummyChapter15.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 16","30/12/2020",new Intent(getApplicationContext(), DummyChapter16.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 17","30/12/2020",new Intent(getApplicationContext(), DummyChapter17.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 18","30/12/2020",new Intent(getApplicationContext(), DummyChapter18.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 19","30/12/2020",new Intent(getApplicationContext(), DummyChapter19.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 20","30/12/2020",new Intent(getApplicationContext(), DummyChapter20.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 21","30/12/2020",new Intent(getApplicationContext(), DummyChapter21.class).putExtra("start",true)));
        chapterList.add(new Chapter("Tea Party ","31/12/2020",new Intent(getApplicationContext(), DummyChapter22.class).putExtra("start",true)));
        chapterList.add(new Chapter("  ????    ","31/12/2020",new Intent(getApplicationContext(), DummyChapter23.class).putExtra("start",true)));
        //get the List inside a ListView
        ListView chapterListView = findViewById(R.id.ep1_listview);
        //Adapt our ListView
        chapterListView.setAdapter(new ChapterAdapter(this,chapterList));

        //Return Button
        this.imageView=(ImageView)findViewById(R.id.retour);
        imageView.setOnClickListener(v -> finish());
    }

}