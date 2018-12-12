package com.kihtrak.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SetUp extends AppCompatActivity {

    JSONObject weather;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText zip = findViewById(R.id.zipText);
        Button done = findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        zip.getText();
    }

    public class AsyncThread extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?APPID=700fece42f9b9b804f506a72fd6cc78e&zip=94040,us");
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
                weather
            }
        }
    }
}
