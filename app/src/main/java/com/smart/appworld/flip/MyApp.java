package com.smart.appworld.flip;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

    }
}
