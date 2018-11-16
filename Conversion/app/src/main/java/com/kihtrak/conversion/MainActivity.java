package com.kihtrak.conversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView rate;
    TextView result;
    Spinner firstUnit;
    Spinner finalUnit;
    ArrayList<String> units = new ArrayList<String>();
    ArrayList<Double> rates = new ArrayList<Double>();
    Double FirstRate;
    Double FinalRate;
    int firstSel;
    int finalSel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.editText);
        rate = findViewById(R.id.conversionRate);
        firstUnit= findViewById(R.id.ogUnit);
        finalUnit= findViewById(R.id.finalUnit);
        result = findViewById(R.id.result);
        units.add("Miles");
        units.add("Karthiks");
        units.add("Suds");
        units.add("Patatos");
        rates.add(1609.34);
        rates.add(2.0);
        rates.add(1.0);
        rates.add(.25);
        ArrayAdapter unit = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,units);
        firstUnit.setAdapter(unit);
        finalUnit.setAdapter(unit);
        firstUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                firstSel=position;
                FirstRate = rates.get(firstSel);
                if(FinalRate!=null){
                    rate.setText("1 "+units.get(firstSel)+" is equal to "+FirstRate/FinalRate+" "+units.get(finalSel));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        finalUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                finalSel=position;
                FinalRate = rates.get(position);
                if(FirstRate!=null){
                    rate.setText("1 "+units.get(firstSel)+" is equal to "+FirstRate/FinalRate+" "+units.get(finalSel));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    result.setText(FirstRate / FinalRate * Double.parseDouble(s.toString()) + units.get(finalSel));
                }catch(NumberFormatException e) {
                    //not a double
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
