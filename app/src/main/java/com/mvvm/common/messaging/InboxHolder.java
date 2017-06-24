package com.mvvm.common.messaging;

/**
 * Created by aboelela on 24/06/17.
 *
 * Define interface for all classes that would like to keep inbox for it
 */

public interface InboxHolder
{
    void onReceiveMessage(CustomMessage msg);
}
