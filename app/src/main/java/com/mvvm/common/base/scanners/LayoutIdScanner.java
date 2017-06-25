package com.mvvm.common.base.scanners;

import com.mvvm.common.annotation.InflateLayout;

import java.lang.annotation.Annotation;

import com.mvvm.common.utils.LogUtil;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This is the scanner class to search for layout inflate annotations and inflate layouts
 * from resources
 */

public class LayoutIdScanner implements Function<Object, Integer>
{
    @Override
    public Integer apply(@NonNull Object o) throws UnsupportedOperationException{

        // Search for annotation layout inflate to inflate this Layout from resources
        Annotation[] annotations = o.getClass().getDeclaredAnnotations();
        for (Annotation annotation : annotations)
        {
            if(annotation instanceof InflateLayout)
            {
                return ((InflateLayout) annotation).value();
            }
        }

        LogUtil.writeErrorLog("LayoutIdScanner", "LayoutIdScanner Exception",
                new UnsupportedOperationException(LayoutIdScanner.class.getSimpleName() + " Not declared"));
        return null;
    }
}
