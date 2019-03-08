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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //Code from this program has been used from Beginning Android Games
    //Review SurfaceView, Canvas, continue

    GameSurface gameSurface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameSurface = new GameSurface(this);
        setContentView(gameSurface);
        /*SensorManager sensorManager= (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor sen = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sen, SensorManager.SENSOR_DELAY_NORMAL);*/
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
        final Bitmap rockBit = BitmapFactory.decodeResource(getResources(),R.drawable.rock);
        Bitmap ballBit = BitmapFactory.decodeResource(getResources(),R.drawable.spaceship);
        ball ball;

        Thread gameThread;
        SurfaceHolder holder;
        volatile boolean running = false;
        int ballX=0;
        int ballY=0;
        float ballVX = 0;
        float ballVY = 0;
        int x=200;
        String sensorOutput="";
        Paint paintProperty;

        int screenWidth;
        int screenHeight;

        float leftRight = 0;
        float upDown = 0;

        ArrayList <rock> astroids = new ArrayList<rock>();

        @Override
        public void onSensorChanged(SensorEvent event) {
            leftRight = -1*event.values[0];
            upDown = event.values[1];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public GameSurface(Context context) {
            super(context);
            holder=getHolder();


            Display screenDisplay = getWindowManager().getDefaultDisplay();
            Point sizeOfScreen = new Point();
            screenDisplay.getSize(sizeOfScreen);
            screenWidth=sizeOfScreen.x;
            screenHeight=sizeOfScreen.y;

            ball = new ball(screenWidth/2,screenHeight-ballBit.getHeight()-10,ballBit);

            SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometerSensor,sensorManager.SENSOR_DELAY_NORMAL);

            paintProperty= new Paint();

            paintProperty.setTextSize(100);
            try {
                Timer timdf = new Timer();
                TimerTask timT = new TimerTask() {
                    @Override
                    public void run() {
                        astroids.add(new rock( screenWidth,rockBit,astroidCount/5));
                        astroidCount++;
                    }
                };
                timdf.scheduleAtFixedRate(timT,0,3000);
            } catch(Exception e){

            }
        }

        int astroidCount = 10;




        @Override
        public void run() {
            while (running == true){
                if (holder.getSurface().isValid() == false)
                    continue;

                Canvas canvas= holder.lockCanvas();

                canvas.drawRGB(255,0,0);

                canvas.drawText(sensorOutput,x,200,paintProperty);

                ball.velx(leftRight/20);

                ball.vely(upDown/20);

                ball.update();

                canvas.drawBitmap( ball.bitmap(),ball.x() ,ball.y(),null);

                for(int i = astroids.size()-1;i!=-1;i--){
                    if(ball.isTouching(astroids.get(i))){
                        //GAME OVER
                        //tim.cancel();
                    }

                    astroids.get(i).update();
                    canvas.drawBitmap( astroids.get(i).bitmap(),astroids.get(i).x() ,astroids.get(i).y(),null);
                }

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

    }//GameSurface
}//Activity

