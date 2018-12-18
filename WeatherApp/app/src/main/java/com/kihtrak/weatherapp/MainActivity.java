package com.kihtrak.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.*;
import static android.provider.ContactsContract.CommonDataKinds.Website.*;

public class MainActivity extends AppCompatActivity {
    //key:
//700fece42f9b9b804f506a72fd6cc78e

    //api.openweathermap.org/data/2.5/forecast?zip=94040,us

    JSONObject weather;

    JSONArray listz;

    ArrayList<weatherData> weatherList = new ArrayList<weatherData>();

    CustomAdapter CustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String zip = getIntent().getStringExtra("zip");

        new AsyncThread().execute(zip);

        ListView out = findViewById(R.id.list);



        CustomAdapter = new CustomAdapter(this,R.layout.listlay,weatherList);
        out.setAdapter(CustomAdapter);
        out.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //pos = position;
                //set(pos);
            }
        });
        //set(pos);
    }

    public class CustomAdapter extends ArrayAdapter {
        Context context;
        ArrayList<weatherData> weathers;
        int resource;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
            super(context, resource, objects);

            this.context = context;
            weathers = objects;
            this.resource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterLayout = layoutInflater.inflate(resource,null);
            ImageView img = adapterLayout.findViewById(R.id.img);
            TextView txt = adapterLayout.findViewById(R.id.txt);
            txt.setText(""+weathers.get(position).temp);
            //
            // img.setImageResource(colorList.get(position).img);
            return adapterLayout;
        }

    }


    public class AsyncThread extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... zoop) {
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?APPID=700fece42f9b9b804f506a72fd6cc78e&zip="+zoop[0]+"&&units=imperial");
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
            try {
                listz = weather.getJSONArray("list");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(weather!=null){
                for(int i =0; i!=listz.length();i++){
                    try {
                        weatherList.add(new weatherData(MainActivity.this,listz.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                CustomAdapter.notifyDataSetChanged();
            }
        }
    }

    public class weatherData{
        String time;
        int temp;
        int tempMin;
        int tempMax;
        String weatherState;
        String weatherDesc;
        int icon;
        public weatherData(Context context, JSONObject obj){
            try {
                time = obj.getString("dt_txt");
                JSONObject main = obj.getJSONObject("main");
                temp = main.getInt("temp");
                tempMin = main.getInt("temp_min");
                tempMax = main.getInt("temp_max");
                JSONArray weatherObj = obj.getJSONArray("weather");
                weatherState = weatherObj.getJSONObject(0).getString("main");
                weatherDesc = weatherObj.getJSONObject(0).getString("description");
                String iconNum = "icon" + weatherObj.getJSONObject(0).getString("icon");

                int icon = context.getResources().getIdentifier(
                        iconNum,
                        "drawable",
                        context.getPackageName()
                );

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        int getTemp(){
            return temp;
        }

        String getTime(){
            return time;
        }

    }

}
