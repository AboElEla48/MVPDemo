package com.mvvm.common.base.presenters;

import com.mvvm.common.annotation.ViewModel;

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
