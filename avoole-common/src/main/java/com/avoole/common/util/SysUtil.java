package com.avoole.common.util;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;

import com.avoole.common.Applog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.List;

public class SysUtil
{
    public static Context sApp;

    public static void setApplication(Context app)
    {
        sApp = app;
    }

    public static Context getApplication() {
        return sApp;
    }

    public static boolean isForeground() {
        if (sApp == null) return false;
        int screenOn = isScreenOn();
        return (_isForegroud(sApp)) && (screenOn == 1);
    }

    private static boolean _isForegroud(Context context) {
        if (context == null) return false;

        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return false;
        }
        List tasks = null;
        try {
            tasks = am.getRunningTasks(1);
        }
        catch (Throwable e) {
        }
        if (tasks == null) {
            return false;
        }

        if (!tasks.isEmpty())
        {
            ComponentName topActivity0 = ((ActivityManager.RunningTaskInfo)tasks.get(0)).topActivity;

            if (topActivity0.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static int isScreenOn()
    {
        if (sApp == null) return 1;
        try
        {
            Method mReflectScreenState = PowerManager.class.getMethod("isScreenOn", new Class[0]);

            PowerManager pm = (PowerManager)sApp.getSystemService(Context.POWER_SERVICE);

            boolean screenState = ((Boolean)mReflectScreenState.invoke(pm, new Object[0])).booleanValue();

            return screenState ? 1 : 0;
        }
        catch (Exception nsme)
        {
        }
        return 0;
    }

    public static final boolean isScreenLocked(Context context) {
        KeyguardManager mKeyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
        return mKeyguardManager.inKeyguardRestrictedInputMode();
    }


    private static String getProcessNameFromProc()
    {
        BufferedReader cmdlineReader = null;
        try {
            cmdlineReader = new BufferedReader(new InputStreamReader(new FileInputStream(new StringBuilder().append("/proc/").append(Process.myPid()).append("/cmdline").toString())));

            StringBuilder processName = new StringBuilder();
            int c;
            while ((c = cmdlineReader.read()) > 0) {
                processName.append((char)c);
            }
            return processName.toString();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (cmdlineReader != null) {
                try {
                    cmdlineReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static String getCurProcessName(Context context) {
        String currentProcessName = "";
        int pid = Process.myPid();

        ActivityManager mActivityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        if (mActivityManager == null) {
            return "";
        }
        List<ActivityManager.RunningAppProcessInfo> apps = mActivityManager.getRunningAppProcesses();

        if ((apps == null) || (apps.isEmpty())) {
            String procName = getProcessNameFromProc();
            return procName;
        }
        if (apps == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : apps) {
            if (appProcess.pid == pid) {
                currentProcessName = appProcess.processName;
                break;
            }
        }
        return currentProcessName;
    }

    public static String checkHttpUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        Applog.v("SysUtil", new StringBuilder().append("url before:").append(url).toString());
        StringBuilder resultBuilder = new StringBuilder();
        if (url.startsWith("//")) {
            resultBuilder.append("http:");
            resultBuilder.append(url);
        } else {
            resultBuilder.append(url);
        }
        String result = resultBuilder.toString();
        Applog.v("SysUtil", new StringBuilder().append("url after:").append(result).toString());
        return result;
    }


    public static String getMainProcessName(Context context)
    {
        String mainProcessName = "";
        Intent it = new Intent("android.intent.action.MAIN");
        String packageName = context.getPackageName();
        Applog.i("SysUtil", new StringBuilder().append("current context packageName:").append(packageName).toString());
        it.setPackage(packageName);

        ResolveInfo info = context.getPackageManager().resolveActivity(it, 64);

        if ((info != null) && (info.activityInfo != null)) {
            mainProcessName = info.activityInfo.processName;
        }

        if ((TextUtils.isEmpty(mainProcessName)) || (mainProcessName.contains("system"))) {
            mainProcessName = context.getPackageName();
        }

        return mainProcessName;
    }

    public static boolean isMainProcess()
    {
        int isMainProcess = 0;

        String currentProcessName = getCurProcessName(sApp);
        if (TextUtils.isEmpty(currentProcessName)) {
            isMainProcess = 0;
            return false;
        }
        String mainPrcocessName = getMainProcessName(sApp);
        String packageName = sApp.getPackageName();
        Applog.i("WxUtil", new StringBuilder().append("current process name:").append(currentProcessName).append("---main process name:").append(mainPrcocessName).toString());
        boolean ret = (currentProcessName.equals(mainPrcocessName)) || (currentProcessName.equals(packageName));
        if (ret)
            isMainProcess = 1;
        else {
            isMainProcess = 0;
        }
        return ret;
    }
}