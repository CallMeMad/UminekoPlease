package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter11 extends AppCompatActivity {

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
        chapter01List.add(41); musicListChapter01.add(2);
        chapter01List.add(39); musicListChapter01.add(R.raw.stupefaction);
        chapter01List.add(25); musicListChapter01.add(R.raw.stupefaction);
        chapter01List.add(24); musicListChapter01.add(R.raw.core);
        chapter01List.add(19); musicListChapter01.add(R.raw.core);
        chapter01List.add(18); musicListChapter01.add(R.raw.minute_darkness);
        chapter01List.add(12); musicListChapter01.add(R.raw.minute_darkness);
        chapter01List.add(11); musicListChapter01.add(R.raw.lure);
        chapter01List.add(2); musicListChapter01.add(R.raw.lure);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 11")
                .putExtra("IDName","umineko_v01_ch11_")
                .putExtra("number",38)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter12")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter10");
        finish();
        startActivity(intent);
    }
}