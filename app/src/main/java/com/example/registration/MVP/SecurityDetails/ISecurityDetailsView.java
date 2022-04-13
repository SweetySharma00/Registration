package com.example.registration.MVP.SecurityDetails;

import com.example.registration.RetrofitAPI.models.request.PersonalDetailRequest;
import com.example.registration.RetrofitAPI.models.request.SecurityDetailsRequest;
import com.example.registration.RetrofitAPI.models.response.SecurityDetailsResponse;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;

public interface ISecurityDetailsView {

    void setResponse(SecurityDetailsResponse securityDetailsResponse);
    void setError(Throwable error);
    String getDigest();
    void setDigest(String digest);
}
