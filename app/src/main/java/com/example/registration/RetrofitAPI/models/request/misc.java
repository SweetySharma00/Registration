package com.example.registration.RetrofitAPI.models.request;

import com.google.gson.annotations.SerializedName;

public class misc {
    @SerializedName("areYouPEP")
    String areYouPEP;
    @SerializedName("familyMemberPEP")
    String familyMemberPEP;
     @SerializedName("closeAssociatePEP")
    String closeAssociatePEP;


    public String getAreYouPEP() {
        return areYouPEP;
    }

    public void setAreYouPEP(String areYouPEP) {
        this.areYouPEP = areYouPEP;
    }

    public String getFamilyMemberPEP() {
        return familyMemberPEP;
    }

    public void setFamilyMemberPEP(String familyMemberPEP) {
        this.familyMemberPEP = familyMemberPEP;
    }

    public String getCloseAssociatePEP() {
        return closeAssociatePEP;
    }

    public void setCloseAssociatePEP(String closeAssociatePEP) {
        this.closeAssociatePEP = closeAssociatePEP;
    }
}
