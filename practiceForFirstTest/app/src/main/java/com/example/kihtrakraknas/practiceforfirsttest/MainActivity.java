package com.example.kihtrakraknas.practiceforfirsttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.YELLOW;
import static android.graphics.Color.BLUE;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button1;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.anon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = (int)(Math.random()*50);
                if(button.getTextSize()<size)
                    button.setTextSize(size);
            }
        });
        button1= findViewById(R.id.button1);
        button3= findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text2 = button.getText();
                button.setText(button1.getText());
                button1.setText(text2);
            }
        });

    }

    public void getRandTextColor(View v){
        switch((int)(Math.random()*4)){
            case 0:
                ((Button)v).setTextColor(BLACK);
                break;
            case 1:
                ((Button)v).setTextColor(BLUE);
                break;
            case 2:
                ((Button)v).setTextColor(GREEN);
                break;
            case 3:
                ((Button)v).setTextColor(YELLOW);
                break;
        }

    }
}
