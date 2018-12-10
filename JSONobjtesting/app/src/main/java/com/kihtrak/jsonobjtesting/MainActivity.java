package com.kihtrak.jsonobjtesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject potatoComp = new JSONObject();
        JSONObject farm_1 = new JSONObject();
        JSONObject farm_2 = new JSONObject();
        JSONObject farm_3 = new JSONObject();
        JSONObject farm_4 = new JSONObject();
        JSONObject farm_5 = new JSONObject();
        JSONObject farmer = new JSONObject();
        JSONArray crops = new JSONArray();
        try {
            farmer.put("name","jimmbo");
                    crops.put("potato1");
            crops.put("potato2");
            crops.put("potato3");
            crops.put("potato1\4");
            farm_1.put("Crop","potato");
            farm_2.put("Crop","potato");
            farm_3.put("Crop","potato");
            farm_4.put("Crops",crops);
            farm_5.put("Crop","potato");
            farm_5.put("farmer",farmer);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("JSONobj",potatoComp.toString());


    }
}
