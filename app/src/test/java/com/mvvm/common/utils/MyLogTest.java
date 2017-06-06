package com.mvvm.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by AboelelaA on 6/6/2017.
 * Test code for logger class
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MyLog.class)
public class MyLogTest
{
    @Test
    public void logError() throws Exception {

        String tag = "SampleTage";
        String message = "SampleMessage";
        Throwable ex = new UnsupportedOperationException();

        PowerMockito.mockStatic(MyLog.class);
        PowerMockito.doNothing().when(MyLog.class, "logError", tag, message, ex);

    }

}