package com.example.moses.ex01;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {
    private final int SCREEN_TIME = 3000;
    public static final String TAG = "EnterUserInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate() >>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        waitAndShowNext();
        Log.e(TAG,"onCreate() <<");
    }

    private void waitAndShowNext(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, EnterUserInfo.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SCREEN_TIME); }
}
