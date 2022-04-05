package com.example.registration.RetrofitAPI.models.response;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
     @SerializedName("message")
     Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
