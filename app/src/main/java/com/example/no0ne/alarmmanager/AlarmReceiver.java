package com.example.no0ne.alarmmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by no0ne on 4/1/18.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private MediaPlayer mMediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {
//        mMediaPlayer = MediaPlayer.create(c
// ontext, Settings.System.DEFAULT_RINGTONE_URI);
//        mMediaPlayer.start();
        Intent startIntent = new Intent(context, RingtonePlayingService.class);
        context.startService(startIntent);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

//        Log.e("NOTIFICATION", "notification() is called!");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle("Notification!")
                .setContentText("Your Alarm.")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
