package com.example.personlisterfinal;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;

public class NotificationSender extends Service{

    public static final String CHANNEL_ID = "CHANNEL_RETURN_TO_APP";
    Activity context;

    public NotificationSender(Activity context){
        this.context = context;
    }
    public void runNotification(){
        Intent resultIntent = context.getIntent();
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context,
                0,
                resultIntent,
                0);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Person Lister")
                        .setContentText("Don't forget about me...")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("I'm still here..."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
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
        mNotificationManager.notify(0, builder.build()) ;
    }

    public Intent getPreviousIntent(){
        ContextWrapper wrapper = new ContextWrapper(context.getApplicationContext());
        SharedPreferences sharedPreferences = wrapper.getSharedPreferences("userSharedPreference", context.MODE_PRIVATE);
        String id = sharedPreferences.getString("lastActivityOpen", "ListUsers");
        final String LIST_USERS_ID = "ListUsers";
        final String EDIT_USER_ID = "EditUser";
        final String PERSON_DISPLAY_ID = "PersonDisplayScreen";

        Intent intent = new Intent(context, ListUsers.class);

        switch(id){
            case LIST_USERS_ID:
                intent = new Intent(context, ListUsers.class);
                break;
            case EDIT_USER_ID:
                intent = new Intent(context, EditUser.class);
                break;
            case PERSON_DISPLAY_ID:
                intent = new Intent(context, PersonDisplayScreen.class);
                break;
        }
        return intent;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}