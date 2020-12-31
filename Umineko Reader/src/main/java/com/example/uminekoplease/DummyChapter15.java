package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter15 extends AppCompatActivity {

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
        chapter01List.add(43); musicListChapter01.add(2);
        chapter01List.add(41); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(29); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(28); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(17); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(16); musicListChapter01.add(R.raw.scar_sound);
        chapter01List.add(10); musicListChapter01.add(R.raw.scar_sound);
        chapter01List.add(9); musicListChapter01.add(R.raw.closed_my_heart);
        chapter01List.add(2); musicListChapter01.add(R.raw.closed_my_heart);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 15")
                .putExtra("IDName","umineko_v01_ch15_")
                .putExtra("number",40)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter16")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter14");
        finish();
        startActivity(intent);
    }
}