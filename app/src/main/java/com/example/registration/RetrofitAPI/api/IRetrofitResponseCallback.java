package com.example.registration.RetrofitAPI.api;

public interface IRetrofitResponseCallback<T> {

    void onResponseReceived(int REQUEST_CODE, T object);

}
