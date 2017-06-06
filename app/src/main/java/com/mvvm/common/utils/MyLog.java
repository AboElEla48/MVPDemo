package com.mvvm.common.utils;

import android.util.Log;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This is a logger class
 */
public class MyLog
{
    private MyLog() {}

    public static void logError(String tag, String message, Throwable e)
    {
        Log.e(tag, message, e);
    }

}