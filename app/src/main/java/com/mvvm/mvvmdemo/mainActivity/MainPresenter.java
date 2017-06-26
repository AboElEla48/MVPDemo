package com.mvvm.mvvmdemo.mainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;
import com.mvvm.R;
import com.mvvm.framework.annotation.DataModel;
import com.mvvm.framework.annotation.ViewModel;
import com.mvvm.framework.annotation.singleton.Singleton;
import com.mvvm.framework.base.presenters.BasePresenter;
import com.mvvm.framework.messaging.CustomMessage;
import com.mvvm.mvvmdemo.NavigationManager;
import com.mvvm.mvvmdemo.mainActivity.data.MainModel;
import com.mvvm.mvvmdemo.mainActivity.data.MainViewModel;

import com.mvvm.framework.utils.DialogMsgUtil;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by AboelelaA on 6/7/2017.
 * Presenter for Main activity
 */

public class MainPresenter extends BasePresenter<MainActivity, MainPresenter>
{
    @ViewModel
    private MainViewModel mainViewModel;

    @Singleton
    @DataModel
    MainModel mainModel;

    int dummyVal = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxView.clicks(getBaseView().mainTextSetter)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mainViewModel.setActivityTextViewValue("Text set from View Model - " + ++dummyVal);
                        DialogMsgUtil.showInfoMessage(getBaseView(), mainViewModel.getActivityTextViewValue());
                    }
                });

        RxView.clicks(getBaseView().mainViewRedButton)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mainViewModel.setActivityTextViewTextColor(0xFFFF0000);
                    }
                });

        RxView.clicks(getBaseView().mainViewGreenButton)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mainViewModel.setActivityTextViewTextColor(0xFF00FF00);
                    }
                });

        RxView.clicks(getBaseView().mainViewVisibility)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        switch (getBaseView().mainImageView.getVisibility()) {
                            case View.VISIBLE: {
                                mainViewModel.setActivityImageViewVisibility(View.GONE);
                                break;
                            }

                            case View.GONE: {
                                mainViewModel.setActivityImageViewVisibility(View.VISIBLE);
                                break;
                            }
                        }

                        DialogMsgUtil.showInfoMessage(getBaseView(), "Image visibility: " + mainViewModel.getActivityImageViewVisibility());

                    }
                });

        RxView.clicks(getBaseView().mainEditSetter)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mainViewModel.setActivityEditorHintText("Hint text from Model " + ++dummyVal);
                        DialogMsgUtil.showInfoMessage(getBaseView(), "Hint Text: " + mainViewModel.getActivityEditorHintText());
                    }
                });

        RxView.clicks(getBaseView().mainChangeImageBtn)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (dummyVal % 2 == 0) {
                            mainViewModel.setImageViewDrawable(R.drawable.notification_icon);
                        }
                        else {
                            mainViewModel.setImageViewDrawable(R.drawable.test);
                        }
                        ++dummyVal;
                    }
                });

        RxView.clicks(getBaseView().mainCheckValueSetterBtn)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mainViewModel.setCheckBoxValue(!mainViewModel.getCheckBoxValue());
                    }
                });

        RxView.clicks(getBaseView().mainActivityLaunchBtn)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        NavigationManager.startMessageSenderActivity(getBaseView());
                    }
                });
    }

    @Override
    public void onMessageReceived(CustomMessage msg) {
        super.onMessageReceived(msg);

        getBaseView().mainActivityMsgBodyTextView.setText("Payload = " + msg.getPayLoad() + ", Data = " + msg.getData());
    }
}
