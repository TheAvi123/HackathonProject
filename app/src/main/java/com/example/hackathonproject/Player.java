package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements GameObject {

    private Rect rect;
    static int color = Color.RED;

    public Player(Rect rectangle){
        this.rect = rectangle; // should take this out player sprite should be defined here not where its instantiated
//        this.color = color;
    }

    public Rect GetPlayerRectangle() {
        return rect;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update() {  //Regular update method

    }

    public void update(Point point) {   //Update method to move the player to a new point
        rect.set(point.x - rect.width()/2, point.y - rect.height()/2,
                point.x + rect.width()/2, point.y + rect.height()/2);
    }
}
