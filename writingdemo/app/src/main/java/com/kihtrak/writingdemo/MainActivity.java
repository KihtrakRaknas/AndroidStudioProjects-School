package com.kihtrak.writingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.textView)).setText("NO DATA");




    }
    public void save(View v){
        JSONObject data = new JSONObject();
        try {
            data.put("info1",((EditText)findViewById(R.id.editText)).getText());
            data.put("info2",((EditText)findViewById(R.id.editText2)).getText());
            data.put("info3",((EditText)findViewById(R.id.editText3)).getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try{
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("info.json",MODE_PRIVATE));
            out.write(data.toString());
            out.close();
        }catch (Exception e){

        }
    }

    public void load(View v){
        try {
            BufferedReader red = new BufferedReader(new InputStreamReader(openFileInput("info.json")));
            JSONObject json = new JSONObject(red.readLine());

            ((TextView)findViewById(R.id.textView)).setText(json.getString("info1")+"; "+json.getString("info2")+"; "+json.getString("info3")+";");
        }catch (Exception w){

        }
    }
}
