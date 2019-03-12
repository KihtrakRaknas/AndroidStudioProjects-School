package com.kihtrak.gamedesign;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class rock extends ball{

    public rock( Bitmap b,int speed,int screenWidth, int screenHeight){
        super((int)(Math.random()*screenWidth) , b.getHeight()/2, b,screenWidth,screenHeight);
        vely(speed);
    }
}
