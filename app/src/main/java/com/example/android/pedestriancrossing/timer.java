package com.example.android.pedestriancrossing;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class timer extends AppCompatActivity {

    TextView timerClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerClock = findViewById(R.id.timerfield);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerClock.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerClock.setText("done!");
                Intent startRegistrationActivity = new Intent(timer.this, location.class);
                startActivity(startRegistrationActivity);
            }
        }.start();


    }
}
