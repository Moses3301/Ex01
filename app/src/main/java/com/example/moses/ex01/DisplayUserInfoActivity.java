package com.example.moses.ex01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_info);
        initViews();
    }

    private void initViews(){
        avatar = findViewById(R.id.udAvatarImage);
        info = findViewById(R.id.infoTextView);
        dial = findViewById(R.id.dialButton);
        sendMail = findViewById(R.id.sendMailButton);

        info.setText(getInfo());

        dial.setOnClickListener(this);
        sendMail.setOnClickListener(this);
    }

    private String getInfo(){
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
        return infoStr;
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG,"onClick() >>");
        switch(v.getId()){
            case R.id.dialButton:
                break;
            case R.id.sendMailButton:
                break;
        }
        Log.e(TAG,"onClick() <<");
    }
}
