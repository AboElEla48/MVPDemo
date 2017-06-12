package com.mvvm.common.base.samples;

import com.mvvm.R;
import com.mvvm.common.annotation.ViewModelTextField;
import com.mvvm.common.base.viewmodels.BaseViewModel;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by AboelelaA on 6/8/2017.
 * Sample baseView Model for testing
 */

public class SampleViewModel extends BaseViewModel
{
    @ViewModelTextField(R.id.main_activity_title_text_view)
    PublishSubject<String> valueTextField;

    public void setValueText(String str)
    {
        valueTextField.onNext(str);
    }
}
