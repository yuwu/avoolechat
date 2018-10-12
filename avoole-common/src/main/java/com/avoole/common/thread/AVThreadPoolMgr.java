package com.avoole.common.thread;

import android.os.Build;

import com.avoole.common.Applog;
import com.avoole.common.thread.priority.AVDefaultExecutor;

public class AVThreadPoolMgr {
    private static String TAG = "WXThreadPoolMgr";

    private static AVThreadPoolMgr sInstance = new AVThreadPoolMgr();

    public static AVThreadPoolMgr getInstance()
    {
        return sInstance;
    }

    public void doAsyncRun(Runnable command, boolean isLocal)
    {
        if (command == null) {
            Applog.e(TAG, "runnable is null");
            return;
        }
        if ((shouldRunInCustomThread()) || (isLocal)) {
            AVDefaultExecutor.getInstance().executeLocal(command);
        }
        else
        {
            AVDefaultExecutor.getInstance().executeHttp(command);
        }
    }

    public void doAsyncRun(Runnable command) {
        doAsyncRun(command, false);
    }

    private boolean shouldRunInCustomThread()
    {
        return (Build.MODEL.equals("X909")) && (Build.BRAND.equals("OPPO"));
    }
}
