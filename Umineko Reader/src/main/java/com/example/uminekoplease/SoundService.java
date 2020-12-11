package com.example.uminekoplease;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class SoundService extends Service {
    private  MediaPlayer mMediaPlayer;
    float volume;

    @Override
    public void onCreate(){
        super.onCreate();
        mMediaPlayer = null;
        this.volume=1;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer = MediaPlayer.create(this, intent.getIntExtra("ID",0)); //select music file
            mMediaPlayer.setLooping(true); //set looping
            mMediaPlayer.setVolume(100,100);
            mMediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i("I AM HERE", "HELLO");
       // new AsyncCaller().execute();
        for(int i=0;i<100;i++)
        {mMediaPlayer.setVolume(volume,volume);
            volume-=0.01f;}
        mMediaPlayer.stop();
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
        @Override
        protected Void doInBackground(Void... locs) {
            do {
                mMediaPlayer.setVolume(volume,volume);
                volume-=0.001f;
            }while(volume>0);
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



