package com.kihtrak.gamedesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //Code from this program has been used from Beginning Android Games
    //Review SurfaceView, Canvas, continue

    GameSurface gameSurface;

    MediaPlayer play;
    MediaPlayer lazer;
    MediaPlayer crash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameSurface = new GameSurface(this);
        setContentView(gameSurface);
        /*SensorManager sensorManager= (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor sen = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sen, SensorManager.SENSOR_DELAY_NORMAL);*/

        play = MediaPlayer.create(this,R.raw.back);
        try{
            play.prepare();
        }catch (Exception e){

        }

        lazer = MediaPlayer.create(this,R.raw.lazer);
        try{
            lazer.prepare();
        }catch (Exception e){

        }
        crash = MediaPlayer.create(this,R.raw.crash);
        try{
            crash.prepare();
        }catch (Exception e){

        }

        play.start();
        play.setLooping(true);


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
        Bitmap ballDamBit = BitmapFactory.decodeResource(getResources(),R.drawable.spaceshipdamaged);
        Bitmap pewBit = BitmapFactory.decodeResource(getResources(),R.drawable.pew);
        ball ball;

        Thread gameThread;
        SurfaceHolder holder;
        volatile boolean running = false;
        int time = 60;
        int score = 0;

        Paint paintProperty;

        int screenWidth;
        int screenHeight;

        float leftRight = 0;
        float upDown = 0;

        boolean flash = false;
        int flashCount = 0;

        ArrayList <rock> astroids = new ArrayList<rock>();
        ArrayList <bullet> bullets = new ArrayList<bullet>();

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

            ball = new ball(screenWidth/2,screenHeight-ballBit.getHeight()-10,ballBit,screenWidth,screenHeight);

            SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometerSensor,sensorManager.SENSOR_DELAY_NORMAL);

            paintProperty= new Paint();
            //astroids.add(new rock( rockBit,astroidCount/5,screenWidth,screenHeight));
            paintProperty.setTextSize(100);
            paintProperty.setColor(Color.WHITE);
            try {
                Timer timdf = new Timer();
                TimerTask timT = new TimerTask() {
                    @Override
                    public void run() {
                        astroids.add(new rock( rockBit,astroidCount/5,screenWidth,screenHeight));
                    }
                };
                timdf.scheduleAtFixedRate(timT,0,2000);
            } catch(Exception e){

            }

            try {
                Timer timdf = new Timer();
                final TimerTask timT = new TimerTask() {
                    @Override
                    public void run() {
                        if(time>0)
                            time--;
                        if(time<=0) {
                            play.start();
                            //this.cancel();
                        }
                    }
                };
                timdf.scheduleAtFixedRate(timT,0,1000);
            } catch(Exception e){

            }

            try {
                Timer timdf = new Timer();
                final TimerTask timT = new TimerTask() {
                    @Override
                    public void run() {
                        if(flash) {
                            flashCount++;
                            if (ball.state() == 1) {
                                ball.state(2);
                                ball.setImg(ballDamBit);
                            } else {
                                ball.state(1);
                                ball.setImg(ballBit);
                            }
                            if(flashCount>10){
                                flash=false;
                            }
                        }else{
                            ball.setImg(ballBit);
                            flashCount=0;
                        }

                    }
                };
                timdf.scheduleAtFixedRate(timT,0,300);
            } catch(Exception e){

            }

            setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    bulletAdd();
                    return false;
                }
            });

        }

        int astroidCount = 10;

        public void bulletAdd(){
            if(time>0) {
                if (!flash && bullets.size() < 2) {
                    bullets.add(new bullet(ball.x, ball.top(), pewBit, screenWidth, screenHeight, upDown));
                    lazer.start();
                }
            }else{
                flash = false;
                time = 60;
                score = 0;
                ball = new ball(screenWidth/2,screenHeight-ballBit.getHeight()-10,ballBit,screenWidth,screenHeight);
                astroidCount = 10;
                while(astroids.size()>0) {
                    astroids.remove(0);
                }
                while(bullets.size()>0) {
                    astroids.remove(0);
                }
                play.start();
            }
        }


        @Override
        public void run() {
            while (running == true){
                if (holder.getSurface().isValid() == false)
                    continue;


                Canvas canvas= holder.lockCanvas();

                canvas.drawRGB(0,0,0);

                if(time>0) {
                    ball.velx(leftRight / 20);
                    if(score>20000) {
                        ball.vely(upDown / 200);
                    }
                    ball.update();
                    canvas.drawBitmap(ball.bitmap(), ball.x(), ball.y(), null);
                    if(ball.twoNeeded()){
                        canvas.drawBitmap(ball.bitmap(), ball.newLeft(), ball.newTop(), null);
                    }

                    for (int i = astroids.size() - 1; i != -1; i--) {
                        if (ball.isTouching(astroids.get(i))) {
                            flash = true;
                            if(score>=500)
                            score-=500;
                            if(!crash.isPlaying())
                                crash.start();
                        }
                        astroids.get(i).update();
                        canvas.drawBitmap(astroids.get(i).bitmap(), astroids.get(i).left(), astroids.get(i).top(), null);
                        boolean removed = false;
                        for (int j = bullets.size() - 1; j != -1; j--) {
                            if (bullets.get(j).isTouching(astroids.get(i))) {
                                astroids.remove(i);
                                score+=1000;
                                removed=true;
                                bullets.remove(j);
                                astroidCount++;
                                break;
                            }
                        }
                        if(!removed&&astroids.get(i).top()>screenHeight){
                            astroids.remove(i);
                            score+=1;
                        }
                    }

                    for (int i = bullets.size() - 1; i != -1; i--) {
                        bullets.get(i).update();
                        canvas.drawBitmap(bullets.get(i).bitmap(), bullets.get(i).left(), bullets.get(i).top(), null);
                        if(bullets.get(i).bottom()<0||bullets.get(i).state()==3){
                            bullets.remove(i);
                        }
                    }

                    paintProperty.setTextSize(100);

                    canvas.drawText(""+time,screenWidth-120,100,paintProperty);

                    canvas.drawText(""+score,20,100,paintProperty);
                }else{
                    paintProperty.setTextSize(120);
                    Rect textRect = new Rect();
                    String text;
                    text ="GAME OVER";
                    paintProperty.getTextBounds(text,0, text.length(),textRect);
                    canvas.drawText(text,screenWidth/2-textRect.width()/2,screenHeight/2-textRect.height()/2-300,paintProperty);

                    text ="SCORE: "+score;
                    paintProperty.getTextBounds(text,0, text.length(),textRect);
                    canvas.drawText(text,screenWidth/2-textRect.width()/2,screenHeight/2-textRect.height()/2,paintProperty);

                    text ="TAP ANYWHERE";
                    paintProperty.getTextBounds(text,0, text.length(),textRect);
                    canvas.drawText(text,screenWidth/2-textRect.width()/2,screenHeight/2-textRect.height()/2+300,paintProperty);

                    text ="TO PLAY AGAIN";
                    paintProperty.getTextBounds(text,0, text.length(),textRect);
                    canvas.drawText(text,screenWidth/2-textRect.width()/2,screenHeight/2-textRect.height()/2+450,paintProperty);
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

