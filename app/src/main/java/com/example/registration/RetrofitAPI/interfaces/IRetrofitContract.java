package com.example.registration.RetrofitAPI.interfaces;

import com.example.registration.RetrofitAPI.models.request.OtpRequest;
import com.example.registration.RetrofitAPI.models.request.SignupRequest;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IRetrofitContract {

    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("api/register-mobile")
    Observable<Response<SignUpResponse>> SignUp(
                                @Header("Platform-Type") String PlatformType,
                                @Header("Client-Type") String clientType,
                                @Body SignupRequest signupRequest
                                 );

    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("api/verify-otp")
    Observable<SignUpResponse> Verify(
            @Header("Access-Medium")  String AcsessMedium,
            @Header("Platform-Type") String PlatformType,
            @Header("Client-Type") String clientType,
            @Body OtpRequest otpRequest);
}
