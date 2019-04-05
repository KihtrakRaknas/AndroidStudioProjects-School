package com.kihtrak.regen_andriod;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void signIn(View v){
        String user = ((EditText)findViewById(R.id.user)).getText().toString(); //MAYBE WORKS
        String pass = ((EditText)findViewById(R.id.pass)).getText().toString();
        new AsyncThread().execute(user,pass);
    }

    public class AsyncThread extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... zoop) {
            try {
                URL url = new URL("https://gradeview.herokuapp.com/?username="+zoop[0]+"&password="+zoop[1]);
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
