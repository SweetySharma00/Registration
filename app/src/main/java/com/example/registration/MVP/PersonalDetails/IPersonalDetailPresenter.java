package com.example.registration.MVP.PersonalDetails;

import com.example.registration.MVP.Signup.ISignUpView;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IPersonalDetailPresenter {
    void hitDetails( HashMap<String,RequestBody> map, MultipartBody.Part doc);
    void setView(IPersonalDetailView iPersonalDetailView);
}
