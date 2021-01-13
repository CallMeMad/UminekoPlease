package com.example.uminekoplease;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    //Variable Declaration
    private Button button;
    private Button button2;
    private Button HDbutton;
    private ImageView imageView;
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

        this.imageView=(ImageView)findViewById(R.id.music);

        //Button to play/stop music
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!toggle)
                {
                    toggle=true;
                    startService(music);
                }
                else
                {
                    stopService(music);
                    toggle=false;
                }

            }
        });

        //Button to go to my chapter list
        this.button= findViewById(R.id.button);
        button.setText("Episode 1");
        button.setOnClickListener(view -> {
           Intent ChapListEp1 = new Intent(getApplicationContext(), ChapListEp1.class);
           stopService(music);
           toggle=false;
           ChapListEp1.putExtra("String","Episode 1");
           startActivity(ChapListEp1);
        });

        //Button to go to my chapter list
        this.button2= findViewById(R.id.button2);
        button2.setText("Episode 2");
        button2.setOnClickListener(view -> {
            Intent ChapListEp1 = new Intent(getApplicationContext(), ChapListEp1.class);
            stopService(music);
            toggle=false;
            ChapListEp1.putExtra("String","Episode 2");
            startActivity(ChapListEp1);
        });
        //Button to go to my chapter list
        this.HDbutton= findViewById(R.id.buttonHD);
        HDbutton.setText("Episode 1 HD");
        HDbutton.setOnClickListener(view -> {
            Intent ChapListEp1 = new Intent(getApplicationContext(), ChapListEp1.class);
            stopService(music);
            toggle=false;
            ChapListEp1.putExtra("String","Episode 1 HD");
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
