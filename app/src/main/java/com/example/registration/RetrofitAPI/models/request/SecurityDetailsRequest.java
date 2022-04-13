package com.example.registration.RetrofitAPI.models.request;

import com.google.gson.annotations.SerializedName;

public class SecurityDetailsRequest {
    @SerializedName("securityDetails")
    Details securityDetails;
    @SerializedName("miscellaneous")
    misc miscellaneous;

    public Details getSecurityDetails() {
        return securityDetails;
    }

    public void setSecurityDetails(Details securityDetails) {
        this.securityDetails = securityDetails;
    }

    public misc getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(misc miscellaneous) {
        this.miscellaneous = miscellaneous;
    }
}
