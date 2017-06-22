package com.mvvm.mvvmdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;
import com.mvvm.common.annotation.DataModel;
import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.common.utils.ToastUtil;
import com.mvvm.mvvmdemo.data.MainModel;
import com.mvvm.mvvmdemo.data.MainViewModel;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by AboelelaA on 6/7/2017.
 * Presenter for Main activity
 */

public class MainPresenter extends BasePresenter<MainActivity>
{
    @ViewModel
    private MainViewModel mainViewModel;

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
                        ToastUtil.showToast(getBaseView(), mainViewModel.getActivityTextViewValue());
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

                        ToastUtil.showToast(getBaseView(), "Image visibility: " + mainViewModel.getActivityImageViewVisibility());

                    }
                });

        // set login fragment
        //        getBaseView().getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, LoginFragment.newInstance()).commit();
    }
}
