package com.mvvm.mvvmdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jakewharton.rxbinding2.view.RxView;
import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.mvvmdemo.data.MainViewModel;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by AboelelaA on 6/7/2017.
 * Presenter for Main activity
 */

class MainPresenter extends BasePresenter<MainActivity>
{
    @ViewModel
    MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxView.clicks(getBaseView().mainTextSetter)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mainViewModel.setActivityTextViewValue("Text set from View Model of Activity");
                    }
                });

        // need to set the text here to text baseView in activity
//        getBaseView().mainTitleTextView.setText("This text is set from Presenter");

        // set login fragment
//        getBaseView().getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, LoginFragment.newInstance()).commit();
    }
}
