package com.example.hackathonproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Missile extends Obstacle {
    public static int width = 100;
    public static int height = 300;
    private float speed = Constants.SCREEN_HEIGHT / 5000.0f;
    private int color = Color.BLACK;


    private int startX;

    public Missile(int height, int startX, int yPos) {
        super(new Rect(height,width,height,width), Color.DKGRAY);
        this.startX = startX;
        //l,t,r,b
        rectangle = new Rect(startX,yPos,startX + width, yPos + height);

    }

    public void update(int elapsedTime) {
        incrementY(speed * elapsedTime);
    }

    public void update(Point point) {   //Update method to move the obstacle to a new point
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2,
                point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
    }

    @Override
    public void draw(Canvas canvas) { // this stuff should be abstract
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

}
