package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter08 extends AppCompatActivity {

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
        chapter01List.add(52); musicListChapter01.add(2);
        chapter01List.add(50); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(38); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(37); musicListChapter01.add(R.raw.worldend_solo);
        chapter01List.add(26); musicListChapter01.add(R.raw.worldend_solo);
        chapter01List.add(25); musicListChapter01.add(R.raw.stupefaction);
        chapter01List.add(21); musicListChapter01.add(R.raw.stupefaction);
        chapter01List.add(20); musicListChapter01.add(R.raw.at_deaths_door);
        chapter01List.add(15); musicListChapter01.add(R.raw.at_deaths_door);
        chapter01List.add(14); musicListChapter01.add(R.raw.worldend_solo);
        chapter01List.add(11); musicListChapter01.add(R.raw.worldend_solo);
        chapter01List.add(10); musicListChapter01.add(R.raw.lure);
        chapter01List.add(8); musicListChapter01.add(R.raw.lure);
        chapter01List.add(7); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(2); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 8")
                .putExtra("IDName","umineko_v01_ch08_")
                .putExtra("number",49)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter08")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter07");
        finish();
        startActivity(intent);
    }
}