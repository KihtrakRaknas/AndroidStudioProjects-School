package com.example.kihtrakraknas.practiceprojectpart2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    boolean one = false;
    boolean two = false;
    boolean three = false;
    void btn1(View v){
        one=true;
        check();
    }
    void btn2(View v){
        two=true;
        check();
    }
    void btn3(View v){
        three=true;
        check();
    }
    void check(){
        if(one&&two&&three){
            findViewById(R.id.btn1).setClickable(false);
            findViewById(R.id.btn2).setClickable(false);
            findViewById(R.id.btn3).setClickable(false);
        }
    }
}
