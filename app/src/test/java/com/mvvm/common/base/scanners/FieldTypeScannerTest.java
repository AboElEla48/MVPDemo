package com.mvvm.common.base.scanners;

import com.mvvm.common.annotation.Model;
import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.annotation.ViewModel;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

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

        ArrayList<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(this.getClass().getDeclaredFields()));

        // Operation
        Field presenterFiled = (Field) fieldTypeScanner.apply(fields, Presenter.class);
        Field modelFiled = (Field) fieldTypeScanner.apply(fields, Model.class);
        Field viewModelFiled = (Field) fieldTypeScanner.apply(fields, ViewModel.class);

        Constructor<?> presenterConstructor = presenterFiled.getType().getDeclaredConstructor();
        presenterConstructor.setAccessible(true);
        Object presenterObject = presenterConstructor.newInstance();

        Constructor<?> viewModelConstructor = viewModelFiled.getType().getDeclaredConstructors()[0];
        viewModelConstructor.setAccessible(true);
        Object viewModelObject = viewModelConstructor.newInstance(10);

        Constructor<?> modelConstructor = modelFiled.getType().getDeclaredConstructors()[0];
        modelConstructor.setAccessible(true);
        Object modelObject = modelConstructor.newInstance(12L);



        Assert.assertTrue(presenterObject.getClass().getSimpleName().equals("String"));
        Assert.assertTrue(modelObject.getClass().getSimpleName().equals("Long"));
        Assert.assertTrue(viewModelObject.getClass().getSimpleName().equals("Integer"));
    }



}