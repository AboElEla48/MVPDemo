package com.mvvm.common.base.views;

import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.InvalidObject;
import com.mvvm.common.base.scanners.FieldTypeScanner;
import com.mvvm.mvvmdemo.MainActivity;
import com.mvvm.mvvmdemo.MainPresenter;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

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
//    @Before
//    public void mockAllNeededObjects() throws Exception {
//        PowerMockito.spy(MyLog.class);
//        PowerMockito.doNothing().when(MyLog.class, PowerMockito.method(MyLog.class, "logError", String.class, String.class, Throwable.class));
//    }

    @Test
    public void toPresenter_ReturnsPresenterObject() throws Exception {
        MainActivity mainActivity = PowerMockito.mock(MainActivity.class);
        final SampleLifeCycleDelegateChild lifeCycleDelegate = new SampleLifeCycleDelegateChild(mainActivity);

        Observable.just(new FieldTypeScanner().apply(MainActivity.class.getDeclaredFields(), Presenter.class))
                .filter(new Predicate<Object>()
                {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        Assert.assertTrue(!(o instanceof InvalidObject));
                        return !(o instanceof InvalidObject);
                    }
                })
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

        Observable.just(new FieldTypeScanner().apply(MainActivityChild.class.getDeclaredFields(), Presenter.class))
                .filter(new Predicate<Object>()
                {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        Assert.assertTrue(!(o instanceof InvalidObject));
                        return !(o instanceof InvalidObject);
                    }
                })
                .map(lifeCycleDelegate.toPresenter(mainActivity))
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Assert.assertTrue(lifeCycleDelegate.getPresenter() instanceof MainActivityPresenterChild);

                        // Create view models
                        Observable.just(new FieldTypeScanner().apply(MainActivityPresenterChild.class.getDeclaredFields(), ViewModel.class))
                                .filter(new Predicate<Object>()
                                {
                                    @Override
                                    public boolean test(@NonNull Object o) throws Exception {
                                        Assert.assertTrue(!(o instanceof InvalidObject));
                                        return !(o instanceof InvalidObject);
                                    }
                                })
                                .map(lifeCycleDelegate.toViewModel(lifeCycleDelegate.getPresenter()))
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

}