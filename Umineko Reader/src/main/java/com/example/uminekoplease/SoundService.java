package com.example.uminekoplease;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SoundService extends Service {

    /**MediaPlayer permettant de jouer la musique de fond*/
    private MediaPlayer myMediaPlayer;
    /**MediaPlayer jouant les sons d'ambiances*/
    private MediaPlayer[] se;
    /**MediaPlayer jouant les voix*/
    private MediaPlayer voice;
    /**Volume sonore*/
    private float[] volume;
    /**TAG*/
    private static final String TAG = "tag";
    /**Variable représentant l'état des bgm*/
    private int bgmState;
    /**Variable représentant l'état des voix*/
    private int voiceState;
    /**Path de la bgm*/
    private String IDBgm;
    /**Path de la voix*/
    private String IDvoice;
    /**Nombre de sons d'ambiance à jouer simultanément*/
    private int numberSe;
    /**Position de la Se dans le tableau de player*/
    private HashMap<String, ArrayList<Integer>> SeMap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate() {
        super.onCreate();
        // création du MediaPlayer
        myMediaPlayer = new MediaPlayer();
        myMediaPlayer.setAudioAttributes(
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
        // création d'un tableau de multiplayer pour les sons d'ambiances
        se=new MediaPlayer[5];
        // Set le premier mediaPlayer
        numberSe=0;
        se[0] = new MediaPlayer();
        se[0].setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        volume = new float[]{1, 1, 1, 1};
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("unchecked")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // récupère le Path à partir des Intents
        IDBgm=intent.getStringExtra("ID");
        IDvoice=intent.getStringExtra("voice");
        SeMap = (HashMap<String, ArrayList<Integer>>) intent.getSerializableExtra("MapSe");
        // récupère les états des différentes types de musique
        bgmState = intent.getIntExtra("bgmState", 2);
        voiceState = intent.getIntExtra("voiceState", 2);
        //S'il n'y a des SE
        if(SeMap!=null)
        {
            //On initialise le nombre de Mediaplayer qu'il faut
            while (numberSe<SeMap.size())
            {
                se[numberSe] = new MediaPlayer();
                se[numberSe].setAudioAttributes(
                        new AudioAttributes
                                .Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .build());
                numberSe++;
            }
        }
        //Lance la musique de façon Asynchrone
        new AsyncCaller().execute();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i("I AM HERE", "HELLO");
        // new AsyncCaller().execute();
        if (myMediaPlayer != null) {
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }
        for (MediaPlayer mediaPlayer : se) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
        if (voice != null) {
            voice.stop();
            voice.release();
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

    private void ResetBgm() {
        //Si le media est en train de jouer
        if (myMediaPlayer.isPlaying()) {
            //Fade out
            do {
                myMediaPlayer.setVolume(volume[0], volume[0]);
                volume[0] -= 0.002f;
            } while (volume[0] > 0);
            myMediaPlayer.stop();
        }
        myMediaPlayer.reset();
    }

    private void SetBgm()
    {
        try
        {
            myMediaPlayer.setDataSource(IDBgm);
            myMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myMediaPlayer.setOnPreparedListener(player -> new FadeIn().execute());
    }
    private void SetBgmFromPackage() {
        try {
            AssetFileDescriptor afd = getAssets().openFd(IDBgm);
            if (afd == null) {
                Log.i(TAG, "afd null");
            }
            assert afd != null;
            myMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            myMediaPlayer.prepareAsync(); // prepare async to not block main thread
        } catch (IOException e) {
            //Toast.makeText(context, "ogg not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        //mp3 will be started after completion of preparing...
        myMediaPlayer.setOnPreparedListener(player -> new FadeIn().execute());
    }

    private void ResetSe_1(int index) {
        if(se[index]!=null) {
            if (se[index].isPlaying()) {
                do {
                    se[index].setVolume(volume[1], volume[1]);
                    volume[1] -= 0.002f;
                } while (volume[1] > 0);
                se[index].stop();
            }
            se[index].reset();
        }
    }

    private void SetSe_1(String ID,int index) {
        try {
            AssetFileDescriptor afd = getAssets().openFd(ID);
            if (afd == null) {
                Log.i(TAG, "afd null");
            }
            assert afd != null;
            Log.i("SE SET",ID);
            se[index].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            se[index].prepareAsync(); // prepare async to not block main thread
        } catch (IOException e) {
            // Toast.makeText(context, "ogg not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        //mp3 will be started after completion of preparing...
        se[index].setOnPreparedListener(player -> {
            se[index].setVolume(0.72f,0.72f);
            se[index].setLooping(true);
            se[index].start();
        });
    }

    private void ResetVoice() {
        if (voice.isPlaying()) {
            do {
                voice.setVolume(volume[3], volume[3]);
                volume[3] -= 0.02f;
            } while (volume[3] > 0);
            voice.stop();
        }
        voice.reset();
    }

    private void SetVoice() {
        try {
            AssetFileDescriptor afd = getAssets().openFd(IDvoice);
            if (afd == null) {
                Log.i(TAG, "afd null");
            }
            assert afd != null;
            voice.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            voice.prepareAsync(); // prepare async to not block main thread
        } catch (IOException e) {
            //Toast.makeText(context, "ogg not found", Toast.LENGTH_SHORT).show();
            //e.printStackTrace();
        }
        //mp3 will be started after completion of preparing...
        voice.setOnPreparedListener(player -> {
            voice.setVolume(1f, 1f);
            voice.start();
        });
    }

    //We use it to fade away our sound when we stop our service
    @SuppressLint("StaticFieldLeak")
    @SuppressWarnings("unchecked")
    private class AsyncCaller extends AsyncTask<Void, Void, Void> {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... locs) {
            // state 0 veut dire changement de musique
            if (bgmState != 0) {
                ResetBgm();
            }
            if(SeMap!=null)
            {
                // on parcourt la map de Se
                for (Map.Entry mapentry : SeMap.entrySet()) {
                    ArrayList<Integer> temp = (ArrayList<Integer>) mapentry.getValue();
                    //Code 0 pour changement de se sur le multiplayer
                    if (temp.get(1)!= 0) {
                        ResetSe_1(temp.get(0));
                    }
                    //Code 2 pour set le se sur un multiplayer non utilisé
                    if (temp.get(1)== 2) {
                        SetSe_1((String)mapentry.getKey(),temp.get(0));
                    }
                }
            }
            // code 0 pour reset la voix
            if (voiceState != 0) {
                ResetVoice();
            }
            // code 2 pour set la bgm du moment que l'id n'est pas null
            if (bgmState == 2 && IDBgm != null) {
                SetBgm();
            }
            // code 2 pour set la voix du moment que l'id n'est pas null
            if (voiceState == 2 && IDvoice != null) {
                SetVoice();
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

    @SuppressLint("StaticFieldLeak")
    private class FadeIn extends AsyncTask<Void, Void, Void> {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... locs) {
            //If a media is playing
            //Fade In
            myMediaPlayer.setVolume(0, 0);
            myMediaPlayer.setLooping(true);
            volume[0] = 0;
            myMediaPlayer.start();
            while (volume[0] < 1) {
                volume[0] += 0.01f;
                myMediaPlayer.setVolume(volume[0], volume[0]);
            }
            volume[0] = 1;
            myMediaPlayer.setVolume(volume[0], volume[0]);

            return null;
        }
    }
}



