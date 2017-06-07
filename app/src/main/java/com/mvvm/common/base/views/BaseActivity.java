package com.mvvm.common.base.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mvvm.common.base.scanners.LayoutIdScanner;
import com.mvvm.common.interfaces.BaseView;

import butterknife.ButterKnife;

/**
 * Created by AboelelaA on 6/6/2017.
 * This is the parent activity
 */

public class BaseActivity extends AppCompatActivity implements BaseView
{
    private LifeCycleDelegate lifeCycleDelegate;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        // Get declared resource Id of this activity
        int resourceId = new LayoutIdScanner().apply(this);
        setContentView(resourceId);

        // Bind views
        ButterKnife.bind(this);

        // pass lifecycle to view life cycle delegate
        lifeCycleDelegate = new LifeCycleDelegate();
        lifeCycleDelegate.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifeCycleDelegate.onStart();
    }

    @Override
    protected final void onRestart() {
        super.onRestart();
        lifeCycleDelegate.onRestart();
    }

    @Override
    protected final void onResume() {
        super.onResume();
        lifeCycleDelegate.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        lifeCycleDelegate.onSaveInstanceState(outState, outPersistentState);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        lifeCycleDelegate.onRestoreInstanceState(savedInstanceState, persistentState);
    }


    @Override
    protected final void onPause() {
        lifeCycleDelegate.onPause();
        super.onPause();
    }

    @Override
    protected final void onDestroy() {
        lifeCycleDelegate.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lifeCycleDelegate.onActivityResult(requestCode, resultCode, data);
    }
}
