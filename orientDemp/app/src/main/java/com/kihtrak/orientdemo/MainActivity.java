package com.kihtrak.orientdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView view;
    TextView port;
    TextView land;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.text);
        port = findViewById(R.id.port);
        land = findViewById(R.id.land);
        if(getResources().getConfiguration().orientation == 2)
            land.setText("TEST");
        Log.d("tagy",""+getResources().getConfiguration().orientation);
    }
}
