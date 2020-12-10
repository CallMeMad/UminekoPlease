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
//    private Timer timer;
//    private TimerTask timerTask;
//    long oldTime=0;
//    public int counter=0;

//    public SoundService(Context context)
//    {
//        super();
//    }
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
//        startTimer();
        return START_REDELIVER_INTENT;
    }

//    private void startTimer() {
//        timer = new Timer();
//        initializeTimerTask();
//        timer.schedule(timerTask, 1000, 1000);
//    }

//    private void initializeTimerTask() {
//        timerTask = new TimerTask() {
//            public void run() {
//                Log.i("in timer", "in timer ++++  "+ (counter++));
//            }
//        };
//    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
        new AsyncCaller().execute();
//        Intent broadcastIntent = new Intent(this, SoundServiceBroadcastReceiver.class);
//        sendBroadcast(broadcastIntent);
//        stoptimertask();
        stopSelf();

    }

//    private void stoptimertask() {
//        //stop the timer, if it's not already null
//        if (timer != null) {
//            timer.cancel();
//            timer = null;
//        }
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onPause()
    {
        player.pause();
    }
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

            //this method will be running on UI thread
           // pdLoading.dismiss();
        }
    }
}



