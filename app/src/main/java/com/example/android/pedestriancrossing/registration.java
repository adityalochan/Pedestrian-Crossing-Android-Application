package com.example.android.pedestriancrossing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText uid;
    EditText password;
    EditText reenterPassword;
    EditText emailId;
    LoginRegistrationDBHelper dbHelper;
    //TextView tvFinalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbHelper = new LoginRegistrationDBHelper(registration.this);
        initialization();
    }

    private void initialization() {
        firstName = findViewById(R.id.enterFirstName);
        lastName = findViewById(R.id.enterLastName);
        uid = findViewById(R.id.enterUid);
        password = findViewById(R.id.enterPassword);
        reenterPassword = findViewById(R.id.reenterPassword);
        emailId = findViewById(R.id.enterEmailId);
//        tvFinalData = findViewById(R.id.finaldata);
        // id = findViewById(R.id.etID);
    }

    public void registrationSubmit(View view) {

        long resultInsert = dbHelper.insert(getValue(firstName),
                getValue(lastName), Integer.parseInt(getValue(uid)), getValue(password), getValue(emailId));

        if (resultInsert == -1) {
            Toast.makeText(registration.this, "Some error occurred while inserting, please fill accuracy and complete details", Toast.LENGTH_SHORT).show();
        } else {
            if (getValue(firstName).isEmpty() || getValue(lastName).isEmpty() || getValue(uid).isEmpty() ||
                    getValue(password).isEmpty() || getValue(reenterPassword).isEmpty() || getValue(emailId).isEmpty()) {
                Toast.makeText(registration.this, "Please do not leave any field blank", Toast.LENGTH_SHORT).show();
            } else {

                if (!(getValue(uid).length() == 8)) {
                    Toast.makeText(registration.this, "Your university ID should be 8 digits", Toast.LENGTH_SHORT).show();
                } else {
                    if ((getValue(password).length() < 3 || getValue(password).length() > 10)) {
                        Toast.makeText(registration.this, "Password should be between 3 and 10 alphanumeric characters", Toast.LENGTH_SHORT).show();
                    } else {


                        if (!getValue(password).equals(getValue(reenterPassword))) {

                            Toast.makeText(registration.this, " Your passwword do not match " + resultInsert, Toast.LENGTH_SHORT).show();

//                                 firstName.setText("");
//                                 lastName.setText("");
//                                 uid.setText("");
                            password.setText("");
                            reenterPassword.setText("");
//                                 emailId.setText("");
                        } else {
                            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(getValue(emailId)).matches()) {
                                Toast.makeText(registration.this, " Enter valid email address " + resultInsert, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(registration.this, "Successfully created your account" + resultInsert, Toast.LENGTH_SHORT).show();
                                Intent startLoginActivity = new Intent(registration.this, Login.class);
                                startActivity(startLoginActivity);
                            }

//
//             if ( (getValue(password).equals(getValue(reenterPassword))) == false){
//                 Toast.makeText(registration.this, "The password do not match " + getValue(password) + " " + getValue(reenterPassword), Toast.LENGTH_SHORT).show();
//                 password.setText("");
//                 reenterPassword.setText("");
//             }
//             if ( getValue(firstName).equals("") || getValue(lastName).equals("") || getValue(uid).equals(null) || getValue(password).equals("")
//                    || getValue(reenterPassword).equals("") || getValue(emailId).equals("")) {
//
//                 Toast.makeText(registration.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
//             }
//                             else {
//                                 firstName.setText("");
//                                 lastName.setText("");
//                                 uid.setText("");
//                                 password.setText("");
//                                 reenterPassword.setText("");
//                                 emailId.setText("");
//                                 return;
//                             }

//         redirecting back to Login page
//        Intent backToLogin = new Intent(registration.this, Login.class);
//        startActivity(backToLogin);
                        }
                    }
                }
            }
        }
    }

    private String getValue(EditText editText) {
        return editText.getText().toString().trim();
    }


//    public void loaddata(View view) {
//        StringBuffer finalData = new StringBuffer();
//        Cursor cursor = dbHelper.getAllRecords();
////                Cursor cursor = dbHelper1.getDataBasedOnQuery("SELECT * FROM " + MyDBHelper.TABLE_NAME
////                    + " WHERE " + MyDBHelper.ADDRESS + " = 'UK'");
//
//        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//            finalData.append(cursor.getInt(cursor.getColumnIndex(LoginRegistrationDBHelper.ID)));
//            finalData.append(" - ");
//            finalData.append(cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.FIRST_NAME)));
//            finalData.append(" - ");
//            finalData.append(cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.LAST_NAME)));
//            finalData.append(" - ");
//            finalData.append(cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.UNIVERSITY_ID)));
//            finalData.append(" - ");
//            finalData.append(cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.PASSWORD)));
//            finalData.append(" - ");
//            finalData.append(cursor.getString(cursor.getColumnIndex(LoginRegistrationDBHelper.EMAIL_ID)));
//            finalData.append("\n");
//        }
//
//        tvFinalData.setText(finalData);
//    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.closeDB();
    }
}
