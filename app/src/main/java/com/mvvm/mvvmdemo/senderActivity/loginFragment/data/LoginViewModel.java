package com.mvvm.mvvmdemo.senderActivity.loginFragment.data;

import com.mvvm.common.base.viewmodels.BaseViewModel;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is the baseView model for login
 */

public class LoginViewModel extends BaseViewModel
{
    // login User name
    String username;

    // Login password
    String password;

    // login error message
    String errorMsg;

    // error message visibility
    int errorMsgVisibility;

}
