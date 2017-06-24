/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.common.base.views;

import com.mvvm.R;
import com.mvvm.common.annotation.viewmodelfields.ViewModelTextField;
import com.mvvm.mvvmdemo.mainActivity.data.MainViewModel;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by AboelelaA on 6/13/2017.
 * Sample for Main activity View Model
 *
 */

public class MainActivityViewModelChild extends MainViewModel
{
    @ViewModelTextField(R.id.main_activity_title_text_view)
    PublishSubject<String> activityTextViewValue;
}
