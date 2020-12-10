package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DummyChapter01 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        {
            ArrayList<Integer> chapter01List = new ArrayList<Integer>();
            ArrayList<Integer> musicListChapter01 = new ArrayList<Integer>();
            chapter01List.add(31);
            chapter01List.add(28);
            chapter01List.add(27);
            chapter01List.add(12);
            chapter01List.add(11);
            chapter01List.add(0);
            musicListChapter01.add(3);
            musicListChapter01.add(R.raw.rengoku);
            musicListChapter01.add(R.raw.sea);
            musicListChapter01.add(R.raw.sea);
            musicListChapter01.add(R.raw.hour_of_darkness);
            musicListChapter01.add(1);
            Intent intent = new Intent(getApplicationContext(), Episode01Chapter03.class).putExtra("start",getIntent().getBooleanExtra("start",true)).putExtra("ChapterName","Chapter1").putExtra("IDName","umineko_v01_ch01_").putExtra("number",30).putExtra("musicNumber",chapter01List.size()).putExtra("ArrayCase",chapter01List).putExtra("musicName",musicListChapter01).putExtra("Nextclass","com.example.uminekoplease.DummyChapter02").putExtra("Prevclass","false");
            startActivity(intent);
            finish();
        }

    }
}
