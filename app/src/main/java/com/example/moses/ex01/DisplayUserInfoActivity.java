package com.example.moses.ex01;

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
        avatar = findViewById(R.id.avatarImageView);
        info = findViewById(R.id.infoTextView);
        dial = findViewById(R.id.dialButton);
        sendMail = findViewById(R.id.sendMailButton);

        info.setText(getInfo());

        dial.setOnClickListener(this);
        sendMail.setOnClickListener(this);
    }

    private String getInfo(){
        String infoStr = String.format(
                getString(R.string.full_name)+": "+ getIntent().getStringExtra("NAME")+ " \n"+
                getString(R.string.email_address)+": "+ getIntent().getStringExtra("EMAIL")+ " \n"+
                getString(R.string.phone_number)+": "+ getIntent().getStringExtra("PHONE")+ " \n"+
                getString(R.string.password)+": "+ getIntent().getStringExtra("PASSWORD")+ " \n"+
                getString(R.string.gander)+": "+ getIntent().getStringExtra("GANDER")+ " \n"+
                getString(R.string.birth_date)+": "+ getIntent().getStringExtra("BIRTHDAY")+ " \n"
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
