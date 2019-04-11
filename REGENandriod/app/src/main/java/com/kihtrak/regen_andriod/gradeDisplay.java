package com.kihtrak.regen_andriod;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class gradeDisplay extends AppCompatActivity {
    JSONObject grades;
    int mp;
    ListView list;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<String> avgList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_display);

        String data = getIntent().getStringExtra("grades");
        mp = getIntent().getIntExtra("mp",1);

        try {
            grades = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //
        Iterator<String> keys = grades.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            try {
                if (grades.get(key) instanceof JSONObject) {
                    //key
                    avgList.add(key);
                    arrayList.add(((JSONObject) grades.get(key)).getJSONObject("MP"+mp).getString("avg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        list = findViewById(R.id.list);
        arrayList.add("asdfa");
        arrayList.add("fdas");
        arrayList.add("hjklhjkl");
        arrayList.add("lkhlkh");
        arrayList.add("cvbnm");

        CustomAdapter CustomAdapter = new CustomAdapter(this,R.layout.custom_layout,avgList,arrayList);
        list.setAdapter(CustomAdapter);
    }


    public class CustomAdapter extends ArrayAdapter {
        Context context;
        List<String> nameList;
        List<String> avgList;
        int resource;
        public CustomAdapter(@NonNull Context context, int resource, @NonNull List objects, List object2) {
            super(context, resource, objects);

            this.context = context;
            nameList = object2;
            avgList = objects;
            this.resource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterLayout = layoutInflater.inflate(resource,null);
            TextView txt = adapterLayout.findViewById(R.id.textView);
            txt.setText(nameList.get(position));
            TextView txt2 = adapterLayout.findViewById(R.id.avg);
            txt2.setText(avgList.get(position));
            return adapterLayout;
        }
    }
}
