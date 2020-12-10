package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Integer> chapter01List = new ArrayList<Integer>();
        ArrayList<Integer> musicListChapter01 = new ArrayList<Integer>();
        chapter01List.add(41);
        chapter01List.add(44);
        chapter01List.add(39);
        chapter01List.add(30);
        chapter01List.add(29);
        chapter01List.add(24);
        chapter01List.add(23);
        chapter01List.add(20);
        chapter01List.add(19);
        chapter01List.add(12);
        chapter01List.add(11);
        chapter01List.add(10);
        chapter01List.add(9);
        chapter01List.add(2);
        chapter01List.add(0);
        musicListChapter01.add(2);
        musicListChapter01.add(R.raw.novelette);
        musicListChapter01.add(R.raw.novelette);
        musicListChapter01.add(R.raw.novelette);
        musicListChapter01.add(R.raw.hope);
        musicListChapter01.add(R.raw.hope);
        musicListChapter01.add(R.raw.white_shadow);
        musicListChapter01.add(R.raw.white_shadow);
        musicListChapter01.add(R.raw.doorway_of_summer);
        musicListChapter01.add(R.raw.doorway_of_summer);
        musicListChapter01.add(R.raw.son_vent);
        musicListChapter01.add(R.raw.son_vent);
        musicListChapter01.add(R.raw.sukashiyuri);
        musicListChapter01.add(R.raw.sukashiyuri);
        musicListChapter01.add(1);
        Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class).putExtra("start",getIntent().getBooleanExtra("start",true)).putExtra("ChapterName","Chapter2").putExtra("IDName","umineko_v01_ch02_").putExtra("number",40).putExtra("musicNumber",chapter01List.size()).putExtra("ArrayCase",chapter01List).putExtra("musicName",musicListChapter01).putExtra("Nextclass","com.example.uminekoplease.DummyChapter03").putExtra("Prevclass","com.example.uminekoplease.DummyChapter01");
        startActivity(intent);
        finish();
    }
}