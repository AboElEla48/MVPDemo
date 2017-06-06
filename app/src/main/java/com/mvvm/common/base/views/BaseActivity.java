package com.mvvm.common.base.views;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mvvm.common.base.LayoutIdScanner;
import com.mvvm.common.interfaces.BaseView;

/**
 * Created by AboelelaA on 6/6/2017.
 * This is the parent activity
 */

public class BaseActivity extends AppCompatActivity implements BaseView
{
    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        // Get declared resource Id of this activity
        int resourceId = new LayoutIdScanner().apply(this);
        setContentView(resourceId);
    }

    @Override
    protected final void onRestart() {
        super.onRestart();
    }

    @Override
    protected final void onResume() {
        super.onResume();
    }

    @Override
    protected final void onPause() {
        super.onPause();
    }

    @Override
    protected final void onDestroy() {
        super.onDestroy();
    }
}
