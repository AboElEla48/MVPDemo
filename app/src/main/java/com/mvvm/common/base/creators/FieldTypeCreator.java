package com.mvvm.common.base.creators;

import com.mvvm.common.base.InvalidObject;
import com.mvvm.common.utils.MyLog;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by AboelelaA on 6/8/2017.
 * This class to create field according to its type
 */

public class FieldTypeCreator
{
    /**
     * Create field object with its annotated value corresponding constructor
     * @param field : Given the field from class declarion, create instance for it
     * @return: corresponding object to field annotation
     */
    public Object createFieldObject(Field field) {
        try {
            Constructor<?> fieldObjectConstructor = ((Field) field).getType().getDeclaredConstructor();
            fieldObjectConstructor.setAccessible(true);
            return fieldObjectConstructor.newInstance();
        }
        catch (Exception e) {
            MyLog.logError(getClass().getSimpleName(), "Error creating field object", e);
        }

        return new InvalidObject();
    }
}
