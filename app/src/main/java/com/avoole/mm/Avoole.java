package com.avoole.mm;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.mobileim.fundamental.widget.roundedimage.RoundedImageView;
import com.alibaba.wxlib.util.SysUtil;

public class Avoole extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SysUtil.setApplication(this);
        SysUtil.setAppkey("25107003");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
