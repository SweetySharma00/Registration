package com.example.registration.RetrofitAPI.models.request;

import com.google.gson.annotations.SerializedName;

public class QuesAns {
    @SerializedName("questionCD")
    String questionCD;
    @SerializedName("answer")
    String answer;

    public String getQuestionCD() {
        return questionCD;
    }

    public void setQuestionCD(String questionCD) {
        this.questionCD = questionCD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
