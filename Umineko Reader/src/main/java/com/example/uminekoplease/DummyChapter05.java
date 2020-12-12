package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter05 extends AppCompatActivity {

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
        chapter01List.add(54); musicListChapter01.add(R.raw.lure);
        chapter01List.add(50); musicListChapter01.add(R.raw.lure);
        chapter01List.add(49); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(46); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(45); musicListChapter01.add(R.raw.fishy_aroma);
        chapter01List.add(37); musicListChapter01.add(R.raw.fishy_aroma);
        chapter01List.add(36); musicListChapter01.add(R.raw.rose);
        chapter01List.add(34); musicListChapter01.add(R.raw.rose);
        chapter01List.add(33); musicListChapter01.add(R.raw.steady_pace);
        chapter01List.add(31); musicListChapter01.add(R.raw.steady_pace);
        chapter01List.add(30); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(26); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(25); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(20); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(19); musicListChapter01.add(R.raw.stupefaction);
        chapter01List.add(7); musicListChapter01.add(R.raw.stupefaction);
        chapter01List.add(6); musicListChapter01.add(R.raw.moonlit_night);
        chapter01List.add(4); musicListChapter01.add(R.raw.moonlit_night);
        chapter01List.add(3); musicListChapter01.add(R.raw.thunder_sound_effect);
        chapter01List.add(2); musicListChapter01.add(R.raw.thunder_sound_effect);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class).putExtra("start",getIntent().getBooleanExtra("start",true)).putExtra("ChapterName","Chapter5").putExtra("IDName","umineko_v01_ch05_").putExtra("number",53).putExtra("musicNumber",chapter01List.size()).putExtra("ArrayCase",chapter01List).putExtra("musicName",musicListChapter01).putExtra("Nextclass","com.example.uminekoplease.DummyChapter05").putExtra("Prevclass","com.example.uminekoplease.DummyChapter04");
        finish();
        startActivity(intent);
    }
}