package com.kihtrak.acceldemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ;
        findViewById(R.id.Y);
        findViewById(R.id.Z);

        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ((TextView)findViewById(R.id.X)).setText(event.values[0]+" X");
        ((TextView)findViewById(R.id.Y)).setText(event.values[1]+" Y");
        ((TextView)findViewById(R.id.Z)).setText(event.values[2]+" Z");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
