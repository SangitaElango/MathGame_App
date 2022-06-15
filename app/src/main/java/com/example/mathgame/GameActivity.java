package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView textViewScore;
    TextView textViewLife;
    TextView textViewTime;
    TextView textViewQuest;
    EditText editTextAns;
    Button buttonOk;
    Button buttonNext;
    Random random=new Random();
    CountDownTimer timer;
    Intent intent;

    int result;
    int life=3;
    int score=0;
    private static final long START_TIME_IN_MILIS=60000;
    long timeLeft=START_TIME_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        textViewScore=findViewById(R.id.textViewScoreLive);
        textViewLife=findViewById(R.id.textViewLifeLive);
        textViewTime=findViewById(R.id.textViewTimeLive);
        textViewQuest=findViewById(R.id.textViewQuest);
        editTextAns=findViewById(R.id.editTextTextAns);
        buttonOk=findViewById(R.id.buttonOk);
        buttonNext=findViewById(R.id.buttonNextQuest);

        gameContinue();
        timer();
        textViewScore.setText(""+score);
        textViewLife.setText(""+life);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonNext.setEnabled(true);
                checkAnswer();
                //timer.cancel();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(life>0) {
                    editTextAns.setText("");
                    gameContinue();
                    resetTimer();
                    timer();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_LONG).show();
                    intent=new Intent(GameActivity.this,LastActivity.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public void gameContinue(){
        buttonNext.setEnabled(false);
        Intent intent=getIntent();
        int mathValue=intent.getIntExtra("math",0);
        int number1,number2;
        if(mathValue==1){
            number1=random.nextInt(100);
            number2=random.nextInt(100);
            result=number1+number2;
            textViewQuest.setText(number1+"+"+number2);
        }
        else if(mathValue==2){
            number1=random.nextInt(100);
            number2=random.nextInt(100);
            result=number1-number2;
            textViewQuest.setText(number1+"-"+number2);
        }
        else if(mathValue==3){
            number1=random.nextInt(100);
            number2=random.nextInt(100);
            result=number1*number2;
            textViewQuest.setText(number1+"*"+number2);
        }
        else{}
    }
    public void checkAnswer(){
        try {
            String ans = editTextAns.getText().toString();
            if(ans.isEmpty())
                throw new RuntimeException("String Empty");
            int answer = Integer.parseInt(ans);
            editTextAns.setText("");
            if (result == answer) {
                textViewQuest.setText("Your answer is correct!");
                textViewScore.setText("" + (++score));
            } else {
                life--;
                textViewQuest.setText("Sorry! wrong answer");
                textViewLife.setText("" + life);
            }
            timer.cancel();
        }
        catch (RuntimeException runExp){
            if(runExp.getMessage()=="String Empty") {
                Toast.makeText(getApplicationContext(), "Please enter an answer or select Next Question", Toast.LENGTH_LONG).show();

            }
            else{}
        }
    }
    public void timer(){
        timer=new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long l) {
                timeLeft=l;
                updateText();
            }

            @Override
            public void onFinish() {
                life--;
                textViewLife.setText(""+life);
                textViewQuest.setText("Sorry! time is up!");
            }
        }.start();
    }

    public void updateText(){
        int countDown=(int)(timeLeft/1000)%60;
        textViewTime.setText(""+countDown);
    }

    public void resetTimer(){

        timeLeft=START_TIME_IN_MILIS;
    }
}