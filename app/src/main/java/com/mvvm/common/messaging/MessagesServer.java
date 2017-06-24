package com.mvvm.common.messaging;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by aboelela on 24/06/17.
 *
 * Define server for routing all messages among different inboxes
 */

public final class MessagesServer
{
    private MessagesServer() {}

    /**
     * get singleton object to server
     * @return
     */
    public static MessagesServer getInstance() {
        if(sInstance == null) {
            sInstance = new MessagesServer();
        }
        return sInstance;
    }

    /**
     * Register as receiver for messages
     * @param inboxHolder
     */
    public void registerInboxHolder(InboxHolder inboxHolder) {
        inboxHolders.add(inboxHolder);
    }

    /**
     * Unregister from listening to messages
     * @param inboxHolder
     */
    public void unRegisterInboxHolder(InboxHolder inboxHolder) {
        inboxHolders.remove(inboxHolder);
    }

    /**
     * Send message to inbox
     * @param inboxHolderClass
     * @param msg
     */
    public void sendMessage(final Class<?> inboxHolderClass, final CustomMessage msg) {
        Observable.fromIterable(inboxHolders)
                .subscribe(new Consumer<InboxHolder>()
                {
                    @Override
                    public void accept(@NonNull InboxHolder inboxHolder) throws Exception {
                        if(inboxHolder.getClass().equals(inboxHolderClass)) {
                            // send message to this inbox
                            inboxHolder.onMessageReceived(msg);
                        }
                    }
                });
    }

    private static MessagesServer sInstance;
    private ArrayList<InboxHolder> inboxHolders = new ArrayList<>();
}
