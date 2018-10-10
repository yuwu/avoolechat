package com.avoole.im.util;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.SimpleDateFormat;

public class Utils {
    private static final String EXTRA_ISINITDB = "isinitdb";
    private static final String EXTRA_DEF_KEYBOARDHEIGHT = "default_keyboard_height";
    private static int sDefKeyboardHeight = 0;

    public static boolean isInitDb(Context context) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getBoolean(EXTRA_ISINITDB, false);
    }

    public static void setIsInitDb(Context context, boolean b) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings.edit().putBoolean(EXTRA_ISINITDB, b).apply();
    }

    public static int getDefKeyboardHeight(Context context) {
        if (sDefKeyboardHeight == 0) {   //evaluate keyboard height
            sDefKeyboardHeight = getDisplayHeightPixels(context) * 3 / 7;
        }
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        int height = settings.getInt(EXTRA_DEF_KEYBOARDHEIGHT, 0);
        if (height > 0 && sDefKeyboardHeight != height) {
            Utils.setDefaultKeyboardHeight(context, height);
        }
        return sDefKeyboardHeight;
    }

    public static void setDefaultKeyboardHeight(Context context, int height) {
        if (sDefKeyboardHeight != height) {
            final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
            settings.edit().putInt(EXTRA_DEF_KEYBOARDHEIGHT, height).apply();
        }
        Utils.sDefKeyboardHeight = height;
    }

    public static int getDisplayHeightPixels(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 返回当前时间的格式为 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(System.currentTimeMillis());
    }

    //毫秒转秒
    public static String long2String(long time) {
        //毫秒转秒
        int sec = (int) time / 1000;
        int min = sec / 60;    //分钟
        sec = sec % 60;        //秒
        if (min < 10) {    //分钟补0
            if (sec < 10) {    //秒补0
                return "0" + min + ":0" + sec;
            } else {
                return "0" + min + ":" + sec;
            }
        } else {
            if (sec < 10) {    //秒补0
                return min + ":0" + sec;
            } else {
                return min + ":" + sec;
            }
        }
    }
}
