package com.example.registration.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registration.Interface.Validators;
import com.example.registration.MVP.PersonalDetails.IPersonalDetailPresenter;
import com.example.registration.MVP.PersonalDetails.IPersonalDetailView;
import com.example.registration.MVP.PersonalDetails.PersonalDetailPresenterImpl;
import com.example.registration.R;
import com.example.registration.RetrofitAPI.models.request.PersonalDetailRequest;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;
import com.example.registration.Utils.ExtraUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PersonalDetails extends BaseActivity implements Validators,View.OnClickListener,
        IPersonalDetailView,View.OnTouchListener {
    TextInputLayout textFirstName, textLastName, textDOB, textGender, text1, text2;
    EditText edtFirstName, edtLastName, edtDOB, edt1, edt2, edtMiddleName;
    AutoCompleteTextView edtGender;
    String item1, item2, item3;
    Button btnSaveContinue, btnBack;
    PersonalDetailRequest personalDetailRequest;
    private IPersonalDetailPresenter iPersonalDetailPresenter;
    private int mYear, mMonth, mDay;
    private boolean dobError = false;
    private MultipartBody.Part document = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        textFirstName = findViewById(R.id.txtFirstName);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        textLastName = findViewById(R.id.txtLastName);
        edtDOB = findViewById(R.id.edtDOB);
        textDOB = findViewById(R.id.txtDOB);
        edtMiddleName = findViewById(R.id.edtMiddleName);
        edtGender = findViewById(R.id.edtGender);
        textGender = findViewById(R.id.txtGender);
        text1 = findViewById(R.id.txt1);
        text2 = findViewById(R.id.txt2);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        setGender();
        setText1();
        setText2();
        btnSaveContinue = findViewById(R.id.btnSaveContinue);
        btnSaveContinue.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public boolean Validatee() {
        if (edtFirstName.getText().toString().length() == 0) {
            textFirstName.setError("This field must be filled");
            textLastName.setError("This field must be filled");
            textDOB.setError("This field must be filled");
            textGender.setError("This field must be filled");
            text1.setError("This field must be filled");
            text2.setError("This field must be filled");
//            edtFirstName.setTextColor(Color.RED);
            return false;
        } else if (edtLastName.getText().toString().length() == 0) {
            textLastName.setError("This field must be filled");
            return false;
        } else if (edtDOB.getText().length() == 0) {
            textDOB.setError("This field must be filled");
            return false;
        } else if (edtGender.getText().length() == 0) {
            textGender.setError("This field must be filled");
            return false;
        } else if (edt1.getText().length() == 0 ) {
            textGender.setError("This field must be filled");
            return false;
        } else if (edt2.getText().length() == 0) {
            textGender.setError("This field must be filled");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void Message() {

    }

    private void setGender() {
        String[] items = {"No Gender Selected", "Male", "Female"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView2, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtGender.setAdapter(arrayAdapter);
        edtGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item1 = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    private void setText1() {
        String[] items = {"No GenderSelected", "Male", "Female"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView2, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtGender.setAdapter(arrayAdapter);
        edtGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item2 = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    private void setText2() {
        String[] items = {"No GenderSelected", "Male", "Female"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView2, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtGender.setAdapter(arrayAdapter);
        edtGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item3 = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(PersonalDetails.this, (view, year, month, dayOfMonth) -> {
//                String day= String.valueOf(dayOfMonth);
//                String Month=String.valueOf(month+1);
//                if(day.length()==1)
//                    day="0"+day;
//                if(Month.length()==1)
//                    Month="0"+Month;
            Calendar userAge = new GregorianCalendar(year, month, dayOfMonth);
            Calendar minAdultAge = new GregorianCalendar();
            minAdultAge.add(Calendar.YEAR, -18);
            if (minAdultAge.before(userAge)) {
                textDOB.setError("Age must be 18 years or above");
                dobError = false;
            } else {
                dobError = true;
            }
            edtDOB.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            // edtDOB.setText(day + "" + "-" +(month + 1) + "" + "-" + year + "");
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 10000);
        datePickerDialog.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSaveContinue:
                if (Validatee()) {
                    iPersonalDetailPresenter=new PersonalDetailPresenterImpl();
//                    hitApi();
                    saveData();

                }
                break;
            case R.id.btnBack:
                Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PersonalDetails.this, Signup.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
    }
    private void saveData() {
//        String FirstName =edtFirstName.getText().toString(),
//                MiddleName=edtMiddleName.getText().toString(),
//                LastName=edtLastName.getText().toString(),
//                DOB=edtDOB.getText().toString(),
//                Gender=edtGender.getText().toString(),
//                COB = "IN",
//                YN="true",
//                nationality="Indian",
//                profileImage=null;
        RequestBody FirstName ,LastName;
        iPersonalDetailPresenter.setView(this);
        HashMap<String,RequestBody> map=new HashMap<>();
//        map.put("firstName",createPartFromString("hiii"));
//        if(!TextUtils.isEmpty(edtFirstName.getText().toString()))

            FirstName = RequestBody.create(okhttp3.MediaType.parse("text/plain"), edtFirstName.getText().toString());
//            FirstName=RequestBody.create(MediaType.parse("text/plain"), "Your Text");
//        else
//            FirstName=RequestBody.create(okhttp3.MediaType.parse("text/plain"), "");
//            textFirstName.setError("This Field must be filled");

//        if(!TextUtils.isEmpty(edtLastName.getText().toString()))
//            LastName = RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtLastName.getText().toString());
//        else
//            textLastName.setError("This Field must be filled");
//            LastName= RequestBody.create(okhttp3.MediaType.parse("text/plain"), "");
//        if(!TextUtils.isEmpty(edtDOB.getText()))
//            DOB=RequestBody.create(okhttp3.MediaType.parse("text/plain"), edtDOB.getText().toString());
//        else
//            textDOB.setError("This Field must be filled");
////            DOB=RequestBody.create(okhttp3.MediaType.parse("text/plain"), "");
//        if(!TextUtils.isEmpty(edtGender.getText().toString()))
//            Gender = RequestBody.create(okhttp3.MediaType.parse("text/plain"), edtGender.getText().toString());
//        else
//            textGender.setError("This Field must be filled");
//            Gender=RequestBody.create(okhttp3.MediaType.parse("text/plain"), "");
//        if(!TextUtils.isEmpty(edtDateTo.getText().toString()))
//            endDate = RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtDateTo.getText().toString());
//        else
//            endDate= RequestBody.create(okhttp3.MediaType.parse("text/plain"), "");
//        if(!TextUtils.isEmpty(edtComments.getText()))
//            comments=RequestBody.create(okhttp3.MediaType.parse("text/plain"), edtComments.getText().toString());
//        else
//            comments=RequestBody.create(okhttp3.MediaType.parse("text/plain"), "");
        map.put("firstName",FirstName);
//        map.put("middleName",MiddleName);
//        map.put("lastName",LastName);
//        map.put("dob",DOB);
//        map.put("genderCD",Gender);
//        map.put("countryOfBirthCD",COB);
//        map.put("profileImage",profileImage);
//        map.put("acceptTermsYN",YN);
//        map.put("nationality",nationality);
        iPersonalDetailPresenter.hitDetails(map,document);

    }

//    private MultipartBody.Part hitApi() {
//        personalDetailRequest = new PersonalDetailRequest();
//        personalDetailRequest.setDob(edtDOB.getText().toString());
//        personalDetailRequest.setFirstName(edtFirstName.getText().toString());
//        personalDetailRequest.setMiddleName(edtMiddleName.getText().toString());
//        personalDetailRequest.setLastName(edtLastName.getText().toString());
//        personalDetailRequest.setAcceptTermsYN("Y");
//        personalDetailRequest.setCountryOfBirthCD("India");
//        personalDetailRequest.setGenderCD(edtGender.getText().toString());
//        personalDetailRequest.setNationality("Indian");
//        personalDetailRequest.setProfileImage(null);
//        iPersonalDetailPresenter=new PersonalDetailPresenterImpl();
//        iPersonalDetailPresenter.setView(this);
//        iPersonalDetailPresenter.hitSignUp();
//        return null;
//    }

    @Override
    public void setResponse(SignUpResponse signUpResponse) {
        if (signUpResponse != null && signUpResponse.getMessage() != null && signUpResponse.getMessage().getSuccessMessage() != null && signUpResponse.getMessage().getSuccessMessage().length() != 0) {
            Toast.makeText(this, signUpResponse.getMessage().getSuccessMessage(), Toast.LENGTH_LONG).show();

        } else {
            assert signUpResponse != null;
            assert signUpResponse.getMessage() != null;
            Toast.makeText(this, signUpResponse.getMessage().getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setError(Throwable error) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public PersonalDetailRequest getRequest() {
        return personalDetailRequest;
    }

    @Override
    public String getDigest() {
        return ExtraUtils.DIGEST;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            switch (view.getId()) {
                case R.id.edtDOB:
                    hideKeyboard(this);
//                ageValidate();
                    showDatePicker();
                    break;
//                case R.id.txtGender:
//                    spinnerCountryCode.performClick();
//                    break;
                case R.id.txtGender:
//                case R.id.txt1:
//                case R.id.txt2:
                    hideKeyboard(this);
                    break;

            }
        }
            return true;
        }


    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }





}