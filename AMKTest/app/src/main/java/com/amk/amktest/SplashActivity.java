package com.amk.amktest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.amk.amktest.login.LoginActivity;
import com.amk.amktest.utils.SharedPreferencesManager;

public class SplashActivity extends AppCompatActivity {

    SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferencesManager = ((AMKTest) getApplication()).getSharedPreferencesManager();

        new Handler().postDelayed(
                () -> {
                    if (sharedPreferencesManager.isSessionLogged()) {
                        Intent animationActivity = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(animationActivity);
                    } else {
                        Intent animationActivity = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(animationActivity);
                    }
                    finish();
                }, 2000);
    }
}
