package com.kihtrak.intentdemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView number;
    TextView name;
    Button launch;
    EditText editText;
    String nameStr = "";

    static final int NUMBER_CODE=1234;
    static final String INTENT_CODE = "number code";

    static final String NAME_CODE = "name code";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NUMBER_CODE && resultCode==RESULT_OK){
            number.setText(data.getStringExtra(INTENT_CODE));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launch = findViewById(R.id.button);
        number = findViewById(R.id.number);
        name = findViewById(R.id.name);

        editText = findViewById(R.id.editText);

        editText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    nameStr = editText.getText().toString();
                    editText.setVisibility(View.INVISIBLE);
                    name.setText(nameStr);

                    View view = getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    Toast.makeText(MainActivity.this, "fgfgf", Toast.LENGTH_SHORT).show();
                    //return true;
                }
                return false;
                }
        });

        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentToLoadActivity = new Intent(MainActivity.this,numberAct.class);
                intentToLoadActivity.putExtra(NAME_CODE,nameStr);
                //startActivity(intentToLoadActivity);
                startActivityForResult(intentToLoadActivity,NUMBER_CODE);
            }
        });


    }

    public void name(String name){

    }
}
