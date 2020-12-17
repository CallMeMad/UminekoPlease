package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter06 extends AppCompatActivity {

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
        chapter01List.add(48); musicListChapter01.add(R.raw.creepy_sound);
        chapter01List.add(44); musicListChapter01.add(R.raw.creepy_sound);
        chapter01List.add(43); musicListChapter01.add(R.raw.moonlit_night);
        chapter01List.add(40); musicListChapter01.add(R.raw.moonlit_night);
        chapter01List.add(39); musicListChapter01.add(R.raw.doorway_of_summer);
        chapter01List.add(35); musicListChapter01.add(R.raw.doorway_of_summer);
        chapter01List.add(34); musicListChapter01.add(R.raw.white_shadow);
        chapter01List.add(30); musicListChapter01.add(R.raw.white_shadow);
        chapter01List.add(29); musicListChapter01.add(R.raw.praise);
        chapter01List.add(23); musicListChapter01.add(R.raw.praise);
        chapter01List.add(22); musicListChapter01.add(R.raw.sakura);
        chapter01List.add(18); musicListChapter01.add(R.raw.sakura);
        chapter01List.add(17); musicListChapter01.add(R.raw.soundless);
        chapter01List.add(16); musicListChapter01.add(R.raw.lure);
        chapter01List.add(12); musicListChapter01.add(R.raw.lure);
        chapter01List.add(12); musicListChapter01.add(R.raw.magic_butterfly);
        chapter01List.add(11); musicListChapter01.add(R.raw.doorway_of_summer);
        chapter01List.add(10); musicListChapter01.add(R.raw.doorway_of_summer);
        chapter01List.add(9); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(8); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(7); musicListChapter01.add(R.raw.clockl);
        chapter01List.add(4); musicListChapter01.add(R.raw.clockl);
        chapter01List.add(3); musicListChapter01.add(R.raw.magic_butterfly);
        chapter01List.add(2); musicListChapter01.add(R.raw.magic_butterfly);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter6")
                .putExtra("IDName","umineko_v01_ch06_")
                .putExtra("number",49)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter07")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter05");
        finish();
        startActivity(intent);
    }
}