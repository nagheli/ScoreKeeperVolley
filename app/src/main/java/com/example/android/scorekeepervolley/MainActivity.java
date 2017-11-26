package com.example.android.scorekeepervolley;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int home_points = 0;
    int guest_points = 0;
    int setHome = 0;
    int setGuest = 0;

    Chronometer simpleChronometer;
    Button start, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initiate views
        simpleChronometer = (Chronometer) findViewById(R.id.clockTimer);
        start = (Button) findViewById(R.id.buttonStart);
        reset = (Button) findViewById(R.id.buttonReset);
        // perform click  event on start button to start a chronometer
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub


                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                simpleChronometer.start();

            }
        });

        // perform click  event on restart button to set the base time on chronometer
        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                simpleChronometer.stop();
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                displayHomePoints(0);
                displayGuestPoints(0);
                setHome = 0;
                setGuest = 0;
                displaySetGuest(0);
                displaySetHome(0);
                displayEndGameGuest(" ");
                displayEndGameHome(" ");
            }
        });
    }

    /**
     * This method is called when the +1 Point Home button is clicked.
     */

    public void addHomePoints(View view) {
        home_points++;
        displayHomePoints(home_points);
        if (home_points >= 25 && home_points - guest_points > 1) {
            displayHomePoints(home_points);
            ++setHome;
            displaySetHome(setHome);
            home_points = 0;
            guest_points = 0;
            displayHomePoints(home_points);
            displayGuestPoints(guest_points);
        }
    }

    /**
     * This method is called when the +1 Point Guest button is clicked.
     */

    public void addGuestPoints(View view) {
        guest_points++;
        displayGuestPoints(guest_points);

        if (guest_points >= 25 && guest_points - home_points > 1) {
            displayGuestPoints(guest_points);
            ++setGuest;
            displaySetGuest(setGuest);
            guest_points = 0;
            home_points = 0;
            displayGuestPoints(guest_points);
            displayHomePoints(home_points);
        }
    }

    /**
     * Display home points.
     */

    public void displayHomePoints(int home_points) {
        TextView scoreView = (TextView) findViewById(R.id.homeScore);
        scoreView.setText(String.valueOf(home_points));
    }

    /**
     * Display guest points.
     */

    public void displayGuestPoints(int guest_points) {
        TextView scoreView = (TextView) findViewById(R.id.guestScore);
        scoreView.setText(String.valueOf(guest_points));
    }


    /**
     * Display home sets.
     */

    public void displaySetHome(int setHome) {
        TextView setView = (TextView) findViewById(R.id.setPointsHome);
        setView.setText(String.valueOf(setHome));
        if (setHome == 3){
            displayEndGameHome("HOME WINS!");
            displaySetHome(0);
        }
            }


    /**
     * Display guest sets.
     */

    public void displaySetGuest(int setGuest) {
        TextView setView = (TextView) findViewById(R.id.setPointsGuest);
        setView.setText(String.valueOf(setGuest));
        if (setGuest == 3){
            displayEndGameGuest("GUEST WINS!");
            displaySetGuest(0);

        }

    }

    public void displayEndGameHome(String a){
        TextView endView = (TextView) findViewById(R.id.endGame);
        endView.setText(a);
    }


    public void displayEndGameGuest(String b){
        TextView endView = (TextView) findViewById(R.id.endGame);
        endView.setText(b);
    }
}
