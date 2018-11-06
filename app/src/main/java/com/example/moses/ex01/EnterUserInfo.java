package com.example.moses.ex01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnterUserInfo extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "EnterUserInfoActivity";
    static final int REQUEST_IMAGE_GET = 1;

    public static final String AVARAR_EXTRA_MESSAGE = "com.example.moses.ex01.EnterUserInfo.AVATAR";
    public static final String NAME_EXTRA_MESSAGE = "com.example.moses.ex01.EnterUserInfo.NAME";
    public static final String EMAIL_EXTRA_MESSAGE = "com.example.moses.ex01.EnterUserInfo.EMAIL";
    public static final String PHONE_EXTRA_MESSAGE = "com.example.moses.ex01.EnterUserInfo.PHONE";
    public static final String PASS_EXTRA_MESSAGE = "com.example.moses.ex01.EnterUserInfo.PASS";
    public static final String GENDER_EXTRA_MESSAGE = "com.example.moses.ex01.EnterUserInfo.GENDER";
    public static final String BIRTHDAY_EXTRA_MESSAGE = "com.example.moses.ex01.EnterUserInfo.BIRTHDAY";

    ImageView avatarImage;
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
        initViews();
        Log.e(TAG,"onCreate() <<");
    }

    private void initViews(){
        Log.e(TAG,"connectComp() >>");
        avatarImage = findViewById(R.id.avatarImageView);
        selectAvatarImage = findViewById(R.id.selectAvatarImageButton);
        selectAvatarImage.setOnClickListener(this);
        fullName = findViewById(R.id.fullNameEditText);
        email = findViewById(R.id.emailEditText);
        phone = findViewById(R.id.phoneEditText);
        ganders = findViewById(R.id.gendersRadioGroup);
        birthday = findViewById(R.id.dateOfBirthEditText);
        password = findViewById(R.id.passwordEditText);
        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(this);
        Log.e(TAG,"connectComp() <<");
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG,"onClick() >>");
        switch(v.getId()){
            case R.id.submitButton:
                Log.e(TAG,"submitButton was clicked");
                if (verifyForm())
                {
                    nextActivity();
                }
            break;
            case R.id.selectAvatarImageButton:
                Log.e(TAG,"selectAvatarImageButton was clicked");
                selectImage();
                break;
        }
        Log.e(TAG,"onClick() <<");
    }

    private boolean verifyForm(){
        boolean isVerify = false;

        if(!verifyString("[a-zA-z]{2,10}+\\s+[a-zA-z]{2,10}",fullName.getText().toString())){
            displayMessage(getString(R.string.full_name_validation_message));
        }
        else if (!verifyString("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",email.getText().toString())){
            displayMessage(getString(R.string.email_address_message));
        }
        else if (!verifyString("[0-9]{10}$",phone.getText().toString())){
            displayMessage(getString(R.string.phone_number_validation_message));
        }

        else if (!verifyPassword(password.getText().toString())){
            displayMessage(getString(R.string.password_validation_message));
        }

        else  if(ganders.getCheckedRadioButtonId() == -1){
            displayMessage(getString(R.string.gander_validation_message));
        }

        else if (!verifyBirthday(birthday.getText().toString())){
            displayMessage(getString(R.string.birth_date_validation_message));
        }

        else {
            isVerify = true;
        }
        return isVerify;
    }

    private  boolean verifyPassword(String str){
        Log.e(TAG,"verifyPassword() >>");
        boolean isVerify = false;
        if (!str.isEmpty()){
            isVerify = true;
        }
        Log.e(TAG, "verifyPassword(string*) return: " + isVerify);
        return isVerify;
    }

    private boolean verifyBirthday(String str){
        Log.e(TAG,"verifyBirthday(string*) >>");
        boolean isVerify = false;
        int minAge = 6;
        int maxAge = 120;
        //check if the month day are valid and the year in the format of 4 digit
        if (verifyString("(0?[1-9]|[12][0-9]|3[01])[\\-./](0?[1-9]|1[012])[\\-./]\\d{4}$", str)){
            //make sure that the user is in the age range
            String yearStr = str.split("[\\-./]")[2];
            int thisYear = Calendar.getInstance().get(Calendar.YEAR);
            int age = thisYear - Integer.parseInt(yearStr);
            isVerify = ( age>=minAge && age<=maxAge );
        }
        Log.e(TAG, "verifyBirthday(string*) return: " + isVerify);
        return isVerify;
    }

    private boolean verifyString(String regExpn, String str){
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

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            avatarImage.setImageURI(fullPhotoUri);
        }
    }

    private void nextActivity(){
        Intent intent = new Intent(this,DisplayUserInfoActivity.class);
        //send the avatar
        Drawable drawable = avatarImage.getDrawable();
        Bitmap bitmap= ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        intent.putExtra(AVARAR_EXTRA_MESSAGE, b);

        intent.putExtra(NAME_EXTRA_MESSAGE,fullName.getText().toString());
        intent.putExtra(EMAIL_EXTRA_MESSAGE,email.getText().toString());
        intent.putExtra(PHONE_EXTRA_MESSAGE,phone.getText().toString());
        intent.putExtra(PASS_EXTRA_MESSAGE,password.getText().toString());
        RadioButton selectedGander = findViewById(ganders.getCheckedRadioButtonId());
        intent.putExtra(GENDER_EXTRA_MESSAGE,selectedGander.getText().toString());
        intent.putExtra(BIRTHDAY_EXTRA_MESSAGE,birthday.getText().toString());
        startActivity(intent);
    }
}
