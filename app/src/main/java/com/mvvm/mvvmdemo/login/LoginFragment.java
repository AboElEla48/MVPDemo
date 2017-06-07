package com.mvvm.mvvmdemo.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;

import com.mvvm.R;
import com.mvvm.common.annotation.InflateLayout;
import com.mvvm.common.annotation.Presenter;
import com.mvvm.common.base.views.BaseFragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
@InflateLayout(R.layout.fragment_login)
public class LoginFragment extends BaseFragment
{
    @Presenter
    LoginPresenter loginPresenter;

    @BindView(R.id.login_fragment_login_btn)
    Button loginBtn;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
}
