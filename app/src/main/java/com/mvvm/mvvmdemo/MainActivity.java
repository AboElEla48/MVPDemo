package com.mvvm.mvvmdemo;

import android.widget.Button;
import android.widget.ImageView;
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

    @BindView(R.id.main_activity_image_view)
    ImageView mainImageView;

    @BindView(R.id.main_activity_set_text_btn)
    Button mainTextSetter;

    @BindView(R.id.main_activity_set_view_visible_btn)
    Button mainViewVisibility;

}
