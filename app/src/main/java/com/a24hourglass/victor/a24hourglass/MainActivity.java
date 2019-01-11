package com.a24hourglass.victor.a24hourglass;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread() {

            @Override
            public void run() {

                try {
                    while(!isInterrupted()) {
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                TextView greeting = findViewById(R.id.greetings);
                                TextView currentTime = findViewById(R.id.time);

                                SharedPreferences.Editor editor = mPreferences.edit();
                                String userName = mPreferences.getString(getString(R.string.username), "");

                                Date date = new Date();
                                String dtStart = "24:00:00";
                                SimpleDateFormat  sdf = new SimpleDateFormat("kk:mm:ss");
                                try {
                                    date = sdf.parse(dtStart);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                long convertedDate = date.getTime();
                                long userTime = System.currentTimeMillis();
                                long diff = convertedDate - userTime + 18000000;
                                int timeInHours = new Time(System.currentTimeMillis()).getHours();
                                String timeString = sdf.format(diff);
                                currentTime.setText(timeString);

                                if(timeInHours >= 0 && timeInHours < 6) {
                                    greeting.setText("Up so early, " + userName + ".");
                                } else if(timeInHours >= 6 && timeInHours < 12) {
                                    greeting.setText("Good morning, " + userName + ".");
                                }else if(timeInHours >= 12 && timeInHours < 17) {
                                    greeting.setText("Good afternoon, " + userName + ".");
                                } else if(timeInHours >= 17) {
                                    greeting.setText("Good evening, " + userName + ".");
                                } else {
                                    greeting.setText(" ¯\\_(ツ)_/¯");
                                }
                            }
                        });
                    }
                } catch(InterruptedException e) { }
            }
        };

        thread.start();
    }


}