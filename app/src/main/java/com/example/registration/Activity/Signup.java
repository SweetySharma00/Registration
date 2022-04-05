package com.example.registration.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.Interface.Validators;
import com.example.registration.MVP.Signup.ISignUpPresenter;
import com.example.registration.MVP.Signup.ISignUpView;
import com.example.registration.MVP.Signup.SignUpPresenterImpl;
import com.example.registration.R;
import com.example.registration.RetrofitAPI.api.RetrofitFactory;
import com.example.registration.RetrofitAPI.models.request.SignupRequest;
import com.example.registration.RetrofitAPI.models.response.Message;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;
import com.example.registration.Utils.Connectivity;
import com.example.registration.Utils.ExtraUtils;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends BaseActivity implements Validators, View.OnClickListener,
        View.OnTouchListener,ISignUpView {

    TextView show,text,resendCode;
    Spinner spinnerCountryCode;
    EditText edtPhone,edtVerifyOtp;
    Button  btnSendCode,btnCancel,btnReset,btnVerifyCode,btnCancel1;
    TextInputLayout txtPhone,txtVerifyOtp;
    AutoCompleteTextView edCountryCode;
    String item;
    LinearLayout Linear1,Linear2;
    private ISignUpPresenter iSignUpPresenter;
    private SignupRequest signUpRequest;
    int c=0;

    public void Show() {
            if (Validatee()) {
//                if(c==0){
//                    btnSendCode.setText("Verify Code");
//                    c++;
//                }
//                btnVerifyCode.setVisibility(View.VISIBLE);
//                btnSendCode.setVisibility(View.GONE);
                text.setVisibility(View.VISIBLE);
                show.setVisibility(View.VISIBLE);

                            Linear1.setVisibility(View.GONE);
                            Linear2.setVisibility(View.VISIBLE);
                new CountDownTimer(10000,100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        show.setText("You Can Request Verification Code Again in  "+String.valueOf(millisUntilFinished/1000));
//                        show.setTextColor(Color.parseColor("#888484"));

                    }
                    @Override
                    public void onFinish() {
//                        show.setText("Resend Verification Code");
//                        show.setTextColor(Color.parseColor("#54A645"));
                        resendCode.setVisibility(View.VISIBLE);
                        show.setVisibility(View.GONE);
                    }
                }.start();
            }
    }
    private void hitApi() {
        String a=item+edtPhone.getText().toString();
                SignupRequest signupRequest=new SignupRequest();
                signupRequest.setPhone(a);
        new Gson().toJson(signupRequest);
        iSignUpPresenter = new SignUpPresenterImpl();
        iSignUpPresenter.setView(this);
        iSignUpPresenter.hitSignUp();

            }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtPhone=findViewById(R.id.txtPhone);
        txtVerifyOtp=findViewById(R.id.txtVerifyOtp);
        text=findViewById(R.id.text);
        text.setVisibility(View.GONE);
        show = findViewById(R.id.show);
        show.setVisibility(View.GONE);
        resendCode=findViewById(R.id.resendCode);
        resendCode.setVisibility(View.GONE);
        edtVerifyOtp=findViewById(R.id.edtVerifyOtp);
        btnSendCode = findViewById(R.id.btnSendCode);
        btnSendCode.setOnClickListener(this);
        btnCancel=findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnReset=findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);
//        btnVerifyCode=findViewById(R.id.btnVerifyCode);
//        btnVerifyCode.setVisibility(View.GONE);
        btnCancel1=findViewById(R.id.btnCancel1);
        btnCancel1.setOnClickListener(this);
//        btnCancel1.setVisibility(View.GONE);
        edCountryCode=findViewById(R.id.edCountryCode);
        btnVerifyCode = findViewById(R.id.btnVerifyCode);
        btnVerifyCode.setOnClickListener(this);
//        btnVerifyCode.setVisibility(View.GONE);
        Linear1=findViewById(R.id.Linear1);
        Linear2=findViewById(R.id.Linear2);
        Linear2.setVisibility(View.GONE);
        edtPhone = findViewById(R.id.edtPhone);
//        spinnerCountryCode=findViewById(R.id.spinnerCountryCode);
//        edtCountryCode=findViewById(R.id.edtCountryCode);
//        edtCountryCode.setOnTouchListener(this);
//        edtCountryCode.setOnClickListener(setCountryCodeAdapter());
//        edtCountryCode.setInputType(InputType.TYPE_NULL);
        setCountryCodeAdapter();


    }

    @Override
    public void Message() {
//       Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
        txtPhone.setError("This field must be filled");
//        txtPhone.setTextColor(Color.parseColor("#F85545"));

    }
    @Override
    public boolean Validatee() {
        if (edtPhone.getText().length() < 10 ) {
            Message();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
           switch(view.getId()){
               case R.id.btnSendCode:
                   if (Validatee()){
                       if (Connectivity.isConnected(this)){
                             Show();
                           hitApi();
//                           Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(this, "Net issue", Toast.LENGTH_SHORT).show();
                       }
                   }
                   break;
               case R.id.btnVerifyCode:
//                   if (Connectivity.isConnected(this)){
                           VerifyOtp();
//                       Toast.makeText(this, "Success1", Toast.LENGTH_SHORT).show();}
//                           signUp();
//                   }else{
//                       Toast.makeText(this, "Net issue", Toast.LENGTH_SHORT).show();
//                       }
                    break;
               case R.id.btnCancel:
               case R.id.btnCancel1:
                   Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                   break;
               case R.id.btnReset:
                   edtPhone.setText(null);
                   edtVerifyOtp.setText(null);
                   show.setVisibility(View.GONE);
                   Linear1.setVisibility(View.VISIBLE);
                   Linear2.setVisibility(View.GONE);
                   text.setVisibility(View.GONE);
                   resendCode.setVisibility(View.GONE);
                   Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
                   break;
           }
    }

    private boolean VerifyOtp() {
        String otp=edtVerifyOtp.getText().toString();
        if(otp.isEmpty() && otp.length()<6){
            txtVerifyOtp.setError("This field must be filled");
            return false;
        }
//        Observable<SignUpResponse> call=RetrofitFactory.getInstance().getApi().Verify(otp);
//        call.enqueue(new Callback<SignUpResponse>() {
//            @Override
//            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
//                SignUpResponse signUpResponse=response.body();
//                Toast.makeText(Signup.this, "res"+signUpResponse, Toast.LENGTH_SHORT).show();
//                if(response.isSuccessful()) {
//                    Toast.makeText(Signup.this, "sucesss", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Signup.this, PersonalDetails.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SignUpResponse> call, Throwable t) {
//                Toast.makeText(Signup.this, "ERROR", Toast.LENGTH_SHORT).show();
//            }
//        });
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
    private void setCountryCodeAdapter(){
        String[] items={"91","1"};
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("91");
//        arrayList.add("1");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.row_spinner,R.id.textView1,items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edCountryCode.setAdapter(arrayAdapter);
        edCountryCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 item=adapterView.getItemAtPosition(i).toString();
            }
        });
//        ArrayList<String> codeList = new ArrayList<>();
//            codeList.add("1");
//            codeList.add("91");
//
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.row_spinner,R.id.textView1,arrayList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        spinnerCountryCode.setAdapter(adapter);
//        spinnerCountryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                edtCountryCode.setText(spinnerCountryCode.getSelectedItem().toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
    }

    public void Resend(View view) {
        Toast.makeText(this, "Resending", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setResponse(SignUpResponse signUpResponse) {
        if (signUpResponse!=null && signUpResponse.getMessage()!=null && signUpResponse.getMessage().getSuccessMessage()!=null && signUpResponse.getMessage().getSuccessMessage().length()!=0){
            Toast.makeText(this,signUpResponse.getMessage().getSuccessMessage(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,PersonalDetails.class);
            ExtraUtils.SIGN_UP_DATA = signUpRequest;
            startActivity(intent);
        }
        else{
            assert signUpResponse != null;
            assert signUpResponse.getMessage() != null;
            Toast.makeText(this, signUpResponse.getMessage().getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void setError(Throwable error) {
        Toast.makeText(this, "abc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public SignupRequest getRequest() {
        return signUpRequest;
    }

//    @Override
//    public void setDigest(String digest) {
//        ExtraUtils.DIGEST = digest;
//        Toast.makeText(this, "digest", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public int getLayoutById() {
        return 0;
    }

    @Override
    public void getViewById() {

    }

    @Override
    public void manageToolBar() {

    }

    @Override
    public void hideKeyboard(Activity activity) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showSnackBarMessage(String message) {

    }

    @Override
    public String getTokenForAPI() {
        return null;
    }

    @Override
    public String getDeviceToken() {
        return null;
    }

    @Override
    public void setTokenForAPI(SignUpResponse signUpResponse) {

    }

    @Override
    public void updateVersionDialog(String updateUrl, String message) {

    }

    @Override
    public String getSelectedCountryCode() {
        return null;
    }

    @Override
    public String getSelectedGroupCode() {
        return null;
    }

    @Override
    public void onClientVersionUpdate(String updateUrl, String message) {

    }

    @Override
    public void showCountryLogo(int countyLogo, int appLogo, int title) {

    }

//    public ArrayList<String> getCountryPhoneCodes() {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("91");
//        arrayList.add("1");
//        return arrayList;
//    }
}