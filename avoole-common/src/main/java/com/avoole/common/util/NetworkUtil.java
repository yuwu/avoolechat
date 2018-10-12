package com.avoole.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

public class NetworkUtil
{
    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isConnected();
    }

    public static boolean isWifi(Context context){
        NetworkInfo networkInfo = getNetworkInfo(context);
        if ((networkInfo != null) && (networkInfo.isAvailable())) {
            return (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) && (networkInfo.getState() == NetworkInfo.State.CONNECTED);
        }
        return false;
    }

    public static NetworkInfo getNetworkInfo(Context context){
        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                Network network = cm.getActiveNetwork();
                networkInfo = cm.getNetworkInfo(network);
            }
            return networkInfo;
        }
        catch (Exception e)
        {
        }
        return null;
    }

    public static WifiInfo getWifiInfo(Context context){
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info;
    }
}