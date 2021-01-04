package com.example.uminekoplease;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.IOException;

public class SoundService extends Service {

    private  MediaPlayer myMediaPlayer;
    float volume;
    private static final String TAG = "tag";
    private Context context;
    private AssetFileDescriptor afd;
    private boolean looping;
    private int ID;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(){
        super.onCreate();
        myMediaPlayer = new MediaPlayer();
        myMediaPlayer.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        context = getApplicationContext();
        this.volume=1;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            ID=intent.getIntExtra("ID",0);
            looping= intent.getBooleanExtra("looping",true);
            new AsyncCaller().execute();
            return START_STICKY;
    }


    @Override
    public void onDestroy() {
        Log.i("I AM HERE", "HELLO");
       // new AsyncCaller().execute();
        if(myMediaPlayer!=null)
        {
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }
        stopSelf();
        super.onDestroy();
        //mMediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    //We use it to fade away our sound when we stop our service
    @SuppressLint("StaticFieldLeak")
    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... locs) {
            //If a media is playing
            if(myMediaPlayer.isPlaying())
            {
                do {
                    myMediaPlayer.setVolume(volume,volume);
                    volume-=0.0001f;
                }while(volume>0);
                myMediaPlayer.stop();
                myMediaPlayer.reset();
            }
            //Add the new media to be read
            try {
                afd = context.getResources().openRawResourceFd(ID);
                if (afd == null) {Log.i(TAG,"afd null");}
                myMediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                afd.close();
                myMediaPlayer.prepareAsync(); // prepare async to not block main thread
            } catch (IOException e) {
                Toast.makeText(context, "mp3 not found", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            //mp3 will be started after completion of preparing...
            myMediaPlayer.setOnPreparedListener(player -> {
                player.setVolume(0,0);
                player.setLooping(looping);
                volume=0;
                player.start();
                //Fade In
                while (volume<1)
                {
                    volume+=0.001f;
                    myMediaPlayer.setVolume(volume,volume);
                }
            });
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}



