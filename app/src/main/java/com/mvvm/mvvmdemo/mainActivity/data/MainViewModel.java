/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.mvvmdemo.mainActivity.data;

import com.mvvm.R;
import com.mvvm.common.annotation.viewmodelfields.ViewModelCheckBoxField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelHintEditTextField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelImageViewField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelTextField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelTextViewTextColorField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelViewVisibilityField;
import com.mvvm.common.base.viewmodels.BaseViewModel;
import com.mvvm.common.utils.MyLog;

/**
 * Created by AboelelaA on 6/12/2017.
 * Define base View model for the activity
 */

public class MainViewModel extends BaseViewModel
{
    @ViewModelTextField(R.id.main_activity_title_text_view)
    String activityTextViewValue;

    @ViewModelTextViewTextColorField(R.id.main_activity_title_text_view)
    Integer activityTextViewTextColor;

    @ViewModelViewVisibilityField(R.id.main_activity_image_view)
    Integer activityImageViewVisibility;

    @ViewModelHintEditTextField(R.id.main_activity_edit_text)
    String activityEditorHintText;

    @ViewModelImageViewField(R.id.main_activity_drawable_view)
    Integer imageViewDrawable;

    @ViewModelCheckBoxField(R.id.main_activity_checkBox)
    Boolean checkBoxValue = new Boolean(false);

    private void setFieldValue(String fieldName, Object val) {
        try {
            setViewModelFieldValue(this, fieldName, val);
        } catch (NoSuchFieldException ex) {
            MyLog.logError(MainViewModel.class.getName(), "No Such Field Exception" + val, ex);
        } catch (IllegalAccessException ex) {
            MyLog.logError(MainViewModel.class.getName(), "Illegal Access Exception" + val, ex);
        }
    }

    /**
     * Setter for text view value
     *
     * @param activityTextViewValue
     */
    public void setActivityTextViewValue(String activityTextViewValue) {
        setFieldValue("activityTextViewValue", activityTextViewValue);
    }

    public String getActivityTextViewValue() {
        return activityTextViewValue;
    }

    /**
     * Set the visibility of image view
     * @param activityImageViewVisibility
     */
    public void setActivityImageViewVisibility(Integer activityImageViewVisibility) {
        setFieldValue("activityImageViewVisibility", activityImageViewVisibility);
    }

    public Integer getActivityImageViewVisibility() {
        return activityImageViewVisibility;
    }

    /**
     * Set the text color of text view
     * @param activityTextViewTextColor
     */
    public void setActivityTextViewTextColor(Integer activityTextViewTextColor) {
        setFieldValue("activityTextViewTextColor", activityTextViewTextColor);

    }

    public Integer getActivityTextViewTextColor() {
        return activityTextViewTextColor;
    }

    public String getActivityEditorHintText() {
        return activityEditorHintText;
    }

    public void setActivityEditorHintText(String activityEditorHintText) {
        setFieldValue("activityEditorHintText", activityEditorHintText);
    }

    public void setImageViewDrawable(Integer imageViewDrawable) {
        setFieldValue("imageViewDrawable", imageViewDrawable);
    }

    public Integer getImageViewDrawable() {
        return imageViewDrawable;
    }

    public void setCheckBoxValue(Boolean checkBoxValue) {
        setFieldValue("checkBoxValue", checkBoxValue);
    }

    public Boolean getCheckBoxValue() {
        return checkBoxValue;
    }
}