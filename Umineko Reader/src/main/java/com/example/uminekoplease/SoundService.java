package com.example.uminekoplease;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class SoundService extends Service {
    private static MediaPlayer player;
    float volume;

    public void onCreate(){
        super.onCreate();
        this.player = new MediaPlayer();
        this.volume=1;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, intent.getIntExtra("ID",0)); //select music file
        player.setLooping(true); //set looping
        player.setVolume(100,100);
        player.start();
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
                player.setVolume(volume,volume);
                volume-=0.0005f;
            }while(volume>0);
            player.stop();
            player.release();
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



