package com.example.moses.ex01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayUserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "DisplayUserInfoActivity";

    ImageView avatar;
    TextView info;
    Button dial;
    Button sendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate() >>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_info);
        initViews();
        Log.e(TAG,"onCreate() <<");
    }

    private void initViews(){
        Log.e(TAG,"initViews() >>");
        avatar = findViewById(R.id.udAvatarImage);
        info = findViewById(R.id.infoTextView);
        dial = findViewById(R.id.dialButton);
        sendMail = findViewById(R.id.sendMailButton);

        info.setText(getInfo());

        dial.setOnClickListener(this);
        sendMail.setOnClickListener(this);
        Log.e(TAG,"initViews() <<");
    }

    private String getInfo(){
        Log.e(TAG,"getInfo() >>");
        Bundle bundle = getIntent().getExtras();
        //getting the avatar image
        byte[] b = bundle.getByteArray(EnterUserInfo.AVARAR_EXTRA_MESSAGE);
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        avatar.setImageBitmap(bmp);

        String infoStr = String.format(
                getString(R.string.full_name)+": "+ bundle.getString(EnterUserInfo.NAME_EXTRA_MESSAGE)+ " \n"+
                getString(R.string.email_address)+": "+ bundle.getString(EnterUserInfo.EMAIL_EXTRA_MESSAGE)+ " \n"+
                getString(R.string.phone_number)+": "+ bundle.getString(EnterUserInfo.PHONE_EXTRA_MESSAGE)+ " \n"+
                getString(R.string.password)+": "+ bundle.getString(EnterUserInfo.PASS_EXTRA_MESSAGE)+ " \n"+
                getString(R.string.gander)+": "+ bundle.getString(EnterUserInfo.GENDER_EXTRA_MESSAGE)+ " \n"+
                getString(R.string.birth_date)+": "+ bundle.getString(EnterUserInfo.BIRTHDAY_EXTRA_MESSAGE)+ " \n"
        );
        Log.e(TAG,"getInfo() <<");
        return infoStr;
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG,"onClick() >>");
        switch(v.getId()){
            case R.id.dialButton:
                dial();
                break;
            case R.id.sendMailButton:
                sendEmail();
                break;
        }
        Log.e(TAG,"onClick() <<");
    }

    private void dial(){
        Log.e(TAG,"dial() >>");
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + getIntent().getStringExtra(EnterUserInfo.PHONE_EXTRA_MESSAGE)));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        Log.e(TAG,"dial() <<");
    }

    private void sendEmail(){
        Log.e(TAG,"sendEmail() >>");
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, getIntent().getStringExtra(EnterUserInfo.EMAIL_EXTRA_MESSAGE));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        Log.e(TAG,"sendEmail() <<");
    }
}