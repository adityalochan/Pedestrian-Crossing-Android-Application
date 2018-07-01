package com.example.android.pedestriancrossing;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    //   TextView tvfinal1;
    // this is the full name i will have to retrieve from sqllite after user enters the credentials
    String fullName = "Aditya Sharma";
    LoginRegistrationDBHelper dbHelper1;
    private EditText userName, password;
    private String verifyID, verifyPassword, firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper1 = new LoginRegistrationDBHelper(Login.this);
        initialization();
    }

    private void initialization() {

        // creating reference variable that points tot edittext "enterusername" and "enterpassword"
        // initialization is done in onCreate method instead of enterusername() and enterpassword() method
        // for better performance and code optimization
        userName = (EditText) findViewById(R.id.enterusername);
        password = (EditText) findViewById(R.id.enterpassword);
        //   tvfinal1 = (TextView) findViewById(R.id.finaldata1);

    }

//    // event when user clicks on edittext "enter your username"
//    public void enterusername(View view){
//        // clear the text value when user click on edittext
//            userName.setText("");
//        }
//
//    // event when user clicks on edittext "enter your password
//    public void enterpassword(View view){
//        // clear the text value when user click on edittext
//            password.setText("");
//    }

    //functionality when user clicks on Login button
    public void login(View view) {
        Cursor cursor = dbHelper1.getAllRecords();

        StringBuffer finalData1 = new StringBuffer();

        if (getValue(userName).isEmpty() || !(getValue(userName).length() == 8)) {
            Toast.makeText(Login.this, "Login Failed : UID should be 8 digits long", Toast.LENGTH_LONG).show();
        } else {
            if (getValue(password).isEmpty() || getValue(password).length() < 3 || getValue(password).length() > 10) {
                Toast.makeText(Login.this, "Login Failed : Password should be between 3 and 10 alphanumeric characters", Toast.LENGTH_LONG).show();
            } else {

                if (!getValue(userName).isEmpty() && !getValue(password).isEmpty() && (getValue(userName).length() == 8) && getValue(password).length() >= 3 && getValue(password).length() < 10) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

                        // test
                        finalData1.append(cursor.getInt(cursor.getColumnIndex(LoginRegistrationDBHelper.ID)));
                        finalData1.append(" - ");
                        finalData1.append(cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.UNIVERSITY_ID)));
                        finalData1.append(" - ");
                        finalData1.append(cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.PASSWORD)));
                        finalData1.append(" - ");
                        finalData1.append("\n");

                        //  tvfinal1.setText(finalData1);
                        // test end

                        verifyID = cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.UNIVERSITY_ID));
                        verifyPassword = cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.PASSWORD));


                        if (verifyID.equals(getValue(userName)) && verifyPassword.equals(getValue(password))) {

                            Toast.makeText(Login.this, "Successful Login", Toast.LENGTH_SHORT).show();
                            firstName = cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.FIRST_NAME));
                            lastName = cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.LAST_NAME));
                            fullName = firstName + " " + lastName;

                            userName.setText("");
                            password.setText("");

                            // redirect to location page
                            Intent startLocationActivity = new Intent(Login.this, location.class);
                            startLocationActivity.putExtra(Intent.EXTRA_TEXT, fullName);
                            startActivity(startLocationActivity);
                        }
                    }
                } else if (!verifyID.equals(getValue(userName)) && !verifyPassword.equals(getValue(password))) {
                    Toast.makeText(Login.this, "Login Failed: Credentials don't match", Toast.LENGTH_LONG).show();
                } else {
                    return;
                }
            }
        }
    }

    public void registration(View view) {
        // redirect to registration page
        Intent startRegistrationActivity = new Intent(Login.this, registration.class);
        startActivity(startRegistrationActivity);
    }

    public void openwebpage(View view) {
        // mention the urk of the web application (web page application)
        String webAddress = "http://www.google.com";
        onClickOpenAddress(webAddress);
    }

    //this method is called in openwebpage method to on webpage using url
    private void onClickOpenAddress(String url) {
        // uri.parse to parse the string into a Uri
        Uri webpage = Uri.parse(url);
        // creating an intent with Intent.ACTION_VIEW with webpage uri as parameter
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        // verifying that intent can be launched
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // get values stored in edittext
    private String getValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper1.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper1.closeDB();
    }

}
