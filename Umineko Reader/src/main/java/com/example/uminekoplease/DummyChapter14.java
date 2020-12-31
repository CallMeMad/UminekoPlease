package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter14 extends AppCompatActivity {

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
        chapter01List.add(42); musicListChapter01.add(2);
        chapter01List.add(40); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(39); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(38); musicListChapter01.add(R.raw.hope);
        chapter01List.add(27); musicListChapter01.add(R.raw.hope);
        chapter01List.add(26); musicListChapter01.add(R.raw.minute_darkness);
        chapter01List.add(22); musicListChapter01.add(R.raw.minute_darkness);
        chapter01List.add(21); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(13); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(12); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(4); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(3); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(2); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 14")
                .putExtra("IDName","umineko_v01_ch14_")
                .putExtra("number",39)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter15")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter13");
        finish();
        startActivity(intent);
    }
}