package com.example.registration.RetrofitAPI.models.request;

import com.google.gson.annotations.SerializedName;

public class PersonalDetailRequest {
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
    @SerializedName("profileImage")
    String profileImage;
    @SerializedName("acceptTermsYN")
    String acceptTermsYN;
    @SerializedName("nationality")
    String nationality;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    public String getGenderCD() {
        return genderCD;
    }

    public void setGenderCD(String genderCD) {
        this.genderCD = genderCD;
    }

    public String getCountryOfBirthCD() {
        return countryOfBirthCD;
    }

    public void setCountryOfBirthCD(String countryOfBirthCD) {
        this.countryOfBirthCD = countryOfBirthCD;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAcceptTermsYN() {
        return acceptTermsYN;
    }

    public void setAcceptTermsYN(String acceptTermsYN) {
        this.acceptTermsYN = acceptTermsYN;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
