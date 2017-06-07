package com.mvvm.common.base.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.base.InvalidObject;
import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.common.base.scanners.FieldTypeScanner;
import com.mvvm.common.interfaces.BaseView;
import com.mvvm.common.interfaces.ViewLifeCycle;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is the object that will held in activities and fragments to pass view life cycle events to it
 */

final class LifeCycleDelegate implements ViewLifeCycle
{
    private Class<?> hostViewClass;
    private WeakReference<ViewLifeCycle> hostObjectReference;
    private BasePresenter presenter;

    LifeCycleDelegate(Object hostViews) {
        hostViewClass = hostViews.getClass();
        hostObjectReference = new WeakReference<>((ViewLifeCycle) hostViews);
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        final BaseView hostView = (BaseView) hostObjectReference.get();
        if (hostView == null) {
            return;
        }

        // Get presenter object by annotation
        Observable.just(new FieldTypeScanner().apply(hostView.getClass().getDeclaredFields(), Presenter.class))
                .filter(new Predicate<Object>()
                {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        return !(o instanceof InvalidObject);
                    }
                })
                .map(new Function<Object, Object>()
                {
                    @Override
                    public Object apply(@NonNull Object presenterField) throws Exception {

                        Constructor<?> presenterConstructor = ((Field)presenterField).getType().getDeclaredConstructor();
                        presenterConstructor.setAccessible(true);
                        presenter = (BasePresenter) presenterConstructor.newInstance();

                        // pass base view to presenter
                        presenter.initBaseView(hostView);

                        return presenter;
                    }
                })
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        presenter.onCreate(savedInstanceState);
                    }
                });

    }

    @Override
    public void onStart() {
        presenter.onStart();
    }

    @Override
    public void onRestart() {
        presenter.onRestart();
    }

    @Override
    public void onResume() {
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        presenter.onRestoreInstanceState(savedInstanceState);
    }
}
