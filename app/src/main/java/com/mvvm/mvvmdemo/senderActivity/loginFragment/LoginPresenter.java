package com.mvvm.mvvmdemo.senderActivity.loginFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.mvvm.framework.annotation.ViewModel;
import com.mvvm.framework.base.presenters.BasePresenter;
import com.mvvm.mvvmdemo.senderActivity.loginFragment.data.LoginViewModel;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is the  presenter for login fragment
 */

class LoginPresenter extends BasePresenter<LoginFragment, LoginPresenter>
{
    @ViewModel
    private LoginViewModel loginViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Handle login click
        RxView.clicks(getBaseView().loginBtn)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Toast.makeText(getBaseView().getContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                    }
                });

        loginViewModel.setUsernameHint("Hint text in fragment set from View Model");
    }
}
