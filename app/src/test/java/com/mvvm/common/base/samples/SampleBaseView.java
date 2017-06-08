package com.mvvm.common.base.samples;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.interfaces.ActivityLifeCycle;
import com.mvvm.common.interfaces.BaseView;

/**
 * Created by AboelelaA on 6/7/2017.
 * Sample mock for Base view
 */

public class SampleBaseView implements BaseView, ActivityLifeCycle
{
    @Presenter
    SampleBasePresenter presenterObject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
