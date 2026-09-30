package com.example.finalgameproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class FlagActivity extends AppCompatActivity {


ImageView flagImageView;
EditText countryEditText;
TextView scoreTextView;
TextView attemptsTextView;
Button submitButton;
Button homeBtn2;


int score=0;
int attempts=0;
    int currentFlagIndex;
    AlertDialog.Builder alertBuilder;
    String[] countryNames = {"america","armenia","austria","bahrain","brazil","canada","china",
      "denmark","england","egypt","france","germany","greece","hongkong","india","iran","italy",
       "japan","netherlands","norway","qatar","singapore","spain","sweden","switzerland","turkey",
      "ukrine","vietnam","yemen"};

    int[] flagImages ={  R.drawable.america,R.drawable.armania,R.drawable.austria,R.drawable.bahrain,
              R.drawable.brazil,R.drawable.canada,R.drawable.china,R.drawable.denmark,R.drawable.england,
              R.drawable.egypt,R.drawable.france,R.drawable.germany,R.drawable.greece,
               R.drawable.hongkong,R.drawable.india,R.drawable.iran,
              R.drawable.italy,R.drawable.japan,R.drawable.netherlands,R.drawable.norway,
              R.drawable.qatar,R.drawable.singapore,R.drawable.spain,R.drawable.sweden,
              R.drawable.switzerland,R.drawable.turkey,R.drawable.ukrine,
               R.drawable.vietnam,R.drawable.yemen};

    boolean[] usedFlags = new boolean[countryNames.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);
flagImageView=findViewById(R.id.flagImageView);
countryEditText=findViewById(R.id.countryEditText);
scoreTextView=findViewById(R.id.scoreTextView);
attemptsTextView=findViewById(R.id.attemptsTextView);
submitButton=findViewById(R.id.submitButton);
homeBtn2=findViewById(R.id.homeBtn2);

        homeBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FlagActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
showNextFlag();

submitButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String answer = countryEditText.getText().toString().trim().toLowerCase();

        if (answer.isEmpty()) {
            Toast.makeText(FlagActivity.this, "Please enter a country name!", Toast.LENGTH_SHORT).show();

        }
else {
            if (answer.equals(countryNames[currentFlagIndex])) {
                score++;

                Toast.makeText(FlagActivity.this, "Correct!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(FlagActivity.this, "Wrong! Correct answer is: " + countryNames[currentFlagIndex], Toast.LENGTH_SHORT).show();
            }
            flagImageView.setAlpha(0f);
            flagImageView.animate().alpha(1f).setDuration(2000);
            attempts++;
            scoreTextView.setText("Score: " + score);
            attemptsTextView.setText("Attempts left: " + (10 - attempts));

            if (attempts < 10) {
                showNextFlag();
            } else {
                endGame();
            }
        }
    }
});


    }

    private void showNextFlag() {
        if (attempts < 10) {
            Random random = new Random();
            do {
                currentFlagIndex = random.nextInt(countryNames.length);
            } while (usedFlags[currentFlagIndex]);

            usedFlags[currentFlagIndex] = true;
            flagImageView.setImageResource(flagImages[currentFlagIndex]);

           countryEditText.setText("");
        } else {
            endGame();
        }
    }
    private void endGame() {
        if (score >= 8) {
            alertBuilder=new AlertDialog.Builder(this);
            alertBuilder.setTitle("result");
            alertBuilder.setMessage("WINNER. You're score is "+score+" /10");
            alertBuilder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    restartGame();
                }
            });
            AlertDialog alert=alertBuilder.create();
            alert.show();        }
        else {
            alertBuilder=new AlertDialog.Builder(this);
            alertBuilder.setTitle("result");
            alertBuilder.setMessage("LOSER. You're score is "+score+" /10");
            alertBuilder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    restartGame();
                }
            });
            AlertDialog alert=alertBuilder.create();
            alert.show();              }
        submitButton.setEnabled(false);

    }
    private void restartGame() {
        score = 0;
        attempts = 0;
        for (int i = 0; i < usedFlags.length; i++) {
            usedFlags[i] = false;
        }
        scoreTextView.setText("Score: " + score);
        attemptsTextView.setText("Attempts left: " + (10 - attempts));
        submitButton.setEnabled(true);

       showNextFlag();
    }
}






