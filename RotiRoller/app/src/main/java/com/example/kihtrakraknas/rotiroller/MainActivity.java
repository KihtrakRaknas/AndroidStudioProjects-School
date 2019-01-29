package com.example.kihtrakraknas.rotiroller;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ImageView balen;
    double clicks = 0;
    int rotomatics = 0;
    int aunties = 0;
    ConstraintLayout layout;
    TextView clicksTextView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    ((ImageView)findViewById(R.id.ball)).setVisibility(View.VISIBLE);
                    findViewById(R.id.shop).setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    ((ImageView)findViewById(R.id.ball)).setVisibility(View.INVISIBLE);
                    findViewById(R.id.shop).setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balen = findViewById(R.id.balen);
        balen.setVisibility(View.INVISIBLE);//View.VISIBLE

        findViewById(R.id.shop).setVisibility(View.INVISIBLE);
        layout = findViewById(R.id.container);

        clicksTextView = findViewById(R.id.clicks);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                addClick((double)(rotomatics*1+aunties*100)/10);
            }
        },0,100);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        final ScaleAnimation clickAnim = new ScaleAnimation(.8f,1f,.8f,1f,Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
        clickAnim.setDuration(200);

        final TranslateAnimation balenRoll = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,.15f,Animation.RELATIVE_TO_PARENT,.5f);
        balenRoll.setDuration(200);

        final TranslateAnimation floatUp = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,-.2f);
        floatUp.setDuration(200);

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setDuration(400);

        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeOut.setDuration(800);

        final Animation hide = new AlphaAnimation(0, 0);
        fadeOut.setDuration(1);


        final ScaleAnimation bigAnim = new ScaleAnimation(1f,1.2f,1f,1.2f,Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
        clickAnim.setDuration(800);

        ((ImageView)findViewById(R.id.ball)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClick(1);
                v.startAnimation(clickAnim);

                balen.startAnimation(balenRoll);

                final ImageView roti = new ImageView(MainActivity.this);
                roti.setImageResource(R.drawable.roti);
                roti.setId(View.generateViewId());




                ConstraintSet set = new ConstraintSet();
                layout.addView(roti);
                set.clone(layout);
                set.connect(roti.getId(), ConstraintSet.LEFT, R.id.ball, ConstraintSet.LEFT, 8);
                set.connect(roti.getId(), ConstraintSet.RIGHT, R.id.ball, ConstraintSet.RIGHT, 8);
                set.connect(roti.getId(), ConstraintSet.TOP, R.id.ball, ConstraintSet.TOP, 50);
                set.connect(roti.getId(), ConstraintSet.BOTTOM, R.id.ball, ConstraintSet.BOTTOM, 50);
                set.setHorizontalBias(roti.getId(), (float)Math.random());
                set.applyTo(layout);



                roti.startAnimation(floatUp);
                //roti.startAnimation(fadeOut);
                roti.setVisibility(View.INVISIBLE);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.removeView(roti);
                    }
                }, 400);

                floatUp.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        roti.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        layout.removeView(roti);
                        roti.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


            }
        });


        ((ImageView)findViewById(R.id.roto)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(clicks-10>=0){
                    v.startAnimation(bigAnim);
                    addClick(-10);
                    rotomatics+=1;

                    final ImageView rotomat = new ImageView(MainActivity.this);
                    rotomat.setImageResource(R.drawable.rotomini);
                    rotomat.setId(View.generateViewId());


                    ConstraintSet set = new ConstraintSet();
                    layout.addView(rotomat);
                    set.clone(layout);
                    set.connect(rotomat.getId(), ConstraintSet.LEFT, R.id.tray, ConstraintSet.LEFT, 8);
                    set.connect(rotomat.getId(), ConstraintSet.RIGHT, R.id.tray, ConstraintSet.RIGHT, 8);
                    set.connect(rotomat.getId(), ConstraintSet.TOP, R.id.tray, ConstraintSet.TOP, 8);
                    set.connect(rotomat.getId(), ConstraintSet.BOTTOM, R.id.tray, ConstraintSet.BOTTOM, 8);
                    set.setHorizontalBias(rotomat.getId(), (float)Math.random());
                    set.setVerticalBias(rotomat.getId(), (float)Math.random());
                    set.applyTo(layout);
                    rotomat.startAnimation(hide);

                    hide.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            rotomat.startAnimation(fadeIn);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }

            }
        });


        ((ImageView)findViewById(R.id.auntie)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(clicks-100>=0){
                    v.startAnimation(bigAnim);
                    addClick(-100);
                    aunties+=1;

                    final ImageView aunt = new ImageView(MainActivity.this);
                    aunt.setImageResource(R.drawable.auntie);
                    aunt.setId(View.generateViewId());


                    ConstraintSet set = new ConstraintSet();
                    layout.addView(aunt);
                    set.clone(layout);
                    set.connect(aunt.getId(), ConstraintSet.LEFT, R.id.tray, ConstraintSet.LEFT, 8);
                    set.connect(aunt.getId(), ConstraintSet.RIGHT, R.id.tray, ConstraintSet.RIGHT, 8);
                    set.connect(aunt.getId(), ConstraintSet.TOP, R.id.tray, ConstraintSet.TOP, 8);
                    set.connect(aunt.getId(), ConstraintSet.BOTTOM, R.id.tray, ConstraintSet.BOTTOM, 8);
                    set.setHorizontalBias(aunt.getId(), (float)Math.random());
                    set.setVerticalBias(aunt.getId(), (float)Math.random());
                    set.applyTo(layout);
                    aunt.startAnimation(hide);

                    hide.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            aunt.startAnimation(fadeIn);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }

            }
        });

    }

    public synchronized void addClick(double numberOfClicks){
        clicks+=numberOfClicks;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int temp = (int)Math.round(clicks);
                clicksTextView.setText(""+temp);
            }
        });

    }
}
