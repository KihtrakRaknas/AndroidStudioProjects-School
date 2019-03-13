package com.kihtrak.gamedesign;

import android.graphics.Bitmap;

public class bullet extends ball{
    float life;
    public bullet(int x, int y, Bitmap ball, int screenWidth, int screenHeight,float life) {
        super(x, y - ball.getHeight() / 2, ball, screenWidth, screenHeight);
        vely((float) -30.0);
        state(0);
        this.life = life;
    }
    int i = 0;
    @Override
    void update() {
        super.update();
        i++;
             if(i>30-life*2){
                 state(3);
             }
    }
}
