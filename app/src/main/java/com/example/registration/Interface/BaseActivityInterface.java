package com.example.registration.Interface;

import android.app.Activity;

import com.example.registration.RetrofitAPI.models.response.SignUpResponse;

public interface BaseActivityInterface {


    int getLayoutById();
    void getViewById();
    void manageToolBar();
    void hideKeyboard(Activity activity);
    void showProgressDialog();
    void hideProgressDialog();
    void showSnackBarMessage(String message);
    String getTokenForAPI();

    String getDeviceToken();
    void setTokenForAPI(SignUpResponse signUpResponse);
    void updateVersionDialog(String updateUrl, String message);
    String getSelectedCountryCode();
    String getSelectedGroupCode();
    void onClientVersionUpdate(String updateUrl, String message);
    void showCountryLogo(int countyLogo, int appLogo, int title);
}
