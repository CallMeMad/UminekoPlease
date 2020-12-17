package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter07 extends AppCompatActivity {

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
        chapter01List.add(60); musicListChapter01.add(2);
        chapter01List.add(58); musicListChapter01.add(R.raw.moonlit_night);
        chapter01List.add(54); musicListChapter01.add(R.raw.moonlit_night);
        chapter01List.add(53); musicListChapter01.add(R.raw.creepy_sound);
        chapter01List.add(49); musicListChapter01.add(R.raw.creepy_sound);
        chapter01List.add(48); musicListChapter01.add(R.raw.lure);
        chapter01List.add(43); musicListChapter01.add(R.raw.lure);
        chapter01List.add(42); musicListChapter01.add(R.raw.rose);
        chapter01List.add(37); musicListChapter01.add(R.raw.rose);
        chapter01List.add(36); musicListChapter01.add(R.raw.white_shadow);
        chapter01List.add(31); musicListChapter01.add(R.raw.white_shadow);
        chapter01List.add(30); musicListChapter01.add(R.raw.hane_feathers);
        chapter01List.add(28); musicListChapter01.add(R.raw.hane_feathers);
        chapter01List.add(27); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(22); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(21); musicListChapter01.add(R.raw.creepy_sound);
        chapter01List.add(19); musicListChapter01.add(R.raw.creepy_sound);
        chapter01List.add(18); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(10); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(9); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(2); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 7")
                .putExtra("IDName","umineko_v01_ch07_")
                .putExtra("number",57)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter08")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter06");
        finish();
        startActivity(intent);
    }
}