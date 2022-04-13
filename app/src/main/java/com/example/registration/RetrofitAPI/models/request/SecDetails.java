package com.example.registration.RetrofitAPI.models.request;

import com.google.gson.annotations.SerializedName;

public class SecDetails {
    @SerializedName("securityDetails")
    Details securityDetails;

    public Details getSecurityDetails() {
        return securityDetails;
    }

    public void setSecurityDetails(Details securityDetails) {
        this.securityDetails = securityDetails;
    }
}
