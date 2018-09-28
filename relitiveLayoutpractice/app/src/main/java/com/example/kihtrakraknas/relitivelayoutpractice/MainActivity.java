package com.example.kihtrakraknas.relitivelayoutpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void btnclicked(View V){
        //String text = V.getResources().getResourceName(V.getId());
        //View ve = (R.id["dis"+text.substring(3)]);
    }
    void toggle1(View V){
        //((TextView)findViewById(R.id.dis2)).setText(((TextView)findViewById(R.id.dis1)).getText());

        if(((TextView)findViewById(R.id.dis1)).getText().equals("ON"))
            ((TextView)findViewById(R.id.dis1)).setText("OFF");
        else
            ((TextView)findViewById(R.id.dis1)).setText("ON");
    }

    void toggle2(View V){
        if(((TextView)findViewById(R.id.dis2)).getText()=="ON")
            ((TextView)findViewById(R.id.dis2)).setText("OFF");
        else
            ((TextView)findViewById(R.id.dis2)).setText("ON");
    }
    void toggle3(View V){
        if(((TextView)findViewById(R.id.dis3)).getText()=="ON")
            ((TextView)findViewById(R.id.dis3)).setText("OFF");
        else
            ((TextView)findViewById(R.id.dis3)).setText("ON");
    }
    void toggle4(View V){
        if(((TextView)findViewById(R.id.dis4)).getText()=="ON")
            ((TextView)findViewById(R.id.dis4)).setText("OFF");
        else
            ((TextView)findViewById(R.id.dis4)).setText("ON");
    }
    void toggle5(View V){
        if(((TextView)findViewById(R.id.dis5)).getText()=="ON")
            ((TextView)findViewById(R.id.dis5)).setText("OFF");
        else
            ((TextView)findViewById(R.id.dis5)).setText("ON");
    }
}
