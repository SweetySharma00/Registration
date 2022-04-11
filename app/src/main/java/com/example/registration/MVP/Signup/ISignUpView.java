package com.example.registration.MVP.Signup;

import com.example.registration.RetrofitAPI.models.request.OtpRequest;
import com.example.registration.RetrofitAPI.models.request.SignupRequest;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;

public interface ISignUpView {

    void setResponse(SignUpResponse signUpResponse);
    void setError(Throwable error);
    SignupRequest getRequest();
    void setDigest(String digest);
//    void setDigestOtp(String digest);
     String getDigest();
    void setValidateResponse(SignUpResponse signUpResponse);
    OtpRequest getOTP();
}
