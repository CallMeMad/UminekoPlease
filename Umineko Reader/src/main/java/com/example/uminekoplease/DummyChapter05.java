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
        chapter01List.add(53); musicListChapter01.add(R.raw.lure);
        chapter01List.add(45); musicListChapter01.add(R.raw.lure);
        chapter01List.add(44); musicListChapter01.add(R.raw.waves);
        chapter01List.add(43); musicListChapter01.add(R.raw.waves);
        chapter01List.add(42); musicListChapter01.add(R.raw.hope);
        chapter01List.add(36); musicListChapter01.add(R.raw.hope);
        chapter01List.add(35); musicListChapter01.add(R.raw.son_vent);
        chapter01List.add(28); musicListChapter01.add(R.raw.son_vent);
        chapter01List.add(27); musicListChapter01.add(R.raw.soundless);
        chapter01List.add(26); musicListChapter01.add(R.raw.soundless);
        chapter01List.add(25); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(22); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(21); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(18); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(17); musicListChapter01.add(R.raw.son_vent);
        chapter01List.add(16); musicListChapter01.add(R.raw.son_vent);
        chapter01List.add(15); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(12); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(11); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(8); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(7); musicListChapter01.add(R.raw.soundless);
        chapter01List.add(6); musicListChapter01.add(R.raw.lure);
        chapter01List.add(1); musicListChapter01.add(R.raw.lure);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class).putExtra("start",getIntent().getBooleanExtra("start",true)).putExtra("ChapterName","Chapter5").putExtra("IDName","umineko_v01_ch05_").putExtra("number",53).putExtra("musicNumber",chapter01List.size()).putExtra("ArrayCase",chapter01List).putExtra("musicName",musicListChapter01).putExtra("Nextclass","com.example.uminekoplease.DummyChapter05").putExtra("Prevclass","com.example.uminekoplease.DummyChapter04");
        finish();
        startActivity(intent);
    }
}