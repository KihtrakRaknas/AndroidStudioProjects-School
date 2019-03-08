package com.kihtrak.gamedesign;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class ball {
    int x = 0;
    int y = 0;
    float vx = 0;
    float vy = 0;
    Bitmap ball;
    Rect hitbox;

    public ball(int x, int y, Bitmap ball) {
        this.ball = ball;
        x(x);
        y(y);

        hitbox = new Rect(left(),top(),right(),bottom());
    }

    int x() {
        return x;
    }
    int left() {
        return x - ball.getWidth()/2;
    }
    int right() {
        return x + ball.getWidth()/2;
    }

    int x(int change) {
        x = change - ball.getWidth()/2;
        return x;
    }

    int y() {
        return y;
    }
    int top() {
        return y- ball.getHeight()/2;
    }
    int bottom() {
        return y+ ball.getHeight()/2;
    }

    int y(int change) {
        y = change - ball.getHeight()/2;
        return y;

    }

    float velx(float change) {
        vx += change;
        return vx;
    }

    float vely(float change) {
        vy += change;
        return vy;
    }

    Rect hitBox(){
        return hitbox;
    }

    void update(){
        x+=vx;
        y+=vy;
    }

    boolean isTouching(ball ball2){

        if(Rect.intersects(ball2.hitBox(),hitbox))
            return true;
        return false;
    }

    Bitmap bitmap(){
        return ball;
    }
}