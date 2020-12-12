package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter04 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Integer> chapter01List = new ArrayList<Integer>();
        ArrayList<Integer> musicListChapter01 = new ArrayList<Integer>();
        chapter01List.add(61); musicListChapter01.add(2);
        chapter01List.add(58); musicListChapter01.add(R.raw.hope);
        chapter01List.add(46); musicListChapter01.add(R.raw.hope);
        chapter01List.add(45); musicListChapter01.add(R.raw.waves);
        chapter01List.add(44); musicListChapter01.add(R.raw.waves);
        chapter01List.add(43); musicListChapter01.add(R.raw.hope);
        chapter01List.add(37); musicListChapter01.add(R.raw.hope);
        chapter01List.add(36); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(29); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(28); musicListChapter01.add(R.raw.soundless);
        chapter01List.add(27); musicListChapter01.add(R.raw.soundless);
        chapter01List.add(26); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(23); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(22); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(19); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(18); musicListChapter01.add(R.raw.son_vent);
        chapter01List.add(17); musicListChapter01.add(R.raw.son_vent);
        chapter01List.add(16); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(13); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(12); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(8); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(7); musicListChapter01.add(R.raw.lure);
        chapter01List.add(2); musicListChapter01.add(R.raw.lure);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class).putExtra("start",getIntent().getBooleanExtra("start",true)).putExtra("ChapterName","Chapter4").putExtra("IDName","umineko_v01_ch04_").putExtra("number",58).putExtra("musicNumber",chapter01List.size()).putExtra("ArrayCase",chapter01List).putExtra("musicName",musicListChapter01).putExtra("Nextclass","com.example.uminekoplease.DummyChapter05").putExtra("Prevclass","com.example.uminekoplease.DummyChapter03");
        startActivity(intent);
        finish();
    }
}