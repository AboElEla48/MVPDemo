package com.mvvm.common.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This scanner to scane annotations of layouts in views to inflate it to views
 */
public class LayoutIdScannerTest
{
    @Test
    public void apply_NoReturnNull() throws Exception {
        // Declare
        LayoutIdScanner layoutIdScanner = new LayoutIdScanner();
        SampleViewLayoutScannerTest sampleViewLayoutScannerTest = new SampleViewLayoutScannerTest();

        //operation
        Integer resId = layoutIdScanner.apply(sampleViewLayoutScannerTest);

        // Assert
        Assert.assertTrue(resId != null);
    }

}