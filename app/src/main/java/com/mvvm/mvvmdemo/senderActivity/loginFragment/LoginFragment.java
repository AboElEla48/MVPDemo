package com.mvvm.mvvmdemo.senderActivity.loginFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;

import com.mvvm.R;
import com.mvvm.framework.annotation.InflateLayout;
import com.mvvm.framework.annotation.Presenter;
import com.mvvm.framework.base.views.BaseFragment;

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

    @BindView(R.id.login_fragment_user_name_edit_text)
    EditText userName;

    @BindView(R.id.login_fragment_password_edit_text)
    EditText password;


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
