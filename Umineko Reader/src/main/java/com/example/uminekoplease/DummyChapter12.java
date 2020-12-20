package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter12 extends AppCompatActivity {

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
        chapter01List.add(54); musicListChapter01.add(2);
        chapter01List.add(52); musicListChapter01.add(R.raw.lure);
        chapter01List.add(44); musicListChapter01.add(R.raw.lure);
        chapter01List.add(43); musicListChapter01.add(R.raw.scar_sound);
        chapter01List.add(39); musicListChapter01.add(R.raw.scar_sound);
        chapter01List.add(38); musicListChapter01.add(R.raw.fishy_aroma);
        chapter01List.add(24); musicListChapter01.add(R.raw.fishy_aroma);
        chapter01List.add(23); musicListChapter01.add(R.raw.nighteyes);
        chapter01List.add(2); musicListChapter01.add(R.raw.nighteyes);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 12")
                .putExtra("IDName","umineko_v01_ch12_")
                .putExtra("number",51)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter13")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter11");
        finish();
        startActivity(intent);
    }
}