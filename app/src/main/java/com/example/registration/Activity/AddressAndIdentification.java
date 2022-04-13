package com.example.registration.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.registration.Model.SetOrientation;
import com.example.registration.RetrofitAPI.models.response.AddressIdentificationResponse;
import com.example.registration.Utils.CompressFile;
import com.example.registration.Utils.ExtraUtils;
import com.example.registration.Utils.FileUtils;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.MVP.AddressIdentification.AddressIdentificationPresenterImpl;
import com.example.registration.MVP.AddressIdentification.IAddressIdentificationPresenter;
import com.example.registration.MVP.AddressIdentification.IAddressIdentificationView;
import com.example.registration.MVP.PersonalDetails.PersonalDetailPresenterImpl;
import com.example.registration.R;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddressAndIdentification extends BaseActivity implements IAddressIdentificationView,View.OnClickListener,View.OnTouchListener {
    TextInputLayout txtStreetAdd,txtApartment,txtParish,txtCity,txtDistrict,txtEmail,txtTRN,txtIDtype,txtIDnumber,txtIDexp,txtSelectTier,txtOccupation,
            txtBankName,txtBankBranch,txtAccNumber,txtAccType;
    EditText edtStreetAdd,edtApartment,edtEmail,edtTRN,edtIDnumber,edtIDexp,edtOccupation,edtBankBranch,edtAccNumber;
    AutoCompleteTextView edtParish,edtCity,edtDistrict,edtIDtype,edtSelectTier,edtBankName,edtAccType;
    Button btnSaveContinue,btnBack;
    String Parish,City,District,IDType,AccType,BankName,Tier,T;
    ImageView imageFront,imageBack,imageSourceFunds,imageAddressProof;
    TextView txtFront,txtBack,txtAddress,txtSource;
    MaterialCardView FrontID,BackID,AddressProof,SourceFunds;
    LinearLayout Linear1,LinearSourceFunds,LinearAddressProof,Linear2,Linear3,Linear4;
    RadioButton radioButton1,radioButton2;
    Boolean radio1=true;
    IAddressIdentificationPresenter iAddressIdentificationPresenter;
    private MultipartBody.Part document,documentBack,sourceOfFund,addressProof;
    private static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE=0;
    private int CAMERA = 2, GALLERY_FRONT = 1,GALLERY_BACK=2,GALLERY_SOURCE=3,GALLERY_ADDRESS=4;
    Bitmap photo = null;
    String currentPhotoPath = "";
    Intent intent = null;
    private Uri fileUri = null;
    private String profile_img = "profileImage";
    private String OptforCash="false";
    private static final int FILE_SELECT_CODE = 0;
    File file,file_back,file_SourceFunds,file_AddressProof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_and_identification);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        txtFront=findViewById(R.id.txtFront);
        txtBack=findViewById(R.id.txtBack);
        txtAddress=findViewById(R.id.txtAddress);
        txtSource=findViewById(R.id.txtSource);
        txtStreetAdd=findViewById(R.id.txtStreetAdd);
        txtApartment=findViewById(R.id.txtApartment);
        txtParish=findViewById(R.id.txtParish);
        txtCity=findViewById(R.id.txtCity);
        txtDistrict=findViewById(R.id.txtDistrict);
        txtEmail=findViewById(R.id.txtEmail);
        txtTRN=findViewById(R.id.txtTRN);
        txtIDtype=findViewById(R.id.txtIDtype);
        txtIDnumber=findViewById(R.id.txtIDnumber);
        txtIDexp=findViewById(R.id.txtIDexp);
        txtSelectTier=findViewById(R.id.txtSelectTier);
        txtOccupation=findViewById(R.id.txtOccupation);
        txtBankName=findViewById(R.id.txtBankName);
        txtBankBranch=findViewById(R.id.txtBankBranch);
        txtAccNumber=findViewById(R.id.txtAccNumber);
        txtAccType=findViewById(R.id.txtAccType);
        edtStreetAdd=findViewById(R.id.edtStreetAdd);
        edtApartment=findViewById(R.id.edtApartment);
        edtEmail=findViewById(R.id.edtEmail);
        edtTRN=findViewById(R.id.edtTRN);
        edtIDnumber=findViewById(R.id.edtIDnumber);
        edtIDexp=findViewById(R.id.edtIDexp);
        edtOccupation=findViewById(R.id.edtOccupation);
        edtBankBranch=findViewById(R.id.edtBankBranch);
        edtAccNumber=findViewById(R.id.edtAccNumber);
        edtParish=findViewById(R.id.edtParish);
        edtCity=findViewById(R.id.edtCity);
        edtDistrict=findViewById(R.id.edtDistrict);
        edtIDtype=findViewById(R.id.edtIDtype);
        edtSelectTier=findViewById(R.id.edtSelectTier);
        edtBankName=findViewById(R.id.edtBankName);
        edtAccType=findViewById(R.id.edtAccType);
        btnSaveContinue=findViewById(R.id.btnSaveContinue);
        btnSaveContinue.setOnClickListener(this);
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        setParish();
        setCity();
        setDistrict();
        setIDType();
        setBankName();
        setAccType();
        setTier();
        imageFront=findViewById(R.id.imageFront);
        imageBack=findViewById(R.id.imageBack);
        imageAddressProof=findViewById(R.id.imageAddressProof);
        imageSourceFunds=findViewById(R.id.imageSourceFunds);
        txtFront=findViewById(R.id.txtFront);
        Linear1=findViewById(R.id.Linear1);
        Linear1.setVisibility(View.GONE);
        Linear1.setOnClickListener(this);
        Linear2=findViewById(R.id.Linear2);
        Linear2.setVisibility(View.GONE);
        Linear2.setOnClickListener(this);
        Linear3=findViewById(R.id.Linear3);
        Linear3.setVisibility(View.GONE);
        Linear3.setOnClickListener(this);
        Linear4=findViewById(R.id.Linear4);
        Linear4.setVisibility(View.GONE);
        Linear4.setOnClickListener(this);
        radioButton1=findViewById(R.id.radioButton1);
        radioButton1.setOnClickListener(this);
        radioButton1.setChecked(true);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton2.setOnClickListener(this);
        FrontID=findViewById(R.id.FrontID);
        FrontID.setOnClickListener(this);
        BackID=findViewById(R.id.BackID);
        BackID.setOnClickListener(this);
        SourceFunds=findViewById(R.id.SourceFunds);
        SourceFunds.setOnClickListener(this);
        AddressProof=findViewById(R.id.AddressProof);
        AddressProof.setOnClickListener(this);
        edtParish.setOnClickListener(this);
        edtCity.setOnClickListener(this);
        edtDistrict.setOnClickListener(this);
        edtIDtype.setOnClickListener(this);
        edtSelectTier.setOnClickListener(this);
        edtBankName.setOnClickListener(this);
        edtAccType.setOnClickListener(this);
    }
    private void setParish() {
        String[] items = {"St.Mary","Westmoreland","Trelawny","St.Ann","Manchester","St.Andrew","Clarendon"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView1, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtParish.setAdapter(arrayAdapter);
        edtParish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Parish = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    private void setCity() {
        String[] items = {"Alexandra","ocho rios","Bamboo","Albania","Algeria","American Samoa","Andorra","Angola"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView1, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtCity.setAdapter(arrayAdapter);
        edtCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                City = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    private void setDistrict() {
        String[] items = {"ocho rios","chalky hill","Aland Islands","Albania","Algeria","American Samoa","Andorra","Angola"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView1, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtDistrict.setAdapter(arrayAdapter);
        edtDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                District = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    private void setIDType() {
        String[] items = {"Select","Driver's License","Passport","National ID"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView1, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtIDtype.setAdapter(arrayAdapter);
        edtIDtype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IDType = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    private void setBankName() {
        String[] items = {"Select","JN Bank","JMMB Bank","FirstGlobal Bank","Sagicor Bank"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView1, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtBankName.setAdapter(arrayAdapter);
        edtBankName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BankName = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    private void setAccType() {
        String[] items = {"Select","Current Account","Savings Account"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView1, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtAccType.setAdapter(arrayAdapter);
        edtAccType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AccType = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    private void setTier() {
        String[] items = {"Tier 1","Tier 2","Tier 3","Tier 4"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_spinner, R.id.textView1, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtSelectTier.setAdapter(arrayAdapter);
        edtSelectTier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tier = adapterView.getItemAtPosition(i).toString();
                setTierDetails();
                Toast.makeText(AddressAndIdentification.this, Tier, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setTierDetails(){
        if(Tier =="Tier 1"){
            Linear1.setVisibility(View.VISIBLE);
            Linear2.setVisibility(View.GONE);
            Linear3.setVisibility(View.GONE);
            Linear4.setVisibility(View.GONE);
        }
        if(Tier =="Tier 2"){
            Linear1.setVisibility(View.VISIBLE);
            Linear2.setVisibility(View.VISIBLE);
            Linear3.setVisibility(View.GONE);
            Linear4.setVisibility(View.GONE);
        }
        if(Tier =="Tier 3"){
            Linear1.setVisibility(View.VISIBLE);
            Linear2.setVisibility(View.VISIBLE);
            Linear3.setVisibility(View.VISIBLE);
            Linear4.setVisibility(View.GONE);
        }
        if(Tier =="Tier 4"){
            Linear1.setVisibility(View.VISIBLE);
            Linear2.setVisibility(View.VISIBLE);
            Linear3.setVisibility(View.VISIBLE);
            Linear4.setVisibility(View.VISIBLE);
        }
    }
    public boolean Validatee() {
        if (edtStreetAdd.getText().toString().length() == 0) {
            txtStreetAdd.setError("This field must be filled");
//            textLastName.setError("This field must be filled");
//            textDOB.setError("This field must be filled");
//            textGender.setError("This field must be filled");
//            textCOB.setError("This field must be filled");
//            textNationality.setError("This field must be filled");
//            edtFirstName.setTextColor(Color.RED);
            return false;
        } else if (edtParish.getText().toString().length() == 0) {
            txtParish.setError("Required field");
            return false;
        } else if (edtCity.getText().length() == 0) {
            txtCity.setError("Required field");
            return false;
        } else if (edtDistrict.getText().length() == 0) {
            txtDistrict.setError("Required field");
            return false;
        } else if (edtEmail.getText().length() == 0 ) {
            txtEmail.setError("Enter valid Email");
            return false;
        } else if (edtTRN.getText().length() == 0) {
            txtTRN.setError("Please enter a valid 9 digit TRN");
            return false;
        }
         else if (edtIDtype.getText().toString().equals("Select")) {
            txtIDtype.setError("This field must be filled");
            return false;
        }
         else if (edtIDnumber.getText().length() == 0) {
        txtIDnumber.setError("This field must be filled");
        return false;
        }
        else if (edtIDexp.getText().length() == 0) {
        txtIDexp.setError("This field must be filled");
        return false;
        }else {
            return true;
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.edtParish:
            case R.id.edtCity:
            case R.id.edtDistrict:
            case R.id.edtIDtype:
            case R.id.edtSelectTier:
            case R.id.edtBankName:
            case R.id.edtAccType:
                hideKeyboard(this);
            break;
            case R.id.radioButton1:
                Linear1.setVisibility(View.GONE);
                OptforCash="false";
                break;
            case R.id.radioButton2:
                Linear1.setVisibility(View.VISIBLE);
                OptforCash="true";

                break;
            case R.id.btnSaveContinue:
                if(Validatee()){
                    hitapi();
                }
                break;
            case R.id.btnBack:
                Toast.makeText(this, "Cancelling", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddressAndIdentification.this, PersonalDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.FrontID:
                hideKeyboard(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddressAndIdentification.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        showPictureDialog(1);


                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
                    }
                } else {
                    showPictureDialog(1);
                }

                break;
            case R.id.BackID:
                hideKeyboard(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddressAndIdentification.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        showPictureDialog(2);


                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
                    }
                } else {
                    showPictureDialog(2);
                }

                break;
            case R.id.SourceFunds:
                hideKeyboard(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddressAndIdentification.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        showPictureDialog(3);


                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
                    }
                } else {
                    showPictureDialog(3);
                }

                break;
            case R.id.AddressProof:
                hideKeyboard(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddressAndIdentification.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        showPictureDialog(4);


                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
                    }
                } else {
                    showPictureDialog(4);
                }

                break;

        }
    }
    private void hitapi(){
        iAddressIdentificationPresenter=new AddressIdentificationPresenterImpl();
        iAddressIdentificationPresenter.setView(this);
        HashMap<String, RequestBody> map=new HashMap<>();


        map.put("streetAddress",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtStreetAdd.getText().toString()));
        map.put("apartment",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtApartment.getText().toString()));
        map.put("parish",RequestBody.create(okhttp3.MediaType.parse("text/plain"),"CLARENDON"));
        map.put("city",RequestBody.create(okhttp3.MediaType.parse("text/plain"),""));
        map.put("district",RequestBody.create(okhttp3.MediaType.parse("text/plain"),"MCKOY"));
        map.put("email",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtEmail.getText().toString()));
        map.put("trnNumber",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtTRN.getText().toString()));
        map.put("idTypeCD",RequestBody.create(okhttp3.MediaType.parse("text/plain"),IDType));
        map.put("idNumber",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtIDnumber.getText().toString()));
        map.put("idExpirationDate",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtIDexp.getText().toString()));
        map.put("county",RequestBody.create(okhttp3.MediaType.parse("text/plain"),"MIDDLESEX"));
//        map.put("documentBack",RequestBody.create(okhttp3.MediaType.parse("text/plain"),""));
                map.put("town",RequestBody.create(okhttp3.MediaType.parse("text/plain"),"AENON_TOWN"));
        if(file!=null){
            document=MultipartBody.Part.createFormData("document", file.getName(), RequestBody.create(MediaType.parse("*/*"), file));
        }

        if(file_back!=null){
            documentBack=MultipartBody.Part.createFormData("documentBack", file_back.getName(), RequestBody.create(MediaType.parse("*/*"), file_back));
        }



//

        map.put("optForCashRecharge",RequestBody.create(okhttp3.MediaType.parse("text/plain"),OptforCash));
        Toast.makeText(this, OptforCash, Toast.LENGTH_SHORT).show();
        if(OptforCash == "true"){
            Tier();
            map.put("tier",RequestBody.create(okhttp3.MediaType.parse("text/plain"),T));
            if(T=="2"){
                if(file_SourceFunds!=null){
                    sourceOfFund=MultipartBody.Part.createFormData("sourceOfFund", file_SourceFunds.getName(), RequestBody.create(MediaType.parse("*/*"), file_SourceFunds));
                }
//                map.put("sourceOfFund",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtBankBranch.getText().toString()));
                map.put("occupation",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtOccupation.getText().toString()));
            }
            if(T=="3"){
                if(file_SourceFunds!=null){
                    sourceOfFund=MultipartBody.Part.createFormData("sourceOfFund", file_SourceFunds.getName(), RequestBody.create(MediaType.parse("*/*"), file_SourceFunds));
                }
              //  map.put("sourceOfFund",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtBankBranch.getText().toString()));
                map.put("occupation",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtOccupation.getText().toString()));
                if(file_AddressProof!=null){
                    addressProof=MultipartBody.Part.createFormData("addressProof", file_AddressProof.getName(), RequestBody.create(MediaType.parse("*/*"), file_AddressProof));
                }
                //map.put("addressProof",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtBankBranch.getText().toString()));
            }
            if(T =="4"){
                if(file_SourceFunds!=null){
                    sourceOfFund=MultipartBody.Part.createFormData("sourceOfFund", file_SourceFunds.getName(), RequestBody.create(MediaType.parse("*/*"), file_SourceFunds));
                }
                if(file_AddressProof!=null){
                    addressProof=MultipartBody.Part.createFormData("addressProof", file_AddressProof.getName(), RequestBody.create(MediaType.parse("*/*"), file_AddressProof));
                }
                //  map.put("sourceOfFund",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtBankBranch.getText().toString()));
                map.put("occupation",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtOccupation.getText().toString()));
                //map.put("addressProof",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtBankBranch.getText().toString()));
                map.put("bankName",RequestBody.create(okhttp3.MediaType.parse("text/plain"),BankName));
                map.put("accountNumber",RequestBody.create(okhttp3.MediaType.parse("text/plain"),"1234567890"));
                map.put("branchName",RequestBody.create(okhttp3.MediaType.parse("text/plain"),edtOccupation.getText().toString()));
                map.put("accountType",RequestBody.create(okhttp3.MediaType.parse("text/plain"),AccType));
                map.put("bankCode",RequestBody.create(okhttp3.MediaType.parse("text/plain"),"004432"));
               map.put("bankAddress",RequestBody.create(okhttp3.MediaType.parse("text/plain"),"Sikandrabad"));
            }
        }



        iAddressIdentificationPresenter.hitDetails(map,document,documentBack,sourceOfFund,addressProof);
    }
    private void Tier(){
        if(Tier == "Tier 1")
            T="1";
        if(Tier == "Tier 2")
            T="2";
        if(Tier == "Tier 3")
            T="3";
        if(Tier == "Tier 4")
            T="4";
    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            switch (view.getId()) {
                case R.id.edtIDtype:
                case R.id.edtCity:
                case R.id.edtParish:
                case R.id.edtDistrict:
                    hideKeyboard(this);
                break;
            }
        }
        return true;
    }
    private void showPictureDialog(int choose) {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.Messagedialog));
        pictureDialog.setTitle("Select Option");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //Toast.makeText(getActivity(), "gfh", Toast.LENGTH_SHORT);
                                choosePhotoFromGallary(choose);
                                break;
                            case 1:
                                takePhotoFromCamera();
//                                requestCameraPermission();
                                break;
                        }
                    }
                });
        pictureDialog.show();


    }
    public void choosePhotoFromGallary(int choose) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(choose == 1)
             startActivityForResult(galleryIntent, GALLERY_FRONT);
        if(choose == 2)
            startActivityForResult(galleryIntent, GALLERY_BACK);
        if(choose == 3)
            startActivityForResult(galleryIntent, GALLERY_SOURCE);
        if(choose == 4)
            startActivityForResult(galleryIntent, GALLERY_ADDRESS);
    }
    private void takePhotoFromCamera() {
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Dexter.withActivity(AddressAndIdentification.this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // Ensure that there's a camera activity to handle the intent
                        if (takePictureIntent.resolveActivity(AddressAndIdentification.this.getPackageManager()) != null) {
                            // Create the File where the photo should go
                            File photoFile = null;
                            try {
                                photoFile = createImageFile();
                            } catch (IOException ex) {

                            }
                            if (photoFile != null) {
                                Uri photoURI = FileProvider.getUriForFile(AddressAndIdentification.this,
                                        "com.example.registration.fileprovider", photoFile);
                                Log.e("photoURI", "onPermissionGranted: " + photoURI);
                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                startActivityForResult(takePictureIntent, CAMERA);
                            }
                        }
                        //startActivityForResult(intent, CAMERA);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
//                            showSettingsDialog();
                            Toast.makeText(AddressAndIdentification.this, "Permission denied", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();

        return image;
    }
//    private void showSettingsDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Need Permissions");
//        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
//        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//                openSettings();
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        builder.show();
//
//    }
//    private void openSettings() {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                    Uri.parse("package:" + this.getPackageName()));
//            this.finish();
//            startActivity(intent);
//            return;
//        }


      /*  Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);*/
//    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY_BACK || requestCode ==GALLERY_FRONT || requestCode ==GALLERY_SOURCE || requestCode ==GALLERY_ADDRESS && resultCode == RESULT_OK && data != null) {


            //the image URI
            Uri selectedImage = data.getData();
            try {
                photo = MediaStore.Images.Media
                        .getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileUri = selectedImage;
            //calling the upload file method after choosing the file
            uploadFile(selectedImage, "My Image",requestCode,true);
            Log.d("GALLERY uri path", String.valueOf(selectedImage));
            if(requestCode == GALLERY_FRONT)
               txtFront.setVisibility(View.GONE);
            if(requestCode == GALLERY_BACK)
                txtBack.setVisibility(View.GONE);
            if(requestCode == GALLERY_SOURCE)
                txtSource.setVisibility(View.GONE);
            if(requestCode == GALLERY_ADDRESS)
                txtAddress.setVisibility(View.GONE);


        } else if (requestCode == CAMERA && resultCode == RESULT_OK) {
           /* Bitmap photo = (Bitmap) data.getExtras().get("data");
            Uri tempUri = getImageUri(getActivity(), photo);
            fileUri = tempUri;
            uploadFile(tempUri, "My Image");*/

            Log.e("onPermissionGranted", ": success" + currentPhotoPath);
            File file = new File(currentPhotoPath);
            try {
                if (file != null) {
                    photo = MediaStore.Images.Media
                            .getBitmap(this.getContentResolver(), Uri.fromFile(file));
                    Uri tempUri = getImageUri(this, photo);

                    fileUri = tempUri;

//            Uri selectedImage = data.getData();
                    uploadFile(tempUri, "My Image", requestCode,true);

                    Log.d("camera uri path", String.valueOf(tempUri));
                    txtFront.setVisibility(View.GONE);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
//        else if (requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK){
//            Uri uri = data.getData();
//            uploadFile(uri, "My PDF", requestCode,false);
////            txtDocName.setVisibility(View.VISIBLE);
//        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, System.currentTimeMillis()+"", null);
        return Uri.parse(path);
    }
    private void uploadFile(Uri fileUri, String desc,int requestCode, boolean image) {
        if(requestCode == GALLERY_FRONT) {
            if (image) {
                profile_img = FileUtils.getPathFromUri(this, fileUri);
//                Log.d("real path uri", FileUtils.getPathFromUri(this, fileUri));
                //creating a file
                file = new File(FileUtils.getPathFromUri(this, fileUri));
                try {
                    if (BitmapFactory.decodeFile(file.getAbsolutePath(), new BitmapFactory.Options()).getWidth() > BitmapFactory.decodeFile(file.getAbsolutePath(), new BitmapFactory.Options()).getHeight())
                        file = SetOrientation.convertBitmapToFile(SetOrientation.rotateImage(BitmapFactory.decodeFile(file.getPath()), 90), AddressAndIdentification.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                file = CompressFile.getCompressedImageFile(file, this);
                Drawable backgroundImage = Drawable.createFromPath(profile_img);
                imageFront.setImageDrawable(backgroundImage);

            } else {
                imageFront.setImageDrawable(getResources().getDrawable(R.drawable.i));
                profile_img = FileUtils.getPathFromUri(this, fileUri);
                file = new File(FileUtils.getPathFromUri(this, fileUri));
                txtFront.setText(file.getName());
            }
        }
        if(requestCode == GALLERY_BACK) {
            if (image) {
                profile_img = FileUtils.getPathFromUri(this, fileUri);
                Log.d("real path uri", FileUtils.getPathFromUri(this, fileUri));
                //creating a file
                file_back= new File(FileUtils.getPathFromUri(this, fileUri));
                try {
                    if (BitmapFactory.decodeFile(file_back.getAbsolutePath(), new BitmapFactory.Options()).getWidth() > BitmapFactory.decodeFile(file_back.getAbsolutePath(), new BitmapFactory.Options()).getHeight())
                        file_back = SetOrientation.convertBitmapToFile(SetOrientation.rotateImage(BitmapFactory.decodeFile(file_back.getPath()), 90), AddressAndIdentification.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                file_back = CompressFile.getCompressedImageFile(file_back, this);
                Drawable backgroundImage = Drawable.createFromPath(profile_img);
                imageBack.setImageDrawable(backgroundImage);

            } else {
                imageBack.setImageDrawable(getResources().getDrawable(R.drawable.i));
                profile_img = FileUtils.getPathFromUri(this, fileUri);
                file_back = new File(FileUtils.getPathFromUri(this, fileUri));
                txtBack.setText(file_back.getName());
            }
        }
        if(requestCode == GALLERY_SOURCE) {
            if (image) {
                profile_img = FileUtils.getPathFromUri(this, fileUri);
                Log.d("real path uri", FileUtils.getPathFromUri(this, fileUri));
                //creating a file
                file_SourceFunds = new File(FileUtils.getPathFromUri(this, fileUri));
                try {
                    if (BitmapFactory.decodeFile(file_SourceFunds.getAbsolutePath(), new BitmapFactory.Options()).getWidth() > BitmapFactory.decodeFile(file_SourceFunds.getAbsolutePath(), new BitmapFactory.Options()).getHeight())
                        file_SourceFunds = SetOrientation.convertBitmapToFile(SetOrientation.rotateImage(BitmapFactory.decodeFile(file_SourceFunds.getPath()), 90), AddressAndIdentification.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                file_SourceFunds = CompressFile.getCompressedImageFile(file_SourceFunds, this);
                Drawable backgroundImage = Drawable.createFromPath(profile_img);
                imageSourceFunds.setImageDrawable(backgroundImage);
//                file_SourceFunds = CompressFile.getCompressedImageFile(file_SourceFunds, this);
//                Drawable backgroundImage = Drawable.createFromPath(profile_img);
//                imageSourceFunds.setImageDrawable(backgroundImage);

            } else {
                imageSourceFunds.setImageDrawable(getResources().getDrawable(R.drawable.i));
                profile_img = FileUtils.getPathFromUri(this, fileUri);
                file_SourceFunds = new File(FileUtils.getPathFromUri(this, fileUri));
                txtSource.setText(file_SourceFunds.getName());
            }
        }
        if(requestCode == GALLERY_ADDRESS) {
            if (image) {
                profile_img = FileUtils.getPathFromUri(this, fileUri);
                Log.d("real path uri", FileUtils.getPathFromUri(this, fileUri));
                //creating a file
                file_AddressProof = new File(FileUtils.getPathFromUri(this, fileUri));
                try {
                    if (BitmapFactory.decodeFile(file_AddressProof.getAbsolutePath(), new BitmapFactory.Options()).getWidth() > BitmapFactory.decodeFile(file_AddressProof.getAbsolutePath(), new BitmapFactory.Options()).getHeight())
                        file_AddressProof = SetOrientation.convertBitmapToFile(SetOrientation.rotateImage(BitmapFactory.decodeFile(file_AddressProof.getPath()), 90), AddressAndIdentification.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                file_AddressProof = CompressFile.getCompressedImageFile(file_AddressProof, this);
                Drawable backgroundImage = Drawable.createFromPath(profile_img);
                imageAddressProof.setImageDrawable(backgroundImage);

            } else {
                imageAddressProof.setImageDrawable(getResources().getDrawable(R.drawable.i));
                profile_img = FileUtils.getPathFromUri(this, fileUri);
                file_AddressProof = new File(FileUtils.getPathFromUri(this, fileUri));
                txtAddress.setText(file_AddressProof.getName());
            }
        }
    }

    @Override
    public void setResponse(AddressIdentificationResponse Response) {
        if (Response != null && Response.getMessage() != null && Response.getMessage().getSuccessMessage() != null && Response.getMessage().getSuccessMessage().length() != 0) {
           ExtraUtils.Address=Response;
            Toast.makeText(this, Response.getMessage().getSuccessMessage(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddressAndIdentification.this, SecurityDetails.class);
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
        Toast.makeText(this, "abcdef", Toast.LENGTH_SHORT).show();
//        return ExtraUtils.DIGEST;
        return ExtraUtils.DIGEST;
    }

    @Override
    public void setDigest(String digest) {

        ExtraUtils.DIGEST=digest;
    }
}