package com.example.registration.RetrofitAPI.interfaces;

import com.example.registration.RetrofitAPI.models.request.Miscellaneous;
import com.example.registration.RetrofitAPI.models.request.OtpRequest;
import com.example.registration.RetrofitAPI.models.request.SecDetails;
import com.example.registration.RetrofitAPI.models.request.SecurityDetailsRequest;
import com.example.registration.RetrofitAPI.models.request.SignupRequest;
import com.example.registration.RetrofitAPI.models.response.AddressIdentificationResponse;
import com.example.registration.RetrofitAPI.models.response.PersonalDetailResponse;
import com.example.registration.RetrofitAPI.models.response.SecurityDetailsResponse;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

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
    Observable<Response<SignUpResponse>> Verify(
            @Header("Access-Medium")  String AcsessMedium,
            @Header("Platform-Type") String PlatformType,
            @Header("Client-Type") String clientType,
            @Body OtpRequest otpRequest);
    @Multipart
    @POST("api/register-personal")
    Observable<Response<PersonalDetailResponse>> Details(
            @Header("Access-Medium")  String AcsessMedium,
            @Header("Platform-Type") String PlatformType,
            @Header("Client-Type") String clientType,
//            @Header("Content-Type") String ContentType,
//            @PartMap PersonalDetailRequest personalDetailRequest
            @PartMap Map<String,RequestBody> map,
            @Part MultipartBody.Part doc
            );
    @Multipart
    @POST("api/register-address")
    Observable<Response<AddressIdentificationResponse>> AddressDetails(
            @Header("Access-Medium")  String AcsessMedium,
            @Header("Platform-Type") String PlatformType,
            @Header("Client-Type") String clientType,
//            @Header("Content-Type") String ContentType,
//            @PartMap PersonalDetailRequest personalDetailRequest
            @PartMap Map<String,RequestBody> map,
            @Part MultipartBody.Part doc,
            @Part MultipartBody.Part docBack,
            @Part MultipartBody.Part SourceFunds,
            @Part MultipartBody.Part AddressProof
    );
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("api/register-security")
    Observable<Response<SecurityDetailsResponse>> SecurityDetails(
            @Header("Access-Medium")  String AcsessMedium,
            @Header("Platform-Type") String PlatformType,
            @Header("Client-Type") String clientType,
            @Body SecurityDetailsRequest securityDetailsRequest
//            @Body Miscellaneous miscellaneous
            );
}
