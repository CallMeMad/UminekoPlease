//package com.example.uminekoplease;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//
//public class SoundServiceBroadcastReceiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(final Context context, Intent intent) {
//        if (intent.getStringExtra("action").equals("pause")) {
//
//        } else {
//            if Action for Play ...
//        }
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        //... Register BroadcastReceiver
//        registerReceiver(new SoundServiceBroadcastReceiver(), new IntentFilter(
//                "com.xx.PAUSE_MUSIC_ACTION"));
//        HN.post(new PlayMusic());
//
//    }
//}
