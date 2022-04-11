package com.example.registration.MVP.AddressIdentification;

import com.example.registration.MVP.PersonalDetails.IPersonalDetailView;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IAddressIdentificationPresenter {

    void hitDetails(HashMap<String, RequestBody> map, MultipartBody.Part doc);
    void setView(IAddressIdentificationView iAddressIdentificationView);
}
