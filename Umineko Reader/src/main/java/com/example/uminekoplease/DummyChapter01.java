package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter01 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        {
            //Create the List of Page and the list of music to play on the said page
            ArrayList<Integer> chapter01List = new ArrayList<Integer>();
            ArrayList<Integer> musicListChapter01 = new ArrayList<Integer>();
            //Data List /musicListChapter(1)= next Chapter
            chapter01List.add(31); musicListChapter01.add(R.raw.rengoku);
            chapter01List.add(28); musicListChapter01.add(R.raw.rengoku);
            chapter01List.add(27); musicListChapter01.add(R.raw.sea);
            chapter01List.add(13); musicListChapter01.add(R.raw.sea);
            chapter01List.add(12); musicListChapter01.add(R.raw.hour_of_darkness);
            chapter01List.add(1);  musicListChapter01.add(R.raw.hour_of_darkness);
            chapter01List.add(0);  musicListChapter01.add(1);
            Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class).putExtra("start",getIntent().getBooleanExtra("start",true)).putExtra("ChapterName","Chapter1").putExtra("IDName","umineko_v01_ch01_").putExtra("number",30).putExtra("musicNumber",chapter01List.size()).putExtra("ArrayCase",chapter01List).putExtra("musicName",musicListChapter01).putExtra("Nextclass","com.example.uminekoplease.DummyChapter02").putExtra("Prevclass","false");
            startActivity(intent);
            finish();
        }

    }
}
