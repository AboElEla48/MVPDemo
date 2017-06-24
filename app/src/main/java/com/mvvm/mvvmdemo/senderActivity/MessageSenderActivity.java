package com.mvvm.mvvmdemo.senderActivity;

import android.widget.Button;

import com.mvvm.R;
import com.mvvm.common.annotation.InflateLayout;
import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.base.views.BaseActivity;

import butterknife.BindView;

@InflateLayout(R.layout.activity_message_sender)
public class MessageSenderActivity extends BaseActivity
{
    @Presenter
    MessageSenderPresenter messageSenderPresenter;

    @BindView(R.id.message_sender_activity_send_message_btn)
    Button sendMessageBtn;
}
