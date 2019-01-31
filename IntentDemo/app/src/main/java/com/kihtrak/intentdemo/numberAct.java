
package com.kihtrak.intentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class numberAct extends AppCompatActivity {

    TextView name;
    TextView text;
    EditText in;
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        name = findViewById(R.id.name);
        text = findViewById(R.id.text);
        in = findViewById(R.id.editText2);
        close = findViewById(R.id.close);


        name.setText(this.getIntent().getStringExtra(MainActivity.NAME_CODE));
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent sendBack = new Intent();

                sendBack.putExtra(MainActivity.INTENT_CODE,in.getText().toString());
                setResult(RESULT_OK,sendBack);

                finish();//startActivity(sendBack);
            }
        });



    }
}
