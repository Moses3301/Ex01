package com.example.moses.ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EnterUserInfo extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "EnterUserInfoActivity";

    Button selectAvatarImage;
    TextView fullName;
    TextView email;
    TextView phone;
    TextView password;
    String gander;
    TextView birthday;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate() >>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_user_info);
        connectComp();
        Log.e(TAG,"onCreate() <<");
    }

    private void connectComp(){
        Log.e(TAG,"connectComp() >>");
        selectAvatarImage = findViewById(R.id.selectAvatarImgeButton);
        selectAvatarImage.setOnClickListener(this);
        fullName = findViewById(R.id.fullNameEditText);
        email = findViewById(R.id.emailEditText);
        phone = findViewById(R.id.phoneEditText);
        birthday = findViewById(R.id.dateOfBirthEditText);
        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(this);
        Log.e(TAG,"connectComp() <<");
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG,"onClick() >>");
        Log.e(TAG,"onClick() <<");
    }
}
