package com.mvvm.common.base.scanners;

import com.mvvm.common.annotation.Model;
import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.InvalidObject;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is a test class for fields scanner according to its annotation type
 */
public class FieldTypeScannerTest
{
    @Model
    Long obj2 = 26L;

    @ViewModel
    Integer obj3 = 17;

    @Presenter
    String obj1 = "obj1_val_not_used";

    @Test
    public void apply_NoReturnNull() throws Exception {

        // Declare
        FieldTypeScanner fieldTypeScanner = new FieldTypeScanner();

        Field[] fields = this.getClass().getDeclaredFields();

        apply_Presenter(fieldTypeScanner, fields);
        apply_Models(fieldTypeScanner, fields);
        apply_ViewModel(fieldTypeScanner, fields);

//
//        // Operation
//        Field presenterFiled = (Field) fieldTypeScanner.apply(fields, Presenter.class);
//        Field modelFiled = (Field) fieldTypeScanner.apply(fields, Model.class);
//        Field viewModelFiled = (Field) fieldTypeScanner.apply(fields, ViewModel.class);
//
//
//
//        Constructor<?> presenterConstructor = presenterFiled.getType().getDeclaredConstructor();
//        presenterConstructor.setAccessible(true);
//        Object presenterObject = presenterConstructor.newInstance();
//
//        Constructor<?> viewModelConstructor = viewModelFiled.getType().getDeclaredConstructors()[0];
//        viewModelConstructor.setAccessible(true);
//        Object viewModelObject = viewModelConstructor.newInstance(10);
//
//        Constructor<?> modelConstructor = modelFiled.getType().getDeclaredConstructors()[0];
//        modelConstructor.setAccessible(true);
//        Object modelObject = modelConstructor.newInstance(12L);
//
//
//        Assert.assertTrue(presenterObject.getClass().getSimpleName().equals("String"));
//        Assert.assertTrue(modelObject.getClass().getSimpleName().equals("Long"));
//        Assert.assertTrue(viewModelObject.getClass().getSimpleName().equals("Integer"));
    }


    private void apply_Presenter(FieldTypeScanner fieldTypeScanner, Field[] fields) throws Exception
    {
        // Extract Presenter
        Observable.just(fieldTypeScanner.apply(fields, Presenter.class))
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
                        return presenterConstructor.newInstance();
                    }
                })
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object presenterObject) throws Exception {
                        Assert.assertTrue(presenterObject.getClass().getSimpleName().equals("String"));
                    }
                });
    }

    private void apply_Models(FieldTypeScanner fieldTypeScanner, Field[] fields) throws Exception
    {
        // Extract Models
        Observable.just(fieldTypeScanner.apply(fields, Model.class))
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
                    public Object apply(@NonNull Object modelField) throws Exception {

                        Constructor<?> presenterConstructor = ((Field)modelField).getType().getDeclaredConstructors()[0];
                        presenterConstructor.setAccessible(true);
                        return presenterConstructor.newInstance(10L);
                    }
                })
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object modelObject) throws Exception {
                        Assert.assertTrue(modelObject.getClass().getSimpleName().equals("Long"));
                    }
                });
    }

    private void apply_ViewModel(FieldTypeScanner fieldTypeScanner, Field[] fields) throws Exception
    {
        // Extract ViewModels
        Observable.just(fieldTypeScanner.apply(fields, ViewModel.class))
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
                    public Object apply(@NonNull Object viewModelField) throws Exception {

                        Constructor<?> presenterConstructor = ((Field)viewModelField).getType().getDeclaredConstructors()[0];
                        presenterConstructor.setAccessible(true);
                        return presenterConstructor.newInstance(20);
                    }
                })
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object viewModelObject) throws Exception {
                        Assert.assertTrue(viewModelObject.getClass().getSimpleName().equals("Integer"));
                    }
                });
    }


}