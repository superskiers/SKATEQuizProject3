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
    private RadioButton ques3Radio1;
    private RadioButton frontFootPush;
    private RadioButton vrai;
    private CheckBox q2c1, q2c2, q2c3, q2c4, q2c5, q2c6;
    private EditText playersName;

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
        Uri uri = Uri.parse("android.resource://com.example.android.skatequizproject3/"+ R.raw.kickflip);

        vv.setVideoURI(uri);

        vv.requestFocus();

        vv.start();


        //Initialize RadioButton for question 1
        rbKickFLIP = findViewById(R.id.rbKickFlip);
        //Initialize Checkboxes for question 2
        q2c1 = findViewById(R.id.checkSwitch);
        q2c2 = findViewById(R.id.checkRegular);
        q2c3 = findViewById(R.id.checkGoofy);
        q2c4 = findViewById(R.id.checkDaffy);
        q2c5 = findViewById(R.id.checkDisney);
        q2c6 = findViewById(R.id.checkFakie);
        //Initialize RadioButton for question 3
        ques3Radio1 = findViewById(R.id.melongrab);
        //Initialize RadioButton for question 4
        frontFootPush = findViewById(R.id.q4rB4);
        //Initialize RadioButton for question 5
        vrai = findViewById(R.id.vrai);
        //Initialize EditText
        playersName = findViewById(R.id.enterName);
    }
    // This method is called when the Submit Button is clicked.
    public void sendMessage(View view) {
        String playerName = playersName.getText().toString();

        boolean rbKickFlip = rbKickFLIP.isChecked();

        boolean rbMelonGrab = ques3Radio1.isChecked();

        boolean checkedSwitch = q2c1.isChecked();

        boolean checkedRegular = q2c2.isChecked();

        boolean checkedGoofy = q2c3.isChecked();

        boolean checkedDaffy = !q2c4.isChecked();

        boolean checkedDisney = !q2c5.isChecked();

        boolean checkedFakie = !q2c6.isChecked();

        boolean frontFootPushing = frontFootPush.isChecked();

        boolean cestvrai = vrai.isChecked();


        int score = calculateScore(rbKickFlip, rbMelonGrab, checkedFakie, checkedDisney, checkedDaffy, checkedGoofy, checkedRegular, checkedSwitch, frontFootPushing, cestvrai);
        String playerMessage = gameSummary(score, playerName);
    }
    //This method calculates the score
    private int calculateScore(boolean rbKickFlip, boolean checkedFakie, boolean checkedDisney, boolean checkedDaffy, boolean rbMelonGrab, boolean checkedGoofy, boolean checkedRegular, boolean checkedSwitch, boolean frontFootPushing, boolean cestvrai) {
        int score = 0;
        if (rbKickFlip) {
            score += 1;
        }
        if (rbMelonGrab) {
            score += 1;
        }
        if (checkedRegular && checkedGoofy && checkedSwitch && checkedDisney && checkedDaffy && checkedFakie) {
            score += 1;
        }
        if (frontFootPushing) {
            score += 1;
        }
        if (cestvrai) {
            score += 1;
        }
        return score;
    }
    //Game Summary called when player clicks submit
    private String gameSummary(int score, String playerName) {
        String endOFgame = getString(R.string.wellDone) + " " + playerName + getString(R.string.exclamationMark) + getString(R.string.finalScore) + score + getString(R.string.out7);
        if (score < 1) {
            Toast.makeText(this, getString(R.string.ansSomething) + " " + playerName + getString(R.string.period), Toast.LENGTH_LONG).show();
            return getString(R.string.nadaComment);
        } else if (score >= 1 && score <= 3) {
            Toast.makeText(this, getString(R.string.almostLanded) + " " + score + getString(R.string.resetTRY), Toast.LENGTH_LONG).show();
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
