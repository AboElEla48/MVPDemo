package com.mvvm.mvvmdemo;

import android.widget.TextView;

import com.mvvm.R;
import com.mvvm.common.annotation.InflateLayout;
import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.base.views.BaseActivity;

import butterknife.BindView;

@InflateLayout(R.layout.activity_main)
public class MainActivity extends BaseActivity
{
    @Presenter
    MainPresenter mainPresenter;

    @BindView(R.id.main_activity_title_text_view)
    TextView mainTitleTextView;

}
