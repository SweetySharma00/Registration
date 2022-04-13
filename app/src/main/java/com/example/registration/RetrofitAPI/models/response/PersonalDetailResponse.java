package com.example.registration.RetrofitAPI.models.response;

import com.google.gson.annotations.SerializedName;

public class PersonalDetailResponse {
    @SerializedName("message")
    Message message;
    @SerializedName("firstName")
    String firstName;
    @SerializedName("middleName")
    String middleName;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("dob")
    String dob;
    @SerializedName("genderCD")
    String genderCD;
    @SerializedName("countryOfBirthCD")
    String countryOfBirthCD;
    @SerializedName("acceptTermsYN")
    String acceptTermsYN;
    @SerializedName("nationality")
    String nationality;
    @SerializedName("profileImage")
    String profileImage;

    public Message getMessage() {
        return message;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getGenderCD() {
        return genderCD;
    }

    public String getCountryOfBirthCD() {
        return countryOfBirthCD;
    }

    public String getAcceptTermsYN() {
        return acceptTermsYN;
    }

    public String getNationality() {
        return nationality;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
