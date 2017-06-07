package com.mvvm.common.base.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvvm.common.base.scanners.LayoutIdScanner;
import com.mvvm.common.interfaces.BaseView;
import com.mvvm.common.interfaces.ViewLifeCycle;

import butterknife.ButterKnife;

/**
 * Created by AboelelaA on 6/6/2017.
 * This is the parent fragment
 */

public class BaseFragment extends Fragment implements BaseView, ViewLifeCycle
{
    private LifeCycleDelegate lifeCycleDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRestart() {
        lifeCycleDelegate.onRestart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Get declared resource Id of this activity
        int resourceId = new LayoutIdScanner().apply(this);
        View fragmentView = inflater.inflate(resourceId, container, false);

        // pass lifecycle to view life cycle delegate
        lifeCycleDelegate = new LifeCycleDelegate(this);
        lifeCycleDelegate.onCreate(savedInstanceState);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getActivity());
        lifeCycleDelegate.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        lifeCycleDelegate.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        lifeCycleDelegate.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifeCycleDelegate.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifeCycleDelegate.onDestroy();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        lifeCycleDelegate.onRestoreInstanceState(savedInstanceState);
    }
}
