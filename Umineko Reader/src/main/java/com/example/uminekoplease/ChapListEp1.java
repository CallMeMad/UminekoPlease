package com.example.uminekoplease;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.uminekoplease.json.EpisodeJson;
import com.example.uminekoplease.json.JSONResourceReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**Activité permettant de */
public class ChapListEp1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap_list_ep1);

        //Set up ToolBar
        Toolbar toolbar = findViewById(R.id.generalToolBar);
        setSupportActionBar(toolbar);
        TextView textToolBar = (TextView) findViewById(R.id.Titre);
        textToolBar.setText(R.string.ToolBarChapList);
        //liste de chapitre
        List<Chapter> chapterList = new ArrayList<>();
        //Set the Cover
        ImageView imageView = (ImageView) findViewById(R.id.cover);
        InputStream ims = null;
        try {
            ims = getAssets().open(getIntent().getStringExtra("Dir")+"/ep-" + getIntent().getStringExtra("String") + "/img/covers/vol-1.jpg");
            Drawable d = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Obtenir la liste ++
        String x = "ep" +getIntent().getStringExtra("String");
        JSONResourceReader jsonReader = new JSONResourceReader(getResources(), getResources().getIdentifier("ep" + getIntent().getStringExtra("String"), "raw", getPackageName()));
        EpisodeJson jsonObj = jsonReader.constructUsingGson(EpisodeJson.class);
        textToolBar = (TextView) findViewById(R.id.underText);
        textToolBar.setText(jsonObj.getTitle());
        for (int i = 0; i < jsonObj.getNumberChapter(); i++) {
            chapterList.add(new Chapter("Chapter " + (i + 1), jsonObj.getArt(), new Intent(getApplicationContext(), ChapterReader.class).putExtra("ep", getIntent().getStringExtra("String"))
                    .putExtra("ChapterName", String.valueOf(i + 1)).putExtra("start", true)));
        }
        //get the List inside a ListView
        ListView chapterListView = findViewById(R.id.ep1_listview);
        chapterListView.setAdapter(new ChapterAdapter(this, chapterList));
        //Bouton retour de la toolbar
        imageView = (ImageView) findViewById(R.id.retour);
        imageView.setOnClickListener(v -> finish());
    }

}