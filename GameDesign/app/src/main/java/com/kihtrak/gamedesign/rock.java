package com.kihtrak.gamedesign;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class rock extends ball{

    public rock(int maxx, Bitmap b,int speed){
        super((int)(Math.random()*maxx) , b.getHeight()/2, b);
        vely(speed);
    }
}
