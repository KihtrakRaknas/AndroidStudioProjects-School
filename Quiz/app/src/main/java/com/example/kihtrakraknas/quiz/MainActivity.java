package com.example.kihtrakraknas.quiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button red;
    Button blue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        red=findViewById(R.id.id_redbtn);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Button)v).setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }
        });
        blue = findViewById(R.id.id_bluebtn);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Button)v).setTextColor(Color.BLUE);
                red.setText(blue.getText());
            }
        });
    }

}
