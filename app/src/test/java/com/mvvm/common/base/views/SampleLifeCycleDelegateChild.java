package com.mvvm.common.base.views;

import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.common.base.samples.SampleBasePresenter;

/**
 * Created by AboelelaA on 6/8/2017.
 * Sample for life cycle delegate to add more getters
 */

public class SampleLifeCycleDelegateChild extends LifeCycleDelegate
{
    public SampleLifeCycleDelegateChild(Object hostViews) {
        super(hostViews);
    }

    BasePresenter getPresenter()
    {
        return this.presenter;
    }

    private SampleBasePresenter sampleBasePresenter = new SampleBasePresenter();

    SampleBasePresenter getSampleBasePresenter() {
        return sampleBasePresenter;
    }
}
