package com.kihtrak.posttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class displaySelection extends AppCompatActivity {

    TextView sel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_selection);
        sel = findViewById(R.id.sel);

        sel.setText(getIntent().getStringExtra(MainActivity.SELECTION_KEY));

    }
}
