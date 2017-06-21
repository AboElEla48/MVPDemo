package com.mvvm.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by aboelela on 22/06/17.
 *
 * Utility for toast messages
 */

public final class ToastUtil
{
    private ToastUtil() {}

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
