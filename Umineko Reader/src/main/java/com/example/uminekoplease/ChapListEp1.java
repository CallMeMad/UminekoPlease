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

        if(getIntent().getStringExtra("String").equals("Episode 1"))
        {
            chapterList.add(new Chapter("Chapter 01","02/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 01").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 02","09/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 02").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 03","10/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 03").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 04","10/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 04").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 05","12/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 05").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 06","12/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 06").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 07","12/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 07").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 08","13/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 08").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 09","13/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 09").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 10","17/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 10").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 11","17/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 11").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 12","17/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 12").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 13","18/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 13").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 14","24/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 14").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 15","27/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 15").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 16","30/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 16").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 17","30/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 17").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 18","30/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 18").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 19","30/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 19").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 20","30/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 20").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 21","31/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 21").putExtra("start",true)));
            chapterList.add(new Chapter("Tea Party","31/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 22").putExtra("start",true)));
            chapterList.add(new Chapter(" ???????? to test","31/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 23").putExtra("start",true)));
        }
        else if(getIntent().getStringExtra("String").equals("Episode 2"))
        {
            //Add our chapter to the LIst
            this.imageView=(ImageView)findViewById(R.id.cover);
            imageView.setImageResource(R.drawable.)
            chapterList.add(new Chapter("Chapter 01","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 01").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 02","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 02").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 03","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChaterName","Chapter 03").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 04","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 04").putExtra("start",true)));
        }
        //get the List inside a ListView
        ListView chapterListView = findViewById(R.id.ep1_listview);
        //Adapt our ListView
        chapterListView.setAdapter(new ChapterAdapter(this,chapterList));

        //Return Button
        this.imageView=(ImageView)findViewById(R.id.retour);
        imageView.setOnClickListener(v -> finish());
    }

}