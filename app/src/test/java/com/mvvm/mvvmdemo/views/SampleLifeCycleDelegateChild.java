package com.mvvm.mvvmdemo.views;

import com.mvvm.framework.base.presenters.BasePresenter;
import com.mvvm.framework.base.views.LifeCycleDelegate;

/**
 * Created by AboelelaA on 6/8/2017.
 * Sample for life cycle delegate to add more getters
 */

public class SampleLifeCycleDelegateChild extends LifeCycleDelegate
{
    public SampleLifeCycleDelegateChild(Object hostViews) {
        super(hostViews);
    }

    public BasePresenter getPresenter() {
        return this.presenter;
    }

    //    private SampleBasePresenter sampleBasePresenter = new SampleBasePresenter();
    //
    //    SampleBasePresenter getSampleBasePresenter() {
    //        return sampleBasePresenter;
    //    }
}
