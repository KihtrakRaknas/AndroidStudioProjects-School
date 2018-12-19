package com.kihtrak.weatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StartUp extends AppCompatActivity {
    JSONObject weather;
    String zipStr = "";
    TextView output;
    boolean done = false;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        EditText zip = findViewById(R.id.zipText);
        Button finished = findViewById(R.id.done);

        intent = new Intent(this, MainActivity.class);

        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done = true;
                new AsyncThread().execute(zipStr);
            }
        });

        output = findViewById(R.id.output);

        zip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                zipStr = s.toString();
                new AsyncThread().execute(zipStr);
                done = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public class AsyncThread extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... zoop) {
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?APPID=700fece42f9b9b804f506a72fd6cc78e&zip="+zoop[0]);
                URLConnection test = url.openConnection();

                    /*URLConnection test = new URLConnection(url) {
                        @Override
                        public void connect() throws IOException {

                        }
                    };*/
                InputStream stream = test.getInputStream();
                BufferedReader buffread = new BufferedReader(new InputStreamReader(stream));
                String data = buffread.readLine();
                Log.d("datatest",data);

                weather = new JSONObject(data);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(weather!=null){
                try {
                    output.setText("City: "+ weather.getJSONObject("city").getString("name"));
                    if(done){
                        intent.putExtra("zip",zipStr);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //weather
            }else{
                output.setText("Enter a valid zip code");
            }
        }
    }
}
