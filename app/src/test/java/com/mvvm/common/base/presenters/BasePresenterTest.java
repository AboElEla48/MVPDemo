package com.mvvm.common.base.presenters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is a test class ofr base presenter
 */
public class BasePresenterTest
{
    private SampleBaseView sampleBaseView;
    private SampleBasePresenter sampleBasePresenter;

    @Before
    public void init() throws Exception {
        sampleBaseView = new SampleBaseView();
        sampleBasePresenter = new SampleBasePresenter();

        sampleBasePresenter.initBaseView(sampleBaseView);
        sampleBasePresenter.onCreate(null);
        sampleBasePresenter.onStart();
        sampleBasePresenter.onResume();
    }

    @Test
    public void initBaseView_NoNull()
    {
        Assert.assertTrue(sampleBasePresenter.getBaseView() != null);
        Assert.assertTrue(sampleBasePresenter.getBaseView() instanceof SampleBaseView);
    }



}