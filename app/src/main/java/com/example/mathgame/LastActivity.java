package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LastActivity extends AppCompatActivity {

    TextView textViewCongrats;
    TextView textViewFinalScore;
    Button buttonPlay;
    Button buttonExit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        textViewCongrats=findViewById(R.id.textViewCongrats);
        textViewFinalScore=findViewById(R.id.textViewFinalScore);
        buttonPlay=findViewById(R.id.buttonPlay);
        buttonExit=findViewById(R.id.buttonExit);

        intent=getIntent();
        int finalScore=intent.getIntExtra("score",0);
        textViewFinalScore.setText("Your Score: "+finalScore);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(LastActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}