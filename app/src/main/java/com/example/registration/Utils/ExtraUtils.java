package com.example.registration.Utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.registration.RetrofitAPI.models.request.SignupRequest;
import com.example.registration.RetrofitAPI.models.response.AddressIdentificationResponse;
import com.example.registration.RetrofitAPI.models.response.PersonalDetailResponse;
import com.example.registration.RetrofitAPI.models.response.SecurityDetailsResponse;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ExtraUtils {
    public static final int UNAUTHORIZED = 401;
    public static final int LARGE_ENTITY = 413;
    public static final int FORBIDDEN = 403;
    public static final int ServiceError = 500;

    public static PersonalDetailResponse details;
    public static AddressIdentificationResponse Address;
    public static SecurityDetailsResponse SecurityDetails;
    public static final String DO_LOGIN = "DO_LOGIN";
    public static final String ADDRESS_DATA = "ADDRESS_DATA";
    public static final String HOUSEHOLD_DATA = "HOUSEHOLD_DATA";
    public static final String STAGE_ONE_RESPONSE = "STAGE_ONE_RESPONSE";

    public static final String STAGE_TWO_RESPONSE = "STAGE_TWO_RESPONSE";
    public static final String SYMPTOM_HISTORY = "SYMPTOM_HISTORY";
    public static final String MEDICAL_RECOMMENDATION = "MEDICAL_RECOMMENDATION";
    public static final String MEDICAL_RECOMMENDATION_VALUE = "MEDICAL_RECOMMENDATION_VALUE";
    public static final String HOUSEHOLD_MESSAGE_DATA = "HOUSEHOLD_MESSAGE_DATA";
    public static final String ADDRESS_FLOW = "ADDRESS_FLOW";
    public static final String VERIFIED_ADDRESS_DATA = "VERIFIED_ADDRESS_DATA";
    public static final int VERIFY_ADDRESS_REQUEST = 101;
    public static final String TRAVEL_HISTORY = null;
    public static final String TEST_HISTORY = "TEST";
    public static String DIGEST = "";
    public static SignupRequest SIGN_UP_DATA = null;
//    public static ArrayList<Household> householdresponselist=null;
//    public static List<PriorMedicalCondition> priorMedicalConditionslist=null;
//    public static List<QuarantineNotice> quarantineNotice=null;
//    public static List<UserFeedback> userFeedbackList=null;

    public static final String INDIVIDUAL_DATA = "INDIVIDUAL_DATA";
//    public static Household household=null;
    public static int  householdposition=-1;

    public static  String PERSONID = "PERSON_ID";
    public static  String SELECTED_COUNTRY = "SELECTED_COUNTRY";
    public static  String ACCESS_KEY = "Access_Key";
    public static  String DESCLAIMER_URL_AMPLIA = "http://www.health.gov.tt/sitepages/default.aspx?id=299";


    public static final String LOGOUT_TO_WELCOME = "LOGOUT_TO_WELCOME";
    public static String token = null;


    public static String selectedcountry="selectedcountry";


    public static String COUNTRYCODE = "TT";
    public static String ORGANIZATIONCODE = "AMPLIA";
    public static final String CLIENT_VERSION_OUTDATED = "ClientVersionOutdated";

    public static String IMAGE;


    public static void setHint(TextInputLayout textInputLayout) {
        String hint = textInputLayout.getHint().toString();
        hint+="*";
        SpannableString spannableString = new SpannableString(hint);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F64346")), hint.length()-1, hint.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textInputLayout.setHint(spannableString);
    }

    public static void setHint(TextView textView) {
        String text = textView.getText().toString();
        text+="*";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F64346")), text.length()-1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }
    public static void setHintNotRequired(TextView textView) {
        String text = textView.getText().toString();
        //text+="*";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F64346")), text.length()-1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }


}
