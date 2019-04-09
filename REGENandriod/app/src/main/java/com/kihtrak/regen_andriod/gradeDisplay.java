package com.kihtrak.regen_andriod;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class gradeDisplay extends AppCompatActivity {
    JSONObject grades;
    int mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_display);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String data = getIntent().getStringExtra("grades");
        mp = getIntent().getIntExtra("mp",1);

        try {
            grades = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //
        Iterator<String> keys = grades.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            try {
                if (grades.get(key) instanceof JSONObject) {
                    ((JSONObject) grades.get(key)).getJSONObject("MP"+mp).getString("avg");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
