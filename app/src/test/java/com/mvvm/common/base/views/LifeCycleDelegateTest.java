package com.mvvm.common.base.views;

import com.mvvm.common.annotation.DataModel;
import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.scanners.FieldTypeScanner;
import com.mvvm.mvvmdemo.mainActivity.MainActivity;
import com.mvvm.mvvmdemo.mainActivity.MainPresenter;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by AboelelaA on 6/8/2017.
 * Test for life cycle
 */
public class LifeCycleDelegateTest
{
    @Test
    public void toPresenter_ReturnsPresenterObject() throws Exception {
        MainActivity mainActivity = PowerMockito.mock(MainActivity.class);
        final SampleLifeCycleDelegateChild lifeCycleDelegate = new SampleLifeCycleDelegateChild(mainActivity);

        Observable.fromIterable(new FieldTypeScanner().apply(MainActivity.class.getDeclaredFields(), Presenter.class))
                .map(lifeCycleDelegate.toPresenter(mainActivity))
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Assert.assertTrue(lifeCycleDelegate.getPresenter() instanceof MainPresenter);
                    }
                });
    }

    @Test
    public void toViewMode_ReturnsViewModeObject() throws Exception {
        MainActivityChild mainActivity = PowerMockito.mock(MainActivityChild.class);
        final SampleLifeCycleDelegateChild lifeCycleDelegate = new SampleLifeCycleDelegateChild(mainActivity);

        Observable.fromIterable(new FieldTypeScanner().apply(MainActivityChild.class.getDeclaredFields(), Presenter.class))
                .map(lifeCycleDelegate.toPresenter(mainActivity))
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Assert.assertTrue(lifeCycleDelegate.getPresenter() instanceof MainActivityPresenterChild);

                        // Create view models
                        Observable.fromIterable(new FieldTypeScanner().apply(MainActivityPresenterChild.class.getDeclaredFields(), ViewModel.class))
                                .map(lifeCycleDelegate.getPresenter().toViewModel(lifeCycleDelegate.getPresenter()))
                                .subscribe(new Consumer<Object>()
                                {
                                    @Override
                                    public void accept(@NonNull Object o) throws Exception {
                                        Assert.assertTrue(((MainActivityPresenterChild) lifeCycleDelegate.getPresenter()).getMainViewModel() != null);
                                        Assert.assertTrue(((MainActivityPresenterChild) lifeCycleDelegate.getPresenter()).getMainViewModel() instanceof MainActivityViewModelChild);
                                    }
                                });
                    }
                });



    }

    @Test
    public void toModel_ReturnsDataModeObject() throws Exception {
        MainActivityChild mainActivity = PowerMockito.mock(MainActivityChild.class);
        final SampleLifeCycleDelegateChild lifeCycleDelegate = new SampleLifeCycleDelegateChild(mainActivity);

        Observable.fromIterable(new FieldTypeScanner().apply(MainActivityChild.class.getDeclaredFields(), Presenter.class))
                .map(lifeCycleDelegate.toPresenter(mainActivity))
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Assert.assertTrue(lifeCycleDelegate.getPresenter() instanceof MainActivityPresenterChild);

                        // Create view models
                        Observable.fromIterable(new FieldTypeScanner().apply(MainActivityPresenterChild.class.getDeclaredFields(), DataModel.class))
                                .map(lifeCycleDelegate.getPresenter().toDataModel(lifeCycleDelegate.getPresenter()))
                                .subscribe(new Consumer<Object>()
                                {
                                    @Override
                                    public void accept(@NonNull Object o) throws Exception {
                                        Assert.assertTrue(((MainActivityPresenterChild) lifeCycleDelegate.getPresenter()).getMainModel() != null);
                                        Assert.assertTrue(((MainActivityPresenterChild) lifeCycleDelegate.getPresenter()).getMainModel() instanceof MainActivityModelChild);
                                    }
                                });
                    }
                });



    }

}