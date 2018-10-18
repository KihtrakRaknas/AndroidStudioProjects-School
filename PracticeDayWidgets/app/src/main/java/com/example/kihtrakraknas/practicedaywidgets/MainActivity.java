package com.example.kihtrakraknas.practicedaywidgets;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean s1C = false;
    boolean s2C = false;
    boolean s3C = false;
    TextView color;
    EditText editText;
    TextView verifiedOut;
    String EditTextVal = "";
    String EditText2Val = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch s1 = findViewById(R.id.switch1);
        Switch s2 = findViewById(R.id.switch3);
        Switch s3 = findViewById(R.id.switch3);
        color = findViewById(R.id.color);
        ArrayList<String> test = new ArrayList<String>();
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s1C =isChecked;
                if(s1C&&s2C&&s3C){
                    color.setTextColor(Color.BLUE);
                }
                else if(s1C&&!s2C&&s3C){
                    color.setTextColor(Color.RED);
                }
                if(!s1C&&!s2C&&s3C){
                    color.setTextColor(Color.GREEN);
                }
            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s2C =isChecked;
                if(s1C&&s2C&&s3C){
                    color.setTextColor(Color.BLUE);
                }
                else if(s1C&&!s2C&&s3C){
                    color.setTextColor(Color.RED);
                }
                if(!s1C&&!s2C&&s3C){
                    color.setTextColor(Color.GREEN);
                }
            }
        });
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s3C =isChecked;
                if(s1C&&s2C&&s3C){
                    color.setTextColor(Color.BLUE);
                }
                else if(s1C&&!s2C&&s3C){
                    color.setTextColor(Color.RED);
                }
                if(!s1C&&!s2C&&s3C){
                    color.setTextColor(Color.GREEN);
                }
            }
        });
        editText = findViewById(R.id.id_email);
        verifiedOut = findViewById(R.id.id_verifiedOut);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditTextVal = ""+s;
                System.out.println(EditTextVal);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        EditText editText2 = findViewById(R.id.id_email2);
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText2Val = ""+s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        SeekBar slider = findViewById(R.id.slider);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((TextView)findViewById(R.id.textSize)).setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void verify(View v){
        if(EditTextVal.contains("@")&&EditTextVal.contains(".com")&&EditTextVal.indexOf('@')<EditTextVal.indexOf(".com")){
            verifiedOut.setText("Verified");
        }
    }
    public void verify2(View v){
        //if(){
            verifiedOut.setText("Verified");
        //}
    }
}
