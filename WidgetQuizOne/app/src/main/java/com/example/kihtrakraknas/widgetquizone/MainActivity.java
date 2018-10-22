package com.example.kihtrakraknas.widgetquizone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText firstPass;
    EditText secondPass;
    Button okbtn;
    Switch matching;
    CharSequence passval="";
    CharSequence passval2="";
    TextView used;
    ArrayList prevPass = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstPass = findViewById(R.id.id_firstPass);
        secondPass = findViewById(R.id.id_secondPass);
        okbtn = findViewById(R.id.id_okbtn);
        matching = findViewById(R.id.id_matchingSwitch);
        used = findViewById(R.id.id_used);
        System.out.println("test");
        prevPass.add("test");
        prevPass.add("abc");
        prevPass.add("123");
        prevPass.add("password");
        System.out.println(prevPass);
        firstPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passval = s;
                used.setText("password not used previously");
                for(int i =0; i!=prevPass.size();i++){
                    System.out.println(prevPass.get(i));
                    System.out.println(passval);
                    if(prevPass.get(i).equals(passval.toString())){
                        System.out.println("FOUND");
                        used.setText("password already used");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        secondPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passval2 = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(used.getText().equals("password already used")){
                    matching.setText("Password is used");
                    matching.setChecked(false);
                }else {
                    if (passval.toString().equals(passval2.toString())) {
                        matching.setText("Match");
                        matching.setChecked(true);
                        prevPass.add(passval.toString());

                    } else {
                        matching.setText("Does not Match");
                        matching.setChecked(false);
                    }
                }
            }
        });
    }
}
