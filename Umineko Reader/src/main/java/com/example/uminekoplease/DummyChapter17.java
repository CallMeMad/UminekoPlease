package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter17 extends AppCompatActivity {

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
        chapter01List.add(56); musicListChapter01.add(2);
        chapter01List.add(54); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(43); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(42); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(38); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(37); musicListChapter01.add(R.raw.at_deaths_door);
        chapter01List.add(25); musicListChapter01.add(R.raw.at_deaths_door);
        chapter01List.add(24); musicListChapter01.add(R.raw.mind);
        chapter01List.add(15); musicListChapter01.add(R.raw.mind);
        chapter01List.add(14); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(2); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 17")
                .putExtra("IDName","umineko_v01_ch17_")
                .putExtra("number",53)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter18")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter16");
        finish();
        startActivity(intent);
    }
}