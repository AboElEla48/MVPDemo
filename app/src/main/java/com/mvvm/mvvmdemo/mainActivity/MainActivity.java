package com.mvvm.mvvmdemo.mainActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvvm.R;
import com.mvvm.framework.annotation.InflateLayout;
import com.mvvm.framework.annotation.Presenter;
import com.mvvm.framework.base.views.BaseActivity;

import butterknife.BindView;

@InflateLayout(R.layout.activity_main)
public class MainActivity extends BaseActivity
{
    @Presenter
    MainPresenter mainPresenter;

    @BindView(R.id.main_activity_title_text_view)
    TextView mainTitleTextView;

    @BindView(R.id.main_activity_image_view)
    View mainImageView;

    @BindView(R.id.main_activity_set_text_btn)
    Button mainTextSetter;

    @BindView(R.id.main_activity_set_view_visible_btn)
    Button mainViewVisibility;

    @BindView(R.id.main_activity_set_view_red_btn)
    Button mainViewRedButton;

    @BindView(R.id.main_activity_set_view_green_btn)
    Button mainViewGreenButton;

    @BindView(R.id.main_activity_edit_text)
    EditText mainEditText;

    @BindView(R.id.main_activity_set_edit_text_hint_btn)
    Button mainEditSetter;

    @BindView(R.id.main_activity_drawable_view)
    ImageView imageView;

    @BindView(R.id.main_activity_change_image_btn)
    Button mainChangeImageBtn;

    @BindView(R.id.main_activity_checkBox)
    CheckBox mainCheckBoxView;

    @BindView(R.id.main_activity_set_checkBox_btn)
    Button mainCheckValueSetterBtn;

    @BindView(R.id.main_activity_launcher_sender_btn)
    Button mainActivityLaunchBtn;

    @BindView(R.id.main_activity_msg_body_textView)
    TextView mainActivityMsgBodyTextView;
}
