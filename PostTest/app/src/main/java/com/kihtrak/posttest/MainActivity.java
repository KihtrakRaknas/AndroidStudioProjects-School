package com.kihtrak.posttest;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

        RadioGroup rad;
        String selection = "You have not selected anything yet";
        ConstraintLayout lay;
        TextView name;
        public static final String SELECTION_KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rad = findViewById(R.id.rad);
        lay = findViewById(R.id.lay);
        name = findViewById(R.id.name);

        rad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.toast){
                    selection="The Toast Radio Button was selected";
                }else if(checkedId==R.id.color){
                    selection="The Change Color Radio Button was selected";
                }else if(checkedId==R.id.uppercase){
                    selection="The Uppercase Radio Button was selected";
                }
            }
        });
    }

    public void run(View v){
        if(selection == "The Toast Radio Button was selected"){
            Toast test = Toast.makeText(MainActivity.this,"Toast Selected",Toast.LENGTH_SHORT);
            test.show();
        }else if(selection == "The Change Color Radio Button was selected"){
            int rand = (int) (Math.random()*3);
            if(rand==0)
                lay.setBackgroundColor(Color.GREEN);
            else if(rand==1)
                lay.setBackgroundColor(Color.BLUE);
            else
                lay.setBackgroundColor(Color.YELLOW);

            /*
            //EXTRA FEATURE
            int red=(int) (Math.random()*255);
            int green=(int)(Math.random()*255);
            int blue=(int)(Math.random()*255);
            int col = Color.rgb(red,green,blue);
            lay.setBackgroundColor(col);
            //END EXTRA FEATURE
            */

        }else if(selection == "The Uppercase Radio Button was selected"){
            name.setText(name.getText().toString().toUpperCase());
        }else{
            Toast test = Toast.makeText(MainActivity.this,"You have not made a selection!",Toast.LENGTH_LONG);
            test.show();
        }
    }

    public void launch(View v){
        Intent intent = new Intent(MainActivity.this, displaySelection.class);
        intent.putExtra(SELECTION_KEY,selection);

        startActivity(intent);
    }
}
