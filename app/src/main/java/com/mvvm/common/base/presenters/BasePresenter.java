package com.mvvm.common.base.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mvvm.common.interfaces.ViewLifeCycle;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This is the parent class for all presenters
 */

public class BasePresenter implements ViewLifeCycle
{
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
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }
}
