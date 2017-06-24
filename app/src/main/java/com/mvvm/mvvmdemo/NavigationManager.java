package com.mvvm.mvvmdemo;

import android.content.Context;
import android.content.Intent;

import com.mvvm.mvvmdemo.senderActivity.MessageSenderActivity;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is the navigation manager among activities
 */

public class NavigationManager
{
    private NavigationManager() {}

    public static void startMessageSenderActivity(Context context) {
        Intent intent = new Intent(context, MessageSenderActivity.class);
        context.startActivity(intent);
    }

}
