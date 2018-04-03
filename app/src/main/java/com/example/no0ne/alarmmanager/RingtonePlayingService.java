package com.example.no0ne.alarmmanager;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by no0ne on 4/1/18.
 */

public class RingtonePlayingService extends Service {

    private Ringtone mRingtone;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        //Uri ringtoneUri = Uri.parse(intent.getExtras().getString("ringtone-uri"));

        this.mRingtone = RingtoneManager.getRingtone(this, alarmSound);
        mRingtone.play();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRingtone.stop();
    }
}
