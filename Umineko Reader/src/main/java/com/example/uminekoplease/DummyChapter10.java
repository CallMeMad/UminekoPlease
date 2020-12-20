package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter10 extends AppCompatActivity {

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
        chapter01List.add(38); musicListChapter01.add(R.raw.fishy_aroma);
        chapter01List.add(37); musicListChapter01.add(R.raw.fishy_aroma);
        chapter01List.add(36); musicListChapter01.add(R.raw.moonlit_night);
        chapter01List.add(34); musicListChapter01.add(R.raw.moonlit_night);
        chapter01List.add(33); musicListChapter01.add(R.raw.core);
        chapter01List.add(25); musicListChapter01.add(R.raw.core);
        chapter01List.add(24); musicListChapter01.add(R.raw.scar_sound);
        chapter01List.add(16); musicListChapter01.add(R.raw.scar_sound);
        chapter01List.add(15); musicListChapter01.add(R.raw.minute_darkness);
        chapter01List.add(8); musicListChapter01.add(R.raw.minute_darkness);
        chapter01List.add(7); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(2); musicListChapter01.add(R.raw.witch_of_the_painting);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 10")
                .putExtra("IDName","umineko_v01_ch10_")
                .putExtra("number",37)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter11")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter09");
        finish();
        startActivity(intent);
    }
}