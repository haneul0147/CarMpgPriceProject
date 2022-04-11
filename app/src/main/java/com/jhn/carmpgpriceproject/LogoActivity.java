package com.jhn.carmpgpriceproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LogoActivity extends AppCompatActivity {

    Thread thread;
    boolean interrupted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);


        final Handler handler = new Handler();
        final Runnable doNextActivity = new Runnable() {
            @Override
            public void run() {

                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(LogoActivity.this, com.jhn.carmpgpriceproject.MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {

                }
            }
        };

        thread = new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);
                if(!interrupted){
                    handler.post(doNextActivity);
                }
            }
        };
        thread.start();
    }

    @Override
    public void onBackPressed() {
        interrupted = true;
        super.onBackPressed();
    }
}