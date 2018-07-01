package com.example.android.pedestriancrossing;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreatorCrossStreetDB extends AppCompatActivity {

    EditText signalName, crossingCount, crossing1, crossing2, crossing3, crossing4, coordinateX, coordinateY;
    TextView crossingName, tvFinalData2;
    CrossStreetDBHelper dbHelper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_cross_street_db);
        dbHelper2 = new CrossStreetDBHelper(CreatorCrossStreetDB.this);
        initialization();
    }

    public void initialization(){

        signalName = findViewById(R.id.enterSignalName);
        crossingCount = findViewById(R.id.enterCrossingCount);
        crossing1 = findViewById(R.id.enterCrossing1);
        crossing2 = findViewById(R.id.enterCrossing2);
        crossing3 = findViewById(R.id.enterCrossing3);
        crossing4 = findViewById(R.id.enterCrossing4);
        coordinateX = findViewById(R.id.enterCoordinateX);
        coordinateY = findViewById(R.id.enterCoordinateY);

        crossingName = (TextView) findViewById(R.id.crossingname);
        tvFinalData2 = (TextView) findViewById(R.id.finaldata2);
    }

    public void signalDataSubmit(View view){

        long resultInsert1 = dbHelper2.insert(getValue(signalName), Integer.parseInt(getValue(crossingCount)),
                            getValue(crossing1), getValue(crossing2), getValue(crossing3), getValue(crossing4),
                            Float.parseFloat(getValue(coordinateX)), Float.parseFloat(getValue(coordinateY)));

        if(resultInsert1 == -1){
            Toast.makeText(CreatorCrossStreetDB.this, "Some error occurred while inserting, please fill accuracy and complete details", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CreatorCrossStreetDB.this, "Your Account has been created successfully at ID: " + resultInsert1, Toast.LENGTH_SHORT).show();
            signalName.setText("");
            crossingCount.setText("");
            crossing1.setText("");
            crossing2.setText("");
            crossing3.setText("");
            crossing4.setText("");
            coordinateX.setText("");
            coordinateY.setText("");
            return;
        }
    }
    public void loadData(View view){

        Cursor cursor = dbHelper2.getAllRecords();
        StringBuffer finalData2 = new StringBuffer();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            finalData2.append(cursor.getInt(cursor.getColumnIndex(CrossStreetDBHelper.ID)));
            finalData2.append(" - ");
            finalData2.append(cursor.getString(cursor.getColumnIndex(CrossStreetDBHelper.SIGNAL_NAME)));
            finalData2.append(" - ");
            finalData2.append(cursor.getInt(cursor.getColumnIndex(CrossStreetDBHelper.CROSSING_COUNT)));
            finalData2.append(" - ");
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
        }

        tvFinalData2.setText(finalData2);
    }


    private String getValue(EditText editText){
        return editText.getText().toString().trim();
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
