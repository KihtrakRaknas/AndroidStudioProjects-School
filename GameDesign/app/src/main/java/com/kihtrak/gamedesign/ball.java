package com.kihtrak.gamedesign;

import android.graphics.Bitmap;

public class ball {
    int x = 0;
    int y = 0;
    float vx = 0;
    float vy = 0;
    Bitmap ball;

    void ball(int x, int y, Bitmap ball) {
        this.x = x;
        this.y = y;
        this.ball = ball;
    }

    int x() {
        return x;
    }

    int x(int change) {
        x = change - ball.getWidth()/2;
        return x;
    }

    int y() {
        return y;
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

    void update(){
        x+=vx;
        y+=vy;
    }

    Bitmap bitmap(){
        return ball;
    }
}