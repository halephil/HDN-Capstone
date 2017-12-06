package phillips.com.medminder;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    TimePicker mTimePicker;
    String AlarmTime;

    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        AlarmTime = "";

        mTimePicker = (TimePicker) findViewById(R.id.timePicker);

        findViewById(R.id.buttonSetAlarm).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();

                if(Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            mTimePicker.getHour(),
                            mTimePicker.getMinute(),
                            0);
                }else{
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            mTimePicker.getCurrentHour(),
                            mTimePicker.getCurrentMinute(),
                            0);
                }

                setAlarm(calendar.getTimeInMillis());

                if(mTimePicker.getHour() <12){
                    AlarmTime = Integer.toString(mTimePicker.getHour()) + ":" + Integer.toString(mTimePicker.getMinute()) + ":AM";
                }
                else{
                    AlarmTime = Integer.toString(mTimePicker.getHour()) + ":" + Integer.toString(mTimePicker.getMinute()) + ":PM";
                }


            } // close onClick()
        }); // close setOnClickListener
    } // close onCreate()

    private void setAlarm(long timeInMillis){

        intent = new Intent(this, MyReceiver.class);
        intent.putExtra("extra", "start");

        pendingIntent = PendingIntent.getBroadcast(this, 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT);

       alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();

    }

    public void onStopClick(View view) {

        Intent stopIntent = new Intent(this, RingtonePlayingService.class);
        stopService(stopIntent);
    }

    @Override
    public void onBackPressed() {

        Intent closeActivity = new Intent(getBaseContext(),EditDrugInfoActivity.class);
        closeActivity.putExtra("AlertTime",  AlarmTime);
        setResult(RESULT_OK, closeActivity);
        finish();
    }
}