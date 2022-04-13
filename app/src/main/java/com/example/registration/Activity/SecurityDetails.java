package com.example.registration.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.registration.MVP.SecurityDetails.ISecurityDetailsPresenter;
import com.example.registration.MVP.SecurityDetails.ISecurityDetailsView;
import com.example.registration.MVP.SecurityDetails.SecurityDetailsPresenterImpl;
import com.example.registration.MVP.Signup.SignUpPresenterImpl;
import com.example.registration.R;
import com.example.registration.RetrofitAPI.models.request.Details;
import com.example.registration.RetrofitAPI.models.request.Miscellaneous;
import com.example.registration.RetrofitAPI.models.request.PersonalDetailRequest;
import com.example.registration.RetrofitAPI.models.request.QuesAns;
import com.example.registration.RetrofitAPI.models.request.SecDetails;
import com.example.registration.RetrofitAPI.models.request.SecurityDetailsRequest;
import com.example.registration.RetrofitAPI.models.request.misc;
import com.example.registration.RetrofitAPI.models.response.SecurityDetailsResponse;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;
import com.example.registration.Utils.ExtraUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SecurityDetails extends BaseActivity implements ISecurityDetailsView,View.OnTouchListener,View.OnClickListener {
   TextInputLayout txtQues1,txtQues2,txtAns1,txtAns2,txtAccPin,txtConfirmPin;
    AutoCompleteTextView edtQues1,edtQues2;
   EditText edtAns1,edtAns2,edtAccPin,edtConfirmPin;
   Button btnSaveContinue,btnBack;
   RadioButton FirstradioButton1,FirstradioButton2,SecondradioButton1,SecondradioButton2,ThirdradioButton1,ThirdradioButton2;
   ISecurityDetailsPresenter iSecurityDetailsPresenter;
   String Ques1,Ques2;
   String areYouPEP="Y",familyMemberPEP="Y",closeAssociatePEP="Y";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_details);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        txtQues1=findViewById(R.id.txtQues1);
        txtQues2=findViewById(R.id.txtQues2);
        txtAns1=findViewById(R.id.txtAns1);
        txtAns2=findViewById(R.id.txtAns2);
        txtAccPin=findViewById(R.id.txtAccPin);
        txtConfirmPin=findViewById(R.id.txtConfirmPin);
        edtQues1=findViewById(R.id.edtQues1);
        edtQues1.setOnClickListener(this);
        edtQues2=findViewById(R.id.edtQues2);
        edtQues2.setOnClickListener(this);
        edtAns1=findViewById(R.id.edtAns1);
        edtAns2=findViewById(R.id.edtAns2);
        edtAccPin=findViewById(R.id.edtAccPin);
        edtConfirmPin=findViewById(R.id.edtConfirmPin);
        btnSaveContinue=findViewById(R.id.btnSaveContinue);
        btnSaveContinue.setOnClickListener(this);
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        setQuestions();
        setQuestions2();
        FirstradioButton1=findViewById(R.id.FirstradioButton1);
        FirstradioButton1.setOnClickListener(this);
        FirstradioButton1.setChecked(true);
        FirstradioButton2=findViewById(R.id.FirstradioButton2);
        FirstradioButton2.setOnClickListener(this);
        SecondradioButton1=findViewById(R.id.SecondradioButton1);
        SecondradioButton1.setChecked(true);
        SecondradioButton1.setOnClickListener(this);
        SecondradioButton2=findViewById(R.id.SecondradioButton2);
        SecondradioButton2.setOnClickListener(this);
        ThirdradioButton1=findViewById(R.id.ThirdradioButton1);
        ThirdradioButton1.setOnClickListener(this);
        ThirdradioButton1.setChecked(true);
        ThirdradioButton2=findViewById(R.id.ThirdradioButton2);
        ThirdradioButton2.setOnClickListener(this);
    }
    private void setQuestions() {
        String[] items = {"What is your Favourite Book", "What is your mother's maiden name?", "Where did you meet your spouse",
        "What is your favourite food","What city were you born in?","What year did you graduate from your High School"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView1, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtQues1.setAdapter(arrayAdapter);
        edtQues1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ques1 = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    private void setQuestions2() {
        String[] items = {"What is your Favourite Book", "What is your mother's maiden name?", "Where did you meet your spouse",
                "What is your favourite food","What city were you born in?","What year did you graduate from your High School"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView1, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtQues2.setAdapter(arrayAdapter);
        edtQues2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ques2 = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    public boolean Validatee() {
        if (edtQues1.getText().toString().length() == 0) {
            txtQues1.setError("This field must be filled");
            return false;
        } else if (edtQues2.getText().toString().length() == 0) {
            txtQues2.setError("This field must be filled");
            return false;
        } else if (edtAns1.getText().length() == 0) {
            txtAns1.setError("This field must be filled");
            return false;
        }
        else if (edtAns2.getText().length() == 0) {
            txtAns2.setError("This field must be filled");
            return false;
        } else if (edtAccPin.getText().length() == 0 ) {
            txtAccPin.setError("This field must be filled");
            return false;
        } else if (edtConfirmPin.getText().length() == 0) {
            txtConfirmPin.setError("This field must be filled");
            return false;
        }
        else if (!edtConfirmPin.getText().toString().equals(edtAccPin.getText().toString())) {
            txtConfirmPin.setError("Confirm Pin must match Account Pin");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edtQues1:
            case R.id.edtQues2:
                hideKeyboard(this);
            break;
            case R.id.btnSaveContinue:
                if(Validatee()){
                    hitApi();
                }
            break;
            case R.id.btnBack:
                Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SecurityDetails.this,AddressAndIdentification.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            break;
            case R.id.FirstradioButton1:
                FirstradioButton1.setChecked(true);
                FirstradioButton2.setChecked(false);
                areYouPEP="Y";
            break;
            case R.id.FirstradioButton2:
                FirstradioButton2.setChecked(true);
                FirstradioButton1.setChecked(false);
                areYouPEP="N";
                break;
            case R.id.SecondradioButton1:
                SecondradioButton1.setChecked(true);
                SecondradioButton2.setChecked(false);
                familyMemberPEP="Y";
            break;
            case R.id.SecondradioButton2:
                SecondradioButton2.setChecked(true);
                SecondradioButton1.setChecked(false);
                familyMemberPEP="N";
                break;
            case R.id.ThirdradioButton1:
                ThirdradioButton1.setChecked(true);
                ThirdradioButton2.setChecked(false);
                closeAssociatePEP="Y";
            break;
            case R.id.ThirdradioButton2:
                ThirdradioButton2.setChecked(true);
                ThirdradioButton1.setChecked(false);
                closeAssociatePEP="N";
                break;
        }
    }
   private void hitApi(){
       iSecurityDetailsPresenter = new SecurityDetailsPresenterImpl();
       iSecurityDetailsPresenter.setView(this);
       QuesAns quesAnsArray1 = new QuesAns();
       quesAnsArray1.setQuestionCD("FAVOURITE_BOOK_QUES_CD");
       quesAnsArray1.setAnswer(edtAns1.getText().toString());
       QuesAns quesAnsArray2 = new QuesAns();
       quesAnsArray2.setQuestionCD("ROAD_GREW_UP_QUES_CD");
       quesAnsArray2.setAnswer(edtAns2.getText().toString());
       ArrayList<QuesAns> quesAns=new ArrayList<>();
       quesAns.add(0,quesAnsArray1);
       quesAns.add(1,quesAnsArray2);
       Details details=new Details();
       details.setAccountPin(edtAccPin.getText().toString());
       details.setQuestionList(quesAns);
//       SecDetails secDetails=new SecDetails();
//       secDetails.setSecurityDetails(details);
       misc m =new misc();
       m.setAreYouPEP(areYouPEP);
       m.setFamilyMemberPEP(familyMemberPEP);
       m.setCloseAssociatePEP(closeAssociatePEP);
//       Miscellaneous miscellaneous=new Miscellaneous();
//       miscellaneous.setMiscellaneous(m);
       SecurityDetailsRequest securityDetailsRequest=new SecurityDetailsRequest();
       securityDetailsRequest.setSecurityDetails(details);
       securityDetailsRequest.setMiscellaneous(m);
       iSecurityDetailsPresenter.hitDetails(securityDetailsRequest);
   }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void setResponse(SecurityDetailsResponse Response) {
        if (Response != null && Response.getMessage() != null && Response.getMessage().getSuccessMessage() != null && Response.getMessage().getSuccessMessage().length() != 0) {
           ExtraUtils.SecurityDetails=Response;
            Toast.makeText(this, Response.getMessage().getSuccessMessage(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SecurityDetails.this, ReviewDetails.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        } else {
            assert Response != null;
            assert Response.getMessage() != null;
            Toast.makeText(this, Response.getMessage().getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setError(Throwable error) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getDigest() {
        return ExtraUtils.DIGEST;
    }

    @Override
    public void setDigest(String digest) {
        ExtraUtils.DIGEST = digest;
        Toast.makeText(this, "Digest", Toast.LENGTH_SHORT).show();
    }
}