package com.example.uminekoplease;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class SoundService extends Service {
    private static MediaPlayer mMediaPlayer;
    float volume;

    public void onCreate(){
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        this.volume=1;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        mMediaPlayer = MediaPlayer.create(this, intent.getIntExtra("ID",0)); //select music file
        mMediaPlayer.setLooping(true); //set looping
        mMediaPlayer.setVolume(100,100);
        mMediaPlayer.start();

        return START_REDELIVER_INTENT;
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
        new AsyncCaller().execute();
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //We use it to fade away our sound when we stop our service
    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            do {
                mMediaPlayer.setVolume(volume,volume);
                volume-=0.0005f;
            }while(volume>0);
            mMediaPlayer.stop();
            mMediaPlayer.release();
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



