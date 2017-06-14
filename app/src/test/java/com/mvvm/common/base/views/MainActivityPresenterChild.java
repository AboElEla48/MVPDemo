/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.common.base.views;

import com.mvvm.common.annotation.DataModel;
import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.viewmodels.BaseViewModel;
import com.mvvm.mvvmdemo.MainPresenter;
import com.mvvm.mvvmdemo.data.MainViewModel;

import java.lang.reflect.Field;
import java.util.List;

import io.reactivex.Observable;

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

    @Override
    public Observable getViewFieldOfResIdAndClass(final Class clz, final int resId) {
        return super.getViewFieldOfResIdAndClass(clz, resId);
    }

    @Override
    public Observable<List<Field>> getViewModelFieldsOfAnnotationType(BaseViewModel viewModel, Class annotationType)
    {
        return super.getViewModelFieldsOfAnnotationType(viewModel, annotationType);
    }
}
