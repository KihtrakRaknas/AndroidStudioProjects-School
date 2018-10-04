package com.example.kihtrakraknas.layoutquiz;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1;
    Button btn2;
    TextView text1;
    TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text1.getText().equals("Not Clicked"))
                    text1.setText("Clicked");
                else
                    text1.setText("Not Clicked");
            }
        });
        clicklis  clk = new clicklis();
        btn2.setOnClickListener(this);
    }

    public class clicklis implements View.OnClickListener {
        public void onClick(View v) {
            if (text2.getText().equals("Not Clicked"))
                text2.setText("Clicked");
            else
                text2.setText("Not Clicked");
        }
    }

}
