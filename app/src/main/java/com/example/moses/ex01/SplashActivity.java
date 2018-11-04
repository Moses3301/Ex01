package com.example.moses.ex01;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        waitAndShowNext();
    }

    private void waitAndShowNext(){
        long millisToWait = 3000;
        long millisToTick = 1000;
        new CountDownTimer(millisToWait, millisToTick) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                Intent intent = new Intent(com.example.moses.ex01.SplashActivity.this,EnterUserInfo.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    public void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
