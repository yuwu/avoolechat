package com.avoole.mm;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class Avoole extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
