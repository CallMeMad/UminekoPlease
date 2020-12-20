package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter09 extends AppCompatActivity {

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
        chapter01List.add(53); musicListChapter01.add(2);
        chapter01List.add(51); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(45); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(44); musicListChapter01.add(R.raw.suspicion);
        chapter01List.add(32); musicListChapter01.add(R.raw.suspicion);
        chapter01List.add(31); musicListChapter01.add(R.raw.scar_sound);
        chapter01List.add(26); musicListChapter01.add(R.raw.scar_sound);
        chapter01List.add(25); musicListChapter01.add(R.raw.steady_pace);
        chapter01List.add(21); musicListChapter01.add(R.raw.steady_pace);
        chapter01List.add(20); musicListChapter01.add(R.raw.fishy_aroma);
        chapter01List.add(2); musicListChapter01.add(R.raw.fishy_aroma);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 9")
                .putExtra("IDName","umineko_v01_ch09_")
                .putExtra("number",50)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter10")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter08");
        finish();
        startActivity(intent);
    }
}