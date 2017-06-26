/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.mvvmdemo.views;

import com.mvvm.R;
import com.mvvm.framework.annotation.viewmodelfields.ViewModelTextField;
import com.mvvm.mvvmdemo.mainActivity.data.MainViewModel;

import java.lang.reflect.Field;
import java.util.List;

import io.reactivex.Observable;
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

    @Override
    public Observable getViewFieldOfResIdAndClass(final Class clz, final int resId) {
        return super.getViewFieldOfResIdAndClass(clz, resId);
    }

    @Override
    public Observable<List<Field>> getViewModelFieldsOfAnnotationType(Class annotationType)
    {
        return super.getViewModelFieldsOfAnnotationType(annotationType);
    }
}
