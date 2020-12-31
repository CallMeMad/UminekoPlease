package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter19 extends AppCompatActivity {

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
        chapter01List.add(40); musicListChapter01.add(2);
        chapter01List.add(38); musicListChapter01.add(R.raw.system_0);
        chapter01List.add(13); musicListChapter01.add(R.raw.system_0);
        chapter01List.add(12); musicListChapter01.add(R.raw.voiceless);
        chapter01List.add(6); musicListChapter01.add(R.raw.voiceless);
        chapter01List.add(5); musicListChapter01.add(R.raw.mind);
        chapter01List.add(2); musicListChapter01.add(R.raw.mind);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 19")
                .putExtra("IDName","umineko_v01_ch19_")
                .putExtra("number",37)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter20")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter18");
        finish();
        startActivity(intent);
    }
}