package com.example.registration.MVP.PersonalDetails;

import com.example.registration.Activity.PersonalDetails;
import com.example.registration.RetrofitAPI.models.request.PersonalDetailRequest;
import com.example.registration.RetrofitAPI.models.request.SignupRequest;
import com.example.registration.RetrofitAPI.models.response.PersonalDetailResponse;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;

public interface IPersonalDetailView {
    void setResponse(PersonalDetailResponse personalDetailResponse);
    void setError(Throwable error);
    PersonalDetailRequest getRequest();
    String getDigest();
    void setDigest(String digest);
}
