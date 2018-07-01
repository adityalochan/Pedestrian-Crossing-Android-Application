package com.example.android.pedestriancrossing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class location extends AppCompatActivity {
    // variable that will store the fullname of user received from "External Intent" from class "Login"
    String fullName;
    // variable that will store message to be shown in textView "displayname"
    TextView showMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        showMessage = (TextView) findViewById(R.id.displayname);
        // using the getIntent method to store the Intent that started this Activity in a variable
        Intent intentThatStartedThisActivity = getIntent();
        // check if intent has EXTRA that we passed from "Login activity"
        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            //retrieving the intent text and storing in a string variable "fullName"
            fullName = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            //displaying message in textview
            showMessage.setText("Welcome "+fullName);
        }
    }

    public void crossstreet(View view){

        // redirecting to crossStreet activity page
        Intent toCrossStreet = new Intent(location.this, crossStreet.class);
        startActivity(toCrossStreet);
    }
}
