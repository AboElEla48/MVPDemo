package com.mvvm.common.base.samples;

import android.widget.TextView;

import com.mvvm.R;
import com.mvvm.common.annotation.InflateLayout;
import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.base.views.BaseActivity;
import com.mvvm.common.interfaces.ActivityLifeCycle;
import com.mvvm.common.interfaces.BaseView;

import butterknife.BindView;

/**
 * Created by AboelelaA on 6/7/2017.
 * Sample mock for Base baseView
 */

@InflateLayout(R.layout.activity_main)
public class SampleBaseView extends BaseActivity implements BaseView, ActivityLifeCycle
{
    @Presenter
    SampleBasePresenter presenterObject;

    @BindView(R.id.main_activity_title_text_view)
    public TextView textView;

    public SampleBasePresenter getPresenterObject() {
        return presenterObject;
    }

    public void tempInit()
    {
        textView = new TextView(null);
        textView.setId(R.id.main_activity_title_text_view);
    }
}
