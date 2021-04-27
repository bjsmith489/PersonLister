package com.example.personlisterfinal;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;

public class NotificationSender extends Service {
    public static final String CHANNEL_ID = "CHANNEL_RETURN_TO_APP";
    @Override
    public void onCreate() {
       super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent,flags,startId);
        Bundle bundle = intent.getExtras();
        Intent resultIntent = getPreviousIntent(bundle.getString("previous_activity"));
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                this,
                0,
                resultIntent,
                0);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Person Lister")
                        .setContentText("Don't forget about me...")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("I'm still here..."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID ,
                    "REMINDER_NOTIFICATION" ,
                    importance) ;
            notificationChannel.enableLights( true ) ;
            notificationChannel.setLightColor(Color. RED ) ;
            notificationChannel.enableVibration( true ) ;
            notificationChannel.setVibrationPattern( new long []{ 100 , 200 , 300 , 400 , 500 , 400 , 300 , 200 , 400 }) ;
            builder.setChannelId( CHANNEL_ID ) ;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        mNotificationManager.notify(( int ) System. currentTimeMillis (), builder.build()) ;
        return flags;
    }

    public Intent getPreviousIntent(String id){
        final String LIST_USERS_ID = "ListUsers";
        final String EDIT_USER_ID = "EditUser";
        final String PERSON_DISPLAY_ID = "PersonDisplayScreen";
        Intent intent = new Intent(this, ListUsers.class);

        switch(id){
            case LIST_USERS_ID:
                intent = new Intent(this, ListUsers.class);
                break;
            case EDIT_USER_ID:
                intent = new Intent(this, EditUser.class);
                break;
            case PERSON_DISPLAY_ID:
                intent = new Intent(this, PersonDisplayScreen.class);
                break;
        }
        return intent;
    }
}