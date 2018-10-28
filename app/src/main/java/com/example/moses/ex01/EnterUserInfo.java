package com.example.moses.ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnterUserInfo extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "EnterUserInfoActivity";

    Button selectAvatarImage;
    TextView fullName;
    TextView email;
    TextView phone;
    TextView password;
    RadioGroup ganders;
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
        ganders = findViewById(R.id.gendersRadioGroup);
        birthday = findViewById(R.id.dateOfBirthEditText);
        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(this);
        Log.e(TAG,"connectComp() <<");
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG,"onClick() >>");
        //TODO: spread to cases
        //submit logic
        if (verifyForm())
        {

        }
        Log.e(TAG,"onClick() <<");
    }

    private boolean verifyForm(){
        boolean isVerify = false;

        if(!verifyString("[a-zA-z]{2,10}",fullName.getText().toString())){
            displayMessage(getString(R.string.full_name_validation_message));
        }
        else if (!verifyString("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",email.getText().toString())){
            displayMessage(getString(R.string.email_address_message));
        }
        else if (!verifyString("[0-9]{10}$",phone.getText().toString())){
            displayMessage(getString(R.string.phone_number_validation_message));
        }
        /*
        else if (!verifyPassword()){
            displayMessage(getString(R.string.password_validation_message))
        }
        */
        else  if(ganders.getCheckedRadioButtonId() == -1){
            displayMessage(getString(R.string.gander_validation_message))
        }
        /*
        else if (!verifyString("[]",birthday.getText().toString())){
            displayMessage(getString(R.string.birth_date_validation_message));
        }
        else{
            isVerify = true;
        }
        */
        return isVerify;
    }

    public boolean verifyBirthday(String str){
        ,
    }

    public boolean verifyString(String regExpn, String str){
        Log.e(TAG,"verifyString(string*,string*) >>");
        boolean isVerify = false;

        if (!str.isEmpty()) {
            Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(str);
            isVerify = matcher.matches();
        }
        Log.e(TAG, "verifyString(string*,string*) return: " + isVerify);
        Log.e(TAG, "verifyString(string*,string*) <<");
        return (isVerify);
    }

    public void displayMessage(String message) {
        Log.e(TAG,"Toast Message: "+message);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
