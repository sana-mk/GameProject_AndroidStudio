package com.example.finalgameproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MatchColorActivity extends AppCompatActivity {
TextView questionTV,scoreColorTv,timerColorTv;
Button redBtn,blueBtn,greenBtn,yellowBtn,homeBtn3;
    CountDownTimer countdt;

int score=0;

    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_color);
questionTV=findViewById(R.id.questionTV);
redBtn=findViewById(R.id.redBtn);
        blueBtn=findViewById(R.id.blueBtn);
        greenBtn=findViewById(R.id.greenBtn);
       yellowBtn=findViewById(R.id.yellowBtn);
       homeBtn3=findViewById(R.id.homeBtn3);
       scoreColorTv=findViewById(R.id.scoreColorTv);
       timerColorTv=findViewById(R.id.timerColorTv);
        homeBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MatchColorActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

       setColorQuestion();

    }
    private void setColorQuestion(){
timerCount();
        Map<String,String> colorMap=new HashMap<>();
        colorMap.put("red","#FF0C0C");
        colorMap.put("blue","#007BFF");
        colorMap.put("green","#10FE00");
        colorMap.put("yellow","#FEBC08");
        String questionColor=getRandomColor();
        String question=getRandomColor();
        if(colorMap.containsKey(questionColor)&&colorMap.containsKey(question)){

            questionTV.setText(question);

            questionTV.setTextColor(Color.parseColor(colorMap.get(questionColor)));
        }
    }
    private String getRandomColor(){
        List<String> colors=new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("green");
        colors.add("yellow");
        Random random=new Random();
        int randomIndex=random.nextInt(4);
        return colors.get(randomIndex);
    }
   public void submitAnswerColor(View view){
Button btn=(Button) view;
if(questionTV.getText().equals(btn.getText())){
    score+=10;
    scoreColorTv.setText("Score: "+score);
    countdt.cancel();
    setColorQuestion();

}else {
   endColorGame();
}
    }

    private void endColorGame(){
        alertBuilder=new AlertDialog.Builder(this);
        alertBuilder.setTitle("result");
        alertBuilder.setMessage("LOSER. You're score is "+score);
        alertBuilder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                score=0;
                scoreColorTv.setText("Score: "+score);
                setColorQuestion();

            }
        });
        AlertDialog alert=alertBuilder.create();
        alert.show();
        countdt.cancel();
    }
private void timerCount(){

    countdt=new CountDownTimer(4000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
            timerColorTv.setText("Timer: "+sec);
        }

        @Override
        public void onFinish() {

endColorGame();
cancel();
        }
    }.start();
}

}