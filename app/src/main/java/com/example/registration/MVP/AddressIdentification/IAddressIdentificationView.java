package com.example.registration.MVP.AddressIdentification;

import com.example.registration.RetrofitAPI.models.request.PersonalDetailRequest;
import com.example.registration.RetrofitAPI.models.response.AddressIdentificationResponse;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;

public interface IAddressIdentificationView {

    void setResponse(AddressIdentificationResponse addressIdentificationResponse);
    void setError(Throwable error);
    String getDigest();
    void setDigest(String digest);
}
