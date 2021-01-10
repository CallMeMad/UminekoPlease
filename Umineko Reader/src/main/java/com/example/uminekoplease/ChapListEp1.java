package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ChapListEp1 extends AppCompatActivity {

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
        //liste de chapitre
        List<Chapter> chapterList = new ArrayList<>();

        //Obtenir la liste

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
            chapterList.add(new Chapter(" ???????? ","31/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 01").putExtra("ChapterName","Chapter 23").putExtra("start",true)));
        }
        else if(getIntent().getStringExtra("String").equals("Episode 2"))
        {
            //Add our chapter to the LIst
            this.imageView=(ImageView)findViewById(R.id.cover);
            imageView.setImageResource(R.drawable.umineko_v01_ch04_01);
            this.textToolBar=(TextView)findViewById(R.id.underText);
            textToolBar.setText("Turn of The Golden Witch");
            chapterList.add(new Chapter("Chapter 01","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 01").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 02","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 02").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 03","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 03").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 04","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 04").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 05","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 05").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 06","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 06").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 07","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 07").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 08","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 08").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 09","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 09").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 10","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 10").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 11","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 11").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 12","06/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 12").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 13","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 13").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 14","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 14").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 15","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 15").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 16","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 16").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 17","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 17").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 18","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 18").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 19","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 19").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 20","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 20").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 21","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 21").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 22","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 22").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 23","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 23").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 24","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 24").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 25","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 25").putExtra("start",true)));
            chapterList.add(new Chapter("Chapter 26","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 26").putExtra("start",true)));
            chapterList.add(new Chapter("Tea Party","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 27").putExtra("start",true)));
            chapterList.add(new Chapter(" ??????? ","07/12/2020",new Intent(getApplicationContext(), ChapterReader.class)
                    .putExtra("Volume","Volume 02").putExtra("ChapterName","Chapter 28").putExtra("start",true)));
        }

        //get the List inside a ListView
        ListView chapterListView = findViewById(R.id.ep1_listview);
        chapterListView.setAdapter(new ChapterAdapter(this,chapterList));
        //Bouton retour de la toolbar
        this.imageView=(ImageView)findViewById(R.id.retour);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}