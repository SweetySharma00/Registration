package com.example.registration.MVP.SecurityDetails;

import com.example.registration.RetrofitAPI.models.request.Miscellaneous;
import com.example.registration.RetrofitAPI.models.request.SecDetails;
import com.example.registration.RetrofitAPI.models.request.SecurityDetailsRequest;

public interface ISecurityDetailsPresenter {
    void hitDetails(SecurityDetailsRequest securityDetailsRequest);
    void setView(ISecurityDetailsView iSecurityDetailsView);
}
