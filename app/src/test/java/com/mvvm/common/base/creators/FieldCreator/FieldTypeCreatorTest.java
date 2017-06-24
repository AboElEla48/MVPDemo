package com.mvvm.common.base.creators.FieldCreator;

import com.mvvm.common.annotation.DataModel;
import com.mvvm.common.annotation.Singleton;
import com.mvvm.common.base.creators.FieldTypeCreator;
import com.mvvm.common.base.creators.SingletonCreatorTest.SingletonObject;
import com.mvvm.common.base.scanners.FieldTypeScanner;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static org.junit.Assert.*;

/**
 * Created by aboelela on 24/06/17.
 * Test class for fields creator
 */
public class FieldTypeCreatorTest
{
    @Test
    public void createFieldObject() throws Exception {
        final FieldUserObject userObject = new FieldUserObject();

        Observable.fromIterable(new FieldTypeScanner().apply(userObject.getClass().getDeclaredFields(), DataModel.class))
                .map(new Function<Field, Object>()
                {
                    @Override
                    public Object apply(@NonNull Field field) throws Exception {
                        FieldTypeObject o = (FieldTypeObject) new FieldTypeCreator().createFieldObject(field);

                        field.setAccessible(true);
                        field.set(userObject, o);

                        return o;

                    }
                })
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Assert.assertTrue(userObject.object != null);
                        Assert.assertTrue(userObject.object.x == 10);

                    }
                });

    }

}