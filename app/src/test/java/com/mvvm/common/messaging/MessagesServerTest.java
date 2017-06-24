package com.mvvm.common.messaging;

import com.mvvm.common.messaging.inboxes.Inbox1;
import com.mvvm.common.messaging.inboxes.MessageSender;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by aboelela on 24/06/17.
 * Test class for messages server
 */
public class MessagesServerTest
{
    private Inbox1 inbox1;
    private MessageSender messageSender;

    @Before
    public void init() {
        inbox1 = new Inbox1();
        messageSender = new MessageSender();

        MessagesServer.getInstance().registerInboxHolder(inbox1);
    }

    @Test
    public void sendMessage_AssureMessageSent() throws Exception {
        CustomMessage message = new CustomMessage();
        message.setData(10);

        MessagesServer.getInstance().sendMessage(Inbox1.class, message);

        Assert.assertTrue(inbox1.getVal() == 10);
    }

}