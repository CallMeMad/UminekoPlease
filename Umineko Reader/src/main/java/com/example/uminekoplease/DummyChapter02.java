package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Integer> chapter01List = new ArrayList<Integer>();
        ArrayList<Integer> musicListChapter01 = new ArrayList<Integer>();
        chapter01List.add(43); musicListChapter01.add(2);
        chapter01List.add(41); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(31); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(30); musicListChapter01.add(R.raw.hope);
        chapter01List.add(25); musicListChapter01.add(R.raw.hope);
        chapter01List.add(24); musicListChapter01.add(R.raw.white_shadow);
        chapter01List.add(21); musicListChapter01.add(R.raw.white_shadow);
        chapter01List.add(20); musicListChapter01.add(R.raw.doorway_of_summer);
        chapter01List.add(13); musicListChapter01.add(R.raw.doorway_of_summer);
        chapter01List.add(12); musicListChapter01.add(R.raw.son_vent);
        chapter01List.add(11); musicListChapter01.add(R.raw.son_vent);
        chapter01List.add(10);  musicListChapter01.add(R.raw.sukashiyuri);
        chapter01List.add(2);  musicListChapter01.add(R.raw.sukashiyuri);
        chapter01List.add(0);  musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class).putExtra("start",getIntent().getBooleanExtra("start",true)).putExtra("ChapterName","Chapter2").putExtra("IDName","umineko_v01_ch02_").putExtra("number",40).putExtra("musicNumber",chapter01List.size()).putExtra("ArrayCase",chapter01List).putExtra("musicName",musicListChapter01).putExtra("Nextclass","com.example.uminekoplease.DummyChapter03").putExtra("Prevclass","com.example.uminekoplease.DummyChapter01");
        startActivity(intent);
        finish();
    }
}