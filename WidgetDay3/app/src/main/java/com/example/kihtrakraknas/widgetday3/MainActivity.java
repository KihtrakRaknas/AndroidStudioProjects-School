package com.example.kihtrakraknas.widgetday3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rad;

    TextView textV;
    ImageView imgT;
    ImageView imgB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rad = findViewById(R.id.id_sel);
        textV = findViewById(R.id.id_text);
        imgT = findViewById(R.id.id_imgTop);
        imgB = findViewById(R.id.id_imageBot);
        imgB.setImageResource(R.drawable.images);
        rad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.id_yeet) {
                    textV.setText("YEET");
                }else if(checkedId == R.id.id_teey) {
                    textV.setText("TEEY");
                    Toast myToast = Toast.makeText(MainActivity.this, "THIS APP IS DUMB", Toast.LENGTH_SHORT);
                    myToast.show();
                }
            }
        });
    }
}
