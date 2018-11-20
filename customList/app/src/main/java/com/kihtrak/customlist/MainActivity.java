package com.kihtrak.customlist;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        arrayList.add("asdfa");
        arrayList.add("fdas");
        arrayList.add("hjklhjkl");
        arrayList.add("lkhlkh");
        arrayList.add("cvbnm");

        CustomAdapter CustomAdapter = new CustomAdapter(this,R.layout.custom_layout,arrayList);
        list.setAdapter(CustomAdapter);
    }

    public class CustomAdapter extends ArrayAdapter{
        Context context;
        List<String> nameList;
        int resource;
        public CustomAdapter(@NonNull Context context, int resource, @NonNull List objects) {
            super(context, resource, objects);

            this.context = context;
            nameList = objects;
            this.resource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterLayout = layoutInflater.inflate(resource,null);
            ImageView img = adapterLayout.findViewById(R.id.imageView);
            TextView txt = adapterLayout.findViewById(R.id.textView);
            Button btn = adapterLayout.findViewById(R.id.button);
            txt.setText(nameList.get(position)+" "+position);
            return adapterLayout;
        }
    }
}
