package com.kihtrak.gamedesign;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public class ball {
    int x = 0;
    int y = 0;
    float vx = 0;
    float vy = 0;
    Bitmap ball;
    Rect hitbox;
    Rect hitbox2;
    int state = 0;
    int screenWidth;
    int screenHeight;
    int newLeft;
    int newRight;
    int newTop;
    int newBottom;

    public ball(int x, int y, Bitmap ball,int screenWidth, int screenHeight) {
        this.ball = ball;
        x(x);
        y(y);
        setImg(ball);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        genHitbox();
    }

    void setImg(Bitmap ball){
        this.ball = ball;
        hitbox = new Rect(left(),top(),right(),bottom());
    }

    int state(){
        return state;
    }

    int state(int x){
        state = x;
        return state;
    }

    int x() {
        return left();
    }
    int left() {
        return x - ball.getWidth()/2;
    }
    int right() {
        return x + ball.getWidth()/2;
    }

    int x(int change) {
        x = change;
        return left();
    }

    int y() {
        return top();
    }
    int top() {
        return y- ball.getHeight()/2;
    }
    int bottom() {
        return y+ ball.getHeight()/2;
    }

    int y(int change) {
        y = change;
        return top();

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

    Rect hitBox2(){
        return hitbox;
    }

    void update(){
        x+=vx;
        y+=vy;
        genHitbox();
    }

    void genHitbox(){
        if(left()>screenWidth) {
            x = x % screenWidth;
        }
        while(right()<0) {
            x = x + screenWidth;
        }

        if(top()>screenHeight) {
            y = y % screenHeight;
        }
        while(bottom()<0) {
            y = y + screenHeight;
        }
        hitbox = new Rect(left(),top(),right(),bottom());
        newLeft = left();
        newRight = right();
        newTop = top();
        newBottom = bottom();
        if(left()<0){
            newLeft = screenWidth + left();
            newRight = newLeft+ball.getWidth();
        }
        if(right()>screenWidth){
            newRight = right()-screenWidth;
            newLeft = newRight-ball.getWidth();
        }
        if(top()<0){
            newTop = screenHeight + top();
            newBottom = newTop+ball.getHeight();
        }
        if(bottom()>screenHeight){
            newBottom = bottom()-screenHeight;
            newTop = newBottom-ball.getHeight();

        }
        hitbox2 = new Rect(newLeft,newTop,newRight,newBottom);
    }

    int newTop(){
        return newTop;
    }

    int newBottom(){
        return newBottom;
    }

    int newLeft(){
        return newLeft;
    }

    int newRight(){
        return newRight;
    }

    boolean twoNeeded(){
        if(newRight == right() && newTop == top())
            return false;
        return true;
    }

    boolean isTouching(ball ball2){
        if(Rect.intersects(ball2.hitBox(),hitbox)||Rect.intersects(ball2.hitBox(),hitbox2)||Rect.intersects(ball2.hitBox2(),hitbox)||Rect.intersects(ball2.hitBox2(),hitbox2)) {
            Log.d("rect","SHIT");
            return true;
        }
        return false;
    }



    Bitmap bitmap(){
        return ball;
    }
}