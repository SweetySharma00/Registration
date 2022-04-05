package com.example.registration.RetrofitAPI.interfaces;

import com.example.registration.RetrofitAPI.models.request.SignupRequest;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofitContract {

    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("api/register-mobile")
    Observable<SignUpResponse> SignUp(
//                                @Query("Platform-Type") String PlatformType,
//                                @Query("Client-Type") String clientType,
                                @Body SignupRequest signupRequest
                                 );

    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("api/verify-otp")
//    @Header("Client-Version") String clientVersion, @Header("Client-Source") String clientSource,
//    @Body RegisterSendOTPRequest registerSendOTPRequest,@Header("Firebase-Token") String deviceToken);
    Observable<Response<SignUpResponse>> Verify(@Query("otp") String otp);
}
