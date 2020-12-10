//package com.example.uminekoplease;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.util.Log;
//
//public class SoundServiceBroadcastReceiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(final Context context, Intent intent) {
//        Log.d(TAG, "about to start timer " + context.toString());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            scheduleJob(context);
//        } else {
//            ProcessMainClass bck = new ProcessMainClass();
//            bck.launchService(context);
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public static void scheduleJob(Context context) {
//        if (jobScheduler == null) {
//            jobScheduler = (JobScheduler) context
//                    .getSystemService(JOB_SCHEDULER_SERVICE);
//        }
//        ComponentName componentName = new ComponentName(context,
//                JobService.class);
//        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
//                // setOverrideDeadline runs it immediately - you must have at least one constraint
//                // https://stackoverflow.com/questions/51064731/firing-jobservice-without-constraints
//                .setOverrideDeadline(0)
//                .setPersisted(true).build();
//        jobScheduler.schedule(jobInfo);
//    }
//}
