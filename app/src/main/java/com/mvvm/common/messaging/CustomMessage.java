package com.mvvm.common.messaging;

/**
 * Created by aboelela on 24/06/17.
 * Define data message to send and receive
 */

public class CustomMessage
{
    private Object data;
    private int payLoad;

    public CustomMessage() {}

    public CustomMessage(int payload, Object data) {
        setData(data);
        setPayLoad(payload);
    }

    public Object getData() {
        return data;
    }

    public void setPayLoad(int payLoad) {
        this.payLoad = payLoad;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getPayLoad() {
        return payLoad;
    }
}
