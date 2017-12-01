package com.walker.alarmdemo;

/**
 * Created by Dave on 11/30/2017.
 */


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Dave on 11/22/2017.
 */


public class RingtonePlayingService extends Service {

    MediaPlayer mediaPlayer;

    public IBinder onBind(Intent intent) { return null; }

    public int onStartCommand(Intent intent, int flags, int startId) {

        String state = intent.getExtras().getString("extra");

        if(state.equals("start")) {

            Intent alarmIntent = new Intent(this, MainActivity.class);
            startActivity(alarmIntent);

            mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
            mediaPlayer.start();
        }

        Toast.makeText(this, "onStartCommand() called", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        mediaPlayer.stop();
        mediaPlayer.reset();

        Toast.makeText(this, "onDestroy() called", Toast.LENGTH_LONG).show();

    }
}
