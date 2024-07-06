package com.example.fasa7ni;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.window.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        new Handler().postDelayed(() ->{
            Intent intent = new Intent (Splash.this,Login.class);
            startActivity(intent);
            finish();
        },2000);
    }

}
