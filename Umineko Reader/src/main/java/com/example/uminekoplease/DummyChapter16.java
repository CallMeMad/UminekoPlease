package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter16 extends AppCompatActivity {

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
        chapter01List.add(54); musicListChapter01.add(R.raw.closed_my_heart);
        chapter01List.add(48); musicListChapter01.add(R.raw.closed_my_heart);
        chapter01List.add(47); musicListChapter01.add(R.raw.magic_butterfly);
        chapter01List.add(40); musicListChapter01.add(R.raw.magic_butterfly);
        chapter01List.add(39); musicListChapter01.add(R.raw.ahaha);
        chapter01List.add(38); musicListChapter01.add(R.raw.stab);
        chapter01List.add(37); musicListChapter01.add(R.raw.requiem);
        chapter01List.add(23); musicListChapter01.add(R.raw.requiem);
        chapter01List.add(22); musicListChapter01.add(R.raw.mind);
        chapter01List.add(7); musicListChapter01.add(R.raw.mind);
        chapter01List.add(6); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(2); musicListChapter01.add(R.raw.witch_in_gold_cembalo);
        chapter01List.add(0); musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class)
                .putExtra("start",getIntent().getBooleanExtra("start",true))
                .putExtra("ChapterName","Chapter 16")
                .putExtra("IDName","umineko_v01_ch16_")
                .putExtra("number",53)
                .putExtra("musicNumber",chapter01List.size())
                .putExtra("ArrayCase",chapter01List)
                .putExtra("musicName",musicListChapter01)
                .putExtra("Nextclass","com.example.uminekoplease.DummyChapter17")
                .putExtra("Prevclass","com.example.uminekoplease.DummyChapter15");
        finish();
        startActivity(intent);
    }
}