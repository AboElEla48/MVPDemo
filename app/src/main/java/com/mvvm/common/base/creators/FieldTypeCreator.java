package com.mvvm.common.base.creators;

import com.mvvm.common.annotation.Singleton;
import com.mvvm.common.base.scanners.FieldTypeScanner;
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
     * @return: corresponding object to field annotation or null of there is
     * no field declared with this annotation
     */
    public Object createFieldObject(Field field) {
        if(!new FieldTypeScanner().isFieldAnnotatedBy(field, Singleton.class)) {
            // If this object isn't singleton, create new instance of it
            return getNewInstance(field);
        }
        else {
            try {
                // This object is created as singleton, get the singleton object of it
                return SingletonCreator.getCreator().getInstance(field.getType());
            }
            catch (Exception ex){
                MyLog.logError(getClass().getSimpleName(), "Error creating Singleton object", ex);
                return null;
            }

        }
    }

    private Object getNewInstance(Field field) {
        try {
            Constructor<?> fieldObjectConstructor = ((Field) field).getType().getDeclaredConstructor();
            fieldObjectConstructor.setAccessible(true);
            return fieldObjectConstructor.newInstance();
        }
        catch (Exception e) {
            MyLog.logError(getClass().getSimpleName(), "Error creating field object", e);
        }

        return null;
    }
}
