package com.mvvm.common.base.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.common.base.viewmodels.BaseViewModel;

import io.reactivex.Observable;

/**
 * Created by AboelelaA on 6/7/2017.
 * sample base presenter for testing
 */

public class SampleBasePresenter extends BasePresenter<SampleBaseView>
{
    @ViewModel
    private SampleViewModel sampleViewModel1;

    public SampleViewModel getSampleViewModel1() {
        return sampleViewModel1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        sampleViewModel1.valueText
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>()
//                {
//                    @Override
//                    public void accept(@NonNull String s) throws Exception {
//                        getBaseView().textView.setText(s);
//                    }
//                });
    }

    public Observable getViewModelFieldsOfAnnotationType(BaseViewModel viewModel, Class annotationType) {
        return super.getViewModelFieldsOfAnnotationType(viewModel, annotationType);
    }

    public Observable getViewFieldOfResIdAndClass(Class clz, final int resId) {
        return super.getViewFieldOfResIdAndClass(clz, resId);
    }
}
