package com.mvvm.common.base.viewmodels;

import android.support.annotation.NonNull;

import com.mvvm.common.interfaces.BaseView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This is the parent class for all ViewModels
 */

public class BaseViewModel
{
    protected BaseView baseView;

    protected HashMap<Field, PublishSubject<Object>> viewModelFieldsNotifiers = new HashMap<>();

    public void initView(@NonNull BaseView view) {
        this.baseView = view;
    }

    public void addField(Field viewModelField, PublishSubject subject) {
        viewModelFieldsNotifiers.put(viewModelField, subject);
    }

    public void releaseViewModelValues() {
        Observable.fromIterable(viewModelFieldsNotifiers.values())
                .subscribe(new Consumer<PublishSubject<Object>>()
                {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull PublishSubject<Object> objectPublishSubject) throws Exception {
                        objectPublishSubject.onComplete();
                    }
                });
    }

    public void setViewModelFieldValue(BaseViewModel viewModel, String fieldName, final Object value) throws NoSuchFieldException, IllegalAccessException{
        Field field = viewModel.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(viewModel, value);

        // Notify change to reflect on UI
        Observable.just(viewModelFieldsNotifiers.get(field))
                .subscribe(new Consumer<PublishSubject<Object>>()
                {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull PublishSubject<Object> objectPublishSubject) throws Exception {
                        objectPublishSubject.onNext(value);
                    }
                });

    }

}
