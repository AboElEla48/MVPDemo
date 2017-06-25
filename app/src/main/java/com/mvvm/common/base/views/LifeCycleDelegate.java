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
import com.mvvm.common.base.creators.FieldTypeCreator;
import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.common.base.scanners.FieldTypeScanner;
import com.mvvm.common.base.viewmodels.BaseViewModel;
import com.mvvm.common.interfaces.ActivityLifeCycle;
import com.mvvm.common.interfaces.BaseView;
import com.mvvm.common.interfaces.FragmentLifeCycle;
import com.mvvm.common.interfaces.ViewLifeCycle;
import com.mvvm.common.messaging.CustomMessage;
import com.mvvm.common.messaging.InboxHolder;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is the object that will held in activities and fragments to pass baseView life cycle events to it
 */

public class LifeCycleDelegate implements ActivityLifeCycle, FragmentLifeCycle, InboxHolder
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

    @Override
    public void onMessageReceived(CustomMessage msg) {
        if(presenter != null) {
            presenter.onMessageReceived(msg);
        }
    }

    /**
     * Create presenter object
     * @param hostView : the baseView object to get fields from
     */
    private void createFieldsAnnotatedAsPresenter(final BaseView hostView, @Nullable final Bundle savedInstanceState) {
        Observable.fromIterable(new FieldTypeScanner().apply(hostView.getClass().getDeclaredFields(), Presenter.class))
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
    Function<Field, BasePresenter> toPresenter(final BaseView hostView) {
        return new Function<Field, BasePresenter>()
        {
            @Override
            public BasePresenter apply(@NonNull Field presenterField) throws Exception {

                // Create object of presenter type class
                presenter = (BasePresenter) (new FieldTypeCreator().createFieldObject(presenterField));

                // pass base baseView to presenter
                presenter.initBaseView(hostView, presenter);

                // set presenter to baseView
                presenterField.setAccessible(true);
                presenterField.set(hostView, presenter);

                return presenter;

            }
        };
    }

    /**
     * Create View Models
     */
    private void createFieldsAnnotatedAsViewModels() {
        Observable.fromIterable(new FieldTypeScanner().apply(presenter.getClass().getDeclaredFields(), ViewModel.class))
                .map(toViewModel(presenter))
                .subscribe();

    }

    Function<Field, BaseViewModel> toViewModel(final BasePresenter viewModelPresenter) {
        return new Function<Field, BaseViewModel>()
        {
            @Override
            public BaseViewModel apply(@io.reactivex.annotations.NonNull Field viewModelField) throws Exception {
                BaseViewModel viewModel = (BaseViewModel) new FieldTypeCreator().createFieldObject(viewModelField);

                viewModel.initView(viewModelPresenter.getBaseView());

                // add view model to presenter list
                viewModelPresenter.addViewModel(viewModel);

                viewModelField.setAccessible(true);
                viewModelField.set(viewModelPresenter, viewModel);

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
