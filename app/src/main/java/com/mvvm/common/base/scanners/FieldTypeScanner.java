package com.mvvm.common.base.scanners;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import io.reactivex.functions.BiFunction;

/**
 * Created by AboelelaA on 6/7/2017.
 * This scanner is to scan given fields and get the required fields with given annotation type
 */

public class FieldTypeScanner implements BiFunction<List<Field>, Class<?>, Object>
{
    @Override
    public Object apply(List<Field> fields, Class<?> requiredAnnotation) {

        // Search annotation on all fields to get the required annotation class
        for(Field field : fields)
        {
            Annotation[] fieldAnnotations =  field.getDeclaredAnnotations();
            for(Annotation annotation : fieldAnnotations)
            {
                if( annotation.annotationType().getName().equals(requiredAnnotation.getName()))
                {
                    return field;
                }
            }
        }

        return null;
    }
}
