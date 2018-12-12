package com.kihtrak.weatherapp;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.provider.ContactsContract.CommonDataKinds.*;
import static android.provider.ContactsContract.CommonDataKinds.Website.*;

public class MainActivity extends AppCompatActivity {
    //key:
//700fece42f9b9b804f506a72fd6cc78e

    //api.openweathermap.org/data/2.5/forecast?zip=94040,us

    JSONObject weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncThread().execute();
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
