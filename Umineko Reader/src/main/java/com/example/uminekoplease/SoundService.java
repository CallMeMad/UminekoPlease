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

    private MediaPlayer myMediaPlayer;
    private MediaPlayer se1;
    private MediaPlayer se2;
    private MediaPlayer voice;
    private float[] volume;
    private static final String TAG = "tag";
    private Context context;
    private boolean bgm;
    private String[] ID;

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
        se1 = new MediaPlayer();
        se1.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        se2 = new MediaPlayer();
        se2.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        voice = new MediaPlayer();
        voice.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        context = getApplicationContext();
        volume= new float[]{1, 1, 1, 1};
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            ID=new String[]{intent.getStringExtra("ID"),intent.getStringExtra("se1"),intent.getStringExtra("se2"),intent.getStringExtra("voice")};
            bgm=intent.getBooleanExtra("bgm",true);
            Log.i("DATA",ID[0]+"   "+ID[3]);
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
            if(bgm==true) {
                if (myMediaPlayer.isPlaying()) {
                    do {
                        myMediaPlayer.setVolume(volume[0], volume[0]);
                        volume[0] -= 0.002f;
                    } while (volume[0] > 0);
                    myMediaPlayer.stop();
                }
                myMediaPlayer.reset();
            }
            //if a sound effect is playing
            if(se1.isPlaying())
            {
                do {
                    se1.setVolume(volume[1],volume[1]);
                    volume[1]-=0.002f;
                }while(volume[1]>0);
                se1.stop();
            }
            se1.reset();
            if(se2.isPlaying())
            {
                do {
                    se2.setVolume(volume[2],volume[2]);
                    volume[2]-=0.002f;
                }while(volume[2]>0);
                se2.stop();
            }
            se2.reset();
            if(voice.isPlaying())
            {
                do {
                    voice.setVolume(volume[3],volume[3]);
                    volume[3]-=0.002f;
                }while(volume[3]>0);
                voice.stop();
            }
            voice.reset();
            //Add the new media to be read
            try {
                AssetFileDescriptor afd = getAssets().openFd(ID[0]);
                if (afd == null) {Log.i(TAG,"afd null");}
                myMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                myMediaPlayer.prepareAsync(); // prepare async to not block main thread
            } catch (IOException e) {
                Toast.makeText(context, "ogg not found", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            //mp3 will be started after completion of preparing...
            myMediaPlayer.setOnPreparedListener(player -> {
                new FadeIn().execute();
            });
            //Add the new media to be read
            if(ID[1]!=null) {
                try {
                    AssetFileDescriptor afd = getAssets().openFd(ID[1]);
                    if (afd == null) {
                        Log.i(TAG, "afd null");
                    }
                    se1.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    afd.close();
                    se1.prepareAsync(); // prepare async to not block main thread
                } catch (IOException e) {
                    Toast.makeText(context, "ogg not found", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                //mp3 will be started after completion of preparing...
                se1.setOnPreparedListener(player -> {
                    new FadeIn().execute();
                });
            }
            //Add the new media to be read
            if(ID[2]!=null) {
                try {
                    AssetFileDescriptor afd = getAssets().openFd(ID[3]);
                    if (afd == null) {
                        Log.i(TAG, "afd null");
                    }
                    se2.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    afd.close();
                    se2.prepareAsync(); // prepare async to not block main thread
                } catch (IOException e) {
                    Toast.makeText(context, "ogg not found", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                //mp3 will be started after completion of preparing...
                se2.setOnPreparedListener(player -> {
                    new FadeIn().execute();
                });
            }
            //Add the new media to be read
            if(ID[3]!=null)
            {
                try {
                    AssetFileDescriptor afd = getAssets().openFd(ID[3]);
                    if (afd == null) {
                        Log.i(TAG, "afd null");
                    }
                    voice.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    afd.close();
                    voice.prepareAsync(); // prepare async to not block main thread
                } catch (IOException e) {
                    Toast.makeText(context, "ogg not found", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                //mp3 will be started after completion of preparing...
                voice.setOnPreparedListener(player -> {
                    new FadeIn().execute();
                });
            }
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
    private class FadeIn extends AsyncTask<Void, Void, Void> {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... locs) {
            //If a media is playing
            //Fade In

            myMediaPlayer.setVolume(0, 0);
            myMediaPlayer.setLooping(true);
            se1.setVolume(0, 0);
            se1.setLooping(true);
            se2.setVolume(0, 0);
            se2.setLooping(true);
            voice.setVolume(0, 0);
            voice.setLooping(false);
            volume[0] = 0; volume[1] = 0;volume[2] = 0;volume[3] = 0;
            myMediaPlayer.start();
            se1.start();
            se2.start();
            voice.start();
            while (volume[0] < 1) {
                volume[0] += 0.01f; volume[1] += 0.01f; volume[2] += 0.01f;volume[3] += 0.01f;
                myMediaPlayer.setVolume(volume[0], volume[0]);
                se1.setVolume(volume[1], volume[1]);
                se2.setVolume(volume[2], volume[2]);
                voice.setVolume(volume[3], volume[3]);
            }
            volume[0] = 1;volume[1] = 1; volume[2] = 1; volume[3] = 1;
            myMediaPlayer.setVolume(volume[0], volume[0]);
            se1.setVolume(volume[1], volume[1]);
            se2.setVolume(volume[2], volume[2]);
            voice.setVolume(volume[3], volume[3]);
            return null;
        }
    }
}



