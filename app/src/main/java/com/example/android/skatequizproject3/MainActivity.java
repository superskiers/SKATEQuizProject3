package com.example.android.skatequizproject3;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    //Declare Variables
    private VideoView vv;
    private RadioButton rbKickFLIP;
    private RadioButton q3RB2;
    private RadioButton frontFootPush;
    private RadioButton vrai;
    private CheckBox q2c1;
    private CheckBox q2c2;
    private CheckBox q2c3;
    private CheckBox q2c4;
    private CheckBox q2c5;
    private CheckBox q2c6;
    private EditText playersName;
    private String c1 = "California";
    private EditText getAnswer6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv = (VideoView)findViewById(R.id.kickflip);

        //Set Video Loop
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                vv.start();
            }
        });
        Uri uri = Uri.parse("android.resource://com.example.android.skatescrollview/"+ R.raw.kickflip);

        vv.setVideoURI(uri);

        vv.requestFocus();

        vv.start();

        //Initialize RadioButtons for question 1
        rbKickFLIP = findViewById(R.id.rbKickFlip);
        //Initialize Checkboxes for question 2
        q2c1 = findViewById(R.id.checkSwitch);
        q2c2 = findViewById(R.id.checkRegular);
        q2c3 = findViewById(R.id.checkGoofy);
        q2c4 = findViewById(R.id.checkDaffy);
        q2c5 = findViewById(R.id.checkFakie);
        q2c6 = findViewById(R.id.checkDisney);
        //Initialize RadioButtons for question 3
        q3RB2 = findViewById(R.id.melongrab);
        //Initialize RadioButton for question 4
        frontFootPush = findViewById(R.id.q4rB4);
        //Initialize RadioButton for question 5
        vrai = findViewById(R.id.vrai);
        //Initialize EditTexts
        playersName = findViewById(R.id.enterName);
        getAnswer6 = findViewById(R.id.answer6);
    }
    // This method is called when the Submit Button is clicked.
    public void sendMessage(View view) {
        String playerName = playersName.getText().toString();

        boolean rbKickFlip = rbKickFLIP.isChecked();

        boolean rbMelonGrab = q3RB2.isChecked();

        boolean checkedSwitch = q2c1.isChecked();

        boolean checkedRegular = q2c2.isChecked();

        boolean checkedGoofy = q2c3.isChecked();

        boolean checkedDaffy = q2c4.isChecked();

        boolean checkedFakie = q2c5.isChecked();

        boolean checkedDisney = q2c6.isChecked();

        boolean frontFootPushing = frontFootPush.isChecked();

        boolean cestvrai = vrai.isChecked();

        boolean getAns6 = getAnswer6.getText().toString().equalsIgnoreCase(c1);

        int score = calculateScore(rbKickFlip, rbMelonGrab, checkedGoofy, checkedRegular, checkedSwitch, checkedDaffy, checkedFakie, checkedDisney, frontFootPushing, cestvrai, getAns6);
        String playerMessage = gameSummary(score, playerName);
    }
    //This method calculates the score
    private int calculateScore(boolean rbKickFlip, boolean rbMelonGrab, boolean checkedGoofy, boolean checkedRegular, boolean checkedSwitch, boolean checkedDaffy, boolean checkedFakie, boolean checkedDisney, boolean frontFootPushing, boolean cestvrai, boolean getAns6) {
        int score = 0;
        if (rbKickFlip) {
            score ++;
        }
        if (rbMelonGrab) {
            score ++;
        }
        if(checkedGoofy && checkedRegular && !checkedDaffy && !checkedDisney && !checkedFakie && checkedSwitch) {
            score ++;
        }
        if (frontFootPushing) {
            score ++;
        }
        if (cestvrai) {
            score ++;
        }
        if (getAns6); {
            score += 10;
        }
        return score;
    }
    //Game Summary called when player clicks submit
    private String gameSummary(int score, String playerName) {
        String endOFgame = getString(R.string.wellDone) + playerName + getString(R.string.exclamationMark) + getString(R.string.finalScore) + score + getString(R.string.out7);
        if (score < 1) {
            Toast.makeText(this, getString(R.string.ansSomething) + " " + playerName + getString(R.string.period), Toast.LENGTH_LONG).show();
            return getString(R.string.nadaComment);
        } else if (score >= 1 && score <= 3) {
            Toast.makeText(this, getString(R.string.almostLanded) + score + getString(R.string.resetTRY), Toast.LENGTH_LONG).show();
            return endOFgame;
        } else {
            Toast.makeText(this, getString(R.string.wellDone) + " " + playerName + getString(R.string.merci) + score + " " + getString(R.string.out7) + getString(R.string.exclamationMark), Toast.LENGTH_LONG).show();
            return endOFgame;
        }
    }
    //Click Reset to reset quiz
    public void resetQuiz(View view) {
        finish();
        startActivity(getIntent());
    }
}