package com.example.kihtrakraknas.widgetsday1;

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

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView seekText;

    EditText editText;
    TextView editTextVeiw;

    Switch switchView;
    TextView switchTextVeiw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.id_seekBar);
        seekText = findViewById(R.id.id_seekText);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekText.setText(seekBar.getProgress()+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekText.setText(seekBar.getProgress()+"%");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekText.setText(seekBar.getProgress()+"%");
            }
        });
        switchView = findViewById(R.id.id_switch);
        switchTextVeiw = findViewById(R.id.id_switchText);

        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchTextVeiw.setText("TRUE");
                }else{
                    switchTextVeiw.setText("FALSE");
                }
            }
        });

        editText = findViewById(R.id.editText);
        editTextVeiw = findViewById(R.id.textVeiwout);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextVeiw.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
