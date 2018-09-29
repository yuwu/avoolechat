package com.avoole.im;

import com.avoole.im.common.BuildConfig;
import com.avoole.im.util.Timber;

/**
 * Created by wuyu on 18/9/28.
 */

public class Applog {
    public static String TAG = "avoole";

    public static final boolean DEBUG = BuildConfig.DEBUG;

    static {
        if (DEBUG) {
            Timber.tag(TAG);
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static void setTag(String tag) {
        TAG = tag;
    }

    public static void v(String format, Object... args) {
        Timber.v(format, args);
    }

    public static void d(String format, Object... args) {
        Timber.d(format, args);
    }

    public static void e(String format, Object... args) {
        Timber.e(format, args);
    }

    public static void i(String format, Object... args) {
        Timber.i(format, args);
    }

    public static void e(Throwable tr, String format, Object... args) {
        Timber.e(tr, format, args);
    }

    public static void wtf(String format, Object... args) {
        Timber.w(format, args);
    }

    public static void wtf(Throwable tr, String format, Object... args) {
        Timber.wtf(tr, format, args);
    }
}
