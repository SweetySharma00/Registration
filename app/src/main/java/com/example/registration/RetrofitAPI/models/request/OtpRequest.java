package com.example.registration.RetrofitAPI.models.request;

import com.google.gson.annotations.SerializedName;

public class OtpRequest {
    @SerializedName("otp")
    String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
