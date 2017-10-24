package com.walker.timedialogapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends Activity {

    private TextView mTimeDisplay;
    private Button mPickTime;

    private int mHour;
    private int mMinutes;

    static final int TIME_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Capture our view elements
        mTimeDisplay = (TextView) findViewById(R.id.timeDisplay);
         mPickTime = (Button) findViewById(R.id.pickTime);

        //Add a click listener to the button
        mPickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                showDialog(TIME_DIALOG_ID);
            }
        });

        // Get the current time
        final java.util.Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinutes = c.get(Calendar.MINUTE);

        // Display the current date
        updateDisplay();
    }

    // The overridden method shown below gets invoked when
    // showDialog() is called inside the onClick() method defined
    // for handling the click event of the "Change the time" button

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, mHour, mMinutes, false);
        }
        return null;
    }

    // Update the time we display in the TextView
    private void updateDisplay() {
        mTimeDisplay.setText(new StringBuilder()
                                .append(pad(mHour))
                                .append(":")
                                .append(pad(mMinutes)));
    }

    // The callback recieved when the user "sets" the time in the dialog
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener(){
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinutes = minute;
                    updateDisplay();
                }
    };

    private static String pad(int c){
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

}

























