package com.example.registration.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.registration.R;
import com.example.registration.Utils.ExtraUtils;

public class ReviewDetails extends BaseActivity implements View.OnClickListener,View.OnTouchListener {
    Button btnSubmit,btnBack;
    TextView txtQues1,txtQues2;
    EditText edtFirstName,edtMiddleName,edtLastName,edtdob,edtGender,edtCob,edtNationality,edtStreetAdd,edtApartment,edtParish,edtCity,edtDistrict,edtEmail,
            edtTRN,edtIDtype,edtIDnumber,edtIDexp,edtOptForCash,edtTierDetails,edtOccupation,edtQues1,edtQues2,edtareYouPEP,edtfamilyMemberPEP,edtcloseAssociatePEP,
    edtAccType,edtAccNumber,edtBankName,edtBankBranch;
    ImageView edtPersonalDetails,edtSecurityDetails,edtMiscellaneous,edtAddressIdentification,imagePersonalDetails,imageFront,imageBack,imageSourceFunds,
            imageAddressProof;
    LinearLayout LinearHide,Linear1,Linear2,Linear3,Linear4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        txtQues1=findViewById(R.id.txtQues1);
        txtQues2=findViewById(R.id.txtQues2);
        edtFirstName=findViewById(R.id.edtFirstName);
        edtMiddleName=findViewById(R.id.edtMiddleName);
        edtLastName=findViewById(R.id.edtLastName);
        edtdob=findViewById(R.id.edtdob);
        edtGender=findViewById(R.id.edtGender);
        edtCob=findViewById(R.id.edtCob);
                    edtNationality=findViewById(R.id.edtNationality);
                            edtStreetAdd=findViewById(R.id.edtStreetAdd);
                                    edtApartment=findViewById(R.id.edtApartment);
                                            edtParish=findViewById(R.id.edtParish);
                                                    edtCity=findViewById(R.id.edtCity);
                                                    edtDistrict=findViewById(R.id.edtDistrict);
                                                    edtEmail=findViewById(R.id.edtEmail);
        edtTRN=findViewById(R.id.edtTRN);
        edtIDtype=findViewById(R.id.edtIDtype);
        edtIDnumber=findViewById(R.id.edtIDnumber);
        edtIDexp=findViewById(R.id.edtIDexp);
        edtOptForCash=findViewById(R.id.edtOptForCash);
        edtTierDetails=findViewById(R.id.edtTierDetails);
        edtOccupation=findViewById(R.id.edtOccupation);
        edtQues1=findViewById(R.id.edtQues1);
        edtQues2=findViewById(R.id.edtQues2);
        edtareYouPEP=findViewById(R.id.edtareYouPEP);
        edtfamilyMemberPEP=findViewById(R.id.edtfamilyMemberPEP);
        edtcloseAssociatePEP=findViewById(R.id.edtcloseAssociatePEP);
        edtAccType=findViewById(R.id.edtAccType);
        edtAccNumber=findViewById(R.id.edtAccNumber);
        edtBankName=findViewById(R.id.edtBankName);
        edtBankBranch=findViewById(R.id.edtBankBranch);
        edtPersonalDetails=findViewById(R.id.edtPersonalDetails);
        edtPersonalDetails.setOnClickListener(this);
        edtSecurityDetails=findViewById(R.id.edtSecurityDetails);
        edtSecurityDetails.setOnClickListener(this);
        edtMiscellaneous=findViewById(R.id.edtMiscellaneous);
        edtMiscellaneous.setOnClickListener(this);
        edtAddressIdentification=findViewById(R.id.edtAddressIdentification);
        edtAddressIdentification.setOnClickListener(this);
        LinearHide=findViewById(R.id.LinearHide);
        Linear1=findViewById(R.id.Linear1);
        Linear2=findViewById(R.id.Linear2);
        Linear3=findViewById(R.id.Linear3);
        imagePersonalDetails=findViewById(R.id.imagePersonalDetails);
        imagePersonalDetails.setVisibility(View.GONE);
        imageFront=findViewById(R.id.imageFront);
        imageBack=findViewById(R.id.imageBack);
        imageSourceFunds=findViewById(R.id.imageSourceFunds);
        imageAddressProof=findViewById(R.id.imageAddressProof);
        setValues();
    }
    private void setValues(){
        Glide.with(this)
                .load(ExtraUtils.details.getProfileImage())
                .into(imagePersonalDetails);
        edtFirstName.setText(ExtraUtils.details.getFirstName());
        edtMiddleName.setText(ExtraUtils.details.getMiddleName());
        edtLastName.setText(ExtraUtils.details.getLastName());
        edtdob.setText(ExtraUtils.details.getDob());
        if(ExtraUtils.details.getGenderCD()=="F")
        edtGender.setText("Female");
        else
            edtGender.setText("Male");
        edtCob.setText(ExtraUtils.details.getCountryOfBirthCD());
        edtNationality.setText(ExtraUtils.details.getNationality());
        edtStreetAdd.setText(ExtraUtils.Address.getStreetAddress());
        edtApartment.setText(ExtraUtils.Address.getApartment());
        edtParish.setText(ExtraUtils.Address.getParish());
        edtCity.setText(ExtraUtils.Address.getTown());
        edtDistrict.setText(ExtraUtils.Address.getDistrict());
        edtEmail.setText(ExtraUtils.Address.getEmail());
        edtTRN.setText(ExtraUtils.Address.getTrnNumber());
        edtIDtype.setText(ExtraUtils.Address.getIdTypeCD());
        edtIDnumber.setText(ExtraUtils.Address.getIdNumber());
        edtIDexp.setText(ExtraUtils.Address.getIdExpirationDate());
        Glide.with(this)
                .load(ExtraUtils.Address.getDocument())
                .into(imageFront);
        Glide.with(this)
                .load(ExtraUtils.Address.getDocumentBack())
                .into(imageBack);
//        imageFront.setImageResource(ExtraUtils.Address.getDocument());
        if(ExtraUtils.Address.getOptForCashRecharge() == "false"){
        edtOptForCash.setText("No");
        LinearHide.setVisibility(View.GONE);
        }
        else if(ExtraUtils.Address.getOptForCashRecharge() =="true"){
            edtOptForCash.setText("Yes");
            LinearHide.setVisibility(View.VISIBLE);
            Toast.makeText(this, "tier"+ExtraUtils.Address.getTier()+"asc", Toast.LENGTH_SHORT).show();
//            edtTierDetails.setText(ExtraUtils.Address.getTier());
            if(ExtraUtils.Address.getTier()=="1"){
//            edtTierDetails.setText("Tier 1");
                edtTierDetails.setText(ExtraUtils.Address.getTier());
            Linear1.setVisibility(View.GONE);
            }
            else if(ExtraUtils.Address.getTier()=="2"){
//                edtTierDetails.setText("Tier 2");
                edtTierDetails.setText(ExtraUtils.Address.getTier());
                Glide.with(this)
                        .load(ExtraUtils.Address.getSourceOfFund())
                        .into(imageSourceFunds);
                edtOccupation.setText(ExtraUtils.Address.getOccupation());
                Linear2.setVisibility(View.GONE);
            }
            else if(ExtraUtils.Address.getTier()=="3"){
//                edtTierDetails.setText("Tier 3");
                edtTierDetails.setText(ExtraUtils.Address.getTier());
                Glide.with(this)
                        .load(ExtraUtils.Address.getSourceOfFund())
                        .into(imageSourceFunds);
                Glide.with(this)
                        .load(ExtraUtils.Address.getAddressProof())
                        .into(imageAddressProof);
                edtOccupation.setText(ExtraUtils.Address.getOccupation());
                Linear3.setVisibility(View.GONE);
            }
            else if(ExtraUtils.Address.getTier()=="4"){
//                edtTierDetails.setText("Tier 4");
                edtTierDetails.setText(ExtraUtils.Address.getTier());
                Glide.with(this)
                        .load(ExtraUtils.Address.getSourceOfFund())
                        .into(imageSourceFunds);
                Glide.with(this)
                        .load(ExtraUtils.Address.getAddressProof())
                        .into(imageAddressProof);
                edtOccupation.setText(ExtraUtils.Address.getOccupation());
                edtBankName.setText(ExtraUtils.Address.getBankName());
                edtBankBranch.setText(ExtraUtils.Address.getBranchName());
                edtAccNumber.setText(ExtraUtils.Address.getAccountNumber());
                edtAccType.setText(ExtraUtils.Address.getAccountType());
            }
        }
        txtQues1.setText(ExtraUtils.SecurityDetails.getSecurityDetails().get(0).getQuestionCD());
        edtQues1.setText(ExtraUtils.SecurityDetails.getSecurityDetails().get(0).getAnswer());
        txtQues2.setText(ExtraUtils.SecurityDetails.getSecurityDetails().get(1).getQuestionCD());
        edtQues2.setText(ExtraUtils.SecurityDetails.getSecurityDetails().get(1).getAnswer());
//        Toast.makeText(this, ExtraUtils.SecurityDetails.getSecurityDetails().toString(), Toast.LENGTH_SHORT).show();
        if(ExtraUtils.SecurityDetails.getMiscellaneous().getAreYouPEP() == "Y")
        edtareYouPEP.setText("Yes");
        else
            edtareYouPEP.setText("No");
        if(ExtraUtils.SecurityDetails.getMiscellaneous().getFamilyMemberPEP() == "Y")
        edtfamilyMemberPEP.setText("Yes");
        else
            edtfamilyMemberPEP.setText("No");
        if(ExtraUtils.SecurityDetails.getMiscellaneous().getCloseAssociatePEP() == "Y")
        edtcloseAssociatePEP.setText("Yes");
        else
            edtcloseAssociatePEP.setText("No");
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.edtPersonalDetails:
               Intent intent = new Intent(ReviewDetails.this, PersonalDetails.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
               break;
           case R.id.edtAddressIdentification:
               Intent intent1 = new Intent(ReviewDetails.this, AddressAndIdentification.class);
               intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent1);
               break;
           case R.id.edtSecurityDetails:
           case R.id.edtMiscellaneous:
               Intent intent2 = new Intent(ReviewDetails.this, SecurityDetails.class);
               intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent2);
               break;
           case R.id.btnBack:
               Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
               Intent intent3 = new Intent(ReviewDetails.this, SecurityDetails.class);
               intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent3);
               break;
           case R.id.btnSubmit:
               Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show();
               break;
       }
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
}