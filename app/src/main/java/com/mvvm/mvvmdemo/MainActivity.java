package com.mvvm.mvvmdemo;

import com.mvvm.R;
import com.mvvm.common.annotation.InflateLayout;
import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.base.views.BaseActivity;

@InflateLayout(R.layout.activity_main)
public class MainActivity extends BaseActivity
{
    @Presenter
    MainPresenter mainPresenter;
}
