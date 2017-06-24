package com.mvvm.common.base.creators;

import com.mvvm.common.utils.MyLog;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * Created by aboelela on 24/06/17.
 * <p>
 * This is the creator class for fields annotated as Singleton
 */

public final class SingletonCreator
{
    private SingletonCreator() {
    }

    /**
     * Get singleton object to SingletonCreator
     * @return: singleton object to SingletonCreator
     */
    public static SingletonCreator getCreator() {
        if(instance == null) {
            instance = new SingletonCreator();
        }
        return instance;
    }

    public Object getInstance(Class<?> cls) throws Exception{
        Object singletonObject = singletonObjectsList.get(cls);
        if(singletonObject == null) {
            // this object wasn't created before
//            try {
                Constructor<?> classConstructor = cls.getDeclaredConstructor();
                classConstructor.setAccessible(true);
                singletonObject = classConstructor.newInstance();

                singletonObjectsList.put(cls, singletonObject);

//            }
//            catch (Exception e) {
//                MyLog.logError(getClass().getSimpleName(), "Error creating Singleton object", e);
//            }
        }
        return singletonObject;
    }

    private static SingletonCreator instance;
    private HashMap<Class, Object> singletonObjectsList = new HashMap<>();
}