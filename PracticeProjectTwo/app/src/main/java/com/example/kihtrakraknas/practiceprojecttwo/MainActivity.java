package com.example.kihtrakraknas.practiceprojecttwo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout back;
    Button cyan;
    Button gray;
    Button magenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back = findViewById(R.id.parentLay);
        cyan = findViewById(R.id.cyan);
        gray = findViewById(R.id.gray);
        magenta = findViewById(R.id.magenta);
    }
    void CYAN(View v){
        back.setBackgroundColor(Color.CYAN);
    }
    void GRAY(View v){
        back.setBackgroundColor(Color.GRAY);
    }
    void MAGENTA(View v){
        back.setBackgroundColor(Color.MAGENTA);
    }
    void RED(View v){
        cyan.setTextColor(Color.RED);
        gray.setTextColor(Color.RED);
        magenta.setTextColor(Color.RED);
    }
    void BLUE(View v){
        cyan.setTextColor(Color.BLUE);
        gray.setTextColor(Color.BLUE);
        magenta.setTextColor(Color.BLUE);
    }
    void GREEN(View v){
        cyan.setTextColor(Color.GREEN);
        gray.setTextColor(Color.GREEN);
        magenta.setTextColor(Color.GREEN);
    }
}
