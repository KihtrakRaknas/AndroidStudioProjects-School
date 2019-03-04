package com.kihtrak.gamedesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    //Code from this program has been used from Beginning Android Games
    //Review SurfaceView, Canvas, continue

    GameSurface gameSurface;

    float leftRight = 0;
    float upDown = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        leftRight = event.values[0];
        //upDown = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameSurface = new GameSurface(this);
        setContentView(gameSurface);
        SensorManager sensorManager= (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor sen = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sen, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        gameSurface.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameSurface.resume();
    }



    //----------------------------GameSurface Below This Line--------------------------
    public class GameSurface extends SurfaceView implements Runnable,SensorEventListener{


        Thread gameThread;
        SurfaceHolder holder;
        volatile boolean running = false;
        Bitmap ball;
        int ballX=0;
        int x=200;
        String sensorOutput="";
        Paint paintProperty;

        int screenWidth;
        int screenHeight;

        public GameSurface(Context context) {
            super(context);
            holder=getHolder();
            ball= BitmapFactory.decodeResource(getResources(),R.drawable.marble);


            Display screenDisplay = getWindowManager().getDefaultDisplay();
            Point sizeOfScreen = new Point();
            screenDisplay.getSize(sizeOfScreen);
            screenWidth=sizeOfScreen.x;
            screenHeight=sizeOfScreen.y;

            SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometerSensor,sensorManager.SENSOR_DELAY_NORMAL);

            paintProperty= new Paint();
            paintProperty.setTextSize(100);

        }

        @Override
        public void run() {
            while (running == true){
                if (holder.getSurface().isValid() == false)
                    continue;
                Canvas canvas= holder.lockCanvas();

                canvas.drawRGB(255,0,0);

                canvas.drawText(sensorOutput,x,200,paintProperty);

                canvas.drawBitmap( ball,(screenWidth/2) - ball.getWidth()/2 +ballX ,(screenHeight/2) - ball.getHeight(),null);

                ballX+=leftRight;

                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void resume(){
            running=true;
            gameThread=new Thread(this);
            gameThread.start();
        }

        public void pause() {
            running = false;
            while (true) {
                try {
                    gameThread.join();
                } catch (InterruptedException e) {
                }
            }
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            //tilt phone and change position

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }//GameSurface
}//Activity