package com.example.registration.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registration.BuildConfig;
import com.example.registration.Interface.BaseActivityInterface;
import com.example.registration.R;
import com.example.registration.RetrofitAPI.models.response.SignUpResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.HttpException;

public abstract class BaseActivity extends AppCompatActivity implements BaseActivityInterface {
    private ProgressDialog progressDialog;
    public String getPlatformType(){
        return "JM";
    }
    public String getClientType(){
        return "Android"+" "+Build.VERSION.SDK_INT;
    }



}

