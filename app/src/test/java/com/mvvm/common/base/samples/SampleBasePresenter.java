package com.mvvm.common.base.samples;

import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.presenters.BasePresenter;

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
}
