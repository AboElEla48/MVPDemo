/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.mvvmdemo.data;

import com.mvvm.R;
import com.mvvm.common.annotation.viewmodelfields.ViewModelTextField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelViewVisibilityField;
import com.mvvm.common.base.viewmodels.BaseViewModel;
import com.mvvm.common.utils.MyLog;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by AboelelaA on 6/12/2017.
 * Define base View model for the activity
 */

public class MainViewModel extends BaseViewModel
{
    @ViewModelTextField(R.id.main_activity_title_text_view)
    String activityTextViewValue;

    @ViewModelViewVisibilityField(R.id.main_activity_image_view)
    Integer activityImageViewVisibility;

    /**
     * Setter for text view value
     *
     * @param activityTextViewValue
     */
    public void setActivityTextViewValue(String activityTextViewValue) {
        try {
            setViewModelFieldValue(this, "activityTextViewValue", activityTextViewValue);
        } catch (NoSuchFieldException ex) {
            MyLog.logError(MainViewModel.class.getName(), "No Such Field Exception" + activityTextViewValue, ex);
        } catch (IllegalAccessException ex) {
            MyLog.logError(MainViewModel.class.getName(), "Illegal Access Exception" + activityTextViewValue, ex);
        }
    }

    public String getActivityTextViewValue() {
        return activityTextViewValue;
    }

    /**
     * Set the visibility of image view
     * @param activityImageViewVisibility
     */
    public void setActivityImageViewVisibility(Integer activityImageViewVisibility) {
        try {
            setViewModelFieldValue(this, "activityImageViewVisibility", activityImageViewVisibility);
        } catch (NoSuchFieldException ex) {
            MyLog.logError(MainViewModel.class.getName(), "No Such Field Exception" + activityTextViewValue, ex);
        } catch (IllegalAccessException ex) {
            MyLog.logError(MainViewModel.class.getName(), "Illegal Access Exception" + activityTextViewValue, ex);
        }
    }

    public Integer getActivityImageViewVisibility() {
        return activityImageViewVisibility;
    }
}
