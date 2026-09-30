package com.example.finalgameproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class NumberActivity extends AppCompatActivity {
TextView num1tv,num2tv,scoreTv,attemptsTv;
Button submitBtn,homeBtn;
EditText answerEditText;
int score=0;
int attempts=0;
int answer=0;


AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        num1tv=findViewById(R.id.num1tv);
        num2tv=findViewById(R.id.num2tv);
        scoreTv=findViewById(R.id.scoreTv);
        attemptsTv=findViewById(R.id.attemptsTv);
        submitBtn=findViewById(R.id.submitBtn);
        homeBtn=findViewById(R.id.homeBtn);

        answerEditText=findViewById(R.id.numAnswerEditText);
showNextQuestion();

       submitBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(answer==Integer.parseInt(answerEditText.getText().toString())){
           Toast.makeText(NumberActivity.this, answer+" is correct", Toast.LENGTH_SHORT).show();


                   score++;

               }
               else {
                   Toast.makeText(NumberActivity.this, "wrong,correct answer is: "+answer, Toast.LENGTH_SHORT).show();

               }
               attempts++;
               scoreTv.setText("Score: "+score);
               attemptsTv.setText("Attempts left: "+(15-attempts));

               if(attempts<15)
               {
                   showNextQuestion();
               }else {
                   endGame();
               }

           }
       });
homeBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(NumberActivity.this,MainActivity.class);
        startActivity(intent);
    }
});

    }

private void showNextQuestion() {

            answerEditText.setText("");
            Random random = new Random();
            int num1 = random.nextInt(10) + 1;
            int num2 = random.nextInt(10) + 1;
            num1tv.setText(Integer.toString(num1));
            num2tv.setText(Integer.toString(num2));
            answer = num1 * num2;


    }
    private void endGame(){
        if(score>=10){
            alertBuilder=new AlertDialog.Builder(this);
            alertBuilder.setTitle("result");
            alertBuilder.setMessage("WINNER. You're score is "+score+" /15");
            alertBuilder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    restartGame();
                }
            });
            AlertDialog alert=alertBuilder.create();
            alert.show();
        }else {
            alertBuilder=new AlertDialog.Builder(this);
            alertBuilder.setTitle("result");
            alertBuilder.setMessage("LOSER.You're score is "+score+" /15");
            alertBuilder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    restartGame();
                }
            });
            AlertDialog alert=alertBuilder.create();
            alert.show();
        }
        submitBtn.setEnabled(false);


    }

    private void restartGame(){
score=0;
attempts=0;
scoreTv.setText("score: "+score);
attemptsTv.setText("Attempts left: "+(15-attempts));
submitBtn.setEnabled(true);

showNextQuestion();
    }
}