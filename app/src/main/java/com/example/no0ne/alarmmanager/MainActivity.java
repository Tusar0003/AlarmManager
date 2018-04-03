package com.example.no0ne.alarmmanager;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TimePicker mTimePicker;

    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimePicker = findViewById(R.id.time_picker);
        findViewById(R.id.button_set_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                if (Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            mTimePicker.getHour(),
                            mTimePicker.getMinute(),
                            0
                    );
                } else {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            mTimePicker.getCurrentHour(),
                            mTimePicker.getCurrentMinute(),
                            0
                    );
                }

                setAlarm(calendar.getTimeInMillis());
//                notification();
            }
        });

        findViewById(R.id.button_stop_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopIntent = new Intent(getApplicationContext(), RingtonePlayingService.class);
                getApplicationContext().stopService(stopIntent);
            }
        });
    }

    private void setAlarm(long timeInMillis) {
        mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(this, AlarmReceiver.class);
        mPendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, mPendingIntent);

        Toast.makeText(this, "AlarmReceiver is set.", Toast.LENGTH_SHORT).show();
    }

//    private void notification() {
//        Log.e("NOTIFICATION", "notification() is called!");
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//
//        Notification notification = builder.setContentTitle("Notification!")
//                .setContentText("Your Alarm.")
//                .setAutoCancel(true)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentIntent(mPendingIntent)
//                .build();
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notification);
//    }
}
