package com.mvvm.mvvmdemo.senderActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jakewharton.rxbinding2.view.RxView;
import com.mvvm.R;
import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.common.messaging.CustomMessage;
import com.mvvm.common.messaging.MessagesServer;
import com.mvvm.mvvmdemo.mainActivity.MainActivity;
import com.mvvm.mvvmdemo.senderActivity.loginFragment.LoginFragment;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by aboelela on 24/06/17.
 * Presenter for message sender activity
 */

public class MessageSenderPresenter extends BasePresenter<MessageSenderActivity, MessageSenderPresenter>
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxView.clicks(getBaseView().sendMessageBtn)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        MessagesServer.getInstance().sendMessage(MainActivity.class, new CustomMessage(1, 10, "Message says Hello"));
                        getBaseView().finish();
                    }
                });

        getBaseView().getSupportFragmentManager().beginTransaction().replace(R.id.activity_frameLayout, LoginFragment.newInstance())
                .commit();

    }
}
