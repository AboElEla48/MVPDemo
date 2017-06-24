package com.mvvm.common.base.creators.SingletonCreatorTest;

import com.mvvm.common.annotation.Singleton;

/**
 * Created by aboelela on 24/06/17.
 * Sample user for Singleton field
 */

public class SingletonTest1
{
    @Singleton
    SingletonObject object;

    public void setOddVal() {
        object.setVal(object.getVal() + 1);
    }
}
