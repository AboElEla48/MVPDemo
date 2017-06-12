package com.mvvm.common.base.viewmodels;

import android.support.annotation.NonNull;

import com.mvvm.common.interfaces.BaseView;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This is the parent class for all ViewModels
 */

public class BaseViewModel
{
    protected BaseView baseView;

    public void initView(@NonNull BaseView view) {
        this.baseView = view;
    }

}
