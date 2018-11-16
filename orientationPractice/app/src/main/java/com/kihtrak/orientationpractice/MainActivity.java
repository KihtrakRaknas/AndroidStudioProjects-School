package com.kihtrak.orientationpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinz;
    TextView text;
    ArrayAdapter<String> spinad;
    ArrayList<String> items = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinz = findViewById(R.id.spinner);
        text = findViewById(R.id.text);
        items.add("test");
        items.add("1");
        items.add("3");
        items.add("2");
        if(spinz!=null) {
            spinad = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
            spinz.setAdapter(spinad);
            spinz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    text.setText(items.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    text.setText("all elements removed");
                }
            });
        }
    }
    public void remove(View V){
        while(items.size()>0)
            items.remove(0);
        spinad.notifyDataSetChanged();

    }
}
