package com.mvvm.mvvmdemo.senderActivity.loginFragment.data;

import com.mvvm.R;
import com.mvvm.common.annotation.viewmodelfields.ViewModelHintEditTextField;
import com.mvvm.common.base.viewmodels.BaseViewModel;

import com.mvvm.common.utils.LogUtil;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is the baseView model for login
 */

public class LoginViewModel extends BaseViewModel
{
    // login User name
    @ViewModelHintEditTextField(R.id.login_fragment_user_name_edit_text)
    String usernameHint;

    // Login password
    @ViewModelHintEditTextField(R.id.login_fragment_password_edit_text)
    String passwordHint;

    private void setFieldValue(String fieldName, Object val) {
        try {
            setViewModelFieldValue(this, fieldName, val);
        } catch (NoSuchFieldException ex) {
            LogUtil.writeErrorLog(LoginViewModel.class.getName(), "No Such Field Exception" + val, ex);
        } catch (IllegalAccessException ex) {
            LogUtil.writeErrorLog(LoginViewModel.class.getName(), "Illegal Access Exception" + val, ex);
        }
    }

    public void setUsernameHint(String usernameHint) {
        setFieldValue("usernameHint", usernameHint);
    }

    public void setPasswordHint(String passwordHint) {
        setFieldValue("passwordHint", passwordHint);
    }
}
