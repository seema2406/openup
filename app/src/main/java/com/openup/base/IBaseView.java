package com.openup.base;

import android.support.annotation.StringRes;

/**
 * Created by Niel on 18/07/17.
 */
public interface IBaseView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

}

