package com.mvvm.common.base.views;

import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.base.InvalidObject;
import com.mvvm.common.base.presenters.SampleBasePresenter;
import com.mvvm.common.base.presenters.SampleBaseView;
import com.mvvm.common.base.scanners.FieldTypeScanner;

import org.junit.Assert;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by AboelelaA on 6/8/2017.
 * Test for life cycle
 */
public class LifeCycleDelegateTest
{
    @Test
    public void toPresenter_ReturnsPresenterObject() throws Exception {
        SampleBaseView baseView = new SampleBaseView();
        final SampleLifeCycleDelegateChild lifeCycleDelegate = new SampleLifeCycleDelegateChild(baseView);

        Observable.just(new FieldTypeScanner().apply(baseView.getClass().getDeclaredFields(), Presenter.class))
                .filter(new Predicate<Object>()
                {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        return !(o instanceof InvalidObject);
                    }
                })
                .map(lifeCycleDelegate.toPresenter(baseView))
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Assert.assertTrue(lifeCycleDelegate.getPresenter() instanceof SampleBasePresenter);
                    }
                });
    }

}