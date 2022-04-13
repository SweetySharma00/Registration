package com.example.registration.RetrofitAPI.models.response;

import com.google.gson.annotations.SerializedName;

public class AddressIdentificationResponse {
    @SerializedName("message")
    Message message;
    @SerializedName("streetAddress")
    String streetAddress;
    @SerializedName("apartment")
    String apartment;
    @SerializedName("city")
    String city;
    @SerializedName("parish")
    String parish;
    @SerializedName("email")
    String email;
    @SerializedName("trnNumber")
    String trnNumber;
    @SerializedName("idTypeCD")
    String idTypeCD;
    @SerializedName("idNumber")
    String idNumber;
    @SerializedName("idExpirationDate")
    String idExpirationDate;
    @SerializedName("county")
    String county;
    @SerializedName("town")
    String town;
    @SerializedName("district")
    String district;
    @SerializedName("optForCashRecharge")
    String optForCashRecharge;
    @SerializedName("bankName")
    String bankName;
    @SerializedName("branchName")
    String branchName;
    @SerializedName("accountNumber")
    String accountNumber;
    @SerializedName("bankAddress")
    String bankAddress;
    @SerializedName("bankCode")
    String bankCode;
    @SerializedName("accountType")
    String accountType;
    @SerializedName("document")
    String document;
    @SerializedName("documentBack")
    String documentBack;
   @SerializedName("tier")
   String tier;
   @SerializedName("occupation")
   String occupation;
   @SerializedName("sourceOfFund")
   String sourceOfFund;
   @SerializedName("addressProof")
   String addressProof;
    public Message getMessage() {
        return message;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getApartment() {
        return apartment;
    }

    public String getCity() {
        return city;
    }

    public String getParish() {
        return parish;
    }

    public String getEmail() {
        return email;
    }

    public String getTrnNumber() {
        return trnNumber;
    }

    public String getIdTypeCD() {
        return idTypeCD;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getIdExpirationDate() {
        return idExpirationDate;
    }

    public String getCounty() {
        return county;
    }

    public String getTown() {
        return town;
    }

    public String getDistrict() {
        return district;
    }

    public String getOptForCashRecharge() {
        return optForCashRecharge;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getDocument() {
        return document;
    }

    public String getDocumentBack() {
        return documentBack;
    }

    public String getTier() {
        return tier;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getSourceOfFund() {
        return sourceOfFund;
    }

    public String getAddressProof() {
        return addressProof;
    }
}
