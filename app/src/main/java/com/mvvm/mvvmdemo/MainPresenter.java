package com.mvvm.mvvmdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.mvvmdemo.data.MainViewModel;

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

        // need to set the text here to text baseView in activity
//        getBaseView().mainTitleTextView.setText("This text is set from Presenter");

        // set login fragment
//        getBaseView().getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, LoginFragment.newInstance()).commit();
    }
}
