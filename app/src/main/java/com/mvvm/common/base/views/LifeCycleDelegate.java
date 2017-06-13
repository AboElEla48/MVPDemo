/*
 * Copyright (c) This code is written by Ahmed AboElEla (eng.a.aboelela@gmail.com). You can use it but please refer to Owner
 */

package com.mvvm.common.base.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.InvalidObject;
import com.mvvm.common.base.creators.FieldTypeCreator;
import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.common.base.scanners.FieldTypeScanner;
import com.mvvm.common.base.viewmodels.BaseViewModel;
import com.mvvm.common.interfaces.ActivityLifeCycle;
import com.mvvm.common.interfaces.BaseView;
import com.mvvm.common.interfaces.FragmentLifeCycle;
import com.mvvm.common.interfaces.ViewLifeCycle;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is the object that will held in activities and fragments to pass baseView life cycle events to it
 */

class LifeCycleDelegate implements ActivityLifeCycle, FragmentLifeCycle
{
    private WeakReference<ViewLifeCycle> hostObjectReference;
    protected BasePresenter presenter;

    LifeCycleDelegate(Object hostViews) {
        hostObjectReference = new WeakReference<>((ViewLifeCycle) hostViews);
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        final BaseView hostView = (BaseView) hostObjectReference.get();
        if (hostView == null) {
            return;
        }

        // Get presenter object by annotation
        createFieldsAnnotatedAsPresenter(hostView, savedInstanceState);

        // Get View models by annotation
        createFieldsAnnotatedAsViewModels();
    }

    /**
     * Create presenter object
     * @param hostView : the baseView object to get fields from
     */
    private void createFieldsAnnotatedAsPresenter(final BaseView hostView, @Nullable final Bundle savedInstanceState) {
        Observable.just(new FieldTypeScanner().apply(hostView.getClass().getDeclaredFields(), Presenter.class))
                .filter(new Predicate<Object>()
                {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        return !(o instanceof InvalidObject);
                    }
                })
                .map(toPresenter(hostView))
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        presenter.onCreate(savedInstanceState);
                    }
                });
    }

    /**
     * create Presenter
     * @param hostView: the baseView object to get fields from
     * @return : function to subscribe to it
     */
    public Function<Object, Object> toPresenter(final BaseView hostView) {
        return new Function<Object, Object>()
        {
            @Override
            public Object apply(@NonNull Object presenterField) throws Exception {

                // Create object of presenter type class
                presenter = (BasePresenter) (new FieldTypeCreator().createFieldObject((Field) presenterField));

                // pass base baseView to presenter
                presenter.initBaseView(hostView);

                // set presenter to baseView
                ((Field) presenterField).setAccessible(true);
                ((Field) presenterField).set(hostView, presenter);

                return presenter;
            }
        };
    }

    /**
     * Create View Models
     */
    private void createFieldsAnnotatedAsViewModels() {
        Observable.just(new FieldTypeScanner().apply(presenter.getClass().getDeclaredFields(), ViewModel.class))
                .filter(new Predicate<Object>()
                {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull Object o) throws Exception {
                        return (o != null) && !(o instanceof InvalidObject);
                    }
                })
                .map(toViewModel(presenter))
                .subscribe();

    }

    Function<Object, Object> toViewModel(final BasePresenter viewModelPresenter) {
        return new Function<Object, Object>()
        {
            @Override
            public Object apply(@io.reactivex.annotations.NonNull Object viewModelField) throws Exception {
                BaseViewModel viewModel = (BaseViewModel) new FieldTypeCreator().createFieldObject((Field) viewModelField);

                viewModel.initView(viewModelPresenter.getBaseView());

                ((Field) viewModelField).setAccessible(true);
                ((Field) viewModelField).set(viewModelPresenter, viewModel);

                // Associate all views in baseView model
                viewModelPresenter.associateViewModelWithViews(viewModel);


                return viewModel;
            }
        };
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
    public void onStop() {
        presenter.onStop();
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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return presenter.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        presenter.onActivityCreated(savedInstanceState);
    }
}
