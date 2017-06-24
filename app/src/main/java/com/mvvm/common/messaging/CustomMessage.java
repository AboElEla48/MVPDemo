package com.mvvm.common.messaging;

/**
 * Created by aboelela on 24/06/17.
 * Define data message to send and receive
 */

public class CustomMessage
{
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
