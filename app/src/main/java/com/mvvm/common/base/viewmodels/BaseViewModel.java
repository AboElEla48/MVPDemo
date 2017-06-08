package com.mvvm.common.base.viewmodels;

import com.mvvm.common.interfaces.BaseView;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This is the parent class for all ViewModels
 */

public class BaseViewModel
{
    private BaseView view;

    public void initView(BaseView view) {
        this.view = view;
    }
}
