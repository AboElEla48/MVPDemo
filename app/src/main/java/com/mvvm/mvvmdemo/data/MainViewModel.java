/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.mvvmdemo.data;

import com.mvvm.R;
import com.mvvm.common.annotation.ViewModelTextField;
import com.mvvm.common.base.viewmodels.BaseViewModel;

/**
 * Created by AboelelaA on 6/12/2017.
 * Define base View model for the activity
 */

public class MainViewModel extends BaseViewModel
{
    @ViewModelTextField(R.id.main_activity_title_text_view)
    String activityTextView;
}
