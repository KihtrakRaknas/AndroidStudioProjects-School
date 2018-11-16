package com.kihtrak.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    Button btn;
    int counter=0;

    public static final String COUNTER_KEY = "key1";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("statR", "Saving data");
        outState.putInt(COUNTER_KEY,counter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        txt = findViewById(R.id.txt);
        if(savedInstanceState!=null)
            counter = savedInstanceState.getInt(COUNTER_KEY);

        txt.setText(""+counter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                txt.setText(""+counter);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("statR","STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("statR","onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("statR","onPause");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("statR","START");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("statR","RESUME");
    }

}
