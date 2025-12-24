package com.rtech.eazywalls;

import android.app.Application;
import android.content.Context;

public class EazyWalls extends Application {
    private static EazyWalls instance;

    @Override
    public void onCreate() {
        instance=this;
        super.onCreate();
        Core.init(this);
    }
    public static EazyWalls getInstance(){
        return instance;
    }
    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
