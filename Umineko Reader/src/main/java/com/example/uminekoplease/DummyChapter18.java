package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter18 extends AppCompatActivity {

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
        chapter01List.add(48); musicListChapter01.add(2);
        chapter01List.add(46); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(36); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(35); musicListChapter01.add(R.raw.sukashiyuri);
        chapter01List.add(30); musicListChapter01.add(R.raw.sukashiyuri);
        chapter01List.add(29); musicListChapter01.add(R.raw.worldend_solo);
        chapter01List.add(20); musicListChapter01.add(R.raw.worldend_solo);
        chapter01List.add(19); musicListChapter01.add(R.raw.play);
        chapter01List.add(2); musicListChapter01.add(R.raw.play);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 18")
                .putExtra("IDName","umineko_v01_ch18_")
                .putExtra("number",45)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter19")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter17");
        finish();
        startActivity(intent);
    }
}