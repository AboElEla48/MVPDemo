/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.common.base.views;

import com.mvvm.common.annotation.Presenter;
import com.mvvm.mvvmdemo.MainActivity;

/**
 * Created by AboelelaA on 6/13/2017.
 * Sample for Main activity
 */

public class MainActivityChild extends MainActivity
{
    @Presenter
    MainActivityPresenterChild mainActivityPresenterChild;
}
