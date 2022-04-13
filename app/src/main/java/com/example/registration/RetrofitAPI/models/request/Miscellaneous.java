package com.example.registration.RetrofitAPI.models.request;

import com.google.gson.annotations.SerializedName;

public class Miscellaneous {

   @SerializedName("miscellaneous")
    misc miscellaneous;

    public misc getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(misc miscellaneous) {
        this.miscellaneous = miscellaneous;
    }
}
