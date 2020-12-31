package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter22 extends AppCompatActivity {

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
        chapter01List.add(63); musicListChapter01.add(R.raw.string_quartet_allegrowmv);
        chapter01List.add(54); musicListChapter01.add(R.raw.string_quartet_allegrowmv);
        chapter01List.add(53); musicListChapter01.add(R.raw.lure);
        chapter01List.add(44); musicListChapter01.add(R.raw.lure);
        chapter01List.add(43); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(37); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(36); musicListChapter01.add(R.raw.organ_short_600);
        chapter01List.add(10); musicListChapter01.add(R.raw.organ_short_600);
        chapter01List.add(9); musicListChapter01.add(R.raw.prison_strip);
        chapter01List.add(2); musicListChapter01.add(R.raw.prison_strip);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName"," Tea Party ")
                .putExtra("IDName","umineko_v01_ch22_")
                .putExtra("number",64)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter23")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter21");
        finish();
        startActivity(intent);
    }
}