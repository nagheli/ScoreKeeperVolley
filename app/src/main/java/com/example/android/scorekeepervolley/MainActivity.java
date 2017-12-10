package com.example.android.scorekeepervolley;

import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    int home_points = 0;
    int guest_points = 0;
    int setHome = 0;
    int setGuest = 0;

    long time;


    TextView homeScore,guestScore, setPointsHome, setPointsGuest, endGame;
    Chronometer simpleChronometer;
    Button start, reset,plusOnePointHome,plusOnePointGuest;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate views
        plusOnePointHome = (Button) findViewById(R.id.plusOnePointHome);
        plusOnePointGuest = (Button) findViewById(R.id.plusOnePointGuest);
        simpleChronometer = (Chronometer) findViewById(R.id.clockTimer);
        homeScore = (TextView) findViewById(R.id.homeScore);
        guestScore = (TextView) findViewById(R.id.guestScore);
        setPointsHome = (TextView) findViewById(R.id.setPointsHome);
        setPointsGuest = (TextView) findViewById(R.id.setPointsGuest);
        endGame = (TextView) findViewById(R.id.endGame);
        start = (Button) findViewById(R.id.buttonStart);
        reset = (Button) findViewById(R.id.buttonReset);
        plusOnePointHome.setEnabled(false);
        plusOnePointGuest.setEnabled(false);


        ;

        // perform click  event on start button to start a chronometer

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                simpleChronometer.start();
                plusOnePointHome.setEnabled(true);
                plusOnePointGuest.setEnabled(true);
                start.setEnabled(false);

            }
        });

        /**
         *  Perform click  event on restart button to set the base time on chronometer
         */

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                time =simpleChronometer.getBase()-SystemClock.elapsedRealtime();
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                simpleChronometer.stop();
                displayHomePoints(0);
                displayGuestPoints(0);
                setHome = 0;
                setGuest = 0;
                displaySetGuest(0);
                displaySetHome(0);
                displayEndGameGuest(" ");
                displayEndGameHome(" ");
                plusOnePointHome.setEnabled(false);
                plusOnePointGuest.setEnabled(false);
                start.setEnabled(true);

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
        homeScore.setText(String.valueOf(home_points));
    }


    /**
     * Display guest points.
     */

    public void displayGuestPoints(int guest_points) {
        guestScore.setText(String.valueOf(guest_points));
    }


    /**
     * Display home sets.
     */

    public void displaySetHome(int setHome) {
        setPointsHome.setText(String.valueOf(setHome));
        if (setHome == 3){
            displayEndGameHome(getString(R.string.homeWin));
            plusOnePointHome.setEnabled(false);
            plusOnePointGuest.setEnabled(false);
            simpleChronometer.stop();


        }
    }



    /**
     * Display guest sets.
     */

    public void displaySetGuest(int setGuest) {
        setPointsGuest.setText(String.valueOf(setGuest));
        if (setGuest == 3){
            displayEndGameGuest(getString(R.string.guestWin));
            plusOnePointHome.setEnabled(false);
            plusOnePointGuest.setEnabled(false);
            simpleChronometer.stop();

        }

    }

    /**
     * Display home endGame.
     */

    public void displayEndGameHome(String a){
        endGame.setText(a);
    }


    /**
     * Display guest endGame.
     */
    public void displayEndGameGuest(String b){
        endGame.setText(b);
    }





}
