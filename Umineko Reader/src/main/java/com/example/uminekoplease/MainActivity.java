package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    private boolean toggle;
    private Intent music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Play Music
        music = new Intent(getApplicationContext(), SoundService.class);
        String path="audio/home_sound.mp3";
        music.putExtra("ID", path).putExtra("bgm",2);
        startService(music);

        //Toggle Permet de gérer en 1 Bouton l'arrêt et la repris
        toggle=true;

        ImageView imageView = (ImageView) findViewById(R.id.music);

        //Button to play/stop music
        imageView.setOnClickListener(v -> {
            if(!toggle)
            {
                toggle=true;
                imageView.setImageResource(R.drawable.musicplaying1);
                startService(music);
            }
            else
            {
                stopService(music);
                imageView.setImageResource(R.drawable.musicmuted1);
                toggle=false;
            }

        });

        //Button to go to my chapter list
        //Variable Declaration
        Button button = findViewById(R.id.button);
        button.setText(R.string.Episode1);
        button.setOnClickListener(view -> {
           Intent ChapListEp1 = new Intent(getApplicationContext(), ChapListEp1.class);
           stopService(music);
           toggle=false;
           ChapListEp1.putExtra("String","1");
           startActivity(ChapListEp1);
        });

        //Button to go to my chapter list
        Button button2 = findViewById(R.id.button2);
        button2.setText(R.string.Episode2);
        button2.setOnClickListener(view -> {
            Intent ChapListEp1 = new Intent(getApplicationContext(), ChapListEp1.class);
            stopService(music);
            toggle=false;
            ChapListEp1.putExtra("String","2");
            startActivity(ChapListEp1);
        });
        //Button to go to my chapter list
        Button button3 = findViewById(R.id.button3);
        button3.setText(R.string.Episode3);
        button3.setOnClickListener(view -> {
            Intent ChapListEp1 = new Intent(getApplicationContext(), ChapListEp1.class);
            stopService(music);
            toggle=false;
            ChapListEp1.putExtra("String","3");
            startActivity(ChapListEp1);
        });
    }
    //Destructor
    protected void onDestroy() {
        //stop service and stop music
        stopService(music);
        Log.i("MAINACT", "onDestroy!");
        super.onDestroy();
        //We don't finish because we want to be able to use back to go back here
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
