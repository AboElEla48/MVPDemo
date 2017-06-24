package com.mvvm.mvvmdemo.senderActivity.loginFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.mvvm.common.annotation.ViewModel;
import com.mvvm.common.base.presenters.BasePresenter;
import com.mvvm.mvvmdemo.senderActivity.loginFragment.data.LoginViewModel;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is the  presenter for login fragment
 */

class LoginPresenter extends BasePresenter<LoginFragment>
{
    @ViewModel
    LoginViewModel loginViewModel;

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
    }
}
