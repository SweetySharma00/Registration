package com.example.registration.RetrofitAPI.models.response;

import com.example.registration.RetrofitAPI.models.request.QuesAns;
import com.example.registration.RetrofitAPI.models.request.misc;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SecurityDetailsResponse {
    @SerializedName("message")
    Message message;
    @SerializedName("securityDetails")
    ArrayList<QuesAns> securityDetails;
    @SerializedName("miscellaneous")
    misc miscellaneous;

    public Message getMessage() {
        return message;
    }

    public ArrayList<QuesAns> getSecurityDetails() {
        return securityDetails;
    }

    public misc getMiscellaneous() {
        return miscellaneous;
    }
}
