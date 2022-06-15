package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    Button subButton;
    Button multiplyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton=findViewById(R.id.addButton);
        subButton=findViewById(R.id.subButton);
        multiplyButton=findViewById(R.id.multiplyButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int valueMath=1;
                Intent intent=new Intent(MainActivity.this, GameActivity.class);
                //intent.putExtra("math",valueMath);
                intent.putExtra("math",1);
                startActivity(intent);
                finish();
            }
        });

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("math",2);
                startActivity(intent);
                finish();
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("math",3);
                startActivity(intent);
                finish();
            }
        });
    }
}