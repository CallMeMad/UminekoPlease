package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Integer> chapter01List = new ArrayList<Integer>();
        ArrayList<Integer> musicListChapter01 = new ArrayList<Integer>();
        chapter01List.add(67); musicListChapter01.add(2);
        chapter01List.add(65); musicListChapter01.add(R.raw.rose);
        chapter01List.add(59); musicListChapter01.add(R.raw.rose);
        chapter01List.add(58); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(55); musicListChapter01.add(R.raw.hour_of_darkness);
        chapter01List.add(54); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(37); musicListChapter01.add(R.raw.novelette);
        chapter01List.add(36); musicListChapter01.add(R.raw.soundless);
        chapter01List.add(35); musicListChapter01.add(R.raw.doorway_of_summer);
        chapter01List.add(34); musicListChapter01.add(R.raw.doorway_of_summer);
        chapter01List.add(33); musicListChapter01.add(R.raw.white_shadow);
        chapter01List.add(30); musicListChapter01.add(R.raw.white_shadow);
        chapter01List.add(29); musicListChapter01.add(R.raw.at_deaths_door);
        chapter01List.add(28); musicListChapter01.add(R.raw.at_deaths_door);
        chapter01List.add(23); musicListChapter01.add(R.raw.soundless);
        chapter01List.add(22); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(18); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(17); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(9); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(8); musicListChapter01.add(R.raw.sui_no_rondo);
        chapter01List.add(2); musicListChapter01.add(R.raw.sui_no_rondo);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class).putExtra("start",getIntent().getBooleanExtra("start",true)).putExtra("ChapterName","Chapter3").putExtra("IDName","umineko_v01_ch03_").putExtra("number",64).putExtra("musicNumber",chapter01List.size()).putExtra("ArrayCase",chapter01List).putExtra("musicName",musicListChapter01).putExtra("Nextclass","com.example.uminekoplease.DummyChapter04").putExtra("Prevclass","com.example.uminekoplease.DummyChapter02");
        startActivity(intent);
        finish();
    }
}