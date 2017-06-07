package com.mvvm.common.base.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mvvm.common.interfaces.BaseView;
import com.mvvm.common.interfaces.ViewLifeCycle;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This is the parent class for all presenters
 */

public class BasePresenter<V extends BaseView> implements ViewLifeCycle
{
    private V baseView;

    /**
     * init base view object
     * @param baseView: this is te base view that will be accessed from presenter
     */
    public void initBaseView(@NonNull V baseView)
    {
        this.baseView = baseView;
    }

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

    public V getBaseView(){
        return baseView;
    }
}
