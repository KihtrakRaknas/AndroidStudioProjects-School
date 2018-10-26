package com.example.kihtrakraknas.harrypotterradioprac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rad;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rad = findViewById(R.id.rad);
        img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.download);
        rad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.harry) {
                    img.setImageResource(R.drawable.harry);
                    Toast myToast = Toast.makeText(MainActivity.this,"You Choose Harry!", Toast.LENGTH_LONG);
                    myToast.show();
                }else if(checkedId == R.id.hermione) {
                    img.setImageResource(R.drawable.her);
                    Toast myToast = Toast.makeText(MainActivity.this,"You Choose Hermione!", Toast.LENGTH_LONG);
                    myToast.show();
                }else if(checkedId == R.id.ron) {
                    img.setImageResource(R.drawable.ron);
                    Toast myToast = Toast.makeText(MainActivity.this,"You Choose Ron!", Toast.LENGTH_LONG);
                    myToast.show();
                }
            }
        });
    }
}
