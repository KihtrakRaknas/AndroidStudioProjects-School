package com.kihtrak.regen_andriod;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    JSONObject grades;
    Intent intent;
    int mp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, gradeDisplay.class);
        Log.d("datatest","test");

        List<String> categories = new ArrayList<String>();
        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ((Spinner)findViewById(R.id.spinner)).setAdapter(dataAdapter);

        ((Spinner)findViewById(R.id.spinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mp=position+1;
                Toast.makeText(MainActivity.this, "MP"+mp+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        try {
            BufferedReader red = new BufferedReader(new InputStreamReader(openFileInput("info.json")));
            JSONObject json = new JSONObject(red.readLine());
            ((EditText)findViewById(R.id.user)).setText(json.getString("username"));
            ((EditText)findViewById(R.id.pass)).setText(json.getString("password"));
        }catch (Exception w){

        }


    }

    public void signIn(View v){
        Log.d("datatest","te2st");
        String user = ((EditText)findViewById(R.id.user)).getText().toString(); //MAYBE WORKS
        String pass = ((EditText)findViewById(R.id.pass)).getText().toString();
        Log.d("datatest","user"+user+"pass"+pass);
        new AsyncThread().execute(user,pass);
    }

    public class AsyncThread extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... zoop) {
            try {
                Log.d("datatest","te3st");
                URL url = new URL("http://gradeview.herokuapp.com/?username="+zoop[0]+"&password="+zoop[1]);
                //URL url = new URL("http://gradeview.herokuapp.com/?username=10013096@sbstudents.org&password=Tint%40%2579");
                URLConnection test = url.openConnection();

                    /*URLConnection test = new URLConnection(url) {
                        @Override
                        public void connect() throws IOException {

                        }
                    };*/
                Log.d("datatest","te5st");
                InputStream stream = test.getInputStream();
                Log.d("datatest","te4st");
                BufferedReader buffread = new BufferedReader(new InputStreamReader(stream));
                String data = buffread.readLine();
                Log.d("datatest","test");

                Log.d("datatest",data);

                grades = new JSONObject(data);

            } catch (Exception e) {
                Log.d("datatest","e"+e.toString());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(grades!=null){
                try {
                    Log.d("datatest", grades.getString("Status"));
                    if(grades.getString("Status").equals("loading...")) {
                        Toast.makeText(MainActivity.this, "Loading try again", Toast.LENGTH_LONG).show();
                    }else{
                        if(mp!=0) {
                            //Save Username & Password
                            JSONObject data = new JSONObject();
                            try {
                                data.put("username",((EditText)findViewById(R.id.user)).getText());
                                data.put("password",((EditText)findViewById(R.id.pass)).getText());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try{
                                OutputStreamWriter out = new OutputStreamWriter(openFileOutput("info.json",MODE_PRIVATE));
                                out.write(data.toString());
                                out.close();
                            }catch (Exception e){

                            }

                            intent.putExtra("mp", mp);
                            intent.putExtra("grades", grades.toString());
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Select MP", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //weather
            }else{
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        }
    }
}
