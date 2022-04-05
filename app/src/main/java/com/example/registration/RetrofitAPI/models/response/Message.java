package com.example.registration.RetrofitAPI.models.response;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("errorMessage")
    String errorMessage;
    @SerializedName("successMessage")
    String successMessage;


    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
