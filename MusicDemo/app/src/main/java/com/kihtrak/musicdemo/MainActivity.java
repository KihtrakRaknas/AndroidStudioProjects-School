package com.kihtrak.musicdemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    MediaPlayer play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = MediaPlayer.create(this,R.raw.wavy);

        ((SeekBar)findViewById(R.id.seekBar)).setMax(play.getDuration());

        ((Button)findViewById(R.id.pause)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play.isPlaying()){
                    play.pause();
                }else{
                    play.start();
                }
            }
        });

        ((Button)findViewById(R.id.stop)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play.isPlaying()){
                    play.stop();
                    try{
                        play.prepare();
                    }catch (Exception e){

                    }

                }else{
                    play.start();
                }
            }
        });


        new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    ((SeekBar)findViewById(R.id.seekBar)).post(new Runnable() {
                        @Override
                        public void run() {
                            ((SeekBar)findViewById(R.id.seekBar)).setProgress(play.getCurrentPosition());
                        }
                    });
                }
            }
        }.start();

    }
}
