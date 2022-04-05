package com.example.registration.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.registration.Interface.Validators;
import com.example.registration.R;

public class PersonalDetails extends AppCompatActivity implements Validators{

    EditText edtFirstName,edtLastName,edtDOB,edtGender;



    public void Show(View view) {
       Validatee();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        edtFirstName=findViewById(R.id.edtFirstName);
        edtLastName=findViewById(R.id.edtLastName);
        edtDOB=findViewById(R.id.edtDOB);
        edtGender=findViewById(R.id.edtGender);
    }
    @Override
    public boolean Validatee() {
        if (edtFirstName.getText().toString().length() == 0 ) {
            edtFirstName.setError("This must be filled");
            return false;
        }
        else if (edtLastName.getText().toString().length() == 0 ) {
            edtLastName.setError("This must be filled");
            return false;
        }
        else if (edtDOB.getText().length() == 0 ) {
            edtDOB.setError("This must be filled");
            return false;
        }
        else if (edtGender.getText().length() == 0 ) {
            edtGender.setError("This must be filled");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void Message(){

    }
}