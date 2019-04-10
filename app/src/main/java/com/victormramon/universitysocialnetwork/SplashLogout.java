package com.victormramon.universitysocialnetwork;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashLogout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_logout);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashLogout.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        }, 3000);

    }
}
