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
        //chapterList.add(new Chapter("Chapter 01","02/12/2020",new Intent(getApplicationContext(), Episode01Chapter01.class).putExtra("start",true)));
        //chapterList.add(new Chapter("Chapter 02","03/12/2020",new Intent(getApplicationContext(), Episode01Chapter02.class).putExtra("start",true)));

        chapterList.add(new Chapter("Chapter 01","02/12/2020",new Intent(getApplicationContext(), DummyChapter01.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 02","09/12/2020",new Intent(getApplicationContext(), DummyChapter02.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 03","10/12/2020",new Intent(getApplicationContext(), DummyChapter03.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 04","10/12/2020",new Intent(getApplicationContext(), DummyChapter04.class).putExtra("start",true)));
        chapterList.add(new Chapter("Chapter 05","10/12/2020",new Intent(getApplicationContext(), DummyChapter05.class).putExtra("start",true)));
        //Obtenir la liste
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