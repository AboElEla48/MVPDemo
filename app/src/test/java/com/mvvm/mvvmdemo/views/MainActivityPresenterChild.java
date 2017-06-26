/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.mvvmdemo.views;

import com.mvvm.framework.annotation.DataModel;
import com.mvvm.framework.annotation.ViewModel;
import com.mvvm.mvvmdemo.mainActivity.MainPresenter;
import com.mvvm.mvvmdemo.mainActivity.data.MainViewModel;

/**
 * Created by AboelelaA on 6/13/2017.
 * Sample for Main activity presenter
 */

public class MainActivityPresenterChild extends MainPresenter
{
    @ViewModel
    private MainActivityViewModelChild mainViewModel;

    @DataModel
    private MainActivityModelChild mainActivityModelChild;

    MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    MainActivityModelChild getMainModel() {
        return mainActivityModelChild;
    }

}
