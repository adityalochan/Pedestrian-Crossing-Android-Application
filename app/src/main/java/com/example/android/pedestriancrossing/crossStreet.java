package com.example.android.pedestriancrossing;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class crossStreet extends AppCompatActivity {

    CrossStreetDBHelper dbHelper2;
    TextView crossingnameValue, tvData;
    Button crossing1Value, crossing2Value, crossing3Value, crossing4Value;

    //default coordinates are for tampa
    private Float latitude = 27.9506f;
    private Float longitude = (-(82.4572f));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_street);
        dbHelper2 = new CrossStreetDBHelper(crossStreet.this);

        // entering value to make them available in apk file
        // TEST START

        dbHelper2.insert("Laurel Holly Crossing", 2, "Laurel", "Holly", "No Crossing",
                "No Crossing", 28.065887f, -82.418915f);

        dbHelper2.insert("Holly Magnolia Crossing", 2, "Holly", "Magnolia", "No Crossing"
        , "No Crossing", 28.0661407f, -82.4202218f);

        dbHelper2.insert("Magnolia Fletcher Crossing", 2, "Magnolia", "Fletcher", "No Crossing",
                 "No Crossing", 28.0661407f, -82.4203318f);

        dbHelper2.insert("East Fletcher USF Palm Crossing", 2, "USF Palm Drive", "East Fletcher Avenue",
                "No Crossing", "No Crossing", 28.068993f, -82.413157f);

        dbHelper2.insert("East Fowler Leroy Collins Crossing", 2,  "East Fowler Avenue", "Leroy Collins Blvd",
                "No Crossing", "No Crossing",28.054632f, -82.413042f);

        dbHelper2.insert("Laurel Holly Crossing", 2, "Laurel", "Holly", "No Crossing", "No Crossing"
        , 28.065887f, -82.418915f);

        // TEST END


        initialization();
        //execute(R.i);
        enter();
    }

    public void initialization() {

        crossingnameValue = (TextView) findViewById(R.id.crossingname);
        crossing1Value = findViewById(R.id.street1);
        crossing2Value = findViewById(R.id.street2);
        crossing3Value = findViewById(R.id.street3);
        crossing4Value = findViewById(R.id.street4);
//        tvData = (TextView) findViewById(R.id.tvdata);

    }

    public void street1(View view) {
        // redirecting to timer activity
        Intent toTimer = new Intent(crossStreet.this, timer.class);
        startActivity(toTimer);
    }

    public void street2(View view) {
        if (getValue(crossing2Value).equals("No Crossing") ) {
            Toast.makeText(crossStreet.this, "Invalid", Toast.LENGTH_SHORT).show();
        } else {
            // redirecting to timer activity
            Intent toTimer = new Intent(crossStreet.this, timer.class);
            startActivity(toTimer);
        }
    }

    public void street3(View view) {
        if (getValue(crossing3Value).equals("No Crossing")){
            Toast.makeText(crossStreet.this, "Invalid", Toast.LENGTH_SHORT).show();
        } else {
            // redirecting to timer activity
            Intent toTimer = new Intent(crossStreet.this, timer.class);
            startActivity(toTimer);
        }
    }

    public void street4(View view) {
        if (getValue(crossing4Value).equals("No Crossing")) {
            Toast.makeText(crossStreet.this, "Invalid", Toast.LENGTH_SHORT).show();
        } else {
            // redirecting to timer activity
            Intent toTimer = new Intent(crossStreet.this, timer.class);
            startActivity(toTimer);
        }
    }

    public void maplocation(View view) {

        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        showMap(uri);
    }

    private void showMap(String geoLocation) {
        // creating intent with an action
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoLocation));
        // verify the intent of its null or not before it can be launched
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void enterCrossStreetData(View view) {
        Intent toCreatorCrossStreetDB = new Intent(crossStreet.this, CreatorCrossStreetDB.class);
        startActivity(toCreatorCrossStreetDB);
    }

    public void enter() {

        // Cursor cursor = dbHelper2.getAllRecords();
        // randomly select individual rows from database
        Cursor cursor = dbHelper2.getDataBasedOnQuery("SELECT * FROM signal ORDER BY RANDOM() LIMIT 1;");
        StringBuffer finalData2 = new StringBuffer();
//        int idLocal;
        String crossingNameValueLocal, crossing1ValueLocal, crossing2ValueLocal, crossing3ValueLocal, crossing4ValueLocal;
        Float coordinateXLocal, coordinateYLocal;

//        String finalData_local;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            // creating StringBuffer to display complete fetched data to tvData TextView
            finalData2.append(cursor.getInt(cursor.getColumnIndex(CrossStreetDBHelper.ID)));
            finalData2.append(" - ");
            finalData2.append(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.SIGNAL_NAME)));
            finalData2.append(" - ");
//            finalData_local.append(cursor.getInt(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING_COUNT)));
//            finalData_local.append(" - ");
            finalData2.append(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING1)));
            finalData2.append(" - ");
            finalData2.append(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING2)));
            finalData2.append(" - ");
            finalData2.append(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING3)));
            finalData2.append(" - ");
            finalData2.append(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING4)));
            finalData2.append(" - ");
            finalData2.append(cursor.getFloat(cursor.getColumnIndex(CrossStreetDBHelper.COORDINATE_X)));
            finalData2.append(" - ");
            finalData2.append(cursor.getFloat(cursor.getColumnIndex(CrossStreetDBHelper.COORDINATE_Y)));
            finalData2.append("\n");

//            finalData_local = idLocal + "-" + crossing1ValueLocal+ "-" + crossing1ValueLocal + "-" + crossing2ValueLocal
//                    + "-" + crossing3ValueLocal + "-" + crossing4ValueLocal + "-" ;
            //            idLocal = cursor.getInt(cursor.getColumnIndex(CrossStreetDBHelper.ID));

//            tvData.setText(finalData2);

            //setting TextView and Button Text values
            crossingnameValue.setText("You are at " + cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.SIGNAL_NAME)));
            crossing1Value.setText(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING1)));
            crossing2Value.setText(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING2)));
            crossing3Value.setText(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING3)));
            crossing4Value.setText(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING4)));

            //setting the coordinate values
            latitude = cursor.getFloat(cursor.getColumnIndex(CrossStreetDBHelper.COORDINATE_X));
            longitude = cursor.getFloat(cursor.getColumnIndex(CrossStreetDBHelper.COORDINATE_Y));
        }
    }

    // get values stored in Button
    private String getValue(Button button) {
        return button.getText().toString().trim();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper2.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper2.closeDB();
    }
}
