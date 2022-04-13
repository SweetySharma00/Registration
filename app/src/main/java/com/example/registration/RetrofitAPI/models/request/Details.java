package com.example.registration.RetrofitAPI.models.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Details {
    @SerializedName("accountPin")
    String accountPin;
    @SerializedName("questionList")
    ArrayList<QuesAns> QuestionList;

    public String getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(String accountPin) {
        this.accountPin = accountPin;
    }

    public ArrayList<QuesAns> getQuestionList() {
        return QuestionList;
    }

    public void setQuestionList(ArrayList<QuesAns> questionList) {
        QuestionList = questionList;
    }
}
