package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter21 extends AppCompatActivity {

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
        chapter01List.add(67); musicListChapter01.add(2);
        chapter01List.add(65); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(48); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(47); musicListChapter01.add(R.raw.dead_angle);
        chapter01List.add(32); musicListChapter01.add(R.raw.dead_angle);
        chapter01List.add(31); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(16); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(15); musicListChapter01.add(R.raw.bring_the_fate);
        chapter01List.add(2); musicListChapter01.add(R.raw.bring_the_fate);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 21")
                .putExtra("IDName","umineko_v01_ch21_")
                .putExtra("number",64)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter22")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter20");
        finish();
        startActivity(intent);
    }
}