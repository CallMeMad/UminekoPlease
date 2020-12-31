package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter20 extends AppCompatActivity {

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
        chapter01List.add(58); musicListChapter01.add(2);
        chapter01List.add(56); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(45); musicListChapter01.add(R.raw.fortitude);
        chapter01List.add(44); musicListChapter01.add(R.raw.mind);
        chapter01List.add(36); musicListChapter01.add(R.raw.mind);
        chapter01List.add(35); musicListChapter01.add(R.raw.lure);
        chapter01List.add(30); musicListChapter01.add(R.raw.lure);
        chapter01List.add(29); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(21); musicListChapter01.add(R.raw.corridor_of_the_sands_of_purgatory);
        chapter01List.add(20); musicListChapter01.add(R.raw.closed_my_heart);
        chapter01List.add(10); musicListChapter01.add(R.raw.closed_my_heart);
        chapter01List.add(9); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(2); musicListChapter01.add(R.raw.golden_slaughterer2);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 20")
                .putExtra("IDName","umineko_v01_ch20_")
                .putExtra("number",55)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter21")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter19");
        finish();
        startActivity(intent);
    }
}