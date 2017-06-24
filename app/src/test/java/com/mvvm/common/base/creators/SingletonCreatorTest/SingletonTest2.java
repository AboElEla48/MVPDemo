package com.mvvm.common.base.creators.SingletonCreatorTest;

import com.mvvm.common.annotation.singleton.Singleton;

/**
 * Created by aboelela on 24/06/17.
 * Sample user for Singleton field
 */

public class SingletonTest2
{
    @Singleton
    SingletonObject object;

    public void setEvenVal() {
        object.setVal(object.getVal() * 2);
    }
}
