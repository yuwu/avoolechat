package com.avoole.common;

import com.avoole.common.util.Timber;

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

    public static void e(String tag, String message) {
        Timber.e("[%s] %s", tag, message);
    }

    public static void d(String tag, String message) {
        Timber.d("[%s] %s", tag, message);
    }

    public static void v(String tag, String message) {
        Timber.v("[%s] %s", tag, message);
    }

    public static void v(String format, Object... args) {
        Timber.v(format, args);
    }

    public static void w(String tag, String message) {
        Timber.w("[%s] %s", tag, message);
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
