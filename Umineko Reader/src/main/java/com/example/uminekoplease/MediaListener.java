package com.example.uminekoplease;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;

public class MediaListener {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer mMediaPlayer2;
    private AssetFileDescriptor afd;
    private int main;
    private int CurrentID;
    private static final String TAG = "tag";
    private float volume;
    private float volume2;
    private boolean reset;
    private final Resources mRes;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MediaListener(Resources res){
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        mMediaPlayer2 = new MediaPlayer();
        mMediaPlayer2.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        reset=false;
        mRes=res;
        main=0;
    }
    protected void startMusic(int ID,boolean looping,int main)
    {
        //Add the new media to be read
        try {
            Log.i("Ressource",String.valueOf(R.raw.rengoku));
            afd = mRes.openRawResourceFd(ID);
            if (afd == null) {
                Log.i(TAG,"afd null");}
            if(main==1)
            {
                mMediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                mMediaPlayer.prepareAsync(); // prepare async to not block main thread
            }
            else {
                mMediaPlayer2.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                mMediaPlayer2.prepareAsync(); // prepare async to not block main thread
            }
            afd.close();
        } catch (IOException e) {
            //Toast.makeText(context, "mp3 not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        //mp3 will be started after completion of preparing
        if(main==1)
        {
            mMediaPlayer.setOnPreparedListener(player -> {
                player.setVolume(0,0);
                player.setLooping(looping);
                volume=0;
                player.start();
                //Fade In
                new FadeIn().execute();
                volume=1;
            });
        }
        else
        {
            mMediaPlayer2.setOnPreparedListener(player -> {
                player.setVolume(0,0);
                player.setLooping(looping);
                volume2=0;
                player.start();
                //Fade In
                new FadeIn().execute();
                volume2=1;
            });
            CurrentID=ID;
        }

    }
    protected void stopMusic(){
        if(mMediaPlayer2.isPlaying()){mMediaPlayer2.stop();}
        if (mMediaPlayer.isPlaying()) {mMediaPlayer.stop();}
        mMediaPlayer.release();
        mMediaPlayer2.release();
    }
    protected Integer getID()
    {
        return CurrentID;
    }
    public void setmMediaPlayer(int ID,boolean looping)
    {
        //We reset it
        if(main==0)
        {
            main=1;
            startMusic(ID,looping,1);
        }
        if(main==1)
        {
            //We fade out our media
            new FadeOut().execute();
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            main=2;
            startMusic(ID,looping,main);

        }
        else
        {
            //We fade out our media
            new FadeOut().execute();
            mMediaPlayer2.stop();
            mMediaPlayer2.reset();
            main=1;
            startMusic(ID,looping,main);
        }

    }
    private class FadeIn extends AsyncTask<Void, Void, Void>
    {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... locs) {
            //If a media is playing
                //Fade In
            if(main==1)
            {
                while (volume<1)
                {
                    volume+=0.001f;

                    mMediaPlayer.setVolume(volume,volume);
                }
            }
            else
            {
                while (volume2<1)
                {
                    volume2+=0.001f;

                    mMediaPlayer2.setVolume(volume2,volume2);
                }
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
    private class FadeOut extends AsyncTask<Void, Void, Void>
    {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... locs) {
            //If a media is playing
            if(main==1)
            {
                while (volume>0)
                {
                    volume-=0.001f;
                    mMediaPlayer.setVolume(volume,volume);
                }
            }
            else
            {
                while (volume2>0)
                {
                    volume2-=0.001f;
                    mMediaPlayer2.setVolume(volume2,volume2);
                }
            }
            //Fade In
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            reset=true;
            super.onPostExecute(result);
        }
    }
}
