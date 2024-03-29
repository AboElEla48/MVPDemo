/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.mvvmdemo.mainActivity.data;

import com.mvvm.R;
import com.mvvm.framework.annotation.viewmodelfields.ViewModelCheckBoxField;
import com.mvvm.framework.annotation.viewmodelfields.ViewModelHintEditTextField;
import com.mvvm.framework.annotation.viewmodelfields.ViewModelImageViewField;
import com.mvvm.framework.annotation.viewmodelfields.ViewModelTextField;
import com.mvvm.framework.annotation.viewmodelfields.ViewModelTextViewTextColorField;
import com.mvvm.framework.annotation.viewmodelfields.ViewModelViewVisibilityField;
import com.mvvm.framework.base.viewmodels.BaseViewModel;

import com.mvvm.framework.utils.LogUtil;
import com.mvvm.mvvmdemo.mainActivity.MainActivity;

/**
 * Created by AboelelaA on 6/12/2017.
 * Define base View model for the activity
 */

public class MainViewModel extends BaseViewModel<MainActivity, MainViewModel>
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
            LogUtil.writeErrorLog(MainViewModel.class.getName(), "No Such Field Exception" + val, ex);
        } catch (IllegalAccessException ex) {
            LogUtil.writeErrorLog(MainViewModel.class.getName(), "Illegal Access Exception" + val, ex);
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
