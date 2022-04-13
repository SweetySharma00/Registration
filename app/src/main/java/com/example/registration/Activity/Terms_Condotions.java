package com.example.registration.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.registration.R;

public class Terms_Condotions extends AppCompatActivity implements View.OnClickListener {
    Button btnAcceptTerms,btnDecline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_condotions);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        btnAcceptTerms=findViewById(R.id.btnAcceptTerms);
        btnAcceptTerms.setOnClickListener(this);
        btnDecline=findViewById(R.id.btnDecline);
        btnDecline.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAcceptTerms:
                Intent intent = new Intent(Terms_Condotions.this, PersonalDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.btnDecline:
                Intent intent1 = new Intent(Terms_Condotions.this, Signup.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
        }
    }
}