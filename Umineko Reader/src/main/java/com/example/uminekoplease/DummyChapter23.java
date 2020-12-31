package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter23 extends AppCompatActivity {

    ArrayList<Integer> chapter01List;
    ArrayList<Integer> musicListChapter01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chapter01List= new ArrayList<Integer>();
        musicListChapter01 = new ArrayList<Integer>();
    }
    protected void onStart() {
        super.onStart();
        chapter01List.add(13); musicListChapter01.add(2);
        chapter01List.add(11); musicListChapter01.add(R.raw.organ_short_600);
        chapter01List.add(1); musicListChapter01.add(R.raw.organ_short_600);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName"," ??? ")
                .putExtra("IDName","umineko_v01_ch23_")
                .putExtra("number",11)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","false")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter22");
        finish();
        startActivity(intent);
    }
}