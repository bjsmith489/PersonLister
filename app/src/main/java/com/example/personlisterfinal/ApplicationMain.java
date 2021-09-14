package com.example.personlisterfinal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class ApplicationMain extends Application
        implements Application.ActivityLifecycleCallbacks, LifecycleOwner {
    Activity currentActivity;
    private int tracker = 0;
    private boolean isActivityChangingConfigurations = false;
    public ApplicationMain(){}
    public ApplicationMain(Activity activity){
        this.currentActivity = activity;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);

        Log.v("TAG:", "-----------ApplicationMain-----------");
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        if(++tracker == 1 && !isActivityChangingConfigurations){
            System.out.println("-----------------------" + tracker);
        }
        Log.i("Track Activity Started", "----_______-----"+activity.getLocalClassName());

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.i("Track Activity Stopped", "----_______-----"+activity.getLocalClassName());
        System.out.println("-----------------------" + tracker);
        isActivityChangingConfigurations = activity.isChangingConfigurations();
        if (--tracker == 0 && !isActivityChangingConfigurations) {
            NotificationSender notificationSender = new NotificationSender(activity);
            notificationSender.runNotification();
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
